package by.oxagile.guardian.appmanager;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CarerHelper extends BaseHelper {

    public CarerHelper(AndroidDriver wd) {
        super(wd);
    }

    public void makeCall(String contact) throws InterruptedException {
        List<MobileElement> contactsList = wd.findElements(By.id("com.oxagile.GuardianAssist.PatientDev:id/cell_name"));
        for (int i = 0; i < contactsList.size(); i++) {
            if (contactsList.get(i).getText().equals(contact)) {
                contactsList.get(i).click();
            }
        }
        wd.findElement(By.id("com.oxagile.GuardianAssist.PatientDev:id/start_call_btn")).click();
        Thread.sleep(10000);
    }


}
