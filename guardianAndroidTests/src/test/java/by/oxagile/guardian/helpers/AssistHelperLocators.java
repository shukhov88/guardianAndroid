package by.oxagile.guardian.helpers;


import org.openqa.selenium.By;

public class AssistHelperLocators {

    By tokBoxLogin = By.id("user_email");
    By tokBoxPass = By.id("user_password");
    By tokBoxLoginButton = By.name("button");
    By joinExistingSession = By.cssSelector("[aria-controls=join]");
    By useExistingToken = By.cssSelector("label.existing-token:not(.active)");
    By tokenField = By.name("token");
    By subnitJoinSession = By.id("join_session_submit2");
    By connectSession = By.id("button_connect");
    By publishStream = By.id("publish_properties_link");
    By submitStreamPublish = By.id("button_publish");
}
