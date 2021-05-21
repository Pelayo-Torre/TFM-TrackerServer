package es.uniovi.hci.business.demographicdata;

import org.apache.log4j.Logger;

import es.uniovi.hci.business.exception.DemographicDataException;
import es.uniovi.hci.business.validators.DemographicDataValidator;
import es.uniovi.hci.model.DemographicData;
import es.uniovi.hci.model.DemographicDataType;
import es.uniovi.hci.persistence.helper.DemographicDataServiceHelper;
import es.uniovi.hci.persistence.helper.UserDataServiceHelper;

public class DemographicDataManagerServiceImpl implements DemographicDataManagerService{
	
	private final static Logger logger = Logger.getLogger(DemographicDataManagerServiceImpl.class);

	public void register(DemographicData dd) throws DemographicDataException {
		logger.debug("[INICIO] - DemographicDataManagerServiceImpl - register");
		
		DemographicDataValidator validator = new DemographicDataValidator();
		validator.validate(dd);
		
		logger.debug("\tParámetros de entrada: " + dd);
		
		DemographicDataServiceHelper helper = new DemographicDataServiceHelper();
		UserDataServiceHelper userHelper = new UserDataServiceHelper();
		
		//Se obtiene el usuario para comprobar su existencia
		if(!userHelper.userExist(dd.getSessionId())) {
			logger.error("\t[ERROR] El usuario: " + dd.getSessionId() + " no se encuentra registrado en el sistema");
			throw new DemographicDataException("El usuario " + dd.getSessionId() + " no se encuentra registrado en el sistema");
		}
		
		//Se obtiene el dato demográfico registrado en bbdd
		DemographicData dd2 = helper.getDemographicDataByIdAndIdExperiment(dd.getId(), dd.getIdExperiment());
		logger.debug("\tDato demográfico obtenido: " + dd2);
		
		if(dd2 == null || dd2.getType() == null) {
			logger.error("\t[ERROR] El dato demográfico obtenido a partir del id y del idExperiment es nulo o no tiene tipo: " + dd2);
			throw new DemographicDataException("El dato demográfico obtenido a partir del id y del idExperiment es nulo o no tiene tipo: " + dd2);
		}
		else {
			if(dd2.getType().equals(DemographicDataType.STRING.name()) && dd.getStringValue() != null) {
				if(helper.existDemographicDataString(dd.getId(), dd.getSessionId())) {
					logger.error("\t[ERROR] El dato demográfico STRING con ID " + dd.getId() + " y USUARIO " + dd.getSessionId() + " ya está registrado");
					throw new DemographicDataException("El dato demográfico STRING con ID " + dd.getId() + " y USUARIO " + dd.getSessionId() + " ya está registrado");
				}
				helper.registerString(dd);
			}
			else if(dd2.getType().equals(DemographicDataType.DATE.name()) && dd.getDateValue() != null) {
				if(helper.existDemographicDataDate(dd.getId(), dd.getSessionId())) {
					logger.error("\t[ERROR] El dato demográfico DATE con ID " + dd.getId() + " y USUARIO " + dd.getSessionId() + " ya está registrado");
					throw new DemographicDataException("El dato demográfico DATE con ID " + dd.getId() + " y USUARIO " + dd.getSessionId() + " ya está registrado");
				}
				helper.registerDate(dd);
			}
			else if(dd2.getType().equals(DemographicDataType.NUMBER.name()) && dd.getNumberValue() != null){
				if(helper.existDemographicDataNumber(dd.getId(), dd.getSessionId())) {
					logger.error("\t[ERROR] El dato demográfico NUMBER con ID " + dd.getId() + " y USUARIO " + dd.getSessionId() + " ya está registrado");
					throw new DemographicDataException("El dato demográfico NUMBER con ID " + dd.getId() + " y USUARIO " + dd.getSessionId() + " ya está registrado");
				}
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
