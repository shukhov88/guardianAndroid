package by.oxagile.guardian.helpers;

import by.oxagile.guardian.managers.CarerManager;
import by.oxagile.guardian.managers.PatientManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.By;

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

    public void skipUberSignin() throws InterruptedException {
        Thread.sleep(3000);
        androidDriver.pressKeyCode(AndroidKeyCode.BACK);
    }

}
