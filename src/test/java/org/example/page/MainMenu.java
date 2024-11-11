package org.example.page;

import org.example.baseAction.BaseSeleniumPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainMenu extends BaseSeleniumPage {

    @FindBy(id = "tl-bar-user")
    private WebElement profile;
    @FindBy(xpath = "//span[@class='text-s text-grey-20 flex-auto truncate']")
    private WebElement inputNameTask;
    @FindBy(xpath = "//div[@class='l-task group relative flex flex-col z-[1] h-full']")
    private WebElement menuTask;
    @FindBy(xpath = "//div[@class='t-menu-toggle hide-in-drag-preview invisible absolute top-1/2 transform -translate-y-1/2  group-hover:visible z-[11] cursor-pointer right-[4px]']")
    private WebElement openMenuTask;
    @FindBy(xpath = "//span[@class='checker avoid-event checkerWrapper_1hxkj']")
    private WebElement buttonComplectedTask;
    @FindBy(xpath = "//div[@class='pop-tip tip-transition clearfix relative rounded-[6px] transform translate-y-0 transition-all duration-200 ease-in popTip_3xKPl cursor-pointer']")
    private WebElement windowComplectedTask;

    private final Actions actions = new Actions(driver);
    private final WebDriverWait wait = new WebDriverWait(driver, 10);

    public MainMenu() {
        PageFactory.initElements(driver, this);
    }

    // Меню профиля
    public ProfilePage profile() {
        actions.moveToElement(profile).click().perform();
        return new ProfilePage();
    }

    // Меню настроек
    public TaskSettingMenu taskSettingMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(menuTask));
        actions.moveToElement(menuTask).perform();
        actions.click(openMenuTask).perform();
        return new TaskSettingMenu();
    }

    // создание новой задачи
    public MainMenu createdTask(String name) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(inputNameTask));
        actions.moveToElement(inputNameTask).click().sendKeys(name, Keys.ENTER).perform();
        Thread.sleep(2000);
        return new MainMenu();
    }

    // получение имени задачи
    public String getNameTask(String name) {
        return wait.until(ExpectedConditions.visibilityOf(driver.findElement(
                        By.xpath("//span[@class='tasklist-static-title-editor " +
                                "staticView_3VDnb editableView_KYyqL inline_3zuKd'][contains(text(),'"
                                + name + "')]"))))
                .getText();
    }

    public MainMenu complectedTask(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(buttonComplectedTask)).click();
        return new MainMenu();
    }

    public String checkComplectedTask() {
        return wait.until(ExpectedConditions.visibilityOf(windowComplectedTask)).getText();
    }
}
