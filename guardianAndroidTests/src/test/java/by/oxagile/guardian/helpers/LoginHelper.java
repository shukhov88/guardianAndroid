package by.oxagile.guardian.helpers;

import by.oxagile.guardian.managers.CarerManager;
import by.oxagile.guardian.managers.PatientManager;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginHelper extends BaseHelper {

    public LoginHelper(PatientManager patientManager) {
        super(patientManager);
    }

    public LoginHelper(CarerManager carerManager) {
        super(carerManager);
    }

    public void acceptPermissions() {
        androidDriver.findElement(By.id("com.oxagile.GuardianAssist.PatientDev:id/loginPermissionsButton")).click();
        androidDriver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
        androidDriver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
        androidDriver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
        androidDriver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
    }

    public void as(String mobilePhone) {
        androidDriver.findElement(By.id("com.oxagile.GuardianAssist.PatientDev:id/phoneET")).sendKeys(mobilePhone);
        androidDriver.findElement(By.id("com.oxagile.GuardianAssist.PatientDev:id/email_sign_in_button")).click();
    }

    //Not ready to use due to SMS code validation added by Uber. Need to implement code extract from SMS:
    public void signinToUber(String login, String password) {
        androidDriver.findElement(By.id("useridInput")).sendKeys(login);
        androidDriver.findElementByAccessibilityId("NEXT \uEA88").click();
        androidDriver.findElement(By.id("password")).sendKeys(password);
        androidDriver.findElementByAccessibilityId("NEXT \uEA88").click();
        androidDriver.findElement(By.id("verificationCode")).sendKeys(getUberCode());
        androidDriver.findElementByAccessibilityId("VERIFY \uEA88").click();
    }

    public String getUberCode() {
        Activity sms = new Activity("appPackage", "appActivity");
        String activity = androidDriver.currentActivity();
        String packge = androidDriver.getCurrentPackage();
        Activity assist = new Activity(packge, activity);
        androidDriver.startActivity(sms);
        // code to fetch the CODE
        androidDriver.startActivity(assist);
        return "";
    }

    public void skipUberSignin() {
        WebDriverWait wait = new WebDriverWait(androidDriver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("useridInput")));
        androidDriver.pressKeyCode(AndroidKeyCode.BACK);
    }

}
