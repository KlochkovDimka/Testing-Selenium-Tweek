package org.example.page;

import org.example.baseAction.BaseSeleniumPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SettingPage extends BaseSeleniumPage {
    @FindBy(xpath = "//span[@class='inline-block text-grey-60 align-baseline text-s']")
    private WebElement emailInProfile;
    public SettingPage(){
        PageFactory.initElements(driver, this);
    }

    public String checkLogin() throws InterruptedException {

        return emailInProfile.getText();
    }
}
