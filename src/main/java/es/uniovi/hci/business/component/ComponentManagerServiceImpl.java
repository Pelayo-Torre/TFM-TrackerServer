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
		
		logger.debug("\t Registro de datos en la base de datos");
		
		new ComponentDataServiceHelper().registry(componentData);
		
		logger.debug("[FINAL] - ComponentManagerServiceImpl.");
	}

}
