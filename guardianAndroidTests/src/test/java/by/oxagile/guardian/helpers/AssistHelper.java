package by.oxagile.guardian.helpers;


import by.oxagile.guardian.managers.AssistManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;


public class AssistHelper extends BaseHelper {

    private static final AssistHelperLocators LOCATORS = new AssistHelperLocators();

    public AssistHelper(AssistManager assist) {
        super(assist);
        logger.info("AssistHelper initiated");
    }

    Logger logger = LoggerFactory.getLogger(AssistHelper.class);

    public void playgroundLogin() {
        setWebDriverWait(2);
        if (webDriver.findElements(LOCATORS.tokBoxLogin).size()==1) {
            input(LOCATORS.tokBoxLogin, assistManager.getProperty("TB.login"));
            input(LOCATORS.tokBoxPass, assistManager.getProperty("TB.pass"));
            click(LOCATORS.tokBoxLoginButton);
        } else {
            webDriver.get(assistManager.getProperty("web.TokBoxUrl"));
        }
        logger.info("Logged into TokBox playground");
        setWebDriverWait(10);
    }

    public void playgroundConnect(String token) {
        click(LOCATORS.joinExistingSession);
        click(LOCATORS.useExistingToken);
        input(LOCATORS.tokenField, token);
        click(LOCATORS.subnitJoinSession);
        click(LOCATORS.connectSession);
        click(LOCATORS.publishStream);
        click(LOCATORS.submitStreamPublish);
        logger.info("ASSIST connected to call");
    }

    public void allowPermission() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            logger.info("Chrome permissions to stream video and audio successfully granted");

        } catch (AWTException e) {
            logger.info("Failed to grant chrome permissions to stream video and audio");
            e.printStackTrace();
        }
    }

    public void joinCall(String token) {
        playgroundLogin();
        playgroundConnect(token);
        allowPermission();
    }

    public void setWebDriverWait(long seconds) {
        webDriver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
        logger.info("Webdriver implicitly wait has set to " + seconds);
    }
}
