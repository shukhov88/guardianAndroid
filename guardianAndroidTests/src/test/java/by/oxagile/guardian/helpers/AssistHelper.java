package by.oxagile.guardian.helpers;


import by.oxagile.guardian.managers.AssistManager;
import org.openqa.selenium.By;


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
    }

    public void connectToSession(String token) {
        playgroundLogin();
        playgroundConnect(token);
    }
}
