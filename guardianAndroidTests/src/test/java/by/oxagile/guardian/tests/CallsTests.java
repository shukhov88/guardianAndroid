package by.oxagile.guardian.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;


public class CallsTests extends TestBase {

    @Test
    public void testCalls() throws IOException, InterruptedException {
        patient.helper().loginAs("1111");
        carer.helper().acceptPermissions();
        carer.helper().loginAs("1234571");
        carer.helper().makeCall("Andrew Leigh");

        Assert.assertTrue(patient.helper().isIncomingCallScreenPresent());

        patient.helper().acceptCall();

        carer.helper().inviteToCall("Guardian Assist");
        carer.helper().stopInvitingToCall();


        /*String token = assist.newSession().requestCall(assist.getProperty("1234567.ID"), assist.getProperty("1111.DeviceID"), assist.getProperty("1111.Platform"));
        assist.tokBox().connectToSession(token);*/

        /*String callID = assist.mongoDB().getLastCallID();
        String token = assist.newSession().joinCall(callID);
        assist.tokBox().connectToSession(token);*/

    }

}
