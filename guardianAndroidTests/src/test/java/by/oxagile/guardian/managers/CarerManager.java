package by.oxagile.guardian.managers;

import by.oxagile.guardian.helpers.CallsHelper;
import by.oxagile.guardian.helpers.CarerHelper;
import by.oxagile.guardian.helpers.LoginHelper;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class CarerManager {

    private AndroidDriver carerWD;
    private CarerHelper carerHelper;
    private LoginHelper loginHelper;
    private CallsHelper callsHelper;

    public AndroidDriver getCarerDriver() {
        if (carerWD == null) {
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

            try {
                carerWD = new AndroidDriver(new URL("http://192.168.33.114:4444/wd/hub"),capabilities);
                carerWD.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return carerWD;
    }

    public void stop() {
        if (carerWD != null) {
            carerWD.quit();
        }
    }

    public void resetApp() {
        if (carerWD != null) {
            carerWD.resetApp();
        }
    }

    public CarerHelper helper() {
        if (carerHelper == null) {
            carerHelper = new CarerHelper(this);
        }
        return carerHelper;
    }

    public LoginHelper login() {
        if (loginHelper == null) {
            loginHelper = new LoginHelper(this);
        }
        return loginHelper;
    }

    public CallsHelper calls() {
        if (callsHelper == null) {
            callsHelper = new CallsHelper(this);
        }
        return callsHelper;
    }
}
