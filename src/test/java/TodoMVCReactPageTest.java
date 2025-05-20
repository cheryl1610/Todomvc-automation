import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoMVCReactPageTest {
    private static ChromeDriver driver;

    @BeforeAll
    static void launchBrowser() {
        driver = new ChromeDriver();

    }

    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }

    @Test
    public void shouldAddItemToTheToDoList() {
        TodoMVCReactPage todoPage = new TodoMVCReactPage(driver);
        todoPage.navigate();
        todoPage.inputToDo("Buy Sweets");
        assertEquals(todoPage.getFirstToDoItem(), "Buy Sweets");
    }

    @Test

    public void shouldAddMultipleItemsToTheToDoList() {
        TodoMVCReactPage todoPage = new TodoMVCReactPage(driver);
        todoPage.navigate();
        todoPage.inputToDo("Buy Sweets");
        todoPage.inputToDo("Walk the dog");
        todoPage.inputToDo("Talk to Gary");
        todoPage.inputToDo("Return Next items");
        todoPage.inputToDo("Read a book");
        assertEquals(todoPage.getLengthOfTodos(), 5);
    }

    @Test
    public void shouldAddEmptyItemToTheToDoList() {
        TodoMVCReactPage todoPage = new TodoMVCReactPage(driver);
        todoPage.navigate();
        todoPage.inputToDo("");
        assertEquals(todoPage.getLengthOfTodos(), 0);
    }

    @Test
    public void modifyFirstItemToTheToDoList() {
        TodoMVCReactPage todoPage = new TodoMVCReactPage(driver);
        todoPage.navigate();
        todoPage.inputToDo("Buy Sweets");
        todoPage.doubleClickFirstTodoItem();// clicks on the first todo item


    }
}