package es.uniovi.hci.business.validators;

import org.apache.log4j.Logger;

import es.uniovi.hci.business.exception.ComponentException;
import es.uniovi.hci.model.ComponentData;

public class ComponentValidator {
	
	private final static Logger logger = Logger.getLogger(ComponentValidator.class);
	
	public void validate(ComponentData componentData) throws ComponentException {
		
		if(componentData == null) {
			logger.error("\t[ERROR] Objeto de entrada sin inicializar.");
			throw new ComponentException("Objeto de entrada sin inicializar");
		}
		else if(componentData.getSessionId() == null) {
			logger.error("\t[ERROR] SessionID nulo.");
			throw new ComponentException("SessionID nulo");
		}
		else if(componentData.getSceneId() == null) {
			logger.error("\t[ERROR] SceneID nulo.");
			throw new ComponentException("SceneID nulo");
		}
		else if(componentData.getComponentId() == null) {
			logger.error("\t[ERROR] ComponentID nulo.");
			throw new ComponentException("ComponentID nulo");
		}
		else if(componentData.getTimeStamp() == null) {
			logger.error("\t[ERROR] TimeStamp nulo.");
			throw new ComponentException("TimeStamp nulo");
		}
		else if(componentData.getX() == null) {
			logger.error("\t[ERROR] X nulo.");
			throw new ComponentException("X nulo");
		}
		else if(componentData.getxF() == null) {
			logger.error("\t[ERROR] xF nulo.");
			throw new ComponentException("xF nulo");
		}
		else if(componentData.getY() == null) {
			logger.error("\t[ERROR] Y nulo.");
			throw new ComponentException("Y nulo");
		}
		else if(componentData.getyF() == null) {
			logger.error("\t[ERROR] yF nulo.");
			throw new ComponentException("yF nulo");
		}
		
	}

}
