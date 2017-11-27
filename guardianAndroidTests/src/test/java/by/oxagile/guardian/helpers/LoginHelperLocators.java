package by.oxagile.guardian.helpers;


import org.openqa.selenium.By;

public class LoginHelperLocators {

    By allowPermission = By.id("com.android.packageinstaller:id/permission_allow_button");
    By proceedToPermissions = By.id("com.oxagile.GuardianAssist.PatientDev:id/loginPermissionsButton");
    By loginFirstNameField = By.id("com.oxagile.GuardianAssist.PatientDev:id/login_edit_f_name");
    By loginLastNameField = By.id("com.oxagile.GuardianAssist.PatientDev:id/login_edit_l_name");
    By loginPhoneField = By.id("com.oxagile.GuardianAssist.PatientDev:id/login_edit_mobile");
    By loginNextButton = By.id("com.oxagile.GuardianAssist.PatientDev:id/email_sign_in_button");
    By uberLoginField = By.id("useridInput");
    By uberPassField = By.id("password");
    By uberOTP = By.id("verificationCode");
    String uberNextButton = "NEXT \uEA88";
    String uberVerifyButton = "VERIFY \uEA88";
    int permissionsQTY = 5;
}
