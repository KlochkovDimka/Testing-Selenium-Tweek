package org.example.page;

import org.example.baseAction.BaseSeleniumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TaskSettingMenu extends BaseSeleniumPage {
    @FindBy(xpath = "//li[@class='rc-menu-item deleteItem']")
    private WebElement deleteTask;
    @FindBy(xpath = "//div[@class='pop-tip tip-transition clearfix relative rounded-[6px] transform translate-y-0 transition-all duration-200 ease-in popTip_3xKPl cursor-pointer before-animation']")
    private WebElement windowDeleteTask;
    private final Actions actions = new Actions(driver);
    private final WebDriverWait wait = new WebDriverWait(driver, 10);

    public TaskSettingMenu() {
        PageFactory.initElements(driver, this);
    }

    public MainMenu deleteTask(String name) {
        actions.moveToElement(deleteTask).click().perform();
        return new MainMenu();
    }

    // Проверка появления окна "Задача удалена"
    public String checkDeleteTask() {
        return wait.until(ExpectedConditions.visibilityOf(windowDeleteTask)).getText();
    }
}
