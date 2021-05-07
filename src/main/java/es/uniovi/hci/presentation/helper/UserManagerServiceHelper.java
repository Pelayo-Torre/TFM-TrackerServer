package es.uniovi.hci.presentation.helper;

import es.uniovi.hci.business.exception.UserException;
import es.uniovi.hci.factories.ServiceFactory;
import es.uniovi.hci.model.UserData;

public class UserManagerServiceHelper {

	public boolean userExist(String sessionId) {
		return ServiceFactory.getUserManagerService().userExist(sessionId);
	}
	
	public void registerUser(UserData userData) throws UserException {
		ServiceFactory.getUserManagerService().registerUser(userData);
	}
	
	public void checkTrackingUser(UserData userData) throws UserException {
		ServiceFactory.getUserManagerService().checkTrackingUser(userData);
	}
	
	public void registerNavigationData(UserData data) throws UserException{
		ServiceFactory.getUserManagerService().registerNavigationData(data);
	}
	
}
