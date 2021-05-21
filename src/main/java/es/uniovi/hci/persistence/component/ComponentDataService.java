package es.uniovi.hci.persistence.component;

import es.uniovi.hci.model.ComponentData;

public interface ComponentDataService {
	
	public void registry(ComponentData componentData);

	public boolean existComponent(String sceneId, String componentId, String sessionId);

}
