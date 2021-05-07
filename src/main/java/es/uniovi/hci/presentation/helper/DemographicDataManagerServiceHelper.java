package es.uniovi.hci.presentation.helper;

import es.uniovi.hci.business.exception.DemographicDataException;
import es.uniovi.hci.factories.ServiceFactory;
import es.uniovi.hci.model.DemographicData;

public class DemographicDataManagerServiceHelper {
	
	public void register(DemographicData dd) throws DemographicDataException{
		ServiceFactory.getDemographicDataManagerService().register(dd);
	}

}
