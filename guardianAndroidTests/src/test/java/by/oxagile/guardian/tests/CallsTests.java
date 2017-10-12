package by.oxagile.guardian.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;


public class CallsTests extends TestBase {

    @Test
    public void testCalls() throws IOException, InterruptedException {
        /*patient.login().as("1111");
        patient.login().skipUberSignin();
        carer.login().acceptPermissions();
        carer.login().as("1234571");
        carer.login().skipUberSignin();
        carer.calls().dialTo("Andrew Leigh");


        Assert.assertTrue(patient.calls().isIncomingCallScreenPresent());

        patient.calls().accept();

        carer.calls().inviteThirdParty("Guardian Assist");*/

        //carer.calls().stopInviting();


        /*String token = assist.newHttpSession().requestCall(assist.getProperty("1234567.ID"), assist.getProperty("1111.DeviceID"), assist.getProperty("1111.Platform"));
        assist.tokBox().joinCall(token);*/

        String callID = assist.mongoDB().getLastCallID();
        String token = assist.newHttpSession().joinCall(callID);
        assist.tokBox().joinCall(token);
        Thread.sleep(10000);

    }

}
