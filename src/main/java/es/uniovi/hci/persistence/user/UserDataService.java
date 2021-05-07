package es.uniovi.hci.persistence.user;

import es.uniovi.hci.model.UserData;

public interface UserDataService {

	public boolean userExist(String sessionId);
	
	public void registerUser(UserData userData);
	
	public void registerNavigationData(UserData data);
}
