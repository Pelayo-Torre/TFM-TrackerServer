package es.uniovi.hci.presentation.controllers;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import es.uniovi.hci.business.exception.TrackerException;
import es.uniovi.hci.business.exception.UserException;
import es.uniovi.hci.model.EventsTransferObject;
import es.uniovi.hci.model.UserData;
import es.uniovi.hci.presentation.helper.TrackerManagerServiceHelper;
import es.uniovi.hci.presentation.helper.UserManagerServiceHelper;

@Path("track")
public class TrackerController {
	
	final static Logger logger = Logger.getLogger(TrackerController.class);

	@Context
	private HttpServletRequest request;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
	public String initTrack(@DefaultValue("Bienvenid@") @QueryParam("sceneId") String sceneId,
			@QueryParam("sessionId") String sessionId) {	
		logger.debug("[INICIO] - TrackerController - initTrack");
		logger.debug("\tParámetros de entrada al SW: " + sceneId+ ", " + sessionId);
		
		try {
			
			if (sessionId == null || sessionId.equals("")){
				logger.debug("\tSessionId es null, se procede a utilizar el sessionId actual: " + request.getSession().getId());
				sessionId = request.getSession().getId();
			}
			
			EventsTransferObject dto = new TrackerManagerServiceHelper().getEventsBySessionAndScene(sessionId, sceneId);
			
			Gson gson = new Gson();
			String jsonInString = gson.toJson(dto);
			
			logger.debug("\tDatos obtenidos: " + jsonInString);
			
			return jsonInString;
			
		} catch (TrackerException e) {
			logger.error("[ERROR] - TrackerController - TrackerException - initTrack -> " + e.toString());
		} catch (Exception e) {
			logger.error("[ERROR] - TrackerController - initTrack -> ERROR NO CONTROLADO: " + e.toString());
		}

		logger.debug("[FINAL] - TrackerController - initTrack");
		return "Error getting tracks";
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String saveEvents(String parametros) {
		logger.debug("[INICIO] - TrackerController - saveEvents");
		logger.debug("\tParametros de entrada al SW: " + parametros);
		
		EventsTransferObject transfer = null;
		try {
			Gson gson = new Gson();
			transfer = gson.fromJson(parametros, EventsTransferObject.class);
			
			if(transfer != null) {
				new UserManagerServiceHelper().checkTrackingUser(fillUserData(request, transfer));
				new TrackerManagerServiceHelper().saveChunk(transfer);
			}
			else {
				logger.error("[ERROR] - TrackerController - saveEvents-  Objeto no convertido a partir de los datos de entrada.");
			}
		} catch (TrackerException e) {
			logger.error("[ERROR] - TrackerController - TrackerException - saveEvents -> " + e.toString());
			return "Error while recording events";
		} catch (UserException e) {
			logger.error("[ERROR] - TrackerController - UserException - saveEvents -> " + e.toString());
			return "Error UserData exception";
		} catch (Exception e) {
			logger.error("[ERROR] - TrackerController - saveEvents -> ERROR NO CONTROLADO: " + e.toString());
			return "Error not controlled";
		}
		
		String result = "OK: ";
		if ( transfer.getList().size()>0){
			 result+= transfer.getList().get(transfer.getList().size()-1);
		}
		
		logger.debug("\tResultado: "+result);
		logger.debug("[FINAL] - TrackerController - saveEvents");
		return result;
	}
	
	private UserData fillUserData(HttpServletRequest request, EventsTransferObject transfer) {
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
