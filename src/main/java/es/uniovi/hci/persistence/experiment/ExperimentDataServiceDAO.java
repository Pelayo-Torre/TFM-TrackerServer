package es.uniovi.hci.persistence.experiment;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import es.uniovi.hci.persistence.conection.Conf;
import es.uniovi.hci.persistence.conection.ConnectionProvider;


public class ExperimentDataServiceDAO implements ExperimentDataService{
	
	private final static Logger logger = Logger.getLogger(ExperimentDataServiceDAO.class);

	public String getStatus(Long idExperiment) {
		logger.debug("[INICIO] - ExperimentDataServiceDAO - getStatus");
		
		Connection con;
		String status = null;
		try {
			con = ConnectionProvider.getInstance().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(Conf.get("SQL_FIND_STATUS_EXPERIMENT"));
			stmt.setLong(1, idExperiment);
				
			logger.debug("\tQuery: " + stmt);
			
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) {
				status = result.getString("status");
			};
			
			con.close();
			
		} catch (ClassNotFoundException e) {
			logger.error("[ERROR] - ClassNotFoundException " + e.toString());
		} catch (SQLException e) {
			logger.error("[ERROR] - SQLException " + e.toString());
		} catch (IOException e) {
			logger.error("[ERROR] - IOException " + e.toString());
		}		
		
		logger.debug("[FINAL] - ExperimentDataServiceDAO - getStatus");
		return status;
	}

}
