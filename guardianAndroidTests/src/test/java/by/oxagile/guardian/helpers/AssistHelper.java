package by.oxagile.guardian.helpers;


import by.oxagile.guardian.managers.AssistManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;



public class AssistHelper extends BaseHelper {

    private static final AssistHelperLocators LOCATORS = new AssistHelperLocators();

    public AssistHelper(AssistManager assist) {
        super(assist);
    }

    public void playgroundLogin() {
        type(LOCATORS.tokBoxLogin, assist.getProperty("TB.login"));
        type(LOCATORS.tokBoxPass, assist.getProperty("TB.pass"));
        click(LOCATORS.tokBoxLoginButton);
    }

    public void playgroundConnect(String token) {
        click(LOCATORS.joinExistingSession);
        click(LOCATORS.useExistingToken);
        type(LOCATORS.tokenField, token);
        click(LOCATORS.subnitJoinSession);
        click(LOCATORS.connectSession);
        click(LOCATORS.publishStream);
        click(LOCATORS.submitStreamPublish);
        //Actions actions = new Actions(webDriver);
        //actions.keyDown(Keys.TAB).keyUp(Keys.TAB).perform();
        //actions.sendKeys("\u2B7E").sendKeys("\u23CE").perform();

    }

    public void joinCall(String token) {
        playgroundLogin();
        playgroundConnect(token);
    }
}
