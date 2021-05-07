package es.uniovi.hci.persistence.helper;

import es.uniovi.hci.factories.DataServiceFactory;
import es.uniovi.hci.model.UserData;

public class UserDataServiceHelper {

	public boolean userExist(String sessionId) {
		return DataServiceFactory.getUserDataService().userExist(sessionId);
	}
	
	public void registerUser(UserData userData) {
		DataServiceFactory.getUserDataService().registerUser(userData);
	}
	
	public void registerNavigationData(UserData data) {
		DataServiceFactory.getUserDataService().registerNavigationData(data);
	}
}
