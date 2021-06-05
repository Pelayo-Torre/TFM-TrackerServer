package es.uniovi.hci.persistence.conection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConnectionProvider {

	private static ConnectionProvider instance = null;
	private String user, password, driver, connectionString;
	final static Logger logger = Logger.getLogger(ConnectionProvider.class);
	private String file = "db.properties";


	public static ConnectionProvider getInstance() throws IOException {
		if (instance == null) {
			instance = new ConnectionProvider();
			instance.init();
		}
		return instance;
	}
	
	private ConnectionProvider() {}

	private void init() throws IOException {
		final InputStream stream =
		           this.getClass().getClassLoader().getResourceAsStream(this.file);
		Properties p = new Properties();
		if (stream != null) {
			p.load(stream);
		} else {
			throw new FileNotFoundException("property file db.properties not found in the classpath");
		}
		
		connectionString = p.getProperty("connectionString");
		user = p.getProperty("user");
		password = p.getProperty("password");
		driver = p.getProperty("driver");

		logger.debug("Read the connection string: " + connectionString);
	}
	
	public synchronized Connection getConnection() throws ClassNotFoundException, SQLException
	{
		Class.forName(driver);
		Connection con = DriverManager.getConnection(connectionString, user, password);
		return con;
	}
	
	/**
	 * Recibe un fichero donde se encuentra la configuración de base de datos
	 * @param file fichero
	 * @throws IOException 
	 */
	public void changeConnection(String file) throws IOException {
		this.file = file;
		init();
	}
}
