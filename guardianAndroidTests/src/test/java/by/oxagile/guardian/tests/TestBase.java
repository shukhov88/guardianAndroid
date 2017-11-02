package by.oxagile.guardian.tests;

import by.oxagile.guardian.managers.AssistManager;
import by.oxagile.guardian.managers.CarerManager;
import by.oxagile.guardian.managers.PatientManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class TestBase {

    protected static final PatientManager patient = new PatientManager();

    protected static final CarerManager carer = new CarerManager();

    protected static final AssistManager assist = new AssistManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        assist.init();
    }

    @AfterSuite
    public void tearDown() {
        assist.stop();
        patient.stop();
        carer.stop();
    }

    @BeforeMethod
    public void resetApp() {
        patient.resetApp();
        carer.resetApp();
    }
}
