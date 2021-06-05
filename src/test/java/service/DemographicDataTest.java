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

import es.uniovi.hci.business.exception.DemographicDataException;
import es.uniovi.hci.business.exception.UserException;
import es.uniovi.hci.model.DemographicData;
import es.uniovi.hci.model.DemographicDataType;
import es.uniovi.hci.model.UserData;
import es.uniovi.hci.persistence.conection.ConnectionProvider;
import es.uniovi.hci.presentation.helper.DemographicDataManagerServiceHelper;
import es.uniovi.hci.presentation.helper.UserManagerServiceHelper;

public class DemographicDataTest {
	
	private UserManagerServiceHelper userHelper = new UserManagerServiceHelper();
	private DemographicDataManagerServiceHelper ddHelper = new DemographicDataManagerServiceHelper();
	
	private String userSessionId = "KJSDF784HD994HP";
	private Long ID_NOT_EXIST = 368436848648L;
	
	private static boolean primeraVez = true;
	
	@Before
    public void init() throws IOException, UserException {
		if(primeraVez) {
			//Se configura la base de datos
			ConnectionProvider.getInstance().changeConnection("db-test.properties");
			
			//Se registra un experimento y un usuario
			insertExperiment();
			insertUser();
			insertDemographicData();
			
			primeraVez = false;
		}
	}
	
	@Test
	/**
	 * Registra un dato demográfico de manera correcta
	 */
	public void registerDemographicData() throws DemographicDataException {
		Long id = findIdDemographicData();
		String name = "Profesión";
		String type = DemographicDataType.STRING.name();
		Long idExperiment = findIdExperiment();
		DemographicData dd = new DemographicData(id, name, type, idExperiment);
		dd.setStringValue("Informático");
		dd.setSessionId(userSessionId);
		
		ddHelper.register(dd);
		
		//Recuperamos el valor
		DemographicData dd2 = findDemographicDataString();
		
		assertNotNull(dd2);
		assertEquals(dd.getId(), dd2.getId());
		assertEquals(dd.getSessionId(), dd2.getSessionId());
		assertEquals(dd.getStringValue(), dd2.getStringValue());
	}
	
	@Test
	/**
	 * Registra un dato demográfico sin especificar la sesión del usuario
	 */
	public void registerDemographicDataERROR01() throws DemographicDataException {
		Long id = findIdDemographicData();
		String name = "Profesión";
		String type = DemographicDataType.STRING.name();
		Long idExperiment = findIdExperiment();
		DemographicData dd = new DemographicData(id, name, type, idExperiment);
		dd.setStringValue("Informático");
		
		try {
			ddHelper.register(dd);
			Assert.fail("Debe lanzarse excepción.");
		} catch (DemographicDataException ex) {
			assertNotNull(ex.getMessage());
		}
	}
	
	@Test
	/**
	 * Registra un dato demográfico sin especificar el identificador del dato demográfico
	 */
	public void registerDemographicDataERROR02() throws DemographicDataException {
		Long id = null;
		String name = "Profesión";
		String type = DemographicDataType.STRING.name();
		Long idExperiment = findIdExperiment();
		DemographicData dd = new DemographicData(id, name, type, idExperiment);
		dd.setStringValue("Informático");
		dd.setSessionId(userSessionId);
		
		try {
			ddHelper.register(dd);
			Assert.fail("Debe lanzarse excepción.");
		} catch (DemographicDataException ex) {
			assertNotNull(ex.getMessage());
		}
	}
	
	@Test
	/**
	 * Registra un dato demográfico sin especificar el identificador del experimento
	 */
	public void registerDemographicDataERROR03() throws DemographicDataException {
		Long id = findIdDemographicData();
		String name = "Profesión";
		String type = DemographicDataType.STRING.name();
		Long idExperiment = null;
		DemographicData dd = new DemographicData(id, name, type, idExperiment);
		dd.setStringValue("Informático");
		dd.setSessionId(userSessionId);
		
		try {
			ddHelper.register(dd);
			Assert.fail("Debe lanzarse excepción.");
		} catch (DemographicDataException ex) {
			assertNotNull(ex.getMessage());
		}
	}
	
