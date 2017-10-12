package by.oxagile.guardian.helpers;


import by.oxagile.guardian.managers.AssistManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;



public class AssistHelper extends BaseHelper {

    public AssistHelper(AssistManager assist) {
        super(assist);
    }

    public void playgroundLogin() {
        //androidDriver.get("https://tokbox.com/developer/tools/playground/");
        type(By.id("user_email"), assist.getProperty("TB.login"));
        type(By.id("user_password"), assist.getProperty("TB.pass"));
        click(By.name("button"));
    }

    public void playgroundConnect(String token) {
        click(By.cssSelector("[aria-controls=join]"));
        click(By.cssSelector("label.existing-token:not(.active)"));
        type(By.name("token"), token);
        click(By.id("join_session_submit2"));
        click(By.id("button_connect"));
        click(By.id("publish_properties_link"));
        click(By.id("button_publish"));
        //Actions actions = new Actions(webDriver);
        //actions.keyDown(Keys.TAB).keyUp(Keys.TAB).perform();
        //actions.sendKeys("\u2B7E").sendKeys("\u23CE").perform();

    }

    public void joinCall(String token) {
        playgroundLogin();
        playgroundConnect(token);
    }
}
