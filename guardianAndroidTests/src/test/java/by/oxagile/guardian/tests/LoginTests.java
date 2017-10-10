package by.oxagile.guardian.tests;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;



public class LoginTests extends TestBase {

    @Test
    public void allowPermissions() {
        carer.login().acceptPermissions();

        Assert.assertTrue(carer.helper().isElementPresent(By.id("com.oxagile.GuardianAssist.PatientDev:id/phoneET")));
    }

    @Test
    public void notExistingUserLogin() {
        carer.login().acceptPermissions();
        carer.login().as("0536987456");

        String expected = "Sorry, your phone is not registered. Please call Guardian Assist";
        String actual = carer.helper().getText(By.id("com.oxagile.GuardianAssist.PatientDev:id/errorDialogText"));

        Assert.assertTrue(carer.helper().isElementPresent(By.id("com.oxagile.GuardianAssist.PatientDev:id/errorDialogText")));
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void emptyLogin() {
        carer.login().acceptPermissions();
        carer.login().as("");

        String expected = "Please enter your mobile \nnumber";
        String actual = carer.helper().getText(By.id("com.oxagile.GuardianAssist.PatientDev:id/errorDialogText"));

        Assert.assertTrue(carer.helper().isElementPresent(By.id("com.oxagile.GuardianAssist.PatientDev:id/errorDialogText")));
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void validPatientLogin() {
        carer.login().acceptPermissions();
        carer.login().as("1111");

        Assert.assertTrue(patient.helper().isElementPresent(By.id("useridInput")));
    }

    @Test
    public void validCarerLogin() {
        carer.login().acceptPermissions();
        carer.login().as("1234571");

        Assert.assertTrue(patient.helper().isElementPresent(By.id("useridInput")));
    }
}
