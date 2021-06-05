package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.uniovi.hci.business.exception.UserException;
import es.uniovi.hci.model.UserData;
import es.uniovi.hci.persistence.conection.ConnectionProvider;
import es.uniovi.hci.presentation.helper.UserManagerServiceHelper;

public class UserTest {
	
private UserManagerServiceHelper userHelper = new UserManagerServiceHelper();
	
	private String userSessionId = "KJSDF784HD994HL" + Math.random()*999999999;
	
	private static boolean primeraVez = true;
	
	@Before
    public void init() throws IOException, UserException {
		if(primeraVez) {
			//Se configura la base de datos
			ConnectionProvider.getInstance().changeConnection("db-test.properties");
			
			//Se registra un experimento y un usuario
			insertExperiment();
			
			primeraVez = false;
		}
	}
	
	@Test
	/**
	 * Registra un usuario de manera correcta
	 */
	public void registerUser() throws UserException {
		Long id = findIdExperiment();
		Long time = new Date().getTime();
		String locale = "es";
		String remoteAddress = "563.3263.326";
		String remoteHost = "156.326.35.32";
		Integer port = 85;
		Integer timezone = 2;
		
		UserData user = new UserData(userSessionId, id, 
				time, locale, remoteAddress, remoteHost, 
				port, timezone);
		
		userHelper.registerUser(user);
		
		UserData u = findUser();
		
		assertNotNull(u);
		assertEquals(id, u.getIdExperiment());
		assertEquals(time, u.getTimeStamp());
		assertEquals(locale, u.getLocale());
		assertEquals(remoteAddress, u.getRemoteAddress());
		assertEquals(remoteHost, u.getRemoteHost());
		assertEquals(port, u.getRemotePort());
		assertEquals(timezone, u.getTimezone());
		assertEquals(userSessionId, u.getSessionId());
	}
	
	@Test
	/**
	 * Registra un usuario sin especificar la identificador de la sesión del usuario
	 */
	public void registerUserERROR01() throws UserException {
		Long id = findIdExperiment();
		Long time = new Date().getTime();
		String locale = "es";
		String remoteAddress = "563.3263.326";
		String remoteHost = "156.326.35.32";
		Integer port = 85;
		Integer timezone = 2;
		
		UserData user = new UserData(null, id, 
				time, locale, remoteAddress, remoteHost, 
				port, timezone);
		
		
		try {
			userHelper.registerUser(user);
			Assert.fail("Debe lanzarse excepción.");
		} catch (UserException ex) {
			assertNotNull(ex.getMessage());
		}
	}
	
	@Test
	/**
	 * Registra un usuario sin especificar el timestamp
	 */
	public void registerUserERROR02() throws UserException {
		Long id = findIdExperiment();
		Long time = null;
		String locale = "es";
		String remoteAddress = "563.3263.326";
		String remoteHost = "156.326.35.32";
		Integer port = 85;
		Integer timezone = 2;
		
		UserData user = new UserData(userSessionId, id, 
				time, locale, remoteAddress, remoteHost, 
				port, timezone);
		
		
		try {
			userHelper.registerUser(user);
			Assert.fail("Debe lanzarse excepción.");
		} catch (UserException ex) {
			assertNotNull(ex.getMessage());
		}
	}
	
	@Test
	/**
	 * Registra un usuario sin especificar el locale
	 */
	public void registerUserERROR03() throws UserException {
		Long id = findIdExperiment();
		Long time = new Date().getTime();
		String locale = null;
		String remoteAddress = "563.3263.326";
		String remoteHost = "156.326.35.32";
		Integer port = 85;
		Integer timezone = 2;
		
		UserData user = new UserData(userSessionId, id, 
				time, locale, remoteAddress, remoteHost, 
				port, timezone);
		
		
		try {
			userHelper.registerUser(user);
			Assert.fail("Debe lanzarse excepción.");
		} catch (UserException ex) {
			assertNotNull(ex.getMessage());
		}
	}
	
