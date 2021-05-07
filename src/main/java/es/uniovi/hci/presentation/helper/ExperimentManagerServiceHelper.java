package es.uniovi.hci.presentation.helper;

import es.uniovi.hci.business.exception.ExperimentException;
import es.uniovi.hci.factories.ServiceFactory;

public class ExperimentManagerServiceHelper {
	
	public String getStatus(Long idExperiment) throws ExperimentException{
		return ServiceFactory.getExperimentManagerService().getStatus(idExperiment);
	}

}
