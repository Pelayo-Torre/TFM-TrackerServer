package service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;


import es.uniovi.hci.business.exception.ComponentException;
import es.uniovi.hci.business.exception.UserException;
import es.uniovi.hci.model.ComponentData;
import es.uniovi.hci.model.UserData;
import es.uniovi.hci.persistence.conection.Conf;
import es.uniovi.hci.persistence.conection.ConnectionProvider;
import es.uniovi.hci.presentation.helper.ComponentManagerServiceHelper;
import es.uniovi.hci.presentation.helper.UserManagerServiceHelper;

public class ComponentTest {
	
	private UserManagerServiceHelper userHelper = new UserManagerServiceHelper();
	private ComponentManagerServiceHelper componentHelper = new ComponentManagerServiceHelper();
	
	private String userSessionId = "KJSDF784HD994HK";
	
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
	 * Registra un componente de manera correcta
	 */
	public void registerComponent() throws ComponentException {
		Integer x = 45;
		Integer y = 56;
		Integer xF = 96;
		Integer yF = 75;
		String scene = "Escena1";
		long time = new Date().getTime();
		Integer timezone = 2;
		Integer type = 5;
		String componentId = "componente1" + Math.random()*999999999;
		String componentIdAssociated = "componente1.5";
		ComponentData component = new ComponentData(
				x, y, xF, yF, componentId, userSessionId, scene,
				timezone, time, type, componentIdAssociated);
		
		componentHelper.registry(component);
		
		//Comprobramos que se ha registrado correctamente
		ComponentData c = findComponent(scene, componentId, userSessionId);
		
		assertNotNull(c);
		assertEquals(x.intValue(), c.getX().intValue());
		assertEquals(y.intValue(), c.getY().intValue());
		assertEquals(xF.intValue(), c.getxF().intValue());
		assertEquals(yF.intValue(), c.getyF().intValue());
		assertEquals(scene, c.getSceneId());
		assertEquals(time, c.getTimeStamp().longValue());
		assertEquals(type.intValue(), c.getTypeId().intValue());
		assertEquals(componentId, c.getComponentId());
		assertEquals(componentIdAssociated, c.getComponentAssociated());
	}
	
	@Test
	/**
	 * Registra un componente sin especificar la sesión de usuario
	 */
	public void registerComponentERROR01() throws ComponentException {
		Integer x = 45;
		Integer y = 56;
		Integer xF = 96;
		Integer yF = 75;
		String scene = "Escena1";
		long time = new Date().getTime();
		Integer timezone = 2;
		Integer type = 5;
		String componentId = "componente1";
		String componentIdAssociated = "componente1.5";
		ComponentData component = new ComponentData(
				x, y, xF, yF, componentId, null, scene,
				timezone, time, type, componentIdAssociated);
		
		try {
			componentHelper.registry(component);
			Assert.fail("Debe lanzarse excepción.");
		} catch (ComponentException ex) {
			assertNotNull(ex.getMessage());
		}
	}
	
	@Test
	/**
	 * Registra un componente sin especificar la escena
	 */
	public void registerComponentERROR02() throws ComponentException {
		Integer x = 45;
		Integer y = 56;
		Integer xF = 96;
		Integer yF = 75;
		String scene = null;
		long time = new Date().getTime();
		Integer timezone = 2;
		Integer type = 5;
		String componentId = "componente1";
		String componentIdAssociated = "componente1.5";
		ComponentData component = new ComponentData(
				x, y, xF, yF, componentId, userSessionId, scene,
				timezone, time, type, componentIdAssociated);
		
		try {
			componentHelper.registry(component);
			Assert.fail("Debe lanzarse excepción.");
		} catch (ComponentException ex) {
			assertNotNull(ex.getMessage());
		}
	}
	
	@Test
	/**
	 * Registra un componente sin especificar el timeStamp
	 */
	public void registerComponentERROR03() throws ComponentException {
		Integer x = 45;
		Integer y = 56;
		Integer xF = 96;
		Integer yF = 75;
		String scene = "Escena1";
		Long time = null;
		Integer timezone = 2;
		Integer type = 5;
		String componentId = "componente1";
		String componentIdAssociated = "componente1.5";
		ComponentData component = new ComponentData(
				x, y, xF, yF, componentId, userSessionId, scene,
				timezone, time, type, componentIdAssociated);
		
		try {
			componentHelper.registry(component);
			Assert.fail("Debe lanzarse excepción.");
		} catch (ComponentException ex) {
			assertNotNull(ex.getMessage());
		}
	}
	
