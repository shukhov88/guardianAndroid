package by.oxagile.guardian.tests;

import org.testng.Assert;
import org.testng.annotations.Test;


public class CallsTests extends TestBase {

    @Test
    public void testCalls() throws InterruptedException {
        patient.patientHelper().loginWithoutPermissions("1111");
        carer.carerHelper().login("1234571");
        carer.carerHelper().makeCall("Andrew Leigh");

        Assert.assertTrue(patient.patientHelper().isIncomingCallScreenPresent());
    }


}