	@Test
	/**
	 * Registra un usuario sin especificar la dirección
	 */
	public void registerUserERROR04() throws UserException {
		Long id = findIdExperiment();
		Long time = new Date().getTime();
		String locale = "es";
		String remoteAddress = null;
		String remoteHost = "156.326.35.32";
		Integer port = 85;
		Integer timezone = 2;
		
		UserData user = new UserData(userSessionId, id, 
				time, locale, remoteAddress, remoteHost, 
				port, timezone);
		
		
		try {
			userHelper.registerUser(user);
			Assert.fail("Debe lanzarse excepción.");
		} catch (UserException ex) {
			assertNotNull(ex.getMessage());
		}
	}
	
	@Test
	/**
	 * Registra un usuario sin especificar el host
	 */
	public void registerUserERROR05() throws UserException {
		Long id = findIdExperiment();
		Long time = new Date().getTime();
		String locale = "es";
		String remoteAddress = "563.3263.326";
		String remoteHost = null;
		Integer port = 85;
		Integer timezone = 2;
		
		UserData user = new UserData(userSessionId, id, 
				time, locale, remoteAddress, remoteHost, 
				port, timezone);
		
		
		try {
			userHelper.registerUser(user);
			Assert.fail("Debe lanzarse excepción.");
		} catch (UserException ex) {
			assertNotNull(ex.getMessage());
		}
	}
	
	@Test
	/**
	 * Registra un usuario sin especificar el puerto
	 */
	public void registerUserERROR06() throws UserException {
		Long id = findIdExperiment();
		Long time = new Date().getTime();
		String locale = "es";
		String remoteAddress = "563.3263.326";
		String remoteHost = "156.326.35.32";
		Integer port = null;
		Integer timezone = 2;
		
		UserData user = new UserData(userSessionId, id, 
				time, locale, remoteAddress, remoteHost, 
				port, timezone);
		
		
		try {
			userHelper.registerUser(user);
			Assert.fail("Debe lanzarse excepción.");
		} catch (UserException ex) {
			assertNotNull(ex.getMessage());
		}
	}
	
	@Test
	/**
	 * Registra un usuario sin especificar el timezone
	 */
	public void registerUserERROR07() throws UserException {
		Long id = findIdExperiment();
		Long time = new Date().getTime();
		String locale = "es";
		String remoteAddress = "563.3263.326";
		String remoteHost = "156.326.35.32";
		Integer port = 85;
		Integer timezone = null;
		
		UserData user = new UserData(userSessionId, id, 
				time, locale, remoteAddress, remoteHost, 
				port, timezone);
		
		
		try {
			userHelper.registerUser(user);
			Assert.fail("Debe lanzarse excepción.");
		} catch (UserException ex) {
			assertNotNull(ex.getMessage());
		}
	}
	
	@Test
	/**
	 * Registra un usuario sin especificar el identificadr del experimento
	 */
	public void registerUserERROR08() throws UserException {
		Long id = null;
		Long time = new Date().getTime();
		String locale = "es";
		String remoteAddress = "563.3263.326";
		String remoteHost = "156.326.35.32";
		Integer port = 85;
		Integer timezone = 2;
		
		UserData user = new UserData(userSessionId, id, 
				time, locale, remoteAddress, remoteHost, 
				port, timezone);
		
		
		try {
			userHelper.registerUser(user);
			Assert.fail("Debe lanzarse excepción.");
		} catch (UserException ex) {
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
	
	/**
	 * Devuelve el último usuario insertado
	 * @return
	 */
	private UserData findUser() {
		Connection con;
		UserData user = null;
		try {
			con = ConnectionProvider.getInstance().getConnection();
			
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM USERDATA WHERE TIME_STAMP IN (SELECT MAX(TIME_STAMP) FROM USERDATA )");
			
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) {
				user = new UserData(
						result.getString("session_id"),
						result.getLong("experiment_id"),
						result.getLong("time_stamp"),
						result.getString("locale"),
						result.getString("remote_address"),
						result.getString("remote_host"),
						result.getInt("remote_port"),
						result.getInt("timezone")
				);
			};
			
			con.close();
		} catch (Exception e) {}
		
		return user;
	}

}
