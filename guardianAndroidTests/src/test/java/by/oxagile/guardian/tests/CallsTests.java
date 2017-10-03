package by.oxagile.guardian.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;


public class CallsTests extends TestBase {

    @Test
    public void testCalls() throws IOException, InterruptedException {
        patient.patientHelper().loginWithoutPermissions("1111");
        carer.carerHelper().login("1234571");
        carer.carerHelper().makeCall("Andrew Leigh");

        Assert.assertTrue(patient.patientHelper().isIncomingCallScreenPresent());
        //patient.patientHelper().acceptCall();
        String token = assist.newSession().requestCall(assist.getProperty("1234567.ID"), assist.getProperty("1111.DeviceID"), assist.getProperty("1111.Platform"));
        assist.tokBox().connectToSession(token);

    }


}
