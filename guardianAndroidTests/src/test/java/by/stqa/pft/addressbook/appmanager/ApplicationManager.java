package by.stqa.pft.addressbook.appmanager;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    WebDriver wd;
    private FirstHelper firstHelper;

    public void init() throws IOException {

        File application = new File("c:\\Users\\Sony\\guardianAssist\\guardianAndroidTests\\src\\test\\resources\\notepad.apk");
        DesiredCapabilities capabilities= new DesiredCapabilities();
        capabilities.setCapability("browserName", "");
        capabilities.setCapability("deviceName", "SGS IV");
        capabilities.setCapability("platformVersion", "5.0.1");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("app", application.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.example.simplenotepad");
        capabilities.setCapability("appActivity", "com.example.simplenotepad.MainActivity");
        wd = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);

        wd.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        firstHelper = new FirstHelper(wd);

    }

    public void stop() {
        wd.quit();
    }

    public FirstHelper firstHelper() {
        return firstHelper;
    }
}
