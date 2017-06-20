package by.stqa.pft.addressbook.appmanager;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Sony on 20.06.2017.
 */
public class FirstHelper extends BaseHelper {

    public FirstHelper(WebDriver wd) {
        super(wd);
    }

    public void test() throws InterruptedException {
        wd.findElements(By.className("android.widget.ImageView")).get(1).click();
        Thread.sleep(2000);
    }
}
