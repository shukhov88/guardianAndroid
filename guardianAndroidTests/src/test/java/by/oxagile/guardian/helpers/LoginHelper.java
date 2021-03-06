package by.oxagile.guardian.helpers;

import by.oxagile.guardian.managers.CarerManager;
import by.oxagile.guardian.managers.PatientManager;
import by.oxagile.guardian.managers.Wait;
import io.appium.java_client.android.AndroidKeyCode;

public class LoginHelper extends BaseHelper {

    private static final LoginHelperLocators LOCATORS = new LoginHelperLocators();

    public LoginHelper(PatientManager patientManager) {
        super(patientManager);
    }

    public LoginHelper(CarerManager carerManager) {
        super(carerManager);
    }

    public void acceptPermissions() {
        tap(LOCATORS.proceedToPermissions);
        for (int i = 0; i < LOCATORS.permissionsQTY; i++) {
            tap(LOCATORS.allowPermission);
        }
    }

    public void as(String mobilePhone) {
        type(LOCATORS.loginFirstNameField, "1");
        logger.info("User " + mobilePhone + " entered first name at login screen");
        type(LOCATORS.loginLastNameField, "1");
        logger.info("User " + mobilePhone + " entered last name at login screen");
        type(LOCATORS.loginPhoneField, mobilePhone);
        logger.info("User " + mobilePhone + " entered mobile phone at login screen");
        tap(LOCATORS.loginNextButton);
        logger.info("User " + mobilePhone + " tapped 'next' button at login screen");
    }

    public void skipUberSignin() {
        waitForElementPresence(LOCATORS.uberLoginField, Wait.FOR_UBER_PAGE.getValue());
        logger.info("Uber sign in web view opened");
        androidDriver.pressKeyCode(AndroidKeyCode.BACK);
    }

    public void toAppAs(boolean permissions, String mobile) {
        if (permissions) {
            acceptPermissions();
            logger.info("User " + mobile + " accepted permissions");
        }
        as(mobile);
        skipUberSignin();
        logger.info("User " + mobile + " skipped Uber sign in");
    }

    //Not ready to use due to SMS code validation added by Uber. Need to implement code extract from SMS:
    /*public void signinToUber(String login, String password) {
        androidDriver.findElement(uberLoginField).sendKeys(login);
        androidDriver.findElementByAccessibilityId(uberNextButton).click();
        androidDriver.findElement(uberPassField).sendKeys(password);
        androidDriver.findElementByAccessibilityId(uberNextButton).click();
        androidDriver.findElement(uberOTP).sendKeys(getUberCode());
        androidDriver.findElementByAccessibilityId(uberVerifyButton).click();
    }

    public String getUberCode() {
        Activity sms = new Activity("appPackage", "appActivity");
        String activity = androidDriver.currentActivity();
        String packge = androidDriver.getCurrentPackage();
        Activity assist = new Activity(packge, activity);
        androidDriver.startActivity(sms);
        // code to fetch the CODE
        androidDriver.startActivity(assist);
        return "";
    }*/
}
