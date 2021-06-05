package es.uniovi.hci.persistence.user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import es.uniovi.hci.model.UserData;
import es.uniovi.hci.persistence.conection.Conf;
import es.uniovi.hci.persistence.conection.ConnectionProvider;

public class UserDataServiceDAO implements UserDataService{

	private final static Logger logger = Logger.getLogger(UserDataServiceDAO.class);
	
	public boolean userExist(String sessionId) {
		logger.debug("[INICIO] - UserDataServiceDAO.");
		
		Connection con;
		try {
			con = ConnectionProvider.getInstance().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(Conf.get("SQL_FIND_USER_BY_SESSION"));
			stmt.setString(1, sessionId);
			logger.debug("\t" + stmt);
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				con.close();
				logger.debug("\t Usuario sí encontrado en base de datos.");
				return true;
			}
			con.close();
			
		} catch (ClassNotFoundException e) {
			logger.error("[ERROR] - ClassNotFoundException " + e.toString());
		} catch (SQLException e) {
			logger.error("[ERROR] - SQLException " + e.toString());
		} catch (IOException e) {
			logger.error("[ERROR] - IOException " + e.toString());
		}

		logger.debug("\t Usuario no encontrado en base de datos.");
		logger.debug("[FINAL] - UserDataServiceDAO.");
		return false;
	}

	public void registerUser(UserData userData) {
		logger.debug("[INICIO] - UserDataServiceDAO - registerUser");
		Connection con;
		try {
			con = ConnectionProvider.getInstance().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(Conf.get("SQL_INSERT_USER"));

			stmt.setString(1, userData.getSessionId());
			stmt.setLong(2, userData.getTimeStamp());
			stmt.setString(3, userData.getLocale());
			stmt.setString(4, userData.getRemoteAddress());
			stmt.setString(5, userData.getRemoteHost());
			stmt.setInt(6, userData.getRemotePort());
			stmt.setInt(7, userData.getTimezone());
			stmt.setLong(8, userData.getIdExperiment());
			stmt.setBoolean(9, false);

			logger.debug("\tInserting user data for session " + userData.getSessionId());
			int i = stmt.executeUpdate();
			logger.debug("\tRegistros insertados: " + i);
			
			con.close();

		} catch (java.sql.SQLIntegrityConstraintViolationException e) {
			logger.error("[ERROR] - SQLIntegrityConstraintViolationException: Duplicate entry '' for key " + userData.getSessionId());
		} catch (ClassNotFoundException e) {
			logger.error("[ERROR] - ClassNotFoundException " + e.toString());
		} catch (SQLException e) {
			logger.error("[ERROR] - SQLException " + e.toString());
		} catch (IOException e) {
			logger.error("[ERROR] - IOException " + e.toString());
		} 
		
		logger.debug("[FINAL] - UserDataServiceDAO - registerUser");
	}

	public void registerNavigationData(UserData data) {
		logger.debug("[INICIO] - UserDataServiceDAO - registerNavigationData");
		
		Connection con;
		try {
			con = ConnectionProvider.getInstance().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(Conf.get("SQL_UPDATE_USER"));
			stmt.setBoolean(1, data.isBrowserOnline());
			stmt.setBoolean(2, data.isJavaEnabled());
			stmt.setBoolean(3, data.isDataCookiesEnabled());
			stmt.setString(4, data.getTimeOpened());
			stmt.setString(5, data.getPageon());
			stmt.setString(6, data.getReferrer());
			stmt.setString(7, data.getBrowserName());
			stmt.setString(8, data.getBrowserEngine());
			stmt.setString(9, data.getBrowserVersion1a());
			stmt.setString(10, data.getBrowserVersion1b());
			stmt.setString(11, data.getBrowserLanguage());
			stmt.setString(12, data.getBrowserPlatform());
			stmt.setString(13, data.getDataCookies1());
			stmt.setString(14, data.getDataCookies2());
			stmt.setString(15, data.getDataStorage());
			stmt.setInt(16, data.getPreviousSites());
			stmt.setInt(17, data.getSizeScreenW());
			stmt.setInt(18, data.getSizeScreenH());
			stmt.setInt(19, data.getSizeDocW());
			stmt.setInt(20, data.getSizeDocH());
			stmt.setInt(21, data.getSizeInW());
			stmt.setInt(22, data.getSizeInH());
			stmt.setInt(23, data.getSizeAvailW());
			stmt.setInt(24, data.getSizeAvailH());
			stmt.setInt(25, data.getScrColorDepth());
			stmt.setInt(26, data.getScrPixelDepth());
			stmt.setString(27, data.getSessionId());

			logger.debug("\tQuery: " + stmt);
			int i = stmt.executeUpdate();
			logger.debug("\tRegistros insertados: " + i);
			
			con.close();
			
		} catch (ClassNotFoundException e) {
			logger.error("[ERROR] - ClassNotFoundException " + e.toString());
		} catch (SQLException e) {
			logger.error("[ERROR] - SQLException " + e.toString());
		} catch (IOException e) {
			logger.error("[ERROR] - IOException " + e.toString());
		} 
		
		logger.debug("[FINAL] - UserDataServiceDAO - registerNavigationData");
	}

}
