package by.oxagile.guardian.helpers;


import by.oxagile.guardian.managers.CarerManager;
import by.oxagile.guardian.managers.PatientManager;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CalsHelper extends BaseHelper {

    public CalsHelper(CarerManager carerManager) {
        super(carerManager);
    }

    public CalsHelper(PatientManager patientManager) {
        super(patientManager);
    }

    public void dialTo(String contact) {
        List<MobileElement> contactsList = androidDriver.findElements(By.id("com.oxagile.GuardianAssist.PatientDev:id/cell_name"));
        for (int i = 0; i < contactsList.size(); i++) {
            if (contactsList.get(i).getText().equals(contact)) {
                contactsList.get(i).click();
            }
        }
        androidDriver.findElement(By.id("com.oxagile.GuardianAssist.PatientDev:id/start_call_btn")).click();
    }

    public boolean isIncomingCallScreenPresent() {
        WebDriverWait wait = new WebDriverWait(androidDriver, 15);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget" +
                ".LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget" +
                ".FrameLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout[2]/android.widget.ImageView")));
        return androidDriver.findElements(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget" +
                ".LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget" +
                ".FrameLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout[2]/android.widget.ImageView")).size() == 1;
    }

    public void accept() {
        WebDriverWait wait = new WebDriverWait(androidDriver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.oxagile.GuardianAssist.PatientDev:id/ra_driver_status")));
        androidDriver.findElement(By.id("com.oxagile.GuardianAssist.PatientDev:id/ra_driver_status")).click();
    }

    public void decline() {
        androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget" +
                ".LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget" +
                ".FrameLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/android.widget.ImageView")).click();
    }

    public void leave() {
        androidDriver.findElement(By.id("com.oxagile.GuardianAssist.PatientDev:id/on_call_end_btn")).click();
        androidDriver.resetApp();
    }

    public String getIncomingCallName() {
        return androidDriver.findElement(By.id("com.oxagile.GuardianAssist.PatientDev:id/incoming_call_name")).getText();
    }

    public String getOnCallName() {
        return androidDriver.findElement(By.id("com.oxagile.GuardianAssist.PatientDev:id/on_call_name")).getText();
    }

    public void inviteThirdParty(String contact) {
        androidDriver.findElement(By.id("com.oxagile.GuardianAssist.PatientDev:id/on_call_invite_btn")).click();
        List<MobileElement> contactsList = androidDriver.findElements(By.id("com.oxagile.GuardianAssist.PatientDev:id/cell_name"));
        for (int i = 0; i < contactsList.size(); i++) {
            if (contactsList.get(i).getText().equals(contact)) {
                contactsList.get(i).click();
            }
        }
        androidDriver.findElement(By.id("com.oxagile.GuardianAssist.PatientDev:id/add_to_call_btn")).click();
    }

    public void stopInviting() {
        androidDriver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='com.oxagile.GuardianAssist.PatientDev:id/on_call_kick_btn']/android.widget.ImageView")).click();
    }
}
