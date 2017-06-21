package by.oxagile.guardian.appmanager;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;


/**
 * Created by Sony on 20.06.2017.
 */
public class FirstHelper extends BaseHelper {

    public FirstHelper(AndroidDriver wd) {
        super(wd);
    }



    public void login() throws InterruptedException {

        wd.findElement(By.id("com.oxagile.GuardianAssist.PatientDev:id/loginPermissionsButton")).click();
        wd.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
        wd.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
        wd.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
        wd.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();

        wd.findElement(By.id("com.oxagile.GuardianAssist.PatientDev:id/phoneET")).sendKeys("1234568");
        wd.findElement(By.id("com.oxagile.GuardianAssist.PatientDev:id/email_sign_in_button")).click();

        wd.findElement(By.className("android.widget.EditText")).sendKeys("yuri.kuchko@oxagile.com");
        wd.findElementByAccessibilityId("NEXT \uEA88").click();
        wd.findElement(By.id("password")).sendKeys("qazQAZ");
        wd.findElementByAccessibilityId("NEXT \uEA88").click();

        wd.findElementByAccessibilityId("You as the developer, may grant your app access to the following additional scope(s):").click();
        scrollDown();
        wd.findElementByAccessibilityId("CONTINUE").click();

        wd.findElement(By.id("com.oxagile.GuardianAssist.PatientDev:id/navigation_taxi")).click();
        wd.findElement(By.id("com.oxagile.GuardianAssist.PatientDev:id/taxi_take_me_home_button")).click();
    }

    public boolean isLocationDetecterPresent() {
        return wd.findElements(By.id("com.oxagile.GuardianAssist.PatientDev:id/pkp_current_location")).size()==1;
    }

    public void scrollDown() {
        Dimension scrSize = wd.manage().window().getSize();
        int fromY = (int) (scrSize.height * 0.80);
        int toY = (int) (scrSize.height * 0.20);
        int fromX = (int) (scrSize.width * 0.20);
        int toX = (int) (scrSize.width * 0.20);
        wd.swipe(fromX, fromY, toX, toY, 600);

    }
}
