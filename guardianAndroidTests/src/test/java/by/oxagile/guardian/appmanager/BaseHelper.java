package by.oxagile.guardian.appmanager;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;


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

    public void makeCall(String contact) throws InterruptedException {
        List<MobileElement> contactsList = wd.findElements(By.id("com.oxagile.GuardianAssist.PatientDev:id/cell_name"));
        for (int i = 0; i < contactsList.size(); i++) {
            if (contactsList.get(i).getText().equals(contact)) {
                contactsList.get(i).click();
            }
        }
        wd.findElement(By.id("com.oxagile.GuardianAssist.PatientDev:id/start_call_btn")).click();
        //To implement wait in isIncomingCallScreenPresent() instead of Thread.sleep!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Thread.sleep(10000);
    }

    public boolean isIncomingCallScreenPresent() {
        return wd.findElements(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget" +
                ".LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget" +
                ".FrameLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout[2]/android.widget.ImageView")).size() == 1;
    }

    public void acceptCall() {
        wd.findElement(By.id("com.oxagile.GuardianAssist.PatientDev:id/ra_driver_status")).click();
    }

    public void declineCall() {
        wd.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget" +
                ".LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget" +
                ".FrameLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/android.widget.ImageView")).click();
    }

    public void leaveCall() {
        wd.findElement(By.id("com.oxagile.GuardianAssist.PatientDev:id/on_call_end_btn")).click();
        wd.resetApp();
    }

    public String getIncomingCallName() {
        return wd.findElement(By.id("com.oxagile.GuardianAssist.PatientDev:id/incoming_call_name")).getText();
    }

    public String getOnCallName() {
        return wd.findElement(By.id("com.oxagile.GuardianAssist.PatientDev:id/on_call_name")).getText();
    }

}
