package by.oxagile.guardian.helpers;


import by.oxagile.guardian.managers.CarerManager;
import by.oxagile.guardian.managers.PatientManager;
import io.appium.java_client.MobileElement;
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
        List<MobileElement> contactsList = androidDriver.findElements(LOCATORS.contactCell);
        for (int i = 0; i < contactsList.size(); i++) {
            if (contactsList.get(i).getText().equals(contact)) {
                contactsList.get(i).click();
            }
        }
        androidDriver.findElement(LOCATORS.startCallButton).click();
    }

    public boolean isIncomingCallScreenPresent() {
        WebDriverWait wait = new WebDriverWait(androidDriver, 15);
        wait.until(ExpectedConditions.presenceOfElementLocated(LOCATORS.incomingCallScreen));
        return androidDriver.findElements(LOCATORS.incomingCallScreen).size() == 1;
    }

    public void accept() {
        WebDriverWait wait = new WebDriverWait(androidDriver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(LOCATORS.acceptCallButton));
        androidDriver.findElement(LOCATORS.acceptCallButton).click();
    }

    public void decline() {
        androidDriver.findElement(LOCATORS.declineCallButton).click();
    }

    public void leave() {
        androidDriver.findElement(LOCATORS.ebdCallButton).click();
        androidDriver.resetApp();
    }

    public String getCallerName() {
        return androidDriver.findElement(LOCATORS.callerName).getText();
    }

    public String getOnCallName() {
        return androidDriver.findElement(LOCATORS.onCallName).getText();
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
}
