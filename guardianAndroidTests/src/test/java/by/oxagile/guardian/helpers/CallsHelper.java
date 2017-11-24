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
import org.testng.Assert;

import java.util.List;
import java.util.NoSuchElementException;

public class CallsHelper extends BaseHelper {

    private static final CallsHelperLocators LOCATORS = new CallsHelperLocators();

    public CallsHelper(CarerManager carerManager) {
        super(carerManager);
        logger.debug("Carer's CallsHelper initiated");
    }

    public CallsHelper(PatientManager patientManager) {
        super(patientManager);
        logger.debug("Patient's CallsHelper initiated");
    }

    public void dialTo(String contact) {
        if (contact.equals("ASSIST")) {
            tap(LOCATORS.assistCallButton);
            logger.info("Patient dialed to ASSIST");
        } else {
            List<MobileElement> contactsList = androidDriver.findElements(LOCATORS.contactCell);
            if (contactsList.size() == 0) {
                logger.error("User doesn't have contacts");
                Assert.fail("User doesn't have contacts");
            } else {
                boolean isContactPresent = false;
                for (int i = 0; i < contactsList.size(); i++) {
                    if (contactsList.get(i).getText().equals(contact)) {
                        isContactPresent = true;
                        contactsList.get(i).click();
                        logger.info(contact + " is found and selected in contacts list of user");
                        break;
                    }
                }
                if (!isContactPresent) {
                    logger.error("User doesn't have contact with name = " + contact);
                    Assert.fail("User doesn't have contact with name = " + contact);
                }
            }
            tap(LOCATORS.startCallButton);
            logger.info("User started dialing to " + contact);
        }
        waitForElementPresence(LOCATORS.cameraSwitchButton, Wait.FOR_SESSION_CONNECTION.getValue());
    }

    public void accept() {
        waitForElementPresence(LOCATORS.acceptCallButton, Wait.FOR_INCOMING_CALL.getValue());
        String callerName = getCallerName();
        logger.info("User received incoming call from: " + callerName);
        tap(LOCATORS.acceptCallButton);
        logger.info("User accepted incoming call from: " + callerName);
        waitForElementPresence(LOCATORS.onCallTopLeftVideoFrame, Wait.FOR_CALL_SET_UP.getValue());
        logger.info("Call with " + callerName + " has set up");
    }

    public void decline() {
        waitForElementPresence(LOCATORS.acceptCallButton, Wait.FOR_INCOMING_CALL.getValue());
        String callerName = getCallerName();
        logger.info("User received incoming call from: " + callerName);
        tap(LOCATORS.declineCallButton);
        logger.info("User declined incoming call from: " + callerName);
    }

    public void leave() {
        String callerName = getOnCallName();
        tap(LOCATORS.endCallButton);
        logger.info("User left the call with " + callerName);
    }

    // Next 2 methods may be used to log call initiator name:
    public String getCallerName() {
        String caller = getText(LOCATORS.callerName);
        logger.info("Caller name detected (from incoming call screen): " + caller);
        return caller;
    }

    public String getOnCallName() {
        String caller = getText(LOCATORS.onCallName);
        logger.info("Caller name detected (from on call screen): " + caller);
        return caller;
    }

    public void tapInviteButton() {
        tap(LOCATORS.inviteThirdPartyButton);
        logger.info("User tapped invite 3rd party button");
    }

    public void inviteThirdParty(String contact) {

        tap(LOCATORS.inviteThirdPartyButton);
        logger.info("User tapped invite 3rd party button");
        List<MobileElement> contactsList = androidDriver.findElements(LOCATORS.contactCell);
        if (contactsList.size() == 0) {
            logger.error("User doesn't have contacts to add as 3rd party");
        } else {
            boolean isContactPresent = false;
            for (int i = 0; i < contactsList.size(); i++) {
                if (contactsList.get(i).getText().equals(contact)) {
                    isContactPresent = true;
                    contactsList.get(i).click();
                    logger.info(contact + " is found and selected in 'add 3rd party' contacts list of user");
                    break;
                }
            }
            if (!isContactPresent) {
                logger.error("User doesn't have contact with name = " + contact + "at add 3rd party contacts list");
                Assert.fail("User doesn't have contact with name = " + contact + "at add 3rd party contacts list");
            }
        }
        tap(LOCATORS.inviteToCallButton);
        logger.info(contact + " is invited to call as 3rd party by User");
    }

    public void stopInviting() {
        tap(LOCATORS.stopInviting);
        logger.info("User stopped inviting 3rd party to call");
    }

    public String getCentralVideoStreamID() {
        waitForElementPresence(LOCATORS.onCallCentralVideoFrame, 5);
        logger.debug("central video frame detected");
        String id = getContentDescriptionValue(LOCATORS.onCallCentralVideoFrame);
        logger.debug("ID of user that streaming to central video frame extracted");
        return id;
    }

    public String getTopLeftVideoStreamID() {
        waitForElementPresence(LOCATORS.onCallTopLeftVideoFrame, 5);
        logger.debug("top left video frame detected");
        String id = getContentDescriptionValue(LOCATORS.onCallTopLeftVideoFrame);
        logger.debug("ID of user that streaming to top left video frame extracted");
        return id;
    }

    public String getTopRightVideoStreamID() {
        waitForElementPresence(LOCATORS.onCallTopRightVideoFrame, 5);
        logger.debug("top right video frame detected");
        String id = getContentDescriptionValue(LOCATORS.onCallTopRightVideoFrame);
        logger.debug("ID of user that streaming to top right video frame extracted");
        return id;
    }

    public void stopDialing() {
        tap(LOCATORS.stopDialingButton);
        logger.info("User stopped dialing");
    }

    public void timeOut() {
        waitForElementPresence(LOCATORS.acceptCallButton, Wait.FOR_INCOMING_CALL.getValue());
        logger.info("User received incoming call");
        waitForElementPresence(LOCATORS.startCallButton, Wait.FOR_CALL_TO_TIMEOUT.getValue());
        logger.info("User time outed incoming call");
    }
}
