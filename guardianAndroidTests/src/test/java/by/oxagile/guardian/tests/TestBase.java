package by.oxagile.guardian.tests;

import by.oxagile.guardian.appmanager.CarerManager;
import by.oxagile.guardian.appmanager.PatientManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {

    protected static final PatientManager patient = new PatientManager();

    protected static final CarerManager carer = new CarerManager();

    @BeforeSuite
    public void setUp() throws Exception {
        patient.init();
        carer.init();
    }

    @AfterSuite
    public void tearDown() {
        patient.stop();
        carer.stop();
    }

}
