package es.uniovi.hci.persistence.helper;

import es.uniovi.hci.factories.DataServiceFactory;
import es.uniovi.hci.model.DemographicData;

public class DemographicDataServiceHelper {

	public void registerString(DemographicData dd) {
		DataServiceFactory.getDemographicDataService().registerString(dd);
	}
	
	public void registerNumber(DemographicData dd) {
		DataServiceFactory.getDemographicDataService().registerNumber(dd);
	}
	
	public void registerDate(DemographicData dd) {
		DataServiceFactory.getDemographicDataService().registerDate(dd);
	}

	public DemographicData getDemographicDataByIdAndIdExperiment(Long id, Long idExperiment) {
		return DataServiceFactory.getDemographicDataService().getDemographicDataByIdAndIdExperiment(id, idExperiment);
	}
	
}
