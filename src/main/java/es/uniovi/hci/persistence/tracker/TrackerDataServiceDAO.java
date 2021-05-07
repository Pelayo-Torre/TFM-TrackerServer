package es.uniovi.hci.persistence.tracker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import es.uniovi.hci.model.EventItem;
import es.uniovi.hci.model.EventsTransferObject;
import es.uniovi.hci.persistence.conection.Conf;
import es.uniovi.hci.persistence.conection.ConnectionProvider;

public class TrackerDataServiceDAO implements TrackerDataService{
	
	private final static Logger logger = Logger.getLogger(TrackerDataServiceDAO.class);

	public void saveChunk(EventsTransferObject dto) {
		logger.debug("[INICIO] - TrackerDataServiceDAO - saveChunk");
		
		Connection con = null;
		try {
			con = ConnectionProvider.getInstance().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(Conf.get("SQL_INSERT_EVENT"));

			for (EventItem item : dto.getList()) {
				
				
				stmt.setString(1, dto.getSessionId());
				stmt.setString(2, item.getSceneId());
				stmt.setLong(3, item.getTimeStamp());
				stmt.setInt(4, item.getX());
				stmt.setInt(5, item.getY());
				stmt.setInt(6, item.getEventType());
				stmt.setString(7, item.getElementId());
				stmt.setString(8, item.getKeyValueEvent());
				stmt.setInt(9, item.getKeyCodeEvent());
				stmt.addBatch(); 
			}
			
			logger.debug("\tQuery: " + stmt);
			int[] i = stmt.executeBatch();
			logger.debug("\tRegistros insertados: " + i);

			con.close();

		} catch (ClassNotFoundException e) {
			logger.error("[ERROR] - ClassNotFoundException " + e.toString());
		} catch (SQLException e) {
			logger.error("[ERROR] - SQLException " + e.toString());
		} catch (IOException e) {
			logger.error("[ERROR] - IOException " + e.toString());
		}
		
		logger.debug("[FINAL] - TrackerDataServiceDAO - saveChunk");
	}

	public EventsTransferObject getEventsBySessionAndScene(String sessionId, String sceneId) {
		logger.debug("[INICIO] - TrackerDataServiceDAO - getEventsBySessionAndScene");
		
		Connection con = null;
		EventsTransferObject dto = new EventsTransferObject();
		
		try {
			con = ConnectionProvider.getInstance().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(Conf.get("SQL_FIND_EVENTS_BY_SESSION_AND_SCENE"));
			stmt.setString(1, sessionId);
			stmt.setString(2, sceneId);
			
			logger.debug("\tQuery: " + stmt);
			ResultSet result = stmt.executeQuery();

			while (result.next()) {
				EventItem item = new EventItem(
						result.getString("session_id"), 
						result.getString("scene_id"),
						result.getInt("event_type"), 
						result.getString("element_id"), 
						result.getLong("time_stamp"),
						result.getInt("x"), 
						result.getInt("y"),
						result.getString("key_event"),
						result.getInt("key_code_event")
				);
				
				logger.debug("\tEvento obtenido: "+item);
				dto.add(item);
			}
			
			con.close();
			
		} catch (ClassNotFoundException e) {
			logger.error("[ERROR] - ClassNotFoundException " + e.toString());
		} catch (SQLException e) {
			logger.error("[ERROR] - SQLException " + e.toString());
		} catch (IOException e) {
			logger.error("[ERROR] - IOException " + e.toString());
		}
		
		
		logger.debug("[FINAL] - TrackerDataServiceDAO - getEventsBySessionAndScene");
		return dto;
	}

}
