package by.oxagile.guardian.helpers;


import by.oxagile.guardian.managers.CarerManager;
import by.oxagile.guardian.managers.PatientManager;
import by.oxagile.guardian.managers.Wait;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CallsHelper extends BaseHelper {

    private static final CallsHelperLocators LOCATORS = new CallsHelperLocators();

    public CallsHelper(CarerManager carerManager) {
        super(carerManager);
    }

    public CallsHelper(PatientManager patientManager) {
        super(patientManager);
    }

    public void dialTo(String contact) {
        if (contact.equals("ASSIST")) {
            androidDriver.findElement(LOCATORS.assistCallButton).click();
        } else {
            List<MobileElement> contactsList = androidDriver.findElements(LOCATORS.contactCell);
            for (int i = 0; i < contactsList.size(); i++) {
                if (contactsList.get(i).getText().equals(contact)) {
                    contactsList.get(i).click();
                }
            }
            androidDriver.findElement(LOCATORS.startCallButton).click();
        }
        setImplicitlyWait(0);
        waitForElementPresence(LOCATORS.cameraSwitchButton, Wait.FOR_SESSION_CONNECTION.getValue());
        setImplicitlyWait(10);
    }

    public void accept() {
        setImplicitlyWait(0);
        waitForElementPresence(LOCATORS.acceptCallButton, Wait.FOR_INCOMING_CALL.getValue());
        androidDriver.findElement(LOCATORS.acceptCallButton).click();
        waitForElementPresence(LOCATORS.onCallTopLeftVideoFrame, Wait.FOR_CALL_SET_UP.getValue());
        setImplicitlyWait(10);
    }

    public void decline() {
        setImplicitlyWait(0);
        waitForElementPresence(LOCATORS.acceptCallButton, Wait.FOR_INCOMING_CALL.getValue());
        androidDriver.findElement(LOCATORS.declineCallButton).click();
        setImplicitlyWait(10);
    }

    public void leave() {
        androidDriver.findElement(LOCATORS.endCallButton).click();
    }

    public String getCallerName() {
        return androidDriver.findElement(LOCATORS.callerName).getText();
    }

    public String getOnCallName() {
        return androidDriver.findElement(LOCATORS.onCallName).getText();
    }

    public void tapInviteButton() {
        androidDriver.findElement(LOCATORS.inviteThirdPartyButton).click();
    }

    public void inviteThirdParty(String contact) {

        androidDriver.findElement(LOCATORS.inviteThirdPartyButton).click();
        List<MobileElement> contactsList = androidDriver.findElements(LOCATORS.contactCell);
        for (int i = 0; i < contactsList.size(); i++) {
            if (contactsList.get(i).getText().equals(contact)) {
                contactsList.get(i).click();
            }
        }
        androidDriver.findElement(LOCATORS.inviteToCallButton).click();
    }

    public void stopInviting() {
        androidDriver.findElement(LOCATORS.stopInviting).click();
    }

    public String getCentralVideoStreamID() {
        waitForElementPresence(LOCATORS.onCallCentralVideoFrame, 5);
        return androidDriver.findElement(LOCATORS.onCallCentralVideoFrame).getAttribute("contentDescription");
    }

    public String getTopLeftVideoStreamID() {
        waitForElementPresence(LOCATORS.onCallTopLeftVideoFrame, 5);
        return androidDriver.findElement(LOCATORS.onCallTopLeftVideoFrame).getAttribute("contentDescription");
    }

    public String getTopRightVideoStreamID() {
        waitForElementPresence(LOCATORS.onCallTopRightVideoFrame, 5);
        return androidDriver.findElement(LOCATORS.onCallTopRightVideoFrame).getAttribute("contentDescription");
    }

    public void stopDialing() {
        androidDriver.findElement(LOCATORS.endCallButton).click();
    }

    public void timeOut() {
        WebDriverWait wait = new WebDriverWait(androidDriver, Wait.FOR_CALL_TO_TIMEOUT.getValue());
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("com.oxagile.GuardianAssist.PatientDev:id/ra_driver_status")));
    }
}
