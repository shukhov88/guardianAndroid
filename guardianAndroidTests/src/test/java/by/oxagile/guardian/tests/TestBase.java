package by.oxagile.guardian.tests;

import by.oxagile.guardian.appmanager.ApplicationManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {

    protected static final ApplicationManager app =
            new ApplicationManager();

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }

}
