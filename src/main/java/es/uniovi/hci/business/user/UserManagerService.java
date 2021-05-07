package es.uniovi.hci.business.user;

import es.uniovi.hci.business.exception.UserException;
import es.uniovi.hci.model.UserData;

public interface UserManagerService {
	
	public boolean userExist(String sessionId);
	
	public void registerUser(UserData userData) throws UserException;
	
	public void checkTrackingUser(UserData userData) throws UserException;
	
	public void registerNavigationData(UserData data) throws UserException;

}
