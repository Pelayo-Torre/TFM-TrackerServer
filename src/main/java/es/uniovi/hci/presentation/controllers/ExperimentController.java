package es.uniovi.hci.presentation.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import es.uniovi.hci.business.exception.ExperimentException;
import es.uniovi.hci.presentation.helper.ExperimentManagerServiceHelper;

@Path("experiment")
public class ExperimentController {
	
	private final static Logger logger = Logger.getLogger(ExperimentController.class);

	@Context
	private HttpServletRequest request;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
	@Path("/status/{idExperiment}")
	public String getStatus(@PathParam("idExperiment") Long idExperiment) {	
		logger.debug("[INICIO] - ExperimentController - getStatus");
		logger.debug("\tParámetros de entrada al SW: IdExperiment - " + idExperiment);
		
		String status = null;
		
		try {
			status = new ExperimentManagerServiceHelper().getStatus(idExperiment);
			logger.debug("\tDatos obtenidos: " + status);
			
		} catch (ExperimentException e) {
			logger.error("[ERROR] - ExperimentController - getStatus -> " + e.toString());
		} catch (Exception e) {
			logger.error("[ERROR] - ExperimentController - getStatus -> ERROR NO CONTROLADO: " + e.toString());
		}
		
		return status;
	}

}
