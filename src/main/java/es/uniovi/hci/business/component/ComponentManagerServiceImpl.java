package es.uniovi.hci.business.component;

import org.apache.log4j.Logger;

import es.uniovi.hci.business.exception.ComponentException;
import es.uniovi.hci.business.validators.ComponentValidator;
import es.uniovi.hci.model.ComponentData;
import es.uniovi.hci.persistence.helper.ComponentDataServiceHelper;

public class ComponentManagerServiceImpl implements ComponentManagerService{
	
	private final static Logger logger = Logger.getLogger(ComponentManagerServiceImpl.class);

	public void registry(ComponentData componentData) throws ComponentException {
		logger.debug("[INICIO] - ComponentManagerServiceImpl.");
				
		logger.debug("\t Validación datos del componente");
		
		ComponentValidator validator = new ComponentValidator();
		validator.validate(componentData);
		
		ComponentDataServiceHelper helper = new ComponentDataServiceHelper();
		
		if(helper.existComponent(componentData.getSceneId(), componentData.getComponentId(), componentData.getSessionId())) {
			logger.error("\t[ERROR] El componente " + componentData.getComponentId() + " en la escena " + componentData.getSceneId() + 
					" para el usuario " + componentData.getSessionId() + " ya se encuentra registrado");
			throw new ComponentException("El componente " + componentData.getComponentId() + " en la escena " + componentData.getSceneId() + 
					" para el usuario " + componentData.getSessionId() + " ya se encuentra registrado");
		}
		
//		UserDataServiceHelper userHelper = new UserDataServiceHelper();
//		if(!userHelper.userExist(componentData.getSessionId())) {
//			logger.error("\t[ERROR] El usuario: " + componentData.getSessionId() + " no se encuentra registrado en el sistema");
//			throw new ComponentException("El usuario: " + componentData.getSessionId() + " no se encuentra registrado en el sistema");
//		}
		
		logger.debug("\t Registro de datos en la base de datos");
		
		helper.registry(componentData);
		
		logger.debug("[FINAL] - ComponentManagerServiceImpl.");
	}

}
