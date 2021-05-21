package es.uniovi.hci.persistence.demographicdata;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import es.uniovi.hci.model.DemographicData;
import es.uniovi.hci.persistence.conection.Conf;
import es.uniovi.hci.persistence.conection.ConnectionProvider;

public class DemographicDataServiceDAO implements DemographicDataService{
	
	private final static Logger logger = Logger.getLogger(DemographicDataServiceDAO.class);

	public void registerString(DemographicData dd) {
		logger.debug("[INICIO] - DemographicDataServiceDAO - registerString");
		
		Connection con;
		try {
			con = ConnectionProvider.getInstance().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(Conf.get("SQL_INSERT_DEMOGRAPHIC_DATA_STRING"));

			stmt.setLong(1, dd.getId());
			stmt.setString(2, dd.getSessionId());
			stmt.setString(3, dd.getStringValue());
				
			logger.debug("\tInserting sessionID: " + dd.getSessionId() + ", id del dato demogáfico: " + dd.getId() + ", valor: " + dd.getStringValue());
			
			stmt.executeUpdate();
			con.close();
			
		} catch (ClassNotFoundException e) {
			logger.error("[ERROR] - ClassNotFoundException " + e.toString());
		} catch (SQLException e) {
			logger.error("[ERROR] - SQLException " + e.toString());
		} catch (IOException e) {
			logger.error("[ERROR] - IOException " + e.toString());
		}
		
		logger.debug("[FINAL] - DemographicDataServiceDAO - registerString");
	}

	public void registerNumber(DemographicData dd) {
		logger.debug("[INICIO] - DemographicDataServiceDAO - registerNumber");
		
		Connection con;
		try {
			con = ConnectionProvider.getInstance().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(Conf.get("SQL_INSERT_DEMOGRAPHIC_DATA_NUMBER"));

			stmt.setLong(1, dd.getId());
			stmt.setString(2, dd.getSessionId());
			stmt.setDouble(3, dd.getNumberValue());
				
			logger.debug("\tInserting sessionID: " + dd.getSessionId() + ", id del dato demogáfico: " + dd.getId() + ", valor: " + dd.getNumberValue());
			
			stmt.executeUpdate();
			con.close();
			
		} catch (ClassNotFoundException e) {
			logger.error("[ERROR] - ClassNotFoundException " + e.toString());
		} catch (SQLException e) {
			logger.error("[ERROR] - SQLException " + e.toString());
		} catch (IOException e) {
			logger.error("[ERROR] - IOException " + e.toString());
		}
		
		logger.debug("[FINAL] - DemographicDataServiceDAO - registerNumber");
	}

	public void registerDate(DemographicData dd) {
		logger.debug("[INICIO] - DemographicDataServiceDAO - registerDate");
		
		Connection con;
		try {
			con = ConnectionProvider.getInstance().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(Conf.get("SQL_INSERT_DEMOGRAPHIC_DATA_DATE"));

			stmt.setLong(1, dd.getId());
			stmt.setString(2, dd.getSessionId());
			stmt.setObject(3, dd.getDateValue());
				
			logger.debug("\tInserting sessionID: " + dd.getSessionId() + ", id del dato demogáfico: " + dd.getId() + ", valor: " + dd.getDateValue());
			
			stmt.executeUpdate();
			con.close();
			
		} catch (ClassNotFoundException e) {
			logger.error("[ERROR] - ClassNotFoundException " + e.toString());
		} catch (SQLException e) {
			logger.error("[ERROR] - SQLException " + e.toString());
		} catch (IOException e) {
			logger.error("[ERROR] - IOException " + e.toString());
		}
		
		logger.debug("[FINAL] - DemographicDataServiceDAO - registerDate");
	}

