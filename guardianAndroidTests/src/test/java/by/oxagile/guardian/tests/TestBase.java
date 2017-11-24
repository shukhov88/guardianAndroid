package by.oxagile.guardian.tests;

import by.oxagile.guardian.managers.AssistManager;
import by.oxagile.guardian.managers.CarerManager;
import by.oxagile.guardian.managers.PatientManager;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class TestBase {

    protected static final PatientManager patient = new PatientManager().withDevice("mine");

    protected static final CarerManager carer = new CarerManager().withDevice("101314");

    protected static final AssistManager assist = new AssistManager(System.getProperty("browser", BrowserType.CHROME));

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite
    public void setUp() throws Exception {
        assist.init();
    }

    @AfterSuite (alwaysRun = true)
    public void tearDown() {
        assist.stop();
        patient.stop();
        carer.stop();
    }

    @BeforeMethod
    public void resetAppAndLogTestStart(Method m) {
        patient.resetApp();
        carer.resetApp();
        logger.info("Start test " + m.getName());
    }

    @AfterMethod (alwaysRun = true)
    public void logTestStop(Method m) {
        logger.info("Stop test " + m.getName());
    }
}
