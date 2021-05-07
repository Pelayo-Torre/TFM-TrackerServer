package es.uniovi.hci.persistence.helper;

import es.uniovi.hci.factories.DataServiceFactory;
import es.uniovi.hci.model.EventsTransferObject;

public class TrackerDataServiceHelper {

	public void saveChunk(EventsTransferObject dto) {
		DataServiceFactory.getTrackerDataService().saveChunk(dto);
	}
	
	public EventsTransferObject getEventsBySessionAndScene(String sessionId, String sceneId) {
		return DataServiceFactory.getTrackerDataService().getEventsBySessionAndScene(sessionId, sceneId);
	}
	
}
