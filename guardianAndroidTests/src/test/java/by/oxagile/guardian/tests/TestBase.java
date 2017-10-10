package by.oxagile.guardian.tests;

import by.oxagile.guardian.managers.AssistManager;
import by.oxagile.guardian.managers.CarerManager;
import by.oxagile.guardian.managers.PatientManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class TestBase {

    protected static final PatientManager patient = new PatientManager();

    protected static final CarerManager carer = new CarerManager();

    protected static final AssistManager assist = new AssistManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        patient.init();
        carer.init();
        assist.init();
    }

    @AfterSuite
    public void tearDown() {
        patient.stop();
        carer.stop();
        assist.stop();
    }

    @BeforeMethod
    public void resetApp() {
        patient.resetApp();
        carer.resetApp();
    }

}
