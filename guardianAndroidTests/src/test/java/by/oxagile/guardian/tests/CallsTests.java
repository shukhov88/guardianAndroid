package by.oxagile.guardian.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;


public class CallsTests extends TestBase {

    @Test (enabled = false)
    public void recieverAcceptsCall() {
        patient.login().toAppAs(false, "1111");
        carer.login().toAppAs(true,"1234571");
        carer.calls().dialTo("Andrew Leigh");
        patient.calls().accept();

        Assert.assertTrue(carer.helper().isElementPresent(By.id("com.oxagile.GuardianAssist.PatientDev:id/on_call_invite_btn")));
        Assert.assertTrue(patient.helper().isElementPresent(By.id("com.oxagile.GuardianAssist.PatientDev:id/on_call_small_video_view_1")));

        Assert.assertEquals(assist.mongoDB().getLastCallStatus(), "ONGOING");
    }

    @Test (enabled = false)
    public void patientCarerVideoStreamsLocation() {
        patient.login().toAppAs(false, "1111");
        carer.login().toAppAs(true,"1234571");
        carer.calls().dialTo("Andrew Leigh");
        patient.calls().accept();

        Assert.assertTrue(carer.helper().isElementPresent(By.id("com.oxagile.GuardianAssist.PatientDev:id/on_call_invite_btn")));
        Assert.assertEquals(carer.calls().getCentralVideoStreamID(), assist.getProperty("1111.ID"));
        Assert.assertTrue(carer.calls().getTopLeftVideoStreamID().isEmpty());
        Assert.assertEquals(patient.calls().getCentralVideoStreamID(), assist.getProperty("1234571.ID"));
        Assert.assertTrue(patient.calls().getTopLeftVideoStreamID().isEmpty());
    }

    @Test (enabled = false)
    public void patientAssistVideoStreamsLocation() throws IOException {
        patient.login().toAppAs(false, "1111");
        patient.calls().dialTo("ASSIST");
        String callID = assist.mongoDB().getLastCallID();
        String token = assist.newHttpSession().joinCall(callID);
        assist.tokBox().joinCall(token);

        Assert.assertTrue(patient.calls().getCentralVideoStreamID().isEmpty());
        Assert.assertEquals(patient.calls().getTopLeftVideoStreamID(), assist.getProperty("Assist.ID"));
    }

    @Test (enabled = false)
    public void carerAssistVideoStreamsLocation() throws IOException {
        carer.login().toAppAs(true,"1234571");
        carer.calls().dialTo("Guardian Assist");
        String callID = assist.mongoDB().getLastCallID();
        String token = assist.newHttpSession().joinCall(callID);
        assist.tokBox().joinCall(token);

        Assert.assertEquals(carer.calls().getCentralVideoStreamID(), assist.getProperty("Assist.ID"));
        Assert.assertTrue(carer.calls().getTopLeftVideoStreamID().isEmpty());
    }

    @Test (enabled = false)
    public void initiatorDeclinesCall() {
        carer.login().toAppAs(true,"1234571");
        patient.login().toAppAs(false, "1111");
        carer.calls().dialTo("Andrew Leigh");
        assist.mongoDB().getLastCallID();

        Assert.assertTrue(patient.helper().isElementPresent(By.id("com.oxagile.GuardianAssist.PatientDev:id/incoming_call_start_btn")));

        carer.calls().stopDialing();

        Assert.assertTrue(carer.helper().isElementPresent(By.id("com.oxagile.GuardianAssist.PatientDev:id/start_call_btn")));
        Assert.assertTrue(patient.helper().isElementPresent(By.id("com.oxagile.GuardianAssist.PatientDev:id/start_call_btn")));
        Assert.assertEquals(assist.mongoDB().getLastCallStatus(), "ORIGINATOR_RESET");
    }

    @Test (enabled = true)
    public void initiatorLeavesCall() {
        patient.login().toAppAs(false, "1111");
        carer.login().toAppAs(true,"1234571");
        carer.calls().dialTo("Andrew Leigh");
        patient.calls().accept();
        carer.calls().leave();

        Assert.assertTrue(carer.helper().isElementPresent(By.id("com.oxagile.GuardianAssist.PatientDev:id/cell_name")));
        Assert.assertTrue(patient.helper().isElementPresent(By.id("com.oxagile.GuardianAssist.PatientDev:id/cell_name")));
        Assert.assertTrue(assist.mongoDB().getLastCallStatus().equals("COMPLETED"));
    }

    @Test (enabled = true)
    public void recieverDeclinesCall() {
        patient.login().toAppAs(false, "1111");
        carer.login().toAppAs(true,"1234571");
        carer.calls().dialTo("Andrew Leigh");
        patient.calls().decline();

        Assert.assertTrue(patient.helper().isElementPresent(By.id("com.oxagile.GuardianAssist.PatientDev:id/cell_name")));
        Assert.assertTrue(carer.helper().isElementPresent(By.id("com.oxagile.GuardianAssist.PatientDev:id/start_call_btn")));
        Assert.assertTrue(assist.mongoDB().getLastCallStatus().equals("RECIPIENT_REJECTED"));
    }

    @Test (enabled = false)
    public void recieverTimeoutesCall() {
        patient.login().toAppAs(false, "1111");
        carer.login().toAppAs(true,"1234571");
        carer.calls().dialTo("Andrew Leigh");
        patient.calls().timeOut();

        Assert.assertTrue(carer.helper().isElementPresent(By.id("com.oxagile.GuardianAssist.PatientDev:id/cell_name")));
        Assert.assertTrue(patient.helper().isElementPresent(By.id("com.oxagile.GuardianAssist.PatientDev:id/start_call_btn")));
        Assert.assertTrue(assist.mongoDB().getLastCallStatus().equals("COMPLETED"));
    }

    @Test (enabled = false)
    public void recieverLeavesCall() {
        patient.login().toAppAs(false, "1111");
        carer.login().toAppAs(true,"1234571");
        carer.calls().dialTo("Andrew Leigh");
        patient.calls().accept();
        patient.calls().leave();

        Assert.assertTrue(patient.helper().isElementPresent(By.id("com.oxagile.GuardianAssist.PatientDev:id/cell_name")));
        Assert.assertTrue(carer.helper().isElementPresent(By.id("com.oxagile.GuardianAssist.PatientDev:id/start_call_btn")));
        Assert.assertTrue(assist.mongoDB().getLastCallStatus().equals("UNANSWERED"));
    }

    @Test (enabled = false)
    public void addThirdPartyButton() {
        patient.login().toAppAs(false, "1111");
        carer.login().toAppAs(true,"1234571");
        carer.calls().dialTo("Andrew Leigh");
        patient.calls().accept();
        carer.calls().tapInviteButton();

        Assert.assertTrue(carer.helper().isElementPresent(By.id("com.oxagile.GuardianAssist.PatientDev:id/add_to_call_btn")));
    }
}
