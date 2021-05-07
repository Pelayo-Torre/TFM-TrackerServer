package es.uniovi.hci.presentation.helper;

import es.uniovi.hci.business.exception.TrackerException;
import es.uniovi.hci.factories.ServiceFactory;
import es.uniovi.hci.model.EventsTransferObject;

public class TrackerManagerServiceHelper {

	public void saveChunk(EventsTransferObject dto) throws TrackerException{
		ServiceFactory.getTrackerManagerService().saveChunk(dto);
	}
	
	public EventsTransferObject getEventsBySessionAndScene(String sessionId, String sceneId) throws TrackerException {
		return ServiceFactory.getTrackerManagerService().getEventsBySessionAndScene(sessionId, sceneId);
	}
	
}
