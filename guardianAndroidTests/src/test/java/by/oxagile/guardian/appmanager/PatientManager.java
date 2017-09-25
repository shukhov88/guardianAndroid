package by.oxagile.guardian.appmanager;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class PatientManager {

    private AndroidDriver patientWD;
    private PatientHelper patientHelper;

    public void init() throws IOException {

        File app = new File ("c:\\Users\\shukhovvg\\guardianAssist\\guardianAndroidTests\\src\\test\\resources\\guardianPatient-android.apk");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        //SGS4 (my personal)
        capabilities.setCapability("deviceName", "4d00e7d3b654a039");
        capabilities.setCapability("platformVersion", "5.0.1");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.oxagile.GuardianAssist.PatientDev");
        capabilities.setCapability("appActivity", "com.guardianassist.patient.login.LoginActivity");

        patientWD = new AndroidDriver(new URL("http://192.168.33.114:4444/wd/hub"),capabilities);
        patientWD.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);

    }

    public void test() throws InterruptedException {
        Thread.sleep(5000);
    }

    public void stop() {
        patientWD.quit();
    }

    public PatientHelper patientHelper() {
        if (patientHelper == null) {
            patientHelper = new PatientHelper(patientWD);
        }
        return patientHelper;
    }
}
