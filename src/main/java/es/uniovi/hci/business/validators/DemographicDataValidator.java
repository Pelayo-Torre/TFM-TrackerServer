package es.uniovi.hci.business.validators;

import org.apache.log4j.Logger;

import es.uniovi.hci.business.exception.DemographicDataException;
import es.uniovi.hci.model.DemographicData;

public class DemographicDataValidator {
	
	private final static Logger logger = Logger.getLogger(DemographicDataValidator.class);
	
	public void validate(DemographicData data) throws DemographicDataException{
		
		if(data == null) {
			logger.error("\t[ERROR] Objeto de entrada sin inicializar.");
			throw new DemographicDataException("Objeto de entrada sin inicializar");
		}
		else if(data.getId() == null) {
			logger.error("\t[ERROR] Identificador del dato nulo.");
			throw new DemographicDataException("Identificador del dato nulo");
		}
		else if(data.getSessionId() == null) {
			logger.error("\t[ERROR] SessionId nulo.");
			throw new DemographicDataException("SessionId nulo");
		}
		else if(data.getIdExperiment() == null) {
			logger.error("\t[ERROR] Experiment ID nulo.");
			throw new DemographicDataException("Experiment ID nulo");
		}
		else if(data.getDateValue() == null && data.getNumberValue() == null && data.getStringValue() == null) {
			logger.error("\t[ERROR] Valor del dato demográfico nulo.");
			throw new DemographicDataException("Valor del dato demográfico nulo");
		}
		else if(data.getDateValue() != null && (data.getNumberValue() != null || data.getStringValue() != null) ) {
			logger.error("\t[ERROR] Valor del dato demográfico con múltiples valores.");
			throw new DemographicDataException("Valor del dato demográfico con múltiples valores");
		}
		else if(data.getNumberValue() != null && (data.getDateValue() != null || data.getStringValue() != null) ) {
			logger.error("\t[ERROR] Valor del dato demográfico con múltiples valores.");
			throw new DemographicDataException("Valor del dato demográfico con múltiples valores");
		}
		else if(data.getStringValue() != null && (data.getNumberValue() != null || data.getDateValue() != null) ) {
			logger.error("\t[ERROR] Valor del dato demográfico con múltiples valores.");
			throw new DemographicDataException("Valor del dato demográfico con múltiples valores");
		}
		
	}

}
