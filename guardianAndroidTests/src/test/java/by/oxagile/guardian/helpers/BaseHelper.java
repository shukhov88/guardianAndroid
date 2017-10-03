package by.oxagile.guardian.helpers;

import by.oxagile.guardian.managers.AssistManager;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class BaseHelper {

    protected AndroidDriver androidDriver;
    protected WebDriver webDriver;
    protected AssistManager assist;

    public BaseHelper(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }

    public BaseHelper(AssistManager assist) {
        this.assist = assist;
        this.webDriver = assist.getDriver();
    }

    protected void click(By locator) {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        //WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        WebElement element = webDriver.findElement(locator);
        wait.until(ExpectedConditions.visibilityOf(element));
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].click();", element);
    }

    protected void type(By locator, String text) {
        click(locator);
        if (text != null) {
            String existingTest = webDriver.findElement(locator).getAttribute("value");
            if (!text.equals(existingTest)) {
                webDriver.findElement(locator).clear();
                webDriver.findElement(locator).sendKeys(text);
            }
        }
    }

    //Not ready to use due to SMS code validation added by Uber. Need to implement code extract from SMS:
    /*public void signinToUber() {
        androidDriver.findElement(By.className("android.widget.EditText")).sendKeys("yuri.kuchko@oxagile.com");
        androidDriver.findElementByAccessibilityId("NEXT \uEA88").click();
        androidDriver.findElement(By.id("password")).sendKeys("qazQAZ");
        androidDriver.findElementByAccessibilityId("NEXT \uEA88").click();
        androidDriver.findElementByAccessibilityId("You as the developer, may grant your patient access to the following additional scope(s):").click();
        scrollDown();
        androidDriver.findElementByAccessibilityId("CONTINUE").click();
    }

    /*public void scrollDown() {
        Dimension scrSize = androidDriver.manage().window().getSize();
        int fromY = (int) (scrSize.height * 0.80);
        int toY = (int) (scrSize.height * 0.20);
        int fromX = (int) (scrSize.width * 0.20);
        int toX = (int) (scrSize.width * 0.20);

        androidDriver.swipe(fromX, fromY, toX, toY, 600);
    }*/

    public boolean isLocationDetecterPresent() {
        return androidDriver.findElements(By.id("com.oxagile.GuardianAssist.PatientDev:id/pkp_current_location")).size()==1;
    }

    public void acceptPermissions() {
        androidDriver.findElement(By.id("com.oxagile.GuardianAssist.PatientDev:id/loginPermissionsButton")).click();
        androidDriver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
        androidDriver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
        androidDriver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
        androidDriver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
    }

    public void loginAs(String mobilePhone) {
        androidDriver.findElement(By.id("com.oxagile.GuardianAssist.PatientDev:id/phoneET")).sendKeys(mobilePhone);
        androidDriver.findElement(By.id("com.oxagile.GuardianAssist.PatientDev:id/email_sign_in_button")).click();
    }

    public void skipUberSignin() throws InterruptedException {
        Thread.sleep(3000);
        androidDriver.pressKeyCode(AndroidKeyCode.BACK);
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

    public void makeCall(String contact) throws InterruptedException {
        List<MobileElement> contactsList = androidDriver.findElements(By.id("com.oxagile.GuardianAssist.PatientDev:id/cell_name"));
        for (int i = 0; i < contactsList.size(); i++) {
            if (contactsList.get(i).getText().equals(contact)) {
                contactsList.get(i).click();
            }
        }
        androidDriver.findElement(By.id("com.oxagile.GuardianAssist.PatientDev:id/start_call_btn")).click();
        //To implement wait in isIncomingCallScreenPresent() instead of Thread.sleep!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Thread.sleep(10000);
    }

    public boolean isIncomingCallScreenPresent() {
        return androidDriver.findElements(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget" +
                ".LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget" +
                ".FrameLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout[2]/android.widget.ImageView")).size() == 1;
    }

    public void acceptCall() {
        androidDriver.findElement(By.id("com.oxagile.GuardianAssist.PatientDev:id/ra_driver_status")).click();
    }

    public void declineCall() {
        androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget" +
                ".LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget" +
                ".FrameLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/android.widget.ImageView")).click();
    }

    public void leaveCall() {
        androidDriver.findElement(By.id("com.oxagile.GuardianAssist.PatientDev:id/on_call_end_btn")).click();
        androidDriver.resetApp();
    }

    public String getIncomingCallName() {
        return androidDriver.findElement(By.id("com.oxagile.GuardianAssist.PatientDev:id/incoming_call_name")).getText();
    }

    public String getOnCallName() {
        return androidDriver.findElement(By.id("com.oxagile.GuardianAssist.PatientDev:id/on_call_name")).getText();
    }

}
