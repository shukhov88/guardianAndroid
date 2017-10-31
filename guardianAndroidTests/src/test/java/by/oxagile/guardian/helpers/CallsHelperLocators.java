package by.oxagile.guardian.helpers;


import org.openqa.selenium.By;

public class CallsHelperLocators {

    By contactCell = By.id("com.oxagile.GuardianAssist.PatientDev:id/cell_name");
    By startCallButton = By.id("com.oxagile.GuardianAssist.PatientDev:id/start_call_btn");
    By acceptCallButton = By.id("com.oxagile.GuardianAssist.PatientDev:id/incoming_call_start_btn");
    By declineCallButton = By.id("com.oxagile.GuardianAssist.PatientDev:id/incoming_call_end_btn");
    By endCallButton = By.id("com.oxagile.GuardianAssist.PatientDev:id/on_call_end_btn");
    By callerName = By.id("com.oxagile.GuardianAssist.PatientDev:id/incoming_call_name");
    By onCallName = By.id("com.oxagile.GuardianAssist.PatientDev:id/on_call_name");
    By inviteThirdPartyButton = By.id("com.oxagile.GuardianAssist.PatientDev:id/on_call_invite_btn");
    By inviteToCallButton = By.id("com.oxagile.GuardianAssist.PatientDev:id/add_to_call_btn");
    By stopInviting = By.xpath("//android.widget.LinearLayout[@resource-id='com.oxagile.GuardianAssist.PatientDev:id/on_call_kick_btn']/android.widget.ImageView");
    By assistCallButton = By.id("com.oxagile.GuardianAssist.PatientDev:id/start_call_btn");
    By cameraSwitchButton = By.id("com.oxagile.GuardianAssist.PatientDev:id/dialling_switch_camera_btn");
    By onCallTopLeftVideoFrame = By.id("com.oxagile.GuardianAssist.PatientDev:id/on_call_small_video_view_1");
    By onCallTopRightVideoFrame = By.id("com.oxagile.GuardianAssist.PatientDev:id/on_call_small_video_view_2");
    By onCallCentralVideoFrame = By.id("com.oxagile.GuardianAssist.PatientDev:id/on_call_big_video_view");

}
