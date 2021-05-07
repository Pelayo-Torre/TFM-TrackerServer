package es.uniovi.hci.business.validators;

import org.apache.log4j.Logger;

import es.uniovi.hci.business.exception.UserException;
import es.uniovi.hci.model.UserData;

public class UserValidator {
	
	private final static Logger logger = Logger.getLogger(UserValidator.class);
	
	public void validate(UserData userData) throws UserException {
		
		if(userData == null) {
			logger.error("\t[ERROR] Objeto de entrada sin inicializar.");
			throw new UserException("Objeto de entrada sin inicializar");
		}
		else if(userData.getSessionId() == null) {
			logger.error("\t[ERROR] SessionId nulo.");
			throw new UserException("SessionId nulo");
		}
		else if(userData.getTimeStamp() == null) {
			logger.error("\t[ERROR] TimeStamp nulo.");
			throw new UserException("TimeStamp nulo");
		}
		else if(userData.getLocale() == null) {
			logger.error("\t[ERROR] Locale nulo.");
			throw new UserException("Locale nulo");	
		}
		else if(userData.getRemoteAddress() == null) {
			logger.error("\t[ERROR] RemoteAddress nulo.");
			throw new UserException("RemoteAddress nulo");
		}
		else if(userData.getRemoteHost() == null) {
			logger.error("\t[ERROR] RemoteHost nulo.");
			throw new UserException("RemoteHost nulo");
		}
		else if(userData.getRemotePort() == null) {
			logger.error("\t[ERROR] RemotePort nulo.");
			throw new UserException("RemotePort nulo");
		}
		else if(userData.getTimezone() == null) {
			logger.error("\t[ERROR] Timezone nulo.");
			throw new UserException("Timezone nulo");
		}
		else if(userData.getIdExperiment() == null) {
			logger.error("\t[ERROR] IdExperimento nulo.");
			throw new UserException("IdExperimento nulo");
		}
		
	}

	public void validateNavigationData(UserData data) throws UserException {
		
		if(data == null) {
			logger.error("\t[ERROR] Objeto de entrada sin inicializar.");
			throw new UserException("Objeto de entrada sin inicializar");
		}
		else if(data.getSessionId() == null) {
			logger.error("\t[ERROR] SessionId nulo.");
			throw new UserException("SessionId nulo.");
		}
		
	}

}
