package by.oxagile.guardian.managers;

import by.oxagile.guardian.helpers.CarerHelper;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class CarerManager {

    private AndroidDriver carerWD;
    private CarerHelper carerHelper;

    public void init() throws IOException {

        File app = new File ("c:\\Users\\shukhovvg\\guardianAssist\\guardianAndroidTests\\src\\test\\resources\\guardianPatient-android.apk");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        //SGS7 (work - #101314)
        capabilities.setCapability("deviceName", "ce12160cbab93cae0c");
        capabilities.setCapability("platformVersion", "6.0.1");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.oxagile.GuardianAssist.PatientDev");
        capabilities.setCapability("appActivity", "com.guardianassist.patient.login.LoginActivity");

        carerWD = new AndroidDriver(new URL("http://192.168.33.114:4444/wd/hub"),capabilities);
        carerWD.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);

    }

    public void test() throws InterruptedException {
        Thread.sleep(5000);
    }

    public void stop() {
        carerWD.quit();
    }

    public CarerHelper carerHelper() {
        if (carerHelper == null) {
            carerHelper = new CarerHelper(carerWD);
        }
        return carerHelper;
    }
}
