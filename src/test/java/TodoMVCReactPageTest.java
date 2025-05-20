import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

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
        TodoMVCReactPage ToDoPage = new TodoMVCReactPage(driver);
        ToDoPage.navigate();
        ToDoPage.inputToDo("Buy Sweets");
        assertEquals(ToDoPage.getFirstToDoItem(), "Buy Sweets");

    }

    @Test
    public void shouldAddMultipleItemsToTheToDoList() {
        TodoMVCReactPage ToDoPage = new TodoMVCReactPage(driver);
        ToDoPage.navigate();
        ToDoPage.inputToDo("Buy Sweets");
        ToDoPage.inputToDo("Walk the dog");
        ToDoPage.inputToDo("Talk to Gary");
        ToDoPage.inputToDo("Return Next items");
        ToDoPage.inputToDo("Read a book");
        assertEquals(ToDoPage.getLengthOfTodos(), 5);
    }

    @Test
    public void shouldAddEmptyItemToTheToDoList() {
        TodoMVCReactPage ToDoPage = new TodoMVCReactPage(driver);
        ToDoPage.navigate();
        ToDoPage.inputToDo("");
        assertEquals(ToDoPage.getLengthOfTodos(), 0);
    }

    @Test
    public void shouldAddSpecialCharactersToTheToDoList() {
        TodoMVCReactPage ToDoPage = new TodoMVCReactPage(driver);
        ToDoPage.navigate();
        ToDoPage.inputToDo("@£%*!^?~");
        assertEquals(ToDoPage.getFirstToDoItem(), "@£%*!^?~");

    }

    @Test
    public void shouldAddTwoCharactersToTheToDoList() {
        TodoMVCReactPage ToDoPage = new TodoMVCReactPage(driver);
        ToDoPage.navigate();
        ToDoPage.inputToDo("AB");
        assertEquals(ToDoPage.getFirstToDoItem(), "AB");

    }

    @Test
    public void modifyFirstItemToTheToDoList() {
        TodoMVCReactPage todoPage = new TodoMVCReactPage(driver);
        todoPage.navigate();
        todoPage.inputToDo("Buy Sweets");
        todoPage.doubleClickFirstTodoItem();
        // This is not finished need to work out how to locate the inputted values and change it

    }
    @Test
    public void markItemComplete(){
        TodoMVCReactPage ToDoPage = new TodoMVCReactPage(driver);
        ToDoPage.navigate();
        ToDoPage.inputToDo("Buy Sweets");
        ToDoPage.inputToDo("Walk the dog");
        ToDoPage.inputToDo("Talk to Gary");
        ToDoPage.toggleToDoItem(1);
        assertEquals(ToDoPage.getItemsLeft(),"2 items left!");
    }
@Test
    public void markItemIncomplete(){
        TodoMVCReactPage ToDoPage = new TodoMVCReactPage(driver);
        ToDoPage.navigate();
        ToDoPage.inputToDo("Buy Sweets");
        ToDoPage.inputToDo("Walk the dog");
        ToDoPage.inputToDo("Talk to Gary");
        ToDoPage.toggleToDoItem(1);
        ToDoPage.toggleToDoItem(1);
        assertEquals(ToDoPage.getItemsLeft(),"3 items left!");
}
}