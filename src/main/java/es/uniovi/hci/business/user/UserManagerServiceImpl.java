package es.uniovi.hci.business.user;


import org.apache.log4j.Logger;

import es.uniovi.hci.business.exception.UserException;
import es.uniovi.hci.business.validators.UserValidator;
import es.uniovi.hci.model.UserData;
import es.uniovi.hci.persistence.helper.UserDataServiceHelper;

public class UserManagerServiceImpl implements UserManagerService{

	private final static Logger logger = Logger.getLogger(UserManagerServiceImpl.class);
	
	public boolean userExist(String sessionId) {
		logger.debug("[INICIO] - UserManagerServiceImpl - userExist");
		logger.debug("\t parámetro de entrada: " + sessionId);
		
		boolean userExist = new UserDataServiceHelper().userExist(sessionId);
		
		logger.debug("[FINAL] - UserManagerServiceImpl - userExist");
		return userExist;
	}

	public void registerUser(UserData userData) throws UserException {
		logger.debug("[INICIO] - UserManagerServiceImpl - registerUser");
		
		UserValidator validator = new UserValidator();
		validator.validate(userData);
		
		logger.debug("\t parámetro de entrada: " + userData);
		
		new UserDataServiceHelper().registerUser(userData);
		
		logger.debug("[FINAL] - UserManagerServiceImpl - registerUser");
	}

	public void checkTrackingUser(UserData userData) throws UserException {
		logger.debug("[INICIO] - UserManagerServiceImpl - checkTrackingUser");
		
		synchronized (this) {
			
			if (userData != null && !userExist(userData.getSessionId())) {
				registerUser(userData);
			} 
			else {
				logger.debug("SOMETHING IS WRONG. The user sessions is not flagged as tracked but the session Id exists in the database");
			}
		}
		logger.debug("[FINAL] - UserManagerServiceImpl - checkTrackingUser");
	}

	public void registerNavigationData(UserData data) throws UserException {
		logger.debug("[INICIO] - UserManagerServiceImpl - registerNavigationData");
		
		UserValidator validator = new UserValidator();
		validator.validateNavigationData(data);
		
		logger.debug("\t parámetro de entrada: " + data);
		
		new UserDataServiceHelper().registerNavigationData(data);
		
		logger.debug("[FINAL] - UserManagerServiceImpl - registerNavigationData");
	}

}
