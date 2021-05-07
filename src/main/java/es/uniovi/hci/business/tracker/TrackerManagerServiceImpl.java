package es.uniovi.hci.business.tracker;


import org.apache.log4j.Logger;

import es.uniovi.hci.business.exception.TrackerException;
import es.uniovi.hci.business.validators.TrackerValidator;
import es.uniovi.hci.model.EventsTransferObject;
import es.uniovi.hci.persistence.helper.TrackerDataServiceHelper;

public class TrackerManagerServiceImpl implements TrackerManagerService{
	
	private final static Logger logger = Logger.getLogger(TrackerManagerServiceImpl.class);

	public void saveChunk(EventsTransferObject dto) throws TrackerException {
		logger.debug("[INICIO] - TrackerManagerServiceImpl - saveChunk");
		
		TrackerValidator validator = new TrackerValidator();
		validator.validate(dto);
		
		logger.debug("\tParámetros de entrada: " + dto);
		
		new TrackerDataServiceHelper().saveChunk(dto);
		
		logger.debug("[FINAL] - TrackerManagerServiceImpl - saveChunk");
	}

	public EventsTransferObject getEventsBySessionAndScene(String sessionId, String sceneId) throws TrackerException {
		logger.debug("[INICIO] - TrackerManagerServiceImpl - getEventsBySessionAndScene");
		
		TrackerValidator validator = new TrackerValidator();
		validator.validateGetEvents(sessionId, sceneId);
		
		logger.debug("\tParámetros de entrada: SessionId -> " + sessionId + "  SceneId -> " + sceneId);
		
		logger.debug("[FINAL] - TrackerManagerServiceImpl - getEventsBySessionAndScene");
		return null;
	}

}
