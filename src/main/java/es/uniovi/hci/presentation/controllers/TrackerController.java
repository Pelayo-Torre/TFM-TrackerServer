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
				request.getSession().setAttribute("timezone", transfer.getTimezone());
				transfer.setSessionId(request.getSession().getId());
								
				new UserManagerServiceHelper().checkTrackingUser(fillUserData(request, transfer.getIdExperiment()));
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
	

//	final static Logger logger = Logger.getLogger(Tracker.class);
//	private UserRegister userRegister = null;
//
//	@Context
//	private HttpServletRequest request;
//
//	
//	@GET
//	@Produces(MediaType.TEXT_PLAIN)
//    @Consumes(MediaType.TEXT_PLAIN)
//	public String initTrack(@DefaultValue("Bienvenid@") @QueryParam("sceneId") String sceneId,
//			@QueryParam("sessionId") String sessionId) {	
//		logger.debug("Received "+sceneId+ ", " + sessionId);
//		try {
//			EventsTransferObject dto = getSessionTrace(sessionId, sceneId);
//			Gson gson = new Gson();
//			String jsonInString = gson.toJson(dto);
//			logger.debug("Recovered trace: " + jsonInString);
//			return jsonInString;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return "Error getting tracks";
//	}
//
//	@POST
//	@Produces(MediaType.TEXT_PLAIN)
//	// @Consumes(MediaType.APPLICATION_JSON)
//	public String saveEvents(String parametros) {
//		logger.debug("parameters: " + parametros);
//		EventsTransferObject transfer = null;
//		try {
//			Gson gson = new Gson();
//			transfer = gson.fromJson(parametros, EventsTransferObject.class);
//			// We store the timezone in the session
//			request.getSession().setAttribute("timezone", transfer.getTimezone());
//
//			// checkTracking();
//			userRegister = new UserRegister();
//			userRegister.checkTracking(request);
//
//			logger.debug("recording events: " + transfer);
//			saveChunk(transfer);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error(e.getStackTrace());
//			return "Error while recording events";
//		}
//		String result = "OK: ";
//		if ( transfer.getList().size()>0)
//		{
//			 result+= transfer.getList().get(transfer.getList().size()-1);
//		}
//		logger.debug("Resultado: "+result);
//		return result;
//	}
//
//	/*
//	 * private void checkTracking() { boolean isTracked =
//	 * (request.getSession().getAttribute("isTracked") == null ? false : true); if
//	 * (!isTracked) { logger.debug("Tracking new sessionId: " +
//	 * request.getSession().getId()); try { if (!findUser()) { registerUser(); }
//	 * else { logger.error(
//	 * "SOMETHING IS WRONG. The user sessions is not flagged as tracked but the session Id exists in the database"
//	 * ); } } catch (SQLException e) { e.printStackTrace(); } // We flag the session
//	 * as tracked request.getSession().setAttribute("isTracked", true); }
//	 * 
//	 * }
//	 */
//	private EventsTransferObject getSessionTrace(String sessionId, String sceneId) throws SQLException {
//		Connection con = null;
//		EventsTransferObject dto = new EventsTransferObject();
//		if (sessionId == null || sessionId.equals(""))
//		{
//			logger.debug("SessionId is null, we use current sessions value");
//			sessionId=request.getSession().getId();
//		}
//		logger.debug("Getting trace for session " + sessionId +" and scene "+sceneId);
//		try {
//			con = getConnection();
//			PreparedStatement stmt = con.prepareStatement("SELECT * FROM EVENT WHERE SESSIONID=? and SCENEID=?");
//			stmt.setString(1, sessionId);
//			stmt.setString(2, sceneId);
//			logger.debug(stmt);
//			ResultSet result = stmt.executeQuery();
//
//			// We prepare the return value
//			while (result.next()) {
//				EventItem item = new EventItem(result.getInt("id"), result.getString("sceneId"),
//						result.getInt("eventType"), result.getString("elementId"), result.getLong("timeStamp"),
//						result.getInt("x"), result.getInt("y"));
//				logger.debug("Retrieving event:"+item);
//				dto.add(item);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			con.close();
//		}
//		return dto;
//	}
//
//	private Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
//		return ConnectionProvider.getInstance().getConnection();
//
//	}
//
//	private void saveChunk(EventsTransferObject dto) throws SQLException {
//		Connection con = null;
//		try {
//			con = getConnection();
//			PreparedStatement stmt = con.prepareStatement(
//					"insert into event (sessionId, sceneId, timeStamp, x, y, eventType, elementId) values(?,?,?,?,?,?,?)");
//
//			for (EventItem item : dto.getList()) {
//				try {
//					stmt.setString(1, request.getSession().getId());
//					stmt.setString(2, item.getSceneId());
//					stmt.setLong(3, item.getTimeStamp());
//					stmt.setInt(4, item.getX());
//					stmt.setInt(5, item.getY());
//					stmt.setInt(6, item.getEventType());
//					stmt.setString(7, item.getElementId());
//					stmt.addBatch();
//					logger.debug("Inserting " + request.getSession().getId() + ": " + item);
//				} catch (Exception e) {
//					System.err.println("Error while inserting: " + item);
//				}
//
//			}
//			int[] i = stmt.executeBatch();
//			logger.debug(i + " records inserted");
//
//			con.close();
//
//		} catch (Exception e) {
//			System.out.println(e);
//			e.printStackTrace();
//		} finally {
//			con.close();
//		}
//	}
}
