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

import es.uniovi.hci.business.exception.UserException;
import es.uniovi.hci.model.UserData;
import es.uniovi.hci.presentation.helper.UserManagerServiceHelper;

@Path("registerUserData")
public class UserController {
	
	final static Logger logger = Logger.getLogger(UserController.class);

	@Context
	private HttpServletRequest request;

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String saveUserData(String parametros) {
		logger.debug("[INICIO] - UserController - saveUserData");
		logger.debug("\t Parámetros de entrada al SW: " + parametros);

		UserData transfer = null;
		try {
			Gson gson = new Gson();
			transfer = gson.fromJson(parametros, UserData.class);
			
			if(transfer != null) {
				UserManagerServiceHelper helper = new UserManagerServiceHelper();
				helper.checkTrackingUser(fillUserData(request, transfer));
				helper.registerNavigationData(transfer);
			}
			else {
				logger.error("[ERROR] - UserController - saveUserData -  Objeto no convertido a partir de los datos de entrada.");
			}

		} catch (UserException e) {
			logger.error("[ERROR] - UserController - UserException - saveUserData -> " + e.toString());
		} catch (Exception e) {
			logger.error("[ERROR] - UserController - saveUserData -> ERROR NO CONTROLADO: " + e.toString());
		}

		logger.debug("[FINAL] - UserController - saveUserData");
		return "ok";
	}
	
	private UserData fillUserData(HttpServletRequest request, UserData transfer) {
		return new UserData(
				transfer.getSessionId(),
				transfer.getIdExperiment(),
				(new Date()).getTime(),
				request.getLocale().toString(),
				request.getRemoteAddr(),
				request.getRemoteHost(),
				request.getRemotePort(),
				transfer.getTimezone()
		);
	}
}
