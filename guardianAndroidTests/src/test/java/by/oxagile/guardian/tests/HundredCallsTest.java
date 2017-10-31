package by.oxagile.guardian.tests;


import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class HundredCallsTest extends TestBase {

    @Test
    public void hundredCallsTest() {

        patient.login().toAppAs(true, "1111");
        carer.login().toAppAs(true,"1234571");

        for (int i = 0; i < 100; i++) {
            carer.calls().dialTo("Andrew Leigh");
            patient.calls().accept();
            patient.calls().leave();
            carer.calls().waitForElementPresence(By.id("com.oxagile.GuardianAssist.PatientDev:id/start_call_btn"), 10);
        }
    }
}
