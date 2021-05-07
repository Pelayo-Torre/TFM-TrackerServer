package es.uniovi.hci.business.experiment;

import org.apache.log4j.Logger;

import es.uniovi.hci.business.exception.ExperimentException;
import es.uniovi.hci.persistence.helper.ExperimentDataServiceHelper;

public class ExperimentManagerServiceImpl implements ExperimentManagerService{
	
	private final static Logger logger = Logger.getLogger(ExperimentManagerServiceImpl.class);

	public String getStatus(Long idExperiment) throws ExperimentException {
		logger.debug("[INICIO] - ExperimentManagerServiceImpl - getStatus");

		if(idExperiment == null) {
			logger.error("[ERROR] - getStatus - El identificador del experimento especificado es null");
			throw new ExperimentException("El identificador del experimento especificado es null");
		}
		
		String status = new ExperimentDataServiceHelper().getStatus(idExperiment);
		logger.debug("\t El estado del experimento " + idExperiment + " es " + status);
		
		logger.debug("[FINAL] - ExperimentManagerServiceImpl - getStatus");
		return status;
	}

}
