package es.uniovi.hci.factories;

import es.uniovi.hci.business.component.ComponentManagerService;
import es.uniovi.hci.business.component.ComponentManagerServiceImpl;
import es.uniovi.hci.business.demographicdata.DemographicDataManagerService;
import es.uniovi.hci.business.demographicdata.DemographicDataManagerServiceImpl;
import es.uniovi.hci.business.experiment.ExperimentManagerService;
import es.uniovi.hci.business.experiment.ExperimentManagerServiceImpl;
import es.uniovi.hci.business.tracker.TrackerManagerService;
import es.uniovi.hci.business.tracker.TrackerManagerServiceImpl;
import es.uniovi.hci.business.user.UserManagerService;
import es.uniovi.hci.business.user.UserManagerServiceImpl;

public class ServiceFactory {
	
	public static ComponentManagerService getComponentManagerService() {
		return new ComponentManagerServiceImpl();
	}
	
	public static UserManagerService getUserManagerService() {
		return new UserManagerServiceImpl();
	}
	
	public static TrackerManagerService getTrackerManagerService() {
		return new TrackerManagerServiceImpl();
	}
	
	public static DemographicDataManagerService getDemographicDataManagerService() {
		return new DemographicDataManagerServiceImpl();
	}
	
	public static ExperimentManagerService getExperimentManagerService() {
		return new ExperimentManagerServiceImpl();
	}

}
