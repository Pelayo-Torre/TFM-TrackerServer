//package es.uniovi.hci;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.Calendar;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.log4j.Logger;
//
//import es.uniovi.hci.model.UserData;
//
//public class UserDataRegister {
//
//	final static Logger logger = Logger.getLogger(ComponentRegistry.class);
//	private UserRegister userRegister;
//
//	public UserDataRegister() {
//
//	}
//
//	public void completeUserData(HttpServletRequest request, UserData data)
//			throws SQLException, ClassNotFoundException, IOException {
//		Connection con = ConnectionProvider.getInstance().getConnection();
//		try {
//			PreparedStatement stmt = con.prepareStatement(
//					"UPDATE userdata SET browserOnline=?, javaEnabled=?, dataCookiesEnabled=?,timeOpened=?, pageon=?, referred=?, browserName=?, browserEngine=?, browserVersion1a=?, browserVersion1b=?,\r\n"
//							+ "					browserLanguage=?, browserPlatform=?, dataCookies1=?, dataCookies2=?, dataStorage=?,previousSites=?, sizeScreenW=?, sizeScreenH=?,"
//							+ "					 sizeDocW=?, sizeDocH=?, sizeInW=?, sizeInH=?, sizeAvailW=?, sizeAvailH=?,\r\n"
//							+ "					scrColorDepth=?, scrPixelDepth=? WHERE sessionId = ?");
//			stmt.setBoolean(1, data.isBrowserOnline());
//			stmt.setBoolean(2, data.isJavaEnabled());
//			stmt.setBoolean(3, data.isDataCookiesEnabled());
//			stmt.setString(4, data.getTimeOpened());
//			stmt.setString(5, data.getPageon());
//			stmt.setString(6, data.getReferrer());
//			stmt.setString(7, data.getBrowserName());
//			stmt.setString(8, data.getBrowserEngine());
//			stmt.setString(9, data.getBrowserVersion1a());
//			stmt.setString(10, data.getBrowserVersion1b());
//			stmt.setString(11, data.getBrowserLanguage());
//			stmt.setString(12, data.getBrowserPlatform());
//			stmt.setString(13, data.getDataCookies1());
//			stmt.setString(14, data.getDataCookies2());
//			stmt.setString(15, data.getDataStorage());
//			stmt.setInt(16, data.getPreviousSites());
//			stmt.setInt(17, data.getSizeScreenW());
//			stmt.setInt(18, data.getSizeScreenH());
//			stmt.setInt(19, data.getSizeDocW());
//			stmt.setInt(20, data.getSizeDocH());
//			stmt.setInt(21, data.getSizeInW());
//			stmt.setInt(22, data.getSizeInH());
//			stmt.setInt(23, data.getSizeAvailW());
//			stmt.setInt(24, data.getSizeAvailH());
//			stmt.setInt(25, data.getScrColorDepth());
//			stmt.setInt(26, data.getScrPixelDepth());
//			stmt.setString(27, request.getSession().getId());
//
//			logger.debug("Inserting user data for session " + request.getSession().getId());
//			int i = stmt.executeUpdate();
//			logger.debug(i + " records inserted");
//
//		} catch (Exception e) {
//			System.out.println(e);
//			e.printStackTrace();
//		} finally {
//			con.close();
//		}
//	}
//
//	public void completeUserDemographicData(HttpServletRequest request, Integer gender, Integer laterality,
//			Integer device, Calendar birthdate) throws SQLException, ClassNotFoundException, IOException {
//
//		Connection con = ConnectionProvider.getInstance().getConnection();
//
//		logger.debug("Completing demographic data: " + gender + ", " + laterality + ", " + device + ", " + birthdate);
//
//		if (laterality == -1) {
//			logger.error(" Error completing demographic data: sessionId= " + request.getSession().getId() + ", gender: "
//					+ gender + ", " + laterality + ", " + device + ", " + birthdate);
//		}
//
//		try {
//			userRegister = new UserRegister();
//			userRegister.checkTracking(request);
//
//			PreparedStatement stmt = con
//					.prepareStatement("UPDATE userdata SET laterality=?, birthdate=? WHERE sessionId = ?");
//			// stmt.setInt(1, gender);
//			stmt.setInt(1, laterality);
//			// stmt.setInt(3, device);
//			stmt.setDate(2, new java.sql.Date(birthdate.getTimeInMillis()));
//			stmt.setString(3, request.getSession().getId());
//
//			logger.debug("Inserting user data for session " + request.getSession().getId());
//			int i = stmt.executeUpdate();
//			logger.debug(i + " records inserted");
//
//		} catch (Exception e) {
//			System.out.println(e);
//			e.printStackTrace();
//		} finally {
//			con.close();
//		}
//	}
//	/*
//	 * public void completeUserDemographicData(HttpServletRequest request, Integer
//	 * gender, Integer laterality, Integer device, Calendar birthdate) throws
//	 * SQLException, ClassNotFoundException, IOException {
//	 * 
//	 * Connection con = ConnectionProvider.getInstance().getConnection();
//	 * 
//	 * logger.debug("Completing demographic data: " + gender + ", " + laterality +
//	 * ", " + device + ", " + birthdate); if (gender == -1 || laterality == -1 ||
//	 * device == -1) {
//	 * logger.error(" Error completing demographic data: sessionId= " +
//	 * request.getSession().getId() + ", gender: " + gender + ", " + laterality +
//	 * ", " + device + ", " + birthdate); } try { userRegister = new UserRegister();
//	 * userRegister.checkTracking(request);
//	 * 
//	 * PreparedStatement stmt = con.prepareStatement(
//	 * "UPDATE userdata SET gender=?, laterality=?, device=?, birthdate=? WHERE sessionId = ?"
//	 * ); stmt.setInt(1, gender); stmt.setInt(2, laterality); stmt.setInt(3,
//	 * device); stmt.setDate(4, new java.sql.Date(birthdate.getTimeInMillis()));
//	 * stmt.setString(5, request.getSession().getId());
//	 * 
//	 * logger.debug("Inserting user data for session " +
//	 * request.getSession().getId()); int i = stmt.executeUpdate(); logger.debug(i +
//	 * " records inserted");
//	 * 
//	 * } catch (Exception e) { System.out.println(e); e.printStackTrace(); } finally
//	 * { con.close(); } }
//	 */
//
//	public void completeDevice(HttpServletRequest request, Integer device)
//			throws SQLException, ClassNotFoundException, IOException {
//
//		Connection con = ConnectionProvider.getInstance().getConnection();
//
//		logger.error("Completing device data: " + request.getSession().getId() + ": " + device);
//		if (device == -1) {
//			logger.error(" Error completing demographic data: sessionId= " + request.getSession().getId() + ", device: "
//					+ device);
//		} else {
//			try {
//				userRegister = new UserRegister();
//				userRegister.checkTracking(request);
//
//				PreparedStatement stmt = con.prepareStatement("UPDATE userdata SET device=? WHERE sessionId = ?");
//				stmt.setInt(1, device);
//				stmt.setString(2, request.getSession().getId());
//
//				logger.debug("Inserting user data for session " + request.getSession().getId());
//				int i = stmt.executeUpdate();
//				logger.debug(i + " records inserted");
//
//			} catch (Exception e) {
//				System.out.println(e);
//				e.printStackTrace();
//			} finally {
//				con.close();
//			}
//		}
//	}
//
//	public void completeLaterality(HttpServletRequest request, Integer laterality)
//			throws SQLException, ClassNotFoundException, IOException {
//
//		Connection con = ConnectionProvider.getInstance().getConnection();
//
//		logger.error("Completing laterality data: " + request.getSession().getId() + ": " + laterality);
//		if (laterality == -1) {
//			logger.error(" Error completing demographic data: sessionId= " + request.getSession().getId()
//					+ ", laterality: " + laterality);
//		} else {
//			try {
//				userRegister = new UserRegister();
//				userRegister.checkTracking(request);
//
//				PreparedStatement stmt = con.prepareStatement("UPDATE userdata SET laterality=? WHERE sessionId = ?");
//				stmt.setInt(1, laterality);
//				stmt.setString(2, request.getSession().getId());
//
//				logger.debug("Inserting user data for session " + request.getSession().getId());
//				int i = stmt.executeUpdate();
//				logger.debug(i + " records inserted");
//
//			} catch (Exception e) {
//				System.out.println(e);
//				e.printStackTrace();
//			} finally {
//				con.close();
//			}
//		}
//	}
//
//	public void completeGender(HttpServletRequest request, Integer gender)
//			throws SQLException, ClassNotFoundException, IOException {
//
//		Connection con = ConnectionProvider.getInstance().getConnection();
//
//		logger.error("Completing gender data: " + request.getSession().getId() + ": " + gender);
//		if (gender == -1) {
//			logger.error(" Error completing demographic data: sessionId= " + request.getSession().getId() + ", gender: "
//					+ gender);
//		} else {
//			try {
//				userRegister = new UserRegister();
//				userRegister.checkTracking(request);
//
//				PreparedStatement stmt = con.prepareStatement("UPDATE userdata SET gender=? WHERE sessionId = ?");
//				stmt.setInt(1, gender);
//				stmt.setString(2, request.getSession().getId());
//
//				logger.debug("Inserting user data for session " + request.getSession().getId());
//				int i = stmt.executeUpdate();
//				logger.debug(i + " records inserted");
//
//			} catch (Exception e) {
//				System.out.println(e);
//				e.printStackTrace();
//			} finally {
//				con.close();
//			}
//		}
//	}
//
//}
