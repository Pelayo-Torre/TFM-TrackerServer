package es.uniovi.hci.business.validators;

import org.apache.log4j.Logger;

import es.uniovi.hci.business.exception.TrackerException;
import es.uniovi.hci.model.EventItem;
import es.uniovi.hci.model.EventsTransferObject;

public class TrackerValidator {
	
	private final static Logger logger = Logger.getLogger(TrackerValidator.class);
	
	public void validate(EventsTransferObject dto) throws TrackerException {
		
		if(dto == null) {
			logger.error("\t[ERROR] Objeto sin inicializar.");
			throw new TrackerException("Objeto sin inicializar.");
		}
		else if(dto.getSessionId() == null) {
			logger.error("\t[ERROR] SessionId nulo.");
			throw new TrackerException("SessionId nulo");
		}
		else if(dto.getTimezone() == null) {
			logger.error("\t[ERROR] Timezone nulo.");
			throw new TrackerException("Timezone nulo");
		}
		else if(dto.getList() == null) {
			logger.error("\t[ERROR] Lista de eventos sin inicializar.");
			throw new TrackerException("Lista de eventos sin inicializar");
		}
		else if(dto.getList().size() == 0) {
			logger.error("\t[ERROR] Lista de eventos vacía.");
			throw new TrackerException("Lista de eventos vacía");
		}
		
		for(EventItem evento : dto.getList()) {
			if(evento == null) {
				logger.error("\t[ERROR] Evento sin inicializar.");
				throw new TrackerException("Evento sin inicializar");
			}
			else if(evento.getSceneId() == null) {
				logger.error("\t[ERROR] SceneID nulo.");
				throw new TrackerException("SceneID nulo");
			}
			else if(evento.getTimeStamp() == null) {
				logger.error("\t[ERROR] TimeStamp nulo.");
				throw new TrackerException("TimeStamp nulo");
			}
			else if(evento.getElementId() == null) {
				logger.error("\t[ERROR] ElementId nulo.");
				throw new TrackerException("ElementId nulo");
			}
			else if(evento.getEventType() == null) {
				logger.error("\t[ERROR] tipo evento nulo.");
				throw new TrackerException("tipo evento nulo");
			}
			else if(evento.getX() == null) {
				logger.error("\t[ERROR] X nulo.");
				throw new TrackerException("X nulo");
			}
			else if(evento.getY() == null) {
				logger.error("\t[ERROR] Y nulo.");
				throw new TrackerException("Y nulo");
			}
		}
		
	}

	public void validateGetEvents(String sessionId, String sceneId) throws TrackerException{

		if(sessionId == null) {
			logger.error("[\tERROR] SessionID nulo");
			throw new TrackerException("SessionID nulo");
		}
		else if(sceneId == null) {
			logger.error("\t[ERROR] SceneID nulo.");
			throw new TrackerException("SceneID nulo");
		}
		
	}

}
