package org.example.page;

import org.example.baseAction.BaseSeleniumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage extends BaseSeleniumPage {
    @FindBy(xpath = "//a//span[contains(text(), 'Настройки')]")
    private WebElement settings;

    public ProfilePage() {
        PageFactory.initElements(driver, this);
    }

    public SettingPage setting(){
        settings.click();
        return new SettingPage();
    }
}
