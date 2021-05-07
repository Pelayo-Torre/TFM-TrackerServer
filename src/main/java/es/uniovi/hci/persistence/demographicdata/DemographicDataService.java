package es.uniovi.hci.persistence.demographicdata;

import es.uniovi.hci.model.DemographicData;

public interface DemographicDataService {
	
	public void registerString(DemographicData dd);
	
	public void registerNumber(DemographicData dd);
	
	public void registerDate(DemographicData dd);

	public DemographicData getDemographicDataByIdAndIdExperiment(Long id, Long idExperiment);

}
