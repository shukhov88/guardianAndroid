package by.oxagile.guardian.appmanager;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    AndroidDriver wd;
    private FirstHelper firstHelper;

    public void init() throws IOException {

        File app = new File ("c:\\Users\\shukhovvg\\guardianAssist\\guardianAndroidTests\\src\\test\\resources\\guardianPatient-android.apk");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("deviceName", "My New Phone");
        capabilities.setCapability("platformVersion", "6.0.1");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.oxagile.GuardianAssist.PatientDev");
        capabilities.setCapability("appActivity", "com.guardianassist.patient.login.LoginActivity");

        wd = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        wd.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);

        firstHelper = new FirstHelper(wd);

    }

    public void stop() {
        wd.quit();
    }

    public FirstHelper firstHelper() {
        return firstHelper;
    }
}
