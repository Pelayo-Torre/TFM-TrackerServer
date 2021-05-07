package es.uniovi.hci.business.tracker;

import es.uniovi.hci.business.exception.TrackerException;
import es.uniovi.hci.model.EventsTransferObject;

public interface TrackerManagerService {
	
	public void saveChunk(EventsTransferObject dto) throws TrackerException;
	
	public EventsTransferObject getEventsBySessionAndScene(String sessionId, String sceneId) throws TrackerException;

}
