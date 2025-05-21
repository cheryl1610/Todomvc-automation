import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
        TodoMVCReactPage todopage = new TodoMVCReactPage(driver);
        todopage.navigate();
        todopage.inputToDo("Buy Sweets");
        assertEquals(todopage.getFirstToDoItem(), "Buy Sweets");

    }

    @Test
    public void shouldAddMultipleItemsToTheToDoList() {
        TodoMVCReactPage toDoPage = new TodoMVCReactPage(driver);
        toDoPage.navigate();
        toDoPage.inputToDo("Buy Sweets");
        toDoPage.inputToDo("Walk the dog");
        toDoPage.inputToDo("Talk to Gary");
        toDoPage.inputToDo("Return Next items");
        toDoPage.inputToDo("Read a book");
        assertEquals(toDoPage.getLengthOfTodos(), 5);
    }

    @Test
    public void shouldAddEmptyItemToTheToDoList() {
        TodoMVCReactPage toDoPage = new TodoMVCReactPage(driver);
        toDoPage.navigate();
        toDoPage.inputToDo("");
        assertEquals(toDoPage.getLengthOfTodos(), 0);
    }

    @Test
    public void shouldAddSpecialCharactersToTheToDoList() {
        TodoMVCReactPage todopage = new TodoMVCReactPage(driver);
        todopage.navigate();
        todopage.inputToDo("@£%*!^?~");
        assertEquals(todopage.getFirstToDoItem(), "@£%*!^?~");

    }

    @Test
    public void shouldAddTwoCharactersToTheToDoList() {
        TodoMVCReactPage toDoPage = new TodoMVCReactPage(driver);
        toDoPage.navigate();
        toDoPage.inputToDo("AB");
        assertEquals(toDoPage.getFirstToDoItem(), "AB");

    }

    @Test
    public void modifyAddTextFirstItemToTheToDoList() {
        TodoMVCReactPage todoPage = new TodoMVCReactPage(driver);
        todoPage.navigate();
        todoPage.inputToDo("Buy Sweets");
        todoPage.addTextToFirstTodoItem(" now!");
        assertEquals(todoPage.getFirstToDoItem(), "Buy Sweets now!");


    }
    @Test
    public void markItemComplete(){
        TodoMVCReactPage todopage = new TodoMVCReactPage(driver);
        todopage.navigate();
        todopage.inputToDo("Buy Sweets");
        todopage.inputToDo("Walk the dog");
        todopage.inputToDo("Talk to Gary");
        todopage.toggleToDoItem(1);
        assertEquals(todopage.getItemsLeft(),"2 items left!");
    }
@Test
    public void markItemIncomplete(){
        TodoMVCReactPage todopage = new TodoMVCReactPage(driver);
        todopage.navigate();
        todopage.inputToDo("Buy Sweets");
        todopage.inputToDo("Walk the dog");
        todopage.inputToDo("Talk to Gary");
        todopage.toggleToDoItem(1);
        todopage.toggleToDoItem(1);
        assertEquals(todopage.getItemsLeft(),"3 items left!");
}
@Test
    public void clearCompletedItems(){
    TodoMVCReactPage toDoPage = new TodoMVCReactPage(driver);
    toDoPage.navigate();
    toDoPage.inputToDo("Buy Sweets");
    toDoPage.inputToDo("Walk the dog");
    toDoPage.inputToDo("Talk to Gary");
    toDoPage.toggleToDoItem(1);
    assertEquals(toDoPage.getLengthOfTodos(), 3);
    toDoPage.clearCompleted();
    assertEquals(toDoPage.getLengthOfTodos(), 2);
}
}