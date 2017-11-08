package by.oxagile.guardian.helpers;


import by.oxagile.guardian.managers.CarerManager;
import by.oxagile.guardian.managers.PatientManager;
import by.oxagile.guardian.managers.Wait;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CallsHelper extends BaseHelper {

    private static final CallsHelperLocators LOCATORS = new CallsHelperLocators();

    Logger logger = LoggerFactory.getLogger(CallsHelper.class);

    public CallsHelper(CarerManager carerManager) {
        super(carerManager);
    }

    public CallsHelper(PatientManager patientManager) {
        super(patientManager);
    }

    public void dialTo(String contact) {
        if (contact.equals("ASSIST")) {
            androidDriver.findElement(LOCATORS.assistCallButton).click();
            logger.info("Patient dial to ASSIST");
        } else {
            List<MobileElement> contactsList = androidDriver.findElements(LOCATORS.contactCell);
            for (int i = 0; i < contactsList.size(); i++) {
                if (contactsList.get(i).getText().equals(contact)) {
                    contactsList.get(i).click();
                }
            }
            androidDriver.findElement(LOCATORS.startCallButton).click();
            logger.info("User dial to " + contact);
        }
        waitForElementPresence(LOCATORS.cameraSwitchButton, Wait.FOR_SESSION_CONNECTION.getValue());
    }

    public void accept() {
        waitForElementPresence(LOCATORS.acceptCallButton, Wait.FOR_INCOMING_CALL.getValue());
        androidDriver.findElement(LOCATORS.acceptCallButton).click();
        waitForElementPresence(LOCATORS.onCallTopLeftVideoFrame, Wait.FOR_CALL_SET_UP.getValue());
    }

    public void decline() {
        waitForElementPresence(LOCATORS.acceptCallButton, Wait.FOR_INCOMING_CALL.getValue());
        androidDriver.findElement(LOCATORS.declineCallButton).click();
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
        androidDriver.findElement(LOCATORS.stopDialingButton).click();
    }

    public void timeOut() {
        waitForElementPresence(LOCATORS.acceptCallButton, Wait.FOR_INCOMING_CALL.getValue());
        waitForElementPresence(LOCATORS.startCallButton, Wait.FOR_CALL_TO_TIMEOUT.getValue());
    }
}
