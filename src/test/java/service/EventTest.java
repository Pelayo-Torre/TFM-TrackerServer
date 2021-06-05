package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.uniovi.hci.business.exception.TrackerException;
import es.uniovi.hci.business.exception.UserException;
import es.uniovi.hci.model.EventItem;
import es.uniovi.hci.model.EventsTransferObject;
import es.uniovi.hci.model.UserData;
import es.uniovi.hci.persistence.conection.ConnectionProvider;
import es.uniovi.hci.presentation.helper.TrackerManagerServiceHelper;
import es.uniovi.hci.presentation.helper.UserManagerServiceHelper;

public class EventTest {
	
	private TrackerManagerServiceHelper helper = new TrackerManagerServiceHelper();
	private UserManagerServiceHelper userHelper = new UserManagerServiceHelper();
	
	private String userSessionId = "KJSDF784HD994HR";
	
	private static boolean primeraVez = true;
	
	@Before
    public void init() throws IOException, UserException {
		if(primeraVez) {
			//Se configura la base de datos
			ConnectionProvider.getInstance().changeConnection("db-test.properties");
			
			//Se registra un experimento y un usuario
			insertExperiment();
			insertUser();
			
			primeraVez = false;
		}
	}
	
	@Test
	/**
	 * Registra un evento de manera correcta
	 */
	public void registerEvent() throws TrackerException {
		EventsTransferObject event = new EventsTransferObject();
		
		event.setList(new ArrayList<EventItem>());
		event.setSessionId(userSessionId);
		event.setTimezone(2);
		
		EventItem e = new EventItem();
		e.setEventType(1);
		e.setKeyCodeEvent(23);
		e.setKeyValueEvent("D");
		e.setSceneId("Escena1");
		long time = new Date().getTime();
		e.setTimeStamp(time);
		e.setX(45);
		e.setY(56);
		e.setElementId("comboCars");
		
		event.getList().add(e);
		
		helper.saveChunk(event);
		
		//recuperamos el evento
		EventItem e2 = findExperiment(e.getTimeStamp());
		
		assertNotNull(e2);
		assertEquals(1, e2.getEventType().intValue());
		assertEquals(23, e2.getKeyCodeEvent().intValue());
		assertEquals("D", e2.getKeyValueEvent());
		assertEquals("Escena1", e2.getSceneId());
		assertEquals(time, e2.getTimeStamp().longValue());
		assertEquals(45, e2.getX().intValue());
		assertEquals(56, e2.getY().intValue());
		assertEquals("comboCars", e2.getElementId());
	}
	
	@Test
	/**
	 * Registra un evento donde no se especifica la sesión del usuario
	 */
	public void registerEventERROR01() throws TrackerException {
		EventsTransferObject event = new EventsTransferObject();
		
		event.setList(new ArrayList<EventItem>());
		event.setTimezone(2);
		
		EventItem e = new EventItem();
		e.setEventType(1);
		e.setKeyCodeEvent(23);
		e.setKeyValueEvent("D");
		e.setSceneId("Escena1");
		long time = new Date().getTime();
		e.setTimeStamp(time);
		e.setX(45);
		e.setY(56);
		e.setElementId("comboCars");
		
		event.getList().add(e);
		
		try {
			helper.saveChunk(event);
			Assert.fail("Debe lanzarse excepción.");
		} catch (TrackerException ex) {
			assertNotNull(ex.getMessage());
		}
	}
	
	@Test
	/**
	 * Registra un evento donde no se especifica el identificador de la escena
	 */
	public void registerEventERROR02() throws TrackerException {
		EventsTransferObject event = new EventsTransferObject();
		
		event.setList(new ArrayList<EventItem>());
		event.setSessionId(userSessionId);
		event.setTimezone(2);
		
		EventItem e = new EventItem();
		e.setEventType(1);
		e.setKeyCodeEvent(23);
		e.setKeyValueEvent("D");
		long time = new Date().getTime();
		e.setTimeStamp(time);
		e.setX(45);
		e.setY(56);
		e.setElementId("comboCars");
		
		event.getList().add(e);
		
		try {
			helper.saveChunk(event);
			Assert.fail("Debe lanzarse excepción.");
		} catch (TrackerException ex) {
			assertNotNull(ex.getMessage());
		}
	}
	
	@Test
	/**
	 * Registra un evento donde no se especifica el identificador del elemento
	 */
	public void registerEventERROR03() throws TrackerException {
		EventsTransferObject event = new EventsTransferObject();
		
		event.setList(new ArrayList<EventItem>());
		event.setSessionId(userSessionId);
		event.setTimezone(2);
		
		EventItem e = new EventItem();
		e.setEventType(1);
		e.setKeyCodeEvent(23);
		e.setKeyValueEvent("D");
		e.setSceneId("Escena1");
		long time = new Date().getTime();
		e.setTimeStamp(time);
		e.setX(45);
		e.setY(56);
		
		event.getList().add(e);
		
		try {
			helper.saveChunk(event);
			Assert.fail("Debe lanzarse excepción.");
		} catch (TrackerException ex) {
			assertNotNull(ex.getMessage());
		}
	}
	
