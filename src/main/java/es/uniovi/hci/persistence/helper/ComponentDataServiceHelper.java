package es.uniovi.hci.persistence.helper;

import es.uniovi.hci.factories.DataServiceFactory;
import es.uniovi.hci.model.ComponentData;

public class ComponentDataServiceHelper {
	
	public void registry(ComponentData componentData) {
		DataServiceFactory.getComponentDataService().registry(componentData);
	}

}
