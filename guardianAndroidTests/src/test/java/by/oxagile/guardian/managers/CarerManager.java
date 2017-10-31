package by.oxagile.guardian.managers;

import by.oxagile.guardian.helpers.CallsHelper;
import by.oxagile.guardian.helpers.CarerHelper;
import by.oxagile.guardian.helpers.LoginHelper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
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

            //SGS7 (work - #101314):
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
            capabilities.setCapability("deviceName", "ce12160cbab93cae0c");
            capabilities.setCapability("platformVersion", "6.0.1");
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("app", app.getAbsolutePath());
            capabilities.setCapability("appPackage", "com.oxagile.GuardianAssist.PatientDev");
            capabilities.setCapability("appActivity", "com.guardianassist.patient.login.LoginActivity");

            AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder()
                    .usingAnyFreePort()
                    .withLogFile(new File("./src/test/resources/appiumLogs/appiumCarer.log"))
                    .withArgument(GeneralServerFlag.LOG_LEVEL, "error:debug")
                    .withArgument(GeneralServerFlag.LOG_TIMESTAMP)
                    .withArgument(GeneralServerFlag.LOCAL_TIMEZONE);
            AppiumDriverLocalService service = AppiumDriverLocalService.buildService(serviceBuilder);

            try {
//                old driver init variant (using 'selenium server + nodes' architecture):
//                carerWD = new AndroidDriver(new URL("http://192.168.33.114:4444/wd/hub"),capabilities);
                carerWD = new AndroidDriver(service, capabilities);
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