	@Test
	/**
	 * Registra un componente sin especificar la coordenada X
	 */
	public void registerComponentERROR04() throws ComponentException {
		Integer x = null;
		Integer y = 56;
		Integer xF = 96;
		Integer yF = 75;
		String scene = "Escena1";
		long time = new Date().getTime();
		Integer timezone = 2;
		Integer type = 5;
		String componentId = "componente1";
		String componentIdAssociated = "componente1.5";
		ComponentData component = new ComponentData(
				x, y, xF, yF, componentId, userSessionId, scene,
				timezone, time, type, componentIdAssociated);
		
		try {
			componentHelper.registry(component);
			Assert.fail("Debe lanzarse excepción.");
		} catch (ComponentException ex) {
			assertNotNull(ex.getMessage());
		}
	}
	
	@Test
	/**
	 * Registra un componente sin especificar la coordenada Y
	 */
	public void registerComponentERROR05() throws ComponentException {
		Integer x = 45;
		Integer y = null;
		Integer xF = 96;
		Integer yF = 75;
		String scene = "Escena1";
		long time = new Date().getTime();
		Integer timezone = 2;
		Integer type = 5;
		String componentId = "componente1";
		String componentIdAssociated = "componente1.5";
		ComponentData component = new ComponentData(
				x, y, xF, yF, componentId, userSessionId, scene,
				timezone, time, type, componentIdAssociated);
		
		try {
			componentHelper.registry(component);
			Assert.fail("Debe lanzarse excepción.");
		} catch (ComponentException ex) {
			assertNotNull(ex.getMessage());
		}
	}
	
	@Test
	/**
	 * Registra un componente sin especficar la coordenada xF
	 */
	public void registerComponentERROR06() throws ComponentException {
		Integer x = 45;
		Integer y = 56;
		Integer xF = null;
		Integer yF = 75;
		String scene = "Escena1";
		long time = new Date().getTime();
		Integer timezone = 2;
		Integer type = 5;
		String componentId = "componente1";
		String componentIdAssociated = "componente1.5";
		ComponentData component = new ComponentData(
				x, y, xF, yF, componentId, userSessionId, scene,
				timezone, time, type, componentIdAssociated);
		
		try {
			componentHelper.registry(component);
			Assert.fail("Debe lanzarse excepción.");
		} catch (ComponentException ex) {
			assertNotNull(ex.getMessage());
		}
	}
	
	@Test
	/**
	 * Registra un componente sin especficar la coordenada yF
	 */
	public void registerComponentERROR07() throws ComponentException {
		Integer x = 45;
		Integer y = 56;
		Integer xF = 96;
		Integer yF = null;
		String scene = "Escena1";
		long time = new Date().getTime();
		Integer timezone = 2;
		Integer type = 5;
		String componentId = "componente1";
		String componentIdAssociated = "componente1.5";
		ComponentData component = new ComponentData(
				x, y, xF, yF, componentId, userSessionId, scene,
				timezone, time, type, componentIdAssociated);
		
		try {
			componentHelper.registry(component);
			Assert.fail("Debe lanzarse excepción.");
		} catch (ComponentException ex) {
			assertNotNull(ex.getMessage());
		}
	}
	
	@Test
	/**
	 * Registra un componente que ya se encuentra registrado para la escena y sesión dados
	 */
	public void registerComponentERROR08() throws ComponentException {
		Integer x = 45;
		Integer y = 56;
		Integer xF = 96;
		Integer yF = 75;
		String scene = "Escena1";
		long time = new Date().getTime();
		Integer timezone = 2;
		Integer type = 5;
		String componentId = "componente1" + Math.random()*999999999;
		String componentIdAssociated = "componente1.5";
		ComponentData component = new ComponentData(
				x, y, xF, yF, componentId, userSessionId, scene,
				timezone, time, type, componentIdAssociated);
		
		componentHelper.registry(component);
		
		try {
			componentHelper.registry(component);
			Assert.fail("Debe lanzarse excepción.");
		} catch (ComponentException ex) {
			assertNotNull(ex.getMessage());
		}
	}
	
	private ComponentData findComponent(String sceneId, String componentId, String sessionId) {		
		Connection con;
		ComponentData component = null;
		try {
			con = ConnectionProvider.getInstance().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(Conf.get("SQL_FIND_COMPONENT_BY_SCENE_COMPONENT_AND_SESSION"));

			stmt.setString(1, sceneId);
			stmt.setString(2, componentId);
			stmt.setString(3, sessionId);
						
			ResultSet result = stmt.executeQuery();

			while (result.next()) {
				component = new ComponentData(
						result.getInt("x"), 
						result.getInt("y"),
						result.getInt("xF"),
						result.getInt("yF"), 
						result.getString("component_id"), 
						result.getString("user_session_id"), 
						result.getString("scene_id"),
						null, 
						result.getLong("time_stamp"), 
						result.getInt("type_id"), 
						result.getString("component_associated"));
			}
			
			con.close();
			
		} catch (Exception e) {}
		
		return component;
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
