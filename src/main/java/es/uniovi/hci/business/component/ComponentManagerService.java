package es.uniovi.hci.business.component;

import es.uniovi.hci.business.exception.ComponentException;
import es.uniovi.hci.model.ComponentData;

public interface ComponentManagerService {
	
	public void registry(ComponentData componentData) throws ComponentException;

}
