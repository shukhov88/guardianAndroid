package by.oxagile.guardian.tests;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @Test
    public void allowPermissions() {
        carer.carerHelper().acceptPermissions();

        Assert.assertTrue(carer.carerHelper().isElementPresent(By.id("com.oxagile.GuardianAssist.PatientDev:id/phoneET")));
    }

    @Test
    public void notExistingUserLogin() {
        carer.carerHelper().acceptPermissions();
        carer.carerHelper().loginAs("6541344684314384");

        String expected = "Sorry, your phone is not registered. Please call Guardian Assist";
        String actual = carer.carerHelper().getText(By.id("com.oxagile.GuardianAssist.PatientDev:id/errorDialogText"));

        Assert.assertTrue(carer.carerHelper().isElementPresent(By.id("com.oxagile.GuardianAssist.PatientDev:id/errorDialogText")));
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void emptyLogin() {
        carer.carerHelper().acceptPermissions();
        carer.carerHelper().loginAs("");

        String expected = "Please enter your mobile \nnumber";
        String actual = carer.carerHelper().getText(By.id("com.oxagile.GuardianAssist.PatientDev:id/errorDialogText"));

        Assert.assertTrue(carer.carerHelper().isElementPresent(By.id("com.oxagile.GuardianAssist.PatientDev:id/errorDialogText")));
        Assert.assertEquals(actual,expected);
    }
}
