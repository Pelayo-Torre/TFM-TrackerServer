package service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	service.EventTest.class,
	service.ComponentTest.class,
	service.DemographicDataTest.class,
	service.UserTest.class
})
public class AllTest {
	
	

}