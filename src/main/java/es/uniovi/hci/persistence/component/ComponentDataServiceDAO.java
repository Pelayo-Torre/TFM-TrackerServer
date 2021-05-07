package es.uniovi.hci.persistence.component;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import es.uniovi.hci.model.ComponentData;
import es.uniovi.hci.persistence.conection.Conf;
import es.uniovi.hci.persistence.conection.ConnectionProvider;

public class ComponentDataServiceDAO implements ComponentDataService{
	
	private final static Logger logger = Logger.getLogger(ComponentDataServiceDAO.class);

	public void registry(ComponentData componentData) {
		logger.debug("[INICIO] - ComponentDataServiceDAO - registry");
		
		Connection con;
		try {
			con = ConnectionProvider.getInstance().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(Conf.get("SQL_INSERT_COMPONENT"));

			stmt.setString(1, componentData.getSessionId());
			stmt.setString(2, componentData.getSceneId());
			stmt.setLong(3, componentData.getTimeStamp());
			stmt.setInt(4, componentData.getX());
			stmt.setInt(5, componentData.getY());
			stmt.setInt(6, componentData.getxF());
			stmt.setInt(7, componentData.getyF());
			stmt.setString(8, componentData.getComponentId());
			if(componentData.getTypeId() != null) {
				stmt.setInt(9, componentData.getTypeId());
			}
			else {
				stmt.setObject(9, componentData.getTypeId());
			}
			stmt.setString(10, componentData.getComponentAssociated());
				
			logger.debug("\tInserting " + componentData.getSessionId() + ": " + componentData);
			
			stmt.executeUpdate();
			con.close();
			
		} catch (ClassNotFoundException e) {
			logger.error("[ERROR] - ClassNotFoundException " + e.toString());
		} catch (SQLException e) {
			logger.error("[ERROR] - SQLException " + e.toString());
		} catch (IOException e) {
			logger.error("[ERROR] - IOException " + e.toString());
		}
		logger.debug("[FINAL] - ComponentDataServiceDAO - registry");
	}

}
