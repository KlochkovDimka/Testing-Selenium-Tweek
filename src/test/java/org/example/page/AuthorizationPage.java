package org.example.page;

import org.example.baseAction.BaseSeleniumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthorizationPage extends BaseSeleniumPage {

    @FindBy(xpath = "//input[@autocomplete='username']")
    private WebElement inputLogin;
    @FindBy(id = "password")
    private WebElement inputPassword;
    @FindBy(xpath = "//button[@class='button__3eXSs ']")
    private WebElement buttonLogin;
    @FindBy(xpath = "//div[@class='row__26fQN']//p")
    private WebElement valueFailLogin;
    @FindBy(xpath = "//div[@class='row__26fQN gap__3bLAr']//p")
    private WebElement valueFailPassword;
    private final WebDriverWait wait = new WebDriverWait(driver, 10);

    public AuthorizationPage() {
        String URI = "https://ticktick.com/signin";
        driver.get(URI);
        PageFactory.initElements(driver, this);
    }

    public MainMenu authorization(String login, String password) {
        wait.until(ExpectedConditions.visibilityOf(inputLogin)).sendKeys(login);
        inputPassword.sendKeys(password);
        buttonLogin.click();
        return new MainMenu();
    }

    public String checkLogin(String login, String password) {
        wait.until(ExpectedConditions.visibilityOf(inputLogin)).sendKeys(login);
        inputLogin.sendKeys(login);
        buttonLogin.click();
        return wait.until(ExpectedConditions.visibilityOf(valueFailLogin)).getText();
    }

    public String checkPassword(String login, String password) {
        wait.until(ExpectedConditions.visibilityOf(inputPassword)).sendKeys(password);
        inputPassword.sendKeys(password);
        buttonLogin.click();
        return wait.until(ExpectedConditions.visibilityOf(valueFailPassword)).getText();
    }
}
