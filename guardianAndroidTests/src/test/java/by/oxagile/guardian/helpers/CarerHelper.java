package by.oxagile.guardian.helpers;

import by.oxagile.guardian.managers.CarerManager;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class CarerHelper extends BaseHelper {

    public CarerHelper(CarerManager carerManager) {
        super(carerManager);
    }
}
