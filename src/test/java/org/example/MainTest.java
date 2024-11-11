package org.example;

import org.example.baseAction.BaseSeleniumTest;
import org.example.page.AuthorizationPage;
import org.example.page.MainMenu;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainTest extends BaseSeleniumTest {

    private final String login = "IvanovIvan@mail.com";
    private final String password = "IvanovIvan";

    private final String nameTask = "new task";

    @Test(priority = 1,
            description = "Проверка авторизации")
    public void authorizationTest() throws InterruptedException {
        AuthorizationPage authorizationPage = new AuthorizationPage();
        MainMenu mainMenu = authorizationPage
                .authorization(login, password);

        assertEquals(mainMenu
                .profile()
                .setting()
                .checkLogin().toLowerCase(), login.toLowerCase());
    }

    @Test(priority = 2,
            dataProvider = "userData",
            description = "проверка ввода пароля")
    public void authorizationPasswordFilledTest(String loginFail, String passwordFail) {
        AuthorizationPage authorizationPage = new AuthorizationPage();
        String value = authorizationPage
                .checkPassword(loginFail, passwordFail);
        assertTrue(value.equals("Пароль должен быть введен")
                || value.equals("Пароль должен содержать не менее 6 символов"));
    }

    @Test(priority = 3,
            dataProvider = "userData",
            description = "проверка ввода логина")
    public void authorizationCompleted(String loginFail, String passwordFail) {
        AuthorizationPage authorizationPage = new AuthorizationPage();
        String value = authorizationPage
                .checkLogin(loginFail, passwordFail);
        assertTrue(value.equals("Неправильный формат e-mail")
                || value.equals("E-mail адрес должен быть введен"));
    }

    @Test(priority = 4, description = "Проверка создания задачи")
    public void createTask() throws InterruptedException {
        AuthorizationPage authorizationPage = new AuthorizationPage();
        MainMenu mainMenu = authorizationPage
                .authorization(login, password).createdTask(nameTask);

        assertEquals(mainMenu.getNameTask(nameTask), nameTask);
    }

    @Test(priority = 6, description = "Проверка удаления задачи")
    public void deleteTask() {
        AuthorizationPage authorizationPage = new AuthorizationPage();
        MainMenu mainMenu = authorizationPage
                .authorization(login, password)
                .taskSettingMenu().deleteTask(nameTask);

        assertEquals(mainMenu
                .taskSettingMenu()
                .checkDeleteTask(), "Задача удалена");
    }

    @Test(priority = 5, description = "Проверка выполнения задачи")
    public void completionOfTask() {
        AuthorizationPage authorizationPage = new AuthorizationPage();
        MainMenu mainMenu = authorizationPage
                .authorization(login, password).complectedTask(nameTask);

        assertEquals(mainMenu.checkComplectedTask(), "Задача выполнена");
    }

    @DataProvider(name = "userData", parallel = true)
    private Object[][] getUsersData() {
        return new Object[][]{
                {" ", " "},
                {"Ivanov", "i"},
                {"Ivanov@mail.", "i"},
                {"Ivanov@mail", "i"},
                {"Ivanov@.com", "i"},
                {"Ivanov.com", "i"},
                {"!@#$%", " "},
                {"!@#$%.com", " "},
                {"!@#$%@mail.com", "i"},
                {"Иванов", " "},
                {"Иванов@mail.com", " "},
                {"Иванов@mail.", "i"},
                {"Иванов@mail", "i"},
                {"Иванов@.com", " "},
                {"Иванов.com", "i"},
        };
    }

}
