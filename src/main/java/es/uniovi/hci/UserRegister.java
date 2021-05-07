//package es.uniovi.hci;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Date;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.log4j.Logger;
//
//public class UserRegister {
//
//	final static Logger logger = Logger.getLogger(ComponentRegistry.class);
//	private HttpServletRequest request = null;
//
//	public UserRegister() {
//
//	}
//
//	public void checkTracking(HttpServletRequest request) throws SQLException, ClassNotFoundException, IOException {
//		this.request = request;
//		Connection con = ConnectionProvider.getInstance().getConnection();
//		boolean isTracked = (request.getSession().getAttribute("isTracked") == null ? false : true);
//		if (!isTracked) {
//			logger.debug("Tracking new sessionId: " + request.getSession().getId());
//			try {
//				synchronized (this) {
//					if (!findUser()) {
//						registerUser();
//					} else {
//						logger.error(
//								"SOMETHING IS WRONG. The user sessions is not flagged as tracked but the session Id exists in the database");
//					}
//				}
//			} catch (SQLException e) {
//				logger.error("Error while tracking user data: "+e.getStackTrace());
//				e.printStackTrace();
//			} finally {
//				con.close();
//			}
//			// We flag the session as tracked
//			request.getSession().setAttribute("isTracked", true);
//		}
//
//	}
//
//	private boolean findUser() throws SQLException, ClassNotFoundException, IOException {
//		Connection con = ConnectionProvider.getInstance().getConnection();
//
//		try {
//			PreparedStatement stmt = con.prepareStatement("SELECT * FROM userdata WHERE SESSIONID=?");
//			stmt.setString(1, this.request.getSession().getId());
//			logger.debug(stmt);
//			ResultSet result = stmt.executeQuery();
//
//			if (result.next()) {
//				logger.debug("User session found in database");
//				return true;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			con.close();
//		}
//		logger.debug("User session NOT found in database");
//		return false;
//	}
//
//	private void registerUser() throws ClassNotFoundException, SQLException, IOException {
//		Connection con = ConnectionProvider.getInstance().getConnection();
//
//		try {
//			PreparedStatement stmt = con.prepareStatement(
//					"insert into userdata (sessionId, timeStamp, locale, remoteAddress, remoteHost, remotePort,timezone ) values(?,?,?,?,?,?,?)");
//
//			stmt.setString(1, request.getSession().getId());
//			stmt.setLong(2, (new Date()).getTime());
//			stmt.setString(3, request.getLocale().toString());
//			stmt.setString(4, request.getRemoteAddr());
//			stmt.setString(5, request.getRemoteHost());
//			stmt.setInt(6, request.getRemotePort());
//			if(this.request.getSession().getAttribute("timezone") != null)
//				stmt.setInt(7, (Integer) this.request.getSession().getAttribute("timezone"));
//			else
//				stmt.setInt(7, 0);
//			logger.debug("Inserting user data for session " + request.getSession().getId());
//			int i = stmt.executeUpdate();
//			logger.debug(i + " records inserted");
//
//		} catch (java.sql.SQLIntegrityConstraintViolationException e) {
//			logger.error("SQLIntegrityConstraintViolationException: Duplicate entry '' for key "
//					+ request.getSession().getId());
//			;
//		} catch (Exception e) {
//			System.out.println(e);
//			e.printStackTrace();
//		} finally {
//			con.close();
//		}
//	}
//
//}
