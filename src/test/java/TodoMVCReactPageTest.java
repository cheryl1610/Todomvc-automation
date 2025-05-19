import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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
        TodoMVCReactPage searchPage = new TodoMVCReactPage(driver);
        searchPage.navigate();
        searchPage.inputToDo("Buy Sweets");
        assertEquals(searchPage.getFirstToDoItem(),"Buy Sweets");
    }
    @Test
    public void shouldAddMultipleItemsToTheToDoList() {
        TodoMVCReactPage searchPage = new TodoMVCReactPage(driver);
        searchPage.navigate();
        searchPage.inputToDo("Buy Sweets");
        searchPage.inputToDo("Walk the dog");
        searchPage.inputToDo("Talk to Gary");
        searchPage.inputToDo("Return Next items");
        searchPage.inputToDo("Read a book");
        assertEquals(searchPage.getLengthOfTodos(),4);
    }

    @Test
    public void shouldAddEmptyItemToTheToDoList() {
        TodoMVCReactPage searchPage = new TodoMVCReactPage(driver);
        searchPage.navigate();
        searchPage.inputToDo("");
        assertEquals(searchPage.getLengthOfTodos(),0);
    }
}