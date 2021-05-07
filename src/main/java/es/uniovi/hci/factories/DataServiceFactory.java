package es.uniovi.hci.factories;

import es.uniovi.hci.persistence.component.ComponentDataService;
import es.uniovi.hci.persistence.component.ComponentDataServiceDAO;
import es.uniovi.hci.persistence.demographicdata.DemographicDataService;
import es.uniovi.hci.persistence.demographicdata.DemographicDataServiceDAO;
import es.uniovi.hci.persistence.experiment.ExperimentDataService;
import es.uniovi.hci.persistence.experiment.ExperimentDataServiceDAO;
import es.uniovi.hci.persistence.tracker.TrackerDataService;
import es.uniovi.hci.persistence.tracker.TrackerDataServiceDAO;
import es.uniovi.hci.persistence.user.UserDataService;
import es.uniovi.hci.persistence.user.UserDataServiceDAO;

public class DataServiceFactory {

	public static ComponentDataService getComponentDataService() {
		return new ComponentDataServiceDAO();
	}
	
	public static UserDataService getUserDataService() {
		return new UserDataServiceDAO();
	}
	
	public static TrackerDataService getTrackerDataService() {
		return new TrackerDataServiceDAO();
	}
	
	public static DemographicDataService getDemographicDataService() {
		return new DemographicDataServiceDAO();
	}

	public static ExperimentDataService getExperimentDataService() {
		return new ExperimentDataServiceDAO();
	}
}
