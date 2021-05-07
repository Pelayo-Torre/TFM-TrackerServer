package es.uniovi.hci.persistence.tracker;

import es.uniovi.hci.model.EventsTransferObject;

public interface TrackerDataService {
	
	public void saveChunk(EventsTransferObject dto);
	
	public EventsTransferObject getEventsBySessionAndScene(String sessionId, String sceneId);

}
