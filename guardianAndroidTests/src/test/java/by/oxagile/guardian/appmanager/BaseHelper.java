package by.oxagile.guardian.appmanager;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;


public class BaseHelper {

    protected AndroidDriver wd;

    public BaseHelper(AndroidDriver wd) {
        this.wd = wd;
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        click(locator);
        if (text != null) {
            String existingTest = wd.findElement(locator).getAttribute("value");
            if (!text.equals(existingTest)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    //Not ready to use due to SMS code validation added by Uber. Need to implement code extract from SMS:
    public void signinToUber() {
        wd.findElement(By.className("android.widget.EditText")).sendKeys("yuri.kuchko@oxagile.com");
        wd.findElementByAccessibilityId("NEXT \uEA88").click();
        wd.findElement(By.id("password")).sendKeys("qazQAZ");
        wd.findElementByAccessibilityId("NEXT \uEA88").click();
        wd.findElementByAccessibilityId("You as the developer, may grant your patient access to the following additional scope(s):").click();
        scrollDown();
        wd.findElementByAccessibilityId("CONTINUE").click();
    }

    public void scrollDown() {
        Dimension scrSize = wd.manage().window().getSize();
        int fromY = (int) (scrSize.height * 0.80);
        int toY = (int) (scrSize.height * 0.20);
        int fromX = (int) (scrSize.width * 0.20);
        int toX = (int) (scrSize.width * 0.20);
        wd.swipe(fromX, fromY, toX, toY, 600);
    }

    public boolean isLocationDetecterPresent() {
        return wd.findElements(By.id("com.oxagile.GuardianAssist.PatientDev:id/pkp_current_location")).size()==1;
    }

    public void acceptPermissions() {
        wd.findElement(By.id("com.oxagile.GuardianAssist.PatientDev:id/loginPermissionsButton")).click();
        wd.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
        wd.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
        wd.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
        wd.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
    }

    public void loginAs(String mobilePhone) {
        wd.findElement(By.id("com.oxagile.GuardianAssist.PatientDev:id/phoneET")).sendKeys(mobilePhone);
        wd.findElement(By.id("com.oxagile.GuardianAssist.PatientDev:id/email_sign_in_button")).click();
    }

    public void skipUberSignin() throws InterruptedException {
        Thread.sleep(3000);
        wd.pressKeyCode(AndroidKeyCode.BACK);
    }

    public void login(String mobile) throws InterruptedException {
        acceptPermissions();
        loginAs(mobile);
        skipUberSignin();
    }

    public void loginWithoutPermissions(String mobile) throws InterruptedException {
        loginAs(mobile);
        skipUberSignin();
    }

    public boolean isIncomingCallScreenPresent() {
        return wd.findElements(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget" +
                ".LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget" +
                ".FrameLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout[2]/android.widget.ImageView")).size() == 1;
    }

}
