package es.uniovi.hci.presentation.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import es.uniovi.hci.business.exception.ComponentException;
import es.uniovi.hci.business.exception.UserException;
import es.uniovi.hci.model.ComponentData;
import es.uniovi.hci.model.UserData;
import es.uniovi.hci.presentation.helper.ComponentManagerServiceHelper;
import es.uniovi.hci.presentation.helper.UserManagerServiceHelper;

@Path("registerComponent")
public class ComponentController {
	
	private final static Logger logger = Logger.getLogger(ComponentController.class);
	
	@Context
	private HttpServletRequest request;
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String registry(String parametros) {
		logger.debug("[INICIO] - ComponentController - registry");
		logger.debug("\t Parámetros de entrada al SW: " + parametros);
		ComponentData transfer = null;
		
		try {
			Gson gson = new Gson();
			transfer = gson.fromJson(parametros, ComponentData.class);
			
			if(transfer != null) {
				
				transfer.setSessionId(request.getSession().getId());
				request.getSession().setAttribute("timezone", transfer.getTimezone());
				
				if (!(request.getSession().getAttribute("isTracked") == null ? false : true)) {
					logger.debug("\t Almacenando un nuevo SessionID: " + request.getSession().getId());
					new UserManagerServiceHelper().checkTrackingUser(fillUserData(request, transfer.getIdExperiment()));
					request.getSession().setAttribute("isTracked", true);
				}
				new ComponentManagerServiceHelper().registry(transfer);
				
			}
			else {
				logger.error("[ERROR] - ComponentController - registry-  Objeto no convertido a partir de los datos de entrada.");
			}
			
		} catch (UserException e) {
			logger.error("[ERROR] - ComponentController - UserException - registry -> " + e.toString());
		} catch (ComponentException e) {
			logger.error("[ERROR] - ComponentController - ComponentException - registry -> " + e.toString());
		} catch (Exception e) {
			logger.error("[ERROR] - ComponentController - registry -> ERROR NO CONTROLADO: " + e.toString());
		}
		
		logger.debug("[FINAL] - ComponentController - registry");
		
		return "ok";
	}
	
	private UserData fillUserData(HttpServletRequest request, Long idExperiment) {
		return new UserData(
				request.getSession().getId(),
				idExperiment,
				(new Date()).getTime(),
				request.getLocale().toString(),
				request.getRemoteAddr(),
				request.getRemoteHost(),
				request.getRemotePort(),
				(Integer) request.getSession().getAttribute("timezone")
		);
	}

}
