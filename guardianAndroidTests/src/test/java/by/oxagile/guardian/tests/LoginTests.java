package by.oxagile.guardian.tests;

import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginTests extends TestBase {

    @Test
    public void testLogin() throws InterruptedException {
        app.firstHelper().login();

        Assert.assertTrue(app.firstHelper().isLocationDetecterPresent());
    }
}