	@Test
	/**
	 * Registra un dato demográfico sin especificar el valor del dato demográfico
	 */
	public void registerDemographicDataERROR04() throws DemographicDataException {
		Long id = findIdDemographicData();
		String name = "Profesión";
		String type = DemographicDataType.STRING.name();
		Long idExperiment = findIdExperiment();
		DemographicData dd = new DemographicData(id, name, type, idExperiment);
		dd.setStringValue(null);
		dd.setSessionId(userSessionId);
		
		try {
			ddHelper.register(dd);
			Assert.fail("Debe lanzarse excepción.");
		} catch (DemographicDataException ex) {
			assertNotNull(ex.getMessage());
		}
	}
	
	@Test
	/**
	 * Registra un dato demográfico donde el id del dato demográfico no existe en el sistema
	 */
	public void registerDemographicDataERROR05() throws DemographicDataException {
		Long id = ID_NOT_EXIST;
		String name = "Profesión";
		String type = DemographicDataType.STRING.name();
		Long idExperiment = findIdExperiment();
		DemographicData dd = new DemographicData(id, name, type, idExperiment);
		dd.setStringValue("Informático");
		dd.setSessionId(userSessionId);
		
		try {
			ddHelper.register(dd);
			Assert.fail("Debe lanzarse excepción.");
		} catch (DemographicDataException ex) {
			assertNotNull(ex.getMessage());
		}
	}
	
	@Test
	/**
	 * Registra un dato demográfico donde el id del experimento no existe en el sistema
	 */
	public void registerDemographicDataERROR06() throws DemographicDataException {
		Long id = findIdDemographicData();
		String name = "Profesión";
		String type = DemographicDataType.STRING.name();
		Long idExperiment = ID_NOT_EXIST;
		DemographicData dd = new DemographicData(id, name, type, idExperiment);
		dd.setStringValue("Informático");
		dd.setSessionId(userSessionId);
		
		try {
			ddHelper.register(dd);
			Assert.fail("Debe lanzarse excepción.");
		} catch (DemographicDataException ex) {
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
	 * Inserta un dato demográfico en la base de datos
	 */
	private void insertDemographicData() {
		Connection con;
		try {
			con = ConnectionProvider.getInstance().getConnection();
			
			PreparedStatement stmt = con.prepareStatement("INSERT INTO DEMOGRAPHIC_DATA (name, type, experiment_id) VALUES (?,?,?)");
			stmt.setString(1, "Profesión");
			stmt.setString(2, "STRING");
			stmt.setLong(3, findIdExperiment());
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
	 * Devuelve el último dato demográfico insertado
	 * @return
	 */
	private Long findIdDemographicData() {
		Connection con;
		Long dd = null;
		try {
			con = ConnectionProvider.getInstance().getConnection();
			
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM DEMOGRAPHIC_DATA WHERE id IN (SELECT MAX(id) FROM DEMOGRAPHIC_DATA )");
			
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) {
				dd = result.getLong("id");
			};
			
			con.close();
		} catch (Exception e) {}
		
		return dd;
	}
	
	/**
	 * Devuelve el último dato demográfico insertado de tipo STRING
	 * @return
	 */
	private DemographicData findDemographicDataString() {
		Connection con;
		DemographicData dd = null;
		try {
			con = ConnectionProvider.getInstance().getConnection();
			
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM DEMOGRAPHIC_DATA_STRING WHERE id IN (SELECT MAX(id) FROM DEMOGRAPHIC_DATA_STRING )");
			
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) {
				dd = new DemographicData(null, null, null, null);
				dd.setId(result.getLong("demographic_data_id"));
				dd.setSessionId(result.getString("user_session_id"));
				dd.setStringValue(result.getString("value"));
			};
			
			con.close();
		} catch (Exception e) {}
		
		return dd;
	}

}
