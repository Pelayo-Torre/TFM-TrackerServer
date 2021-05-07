package es.uniovi.hci.persistence.helper;

import es.uniovi.hci.factories.DataServiceFactory;

public class ExperimentDataServiceHelper {

	public String getStatus(Long idExperiment) {
		return DataServiceFactory.getExperimentDataService().getStatus(idExperiment);
	}
	
}