	public DemographicData getDemographicDataByIdAndIdExperiment(Long id, Long idExperiment) {
		logger.debug("[INICIO] - DemographicDataServiceDAO - getDemographicDataById");
		
		Connection con;
		DemographicData data = null;
		try {
			con = ConnectionProvider.getInstance().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(Conf.get("SQL_FIND_BY_ID_AND_IDEXPERIMENT"));

			stmt.setLong(1, id);
			stmt.setLong(2, idExperiment);
							
			logger.debug("\tQuery: " + stmt);
			ResultSet result = stmt.executeQuery();

			while (result.next()) {
				data = new DemographicData(
						result.getLong("id"),
						result.getString("name"),
						result.getString("type"),
						result.getLong("experiment_id")
				);
				logger.debug("\t Dato demográfico obtenido: " + data);
			}
			
			con.close();
			
		} catch (ClassNotFoundException e) {
			logger.error("[ERROR] - ClassNotFoundException " + e.toString());
		} catch (SQLException e) {
			logger.error("[ERROR] - SQLException " + e.toString());
		} catch (IOException e) {
			logger.error("[ERROR] - IOException " + e.toString());
		}
		
		logger.debug("[FINAL] - DemographicDataServiceDAO - getDemographicDataById");
		return data;
	}

	public boolean existDemographicDataString(Long id, String sessionId) {
		logger.debug("[INICIO] - DemographicDataServiceDAO - existDemographicDataString");
		
		Connection con;
		boolean exist = false;
		try {
			con = ConnectionProvider.getInstance().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(Conf.get("SQL_FIND_DEMOGRAPHIC_DATA_STRING_BY_ID_AND_SESSION"));

			stmt.setLong(1, id);
			stmt.setString(2, sessionId);
							
			logger.debug("\tQuery: " + stmt);
			ResultSet result = stmt.executeQuery();

			while (result.next()) {
				exist = true;
			}
			
			con.close();
			
		} catch (ClassNotFoundException e) {
			logger.error("[ERROR] - ClassNotFoundException " + e.toString());
		} catch (SQLException e) {
			logger.error("[ERROR] - SQLException " + e.toString());
		} catch (IOException e) {
			logger.error("[ERROR] - IOException " + e.toString());
		}
		
		logger.debug("[FINAL] - DemographicDataServiceDAO - existDemographicDataString");
		return exist;
	}
	
	public boolean existDemographicDataNumber(Long id, String sessionId) {
		logger.debug("[INICIO] - DemographicDataServiceDAO - existDemographicDataNumber");
		
		Connection con;
		boolean exist = false;
		try {
			con = ConnectionProvider.getInstance().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(Conf.get("SQL_FIND_DEMOGRAPHIC_DATA_NUMBER_BY_ID_AND_SESSION"));

			stmt.setLong(1, id);
			stmt.setString(2, sessionId);
							
			logger.debug("\tQuery: " + stmt);
			ResultSet result = stmt.executeQuery();

			while (result.next()) {
				exist = true;
			}
			
			con.close();
			
		} catch (ClassNotFoundException e) {
			logger.error("[ERROR] - ClassNotFoundException " + e.toString());
		} catch (SQLException e) {
			logger.error("[ERROR] - SQLException " + e.toString());
		} catch (IOException e) {
			logger.error("[ERROR] - IOException " + e.toString());
		}
		
		logger.debug("[FINAL] - DemographicDataServiceDAO - existDemographicDataNumber");
		return exist;
	}
	
	public boolean existDemographicDataDate(Long id, String sessionId) {
		logger.debug("[INICIO] - DemographicDataServiceDAO - existDemographicDataDate");
		
		Connection con;
		boolean exist = false;
		try {
			con = ConnectionProvider.getInstance().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(Conf.get("SQL_FIND_DEMOGRAPHIC_DATA_DATE_BY_ID_AND_SESSION"));

			stmt.setLong(1, id);
			stmt.setString(2, sessionId);
							
			logger.debug("\tQuery: " + stmt);
			ResultSet result = stmt.executeQuery();

			while (result.next()) {
				exist = true;
			}
			
			con.close();
			
		} catch (ClassNotFoundException e) {
			logger.error("[ERROR] - ClassNotFoundException " + e.toString());
		} catch (SQLException e) {
			logger.error("[ERROR] - SQLException " + e.toString());
		} catch (IOException e) {
			logger.error("[ERROR] - IOException " + e.toString());
		}
		
		logger.debug("[FINAL] - DemographicDataServiceDAO - existDemographicDataDate");
		return exist;
	}

}