	@Test
	/**
	 * Registra un evento donde no se especifica el tipo de evento
	 */
	public void registerEventERROR04() throws TrackerException {
		EventsTransferObject event = new EventsTransferObject();
		
		event.setList(new ArrayList<EventItem>());
		event.setSessionId(userSessionId);
		event.setTimezone(2);
		
		EventItem e = new EventItem();
		e.setKeyCodeEvent(23);
		e.setKeyValueEvent("D");
		e.setSceneId("Escena1");
		long time = new Date().getTime();
		e.setTimeStamp(time);
		e.setX(45);
		e.setY(56);
		e.setElementId("comboCars");
		
		event.getList().add(e);
		
		try {
			helper.saveChunk(event);
			Assert.fail("Debe lanzarse excepción.");
		} catch (TrackerException ex) {
			assertNotNull(ex.getMessage());
		}
	}
	
	@Test
	/**
	 * Registra un evento donde no se especifica la coordenada X
	 */
	public void registerEventERROR05() throws TrackerException {
		EventsTransferObject event = new EventsTransferObject();
		
		event.setList(new ArrayList<EventItem>());
		event.setSessionId(userSessionId);
		event.setTimezone(2);
		
		EventItem e = new EventItem();
		e.setEventType(1);
		e.setKeyCodeEvent(23);
		e.setKeyValueEvent("D");
		e.setSceneId("Escena1");
		long time = new Date().getTime();
		e.setTimeStamp(time);
		e.setY(56);
		e.setElementId("comboCars");
		
		event.getList().add(e);
		
		try {
			helper.saveChunk(event);
			Assert.fail("Debe lanzarse excepción.");
		} catch (TrackerException ex) {
			assertNotNull(ex.getMessage());
		}
	}
	
	@Test
	/**
	 * Registra un evento donde no se especifica la coordenada Y
	 */
	public void registerEventERROR06() throws TrackerException {
		EventsTransferObject event = new EventsTransferObject();
		
		event.setList(new ArrayList<EventItem>());
		event.setSessionId(userSessionId);
		event.setTimezone(2);
		
		EventItem e = new EventItem();
		e.setEventType(1);
		e.setKeyCodeEvent(23);
		e.setKeyValueEvent("D");
		e.setSceneId("Escena1");
		long time = new Date().getTime();
		e.setTimeStamp(time);
		e.setX(45);
		e.setElementId("comboCars");
		
		event.getList().add(e);
		
		try {
			helper.saveChunk(event);
			Assert.fail("Debe lanzarse excepción.");
		} catch (TrackerException ex) {
			assertNotNull(ex.getMessage());
		}
	}
	
	/**
	 * Realiza la inserción de un experimento
	 */
	private void insertExperiment() {
		Connection con;
		try {
			con = ConnectionProvider.getInstance().getConnection();
			
			PreparedStatement stmt = con.prepareStatement("INSERT INTO EXPERIMENT (creation_date, description, status, title) VALUES (?,?,?,?)");
			stmt.setObject(1, new Date());
			stmt.setString(2, "Prueba");
			stmt.setString(3, "OPEN");
			stmt.setString(4, "Prueba");
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {}
	}
	
	/**
	 * Inserta un usuario en la base de datos
	 * @throws UserException 
	 */
	private void insertUser() throws UserException {
		Long id = findIdExperiment();
		UserData user = new UserData(userSessionId, id, 
				875643596834L, "es", "563.3263.326", "1236.25.36.362.6", 
				8080, 2);
		
		userHelper.registerUser(user);
	}
	
	/**
	 * Realiza la búsqueda de un evento
	 */
	private EventItem findExperiment(Long timestamp) {
		Connection con;
		EventItem event = null;
		try {
			con = ConnectionProvider.getInstance().getConnection();
			
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM EVENT WHERE time_stamp = ?");
			stmt.setLong(1, timestamp);
			
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) {
				event = new EventItem();
				event.setElementId(result.getString("element_id"));
				event.setEventType(result.getInt("event_type"));
				event.setKeyCodeEvent(result.getInt("key_code_event"));
				event.setKeyValueEvent(result.getString("key_value_event"));
				event.setSceneId(result.getString("scene_id"));
				event.setTimeStamp(result.getLong("time_stamp"));
				event.setSessionId(result.getString("user_session_id"));
				event.setX(result.getInt("x"));
				event.setY(result.getInt("y"));
			};
			
			con.close();
		} catch (Exception e) {}
		
		return event;
	}
	
	/**
	 * Devuelve el último experimento insertado
	 * @return
	 */
	private Long findIdExperiment() {
		Connection con;
		Long experiment = null;
		try {
			con = ConnectionProvider.getInstance().getConnection();
			
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM EXPERIMENT WHERE creation_date IN (SELECT MAX(creation_date) FROM EXPERIMENT )");
			
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) {
				experiment = result.getLong("id");
			};
			
			con.close();
		} catch (Exception e) {}
		
		return experiment;
	}

}
