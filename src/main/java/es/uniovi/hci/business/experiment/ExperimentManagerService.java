package es.uniovi.hci.business.experiment;

import es.uniovi.hci.business.exception.ExperimentException;

public interface ExperimentManagerService {
	
	public String getStatus(Long idExperiment) throws ExperimentException;

}
