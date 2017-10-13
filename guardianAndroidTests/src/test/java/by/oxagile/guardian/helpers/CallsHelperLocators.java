package by.oxagile.guardian.helpers;


import org.openqa.selenium.By;

public class CallsHelperLocators {

    By contactCell = By.id("com.oxagile.GuardianAssist.PatientDev:id/cell_name");
    By startCallButton = By.id("com.oxagile.GuardianAssist.PatientDev:id/start_call_btn");
    By incomingCallScreen = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget" +
            ".LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget" +
            ".FrameLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout[2]/android.widget.ImageView");
    By acceptCallButton = By.id("com.oxagile.GuardianAssist.PatientDev:id/ra_driver_status");
    By declineCallButton = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget" +
            ".LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget" +
            ".FrameLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/android.widget.ImageView");
    By ebdCallButton = By.id("com.oxagile.GuardianAssist.PatientDev:id/on_call_end_btn");
    By callerName = By.id("com.oxagile.GuardianAssist.PatientDev:id/incoming_call_name");
    By onCallName = By.id("com.oxagile.GuardianAssist.PatientDev:id/on_call_name");
    By inviteThirdPartyButton = By.id("com.oxagile.GuardianAssist.PatientDev:id/on_call_invite_btn");
    By inviteToCallButton = By.id("com.oxagile.GuardianAssist.PatientDev:id/add_to_call_btn");
    By stopInviting = By.xpath("//android.widget.LinearLayout[@resource-id='com.oxagile.GuardianAssist.PatientDev:id/on_call_kick_btn']/android.widget.ImageView");
}
