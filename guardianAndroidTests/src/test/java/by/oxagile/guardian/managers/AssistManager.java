package by.oxagile.guardian.managers;

import by.oxagile.guardian.helpers.*;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class AssistManager {

    private final Properties properties;
    private WebDriver wd;
    private String browser;
    private MongoHelper mongoHelper;
    private AssistHelper tokBox;

    public AssistManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File("src/test/resources/" + target + ".properties")));
    }

    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }

    public HttpSession newHttpSession() {
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public WebDriver getDriver() {

        if (wd == null) {
            if (browser.equals(BrowserType.FIREFOX)) {
                wd = new FirefoxDriver();
            } else if (browser.equals(BrowserType.CHROME)) {
                /*String path = "c:\\Users\\shukhovvg\\AppData\\Local\\Google\\Chrome\\User Data";
                ChromeOptions options = new ChromeOptions();
                options.addArguments("user-data-dir=" + path);
                options.addArguments("--start-maximized");*/
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--use-fake-device-for-media-stream");
                wd = new ChromeDriver();
            } else if (browser.equals(BrowserType.IE)) {
                wd = new InternetExplorerDriver();
            }
            wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            wd.manage().window().maximize();
            wd.get(properties.getProperty("web.TokBoxUrl"));
        }
        return wd;
    }

    public MongoHelper mongoDB() {
        if (mongoHelper == null) {
            mongoHelper = new MongoHelper();
        }
        return mongoHelper;
    }

    public AssistHelper tokBox() {
        if (tokBox == null) {
            tokBox = new AssistHelper(this);
        }
        return tokBox;
    }

}
