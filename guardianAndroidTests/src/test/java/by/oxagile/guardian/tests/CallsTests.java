package by.oxagile.guardian.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;


public class CallsTests extends TestBase {

    private By startCallButton = By.id("com.oxagile.GuardianAssist.PatientDev:id/start_call_btn");
    private By onCallInviteButton = By.id("com.oxagile.GuardianAssist.PatientDev:id/on_call_invite_btn");
    private By smallLeftVideoWindow = By.id("com.oxagile.GuardianAssist.PatientDev:id/on_call_small_video_view_1");
    private By acceptCallButton = By.id("com.oxagile.GuardianAssist.PatientDev:id/incoming_call_start_btn");
    private By addToCallButton = By.id("com.oxagile.GuardianAssist.PatientDev:id/add_to_call_btn");
    private String andrewLeigh = "Andrew Leigh";
    private String patientPhone = "1111";
    private String carerPhone = "1234571";

    @Test (enabled = false)
    public void recieverAcceptsCall() {
        patient.login().toAppAs(false, patientPhone);
        carer.login().toAppAs(true,carerPhone);
        carer.calls().dialTo(andrewLeigh);
        patient.calls().accept();

        Assert.assertTrue(carer.helper().isElementPresent(onCallInviteButton));
        Assert.assertTrue(patient.helper().isElementPresent(smallLeftVideoWindow));
        Assert.assertEquals(assist.mongoDB().getLastCallStatus(), "ONGOING");
    }

    @Test (enabled = false)
    public void patientCarerVideoStreamsLocation() {
        patient.login().toAppAs(false, patientPhone);
        carer.login().toAppAs(true,carerPhone);
        carer.calls().dialTo(andrewLeigh);
        patient.calls().accept();


        Assert.assertTrue(carer.helper().isElementPresent(onCallInviteButton));
        Assert.assertEquals(carer.calls().getCentralVideoStreamID(), assist.getProperty("1111.ID"));
        Assert.assertTrue(carer.calls().getTopLeftVideoStreamID().isEmpty());
        Assert.assertEquals(patient.calls().getCentralVideoStreamID(), assist.getProperty("1234571.ID"));
        Assert.assertTrue(patient.calls().getTopLeftVideoStreamID().isEmpty());
    }

    @Test (enabled = false)
    public void patientAssistVideoStreamsLocation() throws IOException {
        patient.login().toAppAs(false, patientPhone);
        patient.calls().dialTo("ASSIST");
        String callID = assist.mongoDB().getLastCallID();
        String token = assist.newHttpSession().joinCall(callID);
        assist.tokBox().joinCall(token);

        Assert.assertTrue(patient.calls().getCentralVideoStreamID().isEmpty());
        Assert.assertEquals(patient.calls().getTopLeftVideoStreamID(), assist.getProperty("Assist.ID"));
    }

    @Test (enabled = false)
    public void carerAssistVideoStreamsLocation() throws IOException {
        carer.login().toAppAs(true,carerPhone);
        carer.calls().dialTo("Guardian Assist");
        String callID = assist.mongoDB().getLastCallID();
        String token = assist.newHttpSession().joinCall(callID);
        assist.tokBox().joinCall(token);

        Assert.assertEquals(carer.calls().getCentralVideoStreamID(), assist.getProperty("Assist.ID"));
        Assert.assertTrue(carer.calls().getTopLeftVideoStreamID().isEmpty());
    }

    @Test (enabled = false)
    public void initiatorDeclinesCall() {
        carer.login().toAppAs(true,carerPhone);
        patient.login().toAppAs(false, patientPhone);
        carer.calls().dialTo(andrewLeigh);
        assist.mongoDB().getLastCallID();

        Assert.assertTrue(patient.helper().isElementPresent(acceptCallButton));

        carer.calls().stopDialing();

        Assert.assertTrue(carer.helper().isElementPresent(startCallButton));
        Assert.assertTrue(patient.helper().isElementPresent(startCallButton));
        Assert.assertEquals(assist.mongoDB().getLastCallStatus(), "ORIGINATOR_RESET");
    }

    @Test (enabled = false)
    public void initiatorLeavesCall() {
        patient.login().toAppAs(false, patientPhone);
        carer.login().toAppAs(true,carerPhone);
        carer.calls().dialTo(andrewLeigh);
        patient.calls().accept();
        carer.calls().leave();

        Assert.assertTrue(carer.helper().isElementPresent(startCallButton));
        Assert.assertTrue(patient.helper().isElementPresent(startCallButton));
        Assert.assertTrue(assist.mongoDB().getLastCallStatus().equals("COMPLETED"));
    }

    @Test (enabled = false)
    public void recieverDeclinesCall() {
        patient.login().toAppAs(false, patientPhone);
        carer.login().toAppAs(true,carerPhone);
        carer.calls().dialTo(andrewLeigh);
        patient.calls().decline();

        Assert.assertTrue(patient.helper().isElementPresent(startCallButton));
        Assert.assertTrue(carer.helper().isElementPresent(startCallButton));
        Assert.assertTrue(assist.mongoDB().getLastCallStatus().equals("RECIPIENT_REJECTED"));
    }

    @Test (enabled = false)
    public void recieverTimeoutesCall() {
        patient.login().toAppAs(false, patientPhone);
        carer.login().toAppAs(true,carerPhone);
        carer.calls().dialTo(andrewLeigh);
        patient.calls().timeOut();

        Assert.assertTrue(carer.helper().isElementPresent(startCallButton));
        Assert.assertTrue(patient.helper().isElementPresent(startCallButton));
        Assert.assertTrue(assist.mongoDB().getLastCallStatus().equals("UNANSWERED"));
    }

    @Test (enabled = false)
    public void recieverLeavesCall() {
        patient.login().toAppAs(false, patientPhone);
        carer.login().toAppAs(true,carerPhone);
        carer.calls().dialTo(andrewLeigh);
        patient.calls().accept();
        patient.calls().leave();

        Assert.assertTrue(patient.helper().isElementPresent(startCallButton));
        Assert.assertTrue(carer.helper().isElementPresent(startCallButton));
        Assert.assertTrue(assist.mongoDB().getLastCallStatus().equals("COMPLETED"));
    }

    @Test (enabled = false)
    public void addThirdPartyButton() {
        patient.login().toAppAs(false, patientPhone);
        carer.login().toAppAs(true,carerPhone);
        carer.calls().dialTo(andrewLeigh);
        patient.calls().accept();
        carer.calls().tapInviteButton();

        Assert.assertTrue(carer.helper().isElementPresent(addToCallButton));
    }
}
