package by.oxagile.guardian.managers;

import by.oxagile.guardian.helpers.CallsHelper;
import by.oxagile.guardian.helpers.LoginHelper;
import by.oxagile.guardian.helpers.PatientHelper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class PatientManager {

    private AndroidDriver patientWD;
    private PatientHelper patientHelper;
    private LoginHelper loginHelper;
    private CallsHelper callsHelper;

    public AndroidDriver getPatientDriver() {
        if (patientWD == null) {
            File app = new File ("c:\\Users\\shukhovvg\\guardianAssist\\guardianAndroidTests\\src\\test\\resources\\guardianPatient-android.apk");

//            //SGS7 (work - #101313):
//            DesiredCapabilities capabilities = new DesiredCapabilities();
//            capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
//            capabilities.setCapability("deviceName", "ad0c1603a9064c6b0b");
//            capabilities.setCapability("platformVersion", "7.0");
//            capabilities.setCapability("platformName", "Android");
//            capabilities.setCapability("app", app.getAbsolutePath());
//            capabilities.setCapability("appPackage", "com.oxagile.GuardianAssist.PatientDev");
//            capabilities.setCapability("appActivity", "com.guardianassist.patient.login.LoginActivity");

            //SGS4 (my personal):
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
            capabilities.setCapability("newCommandTimeout", "200");
            capabilities.setCapability("deviceName", "4d00e7d3b654a039");
            capabilities.setCapability("platformVersion", "5.0.1");
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("app", app.getAbsolutePath());
            capabilities.setCapability("appPackage", "com.oxagile.GuardianAssist.PatientDev");
            capabilities.setCapability("appActivity", "com.guardianassist.patient.login.LoginActivity");

            AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder()
                    .usingAnyFreePort()
                    .withLogFile(new File("./src/test/resources/appiumLogs/appiumPatient_" + System.currentTimeMillis() + ".log"))
                    .withArgument(GeneralServerFlag.LOG_LEVEL, "error:debug")
                    .withArgument(GeneralServerFlag.LOG_TIMESTAMP)
                    .withArgument(GeneralServerFlag.LOCAL_TIMEZONE);
            AppiumDriverLocalService service = AppiumDriverLocalService.buildService(serviceBuilder);

            try {
//                old driver init variant (using "selenium server + nodes" architecture):
//                patientWD = new AndroidDriver(new URL("http://192.168.33.114:4444/wd/hub"),capabilities);
                patientWD = new AndroidDriver(service, capabilities);
                patientWD.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return patientWD;
    }

    public void stop() {
        if (patientWD != null) {
            patientWD.quit();
        }
    }

    public void resetApp() {
        if (patientWD != null) {
            patientWD.resetApp();
        }
    }

    public PatientHelper helper() {
        if (patientHelper == null) {
            patientHelper = new PatientHelper(this);
        }
        return patientHelper;
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
