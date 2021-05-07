package es.uniovi.hci.presentation.helper;

import es.uniovi.hci.business.exception.ComponentException;
import es.uniovi.hci.factories.ServiceFactory;
import es.uniovi.hci.model.ComponentData;

public class ComponentManagerServiceHelper {
	
	public void registry(ComponentData componentData) throws ComponentException {
		ServiceFactory.getComponentManagerService().registry(componentData);
	}

}
