package by.oxagile.guardian.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;


public class CallsTests extends TestBase {

    @Test
    public void testCalls() throws IOException, InterruptedException {
        patient.patientHelper().loginAs("1111");
        carer.carerHelper().acceptPermissions();
        carer.carerHelper().loginAs("1234571");
        carer.carerHelper().makeCall("Andrew Leigh");

        Assert.assertTrue(patient.patientHelper().isIncomingCallScreenPresent());

        patient.patientHelper().acceptCall();

        carer.carerHelper().inviteToCall("Guardian Assist");
        carer.carerHelper().stopInvitingToCall();


        /*String token = assist.newSession().requestCall(assist.getProperty("1234567.ID"), assist.getProperty("1111.DeviceID"), assist.getProperty("1111.Platform"));
        assist.tokBox().connectToSession(token);*/

        /*String callID = assist.mongoDB().getLastCallID();
        String token = assist.newSession().joinCall(callID);
        assist.tokBox().connectToSession(token);*/

    }

}
