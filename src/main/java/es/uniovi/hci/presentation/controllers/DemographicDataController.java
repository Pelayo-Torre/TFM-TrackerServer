package es.uniovi.hci.presentation.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import es.uniovi.hci.business.exception.DemographicDataException;
import es.uniovi.hci.model.DemographicData;
import es.uniovi.hci.presentation.helper.DemographicDataManagerServiceHelper;

@Path("registerDemographicData")
public class DemographicDataController {

	private final static Logger logger = Logger.getLogger(DemographicDataController.class);
	
	@Context
	private HttpServletRequest request;
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String registry(String parametros) {
		logger.debug("[INICIO] - DemographicDataController - registry");
		logger.debug("\t Parámetros de entrada al SW: " + parametros);
		DemographicData transfer = null;
		
		try {
			Gson gson = new Gson();
			transfer = gson.fromJson(parametros, DemographicData.class);
			
			if(transfer != null) {
				
				request.getSession().setAttribute("timezone", transfer.getTimezone());
				transfer.setSessionId(request.getSession().getId());
				
				new DemographicDataManagerServiceHelper().register(transfer);
				
			}
			else {
				logger.error("[ERROR] - ComponentController - registry-  Objeto no convertido a partir de los datos de entrada.");
			}
			
		} catch (DemographicDataException e) {
			logger.error("[ERROR] - DemographicDataController - DemographicDataException - registry -> " + e.toString());
		} catch (Exception e) {
			logger.error("[ERROR] - DemographicDataController - register -> ERROR NO CONTROLADO: " + e.toString());
		}
		
		logger.debug("[FINAL] - DemographicDataController - registry");
		
		return "ok";
	}
	
}
