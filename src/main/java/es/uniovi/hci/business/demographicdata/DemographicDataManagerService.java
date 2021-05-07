package es.uniovi.hci.business.demographicdata;

import es.uniovi.hci.business.exception.DemographicDataException;
import es.uniovi.hci.model.DemographicData;

public interface DemographicDataManagerService {
	
	public void register(DemographicData dd) throws DemographicDataException;

}
