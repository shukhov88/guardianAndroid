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

public class CallsHelper extends BaseHelper implements Thread.UncaughtExceptionHandler {

    private static final CallsHelperLocators LOCATORS = new CallsHelperLocators();

    //Logger logger = LoggerFactory.getLogger(CallsHelper.class);

    public CallsHelper(CarerManager carerManager) {
        super(carerManager);
        logger.info("Carer's CallsHelper initiated");
    }

    public CallsHelper(PatientManager patientManager) {
        super(patientManager);
        logger.info("Patient's CallsHelper initiated");
    }

    public void dialTo(String contact) {
        if (contact.equals("ASSIST")) {
            androidDriver.findElement(LOCATORS.assistCallButton).click();
            logger.info("Patient dialed to ASSIST");
        } else {
            List<MobileElement> contactsList = androidDriver.findElements(LOCATORS.contactCell);
            for (int i = 0; i < contactsList.size(); i++) {
                if (contactsList.get(i).getText().equals(contact)) {
                    contactsList.get(i).click();
                    logger.info(contact + " is found and selected in contacts list of user");
                }
            }
            androidDriver.findElement(LOCATORS.startCallButton).click();
            logger.info("User dialed to " + contact);
            //trying to identify who is calling Carer or Patient:
            System.out.println();
        }
        waitForElementPresence(LOCATORS.cameraSwitchButton, Wait.FOR_SESSION_CONNECTION.getValue());
    }

    public void accept() {
        waitForElementPresence(LOCATORS.acceptCallButton, Wait.FOR_INCOMING_CALL.getValue());
        String callerName = getCallerName();
        logger.info("User received incoming call from: " + callerName);
        androidDriver.findElement(LOCATORS.acceptCallButton).click();
        logger.info("User accepted incoming call from: " + callerName);
        waitForElementPresence(LOCATORS.onCallTopLeftVideoFrame, Wait.FOR_CALL_SET_UP.getValue());
        logger.info("Call with " + callerName + " has set up");
    }

    public void decline() {
        waitForElementPresence(LOCATORS.acceptCallButton, Wait.FOR_INCOMING_CALL.getValue());
        String callerName = getCallerName();
        logger.info("User received incoming call from: " + callerName);
        androidDriver.findElement(LOCATORS.declineCallButton).click();
        logger.info("User declined incoming call from: " + callerName);
    }

    public void leave() {
        String callerName = getOnCallName();
        androidDriver.findElement(LOCATORS.endCallButton).click();
        logger.info("User left the call with " + callerName);
    }

    // Next 2 methods may be used to log call initiator name:
    public String getCallerName() {
        String caller = androidDriver.findElement(LOCATORS.callerName).getText();
        logger.info("Caller name detected (from incoming call screen): " + caller);
        return caller;
    }

    public String getOnCallName() {
        String caller = androidDriver.findElement(LOCATORS.onCallName).getText();
        logger.info("Caller name detected (from on call screen): " + caller);
        return caller;
    }

    public void tapInviteButton() {
        androidDriver.findElement(LOCATORS.inviteThirdPartyButton).click();
        logger.info("User tapped invite 3rd party button");
    }

    public void inviteThirdParty(String contact) {

        androidDriver.findElement(LOCATORS.inviteThirdPartyButton).click();
        logger.info("User tapped invite 3rd party button");
        List<MobileElement> contactsList = androidDriver.findElements(LOCATORS.contactCell);
        for (int i = 0; i < contactsList.size(); i++) {
            if (contactsList.get(i).getText().equals(contact)) {
                contactsList.get(i).click();
                logger.info(contact + " is found and selected in 'add 3rd party' contacts list of user");
            }
        }
        androidDriver.findElement(LOCATORS.inviteToCallButton).click();
        logger.info(contact + " is invited to call as 3rd party by User");
    }

    public void stopInviting() {
        androidDriver.findElement(LOCATORS.stopInviting).click();
        logger.info("User stopped inviting 3rd party to call");
    }

    public String getCentralVideoStreamID() {
        waitForElementPresence(LOCATORS.onCallCentralVideoFrame, 5);
        logger.info("central video frame detected");
        String id = androidDriver.findElement(LOCATORS.onCallCentralVideoFrame).getAttribute("contentDescription");
        logger.info("ID of user that streaming to central video frame extracted");
        return id;
    }

    public String getTopLeftVideoStreamID() {
        waitForElementPresence(LOCATORS.onCallTopLeftVideoFrame, 5);
        logger.info("top left video frame detected");
        String id = androidDriver.findElement(LOCATORS.onCallTopLeftVideoFrame).getAttribute("contentDescription");
        logger.info("ID of user that streaming to top left video frame extracted");
        return id;
    }

    public String getTopRightVideoStreamID() {
        waitForElementPresence(LOCATORS.onCallTopRightVideoFrame, 5);
        logger.info("top right video frame detected");
        String id = androidDriver.findElement(LOCATORS.onCallTopRightVideoFrame).getAttribute("contentDescription");
        logger.info("ID of user that streaming to top right video frame extracted");
        return id;
    }

    public void stopDialing() {
        androidDriver.findElement(LOCATORS.stopDialingButton).click();
        logger.info("User stopped dialing");
    }

    public void timeOut() {
        waitForElementPresence(LOCATORS.acceptCallButton, Wait.FOR_INCOMING_CALL.getValue());
        logger.info("User received incoming call");
        waitForElementPresence(LOCATORS.startCallButton, Wait.FOR_CALL_TO_TIMEOUT.getValue());
        logger.info("User time outed incoming call");
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        logger.error("Uncaught exception", e);
    }
    Thread.
}
