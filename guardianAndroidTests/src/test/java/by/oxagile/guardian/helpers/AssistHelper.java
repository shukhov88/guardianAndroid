package by.oxagile.guardian.helpers;


import by.oxagile.guardian.managers.AssistManager;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;


public class AssistHelper extends BaseHelper {

    private static final AssistHelperLocators LOCATORS = new AssistHelperLocators();

    public AssistHelper(AssistManager assist) {
        super(assist);
    }

    public void playgroundLogin() {
        setWebDriverWait(2);
        if (webDriver.findElements(LOCATORS.tokBoxLogin).size()==1) {
            type(LOCATORS.tokBoxLogin, assistManager.getProperty("TB.login"));
            type(LOCATORS.tokBoxPass, assistManager.getProperty("TB.pass"));
            click(LOCATORS.tokBoxLoginButton);
        } else {
            webDriver.get(assistManager.getProperty("web.TokBoxUrl"));
        }
        setWebDriverWait(10);
    }

    public void playgroundConnect(String token) {
        click(LOCATORS.joinExistingSession);
        click(LOCATORS.useExistingToken);
        type(LOCATORS.tokenField, token);
        click(LOCATORS.subnitJoinSession);
        click(LOCATORS.connectSession);
        click(LOCATORS.publishStream);
        click(LOCATORS.submitStreamPublish);
    }

    public void allowPermission() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

        } catch (AWTException e) {
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
    }
}
