package es.uniovi.hci.business.demographicdata;

import org.apache.log4j.Logger;

import es.uniovi.hci.business.exception.DemographicDataException;
import es.uniovi.hci.business.validators.DemographicDataValidator;
import es.uniovi.hci.model.DemographicData;
import es.uniovi.hci.model.DemographicDataType;
import es.uniovi.hci.persistence.helper.DemographicDataServiceHelper;

public class DemographicDataManagerServiceImpl implements DemographicDataManagerService{
	
	private final static Logger logger = Logger.getLogger(DemographicDataManagerServiceImpl.class);

	public void register(DemographicData dd) throws DemographicDataException {
		logger.debug("[INICIO] - DemographicDataManagerServiceImpl - register");
		
		DemographicDataValidator validator = new DemographicDataValidator();
		validator.validate(dd);
		
		logger.debug("\tParámetros de entrada: " + dd);
		
		DemographicDataServiceHelper helper = new DemographicDataServiceHelper();
		
		//Se obtiene el dato demográfico registrado en bbdd
		DemographicData dd2 = helper.getDemographicDataByIdAndIdExperiment(dd.getId(), dd.getIdExperiment());
		logger.debug("\tDato demográfico obtenido: " + dd2);
		
		if(dd2 == null || dd2.getType() == null) {
			logger.error("\t[ERROR] El dato demográfico obtenido a partir del id y del idExperiment es nulo o no tiene tipo: " + dd2);
			throw new DemographicDataException("El dato demográfico obtenido a partir del id y del idExperiment es nulo o no tiene tipo: " + dd2);
		}
		else {
			if(dd2.getType().equals(DemographicDataType.STRING.name()) && dd.getStringValue() != null) {
				helper.registerString(dd);
			}
			else if(dd2.getType().equals(DemographicDataType.DATE.name()) && dd.getDateValue() != null) {
				helper.registerDate(dd);
			}
			else if(dd2.getType().equals(DemographicDataType.NUMBER.name()) && dd.getNumberValue() != null){
				helper.registerNumber(dd);
			}
			else {
				logger.error("\t[ERROR] El dato demográfico obtenido no tiene el tipo acorde al de bbdd");
				throw new DemographicDataException("El dato demográfico obtenido no tiene el tipo acorde al de bbdd");
			}
		}
		
		logger.debug("[FINAL] - DemographicDataManagerServiceImpl - register");
	}

}
