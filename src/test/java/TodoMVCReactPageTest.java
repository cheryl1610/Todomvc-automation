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
        TodoMVCReactPage todopage = new TodoMVCReactPage(driver);
        todopage.navigate();
        todopage.inputToDo("Buy Sweets");
        assertEquals("Buy Sweets", todopage.getFirstToDoItem());

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
        assertEquals(5, toDoPage.getLengthOfTodos());
    }

    @Test
    public void shouldAddEmptyItemToTheToDoList() {
        TodoMVCReactPage toDoPage = new TodoMVCReactPage(driver);
        toDoPage.navigate();
        toDoPage.inputToDo("");
        assertEquals(0, toDoPage.getLengthOfTodos());
    }

    @Test
    public void shouldAddSpecialCharactersToTheToDoList() {
        TodoMVCReactPage todopage = new TodoMVCReactPage(driver);
        todopage.navigate();
        todopage.inputToDo("@£%*!^?~");
        assertEquals("@£%*!^?~", todopage.getFirstToDoItem());

    }

    @Test
    public void shouldAddTwoCharactersToTheToDoList() {
        TodoMVCReactPage toDoPage = new TodoMVCReactPage(driver);
        toDoPage.navigate();
        toDoPage.inputToDo("AB");
        assertEquals("AB", toDoPage.getFirstToDoItem());

    }
    @Test
    public void shouldAddOneCharactersToTheToDoList() {
        TodoMVCReactPage toDoPage = new TodoMVCReactPage(driver);
        toDoPage.navigate();
        toDoPage.inputToDo("A");
        assertEquals("A", toDoPage.getFirstToDoItem());
/// known failure
    }



    @Test
    public void modifyAddTextFirstItemToTheToDoList() {
        TodoMVCReactPage todoPage = new TodoMVCReactPage(driver);
        todoPage.navigate();
        todoPage.inputToDo("Buy Sweets");
        todoPage.addTextToFirstTodoItem(" now!");
        assertEquals("Buy Sweets now!", todoPage.getFirstToDoItem());



    }

    @Test
    public void escModifyItemToDoList()  {
        TodoMVCReactPage todoPage = new TodoMVCReactPage(driver);
        todoPage.navigate();
        todoPage.inputToDo("Buy Sweets");
        todoPage.cancelEditFirstTodoItem("now");
      assertEquals("Buy Sweets", todoPage.getFirstToDoItem());
      /// This is an expected fail as the esc doesn't esc the edit mode
    }
    @Test
    public void clickOutModifyItemToDoList()  {
        TodoMVCReactPage todoPage = new TodoMVCReactPage(driver);
        todoPage.navigate();
        todoPage.inputToDo("Buy Sweets");
        todoPage.clickOutToDoItem("now");
        assertEquals("Buy Sweets", todoPage.getFirstToDoItem());}

    @Test
    public void markItemComplete(){
        TodoMVCReactPage todopage = new TodoMVCReactPage(driver);
        todopage.navigate();
        todopage.inputToDo("Buy Sweets");
        todopage.inputToDo("Walk the dog");
        todopage.inputToDo("Talk to Gary");
        todopage.toggleToDoItem(1);
        assertEquals("2 items left!", todopage.getItemsLeft());
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
        assertEquals("3 items left!", todopage.getItemsLeft());
}
@Test
    public void clearCompletedItems(){
    TodoMVCReactPage toDoPage = new TodoMVCReactPage(driver);
    toDoPage.navigate();
    toDoPage.inputToDo("Buy Sweets");
    toDoPage.inputToDo("Walk the dog");
    toDoPage.inputToDo("Talk to Gary");
    toDoPage.toggleToDoItem(1);
    assertEquals(3, toDoPage.getLengthOfTodos());
    toDoPage.clearCompleted();
    assertEquals(2, toDoPage.getLengthOfTodos());

}
@Test
    public void deletedFirstItem() throws Exception {
    TodoMVCReactPage toDoPage = new TodoMVCReactPage(driver);
    toDoPage.navigate();
    toDoPage.inputToDo("Buy Sweets");
    toDoPage.inputToDo("Walk the dog");
    toDoPage.clickDeleteFirstItem();
    TodoMVCReactPage.takeScreenshot(driver,"deleted.png");
    assertEquals(1, toDoPage.getLengthOfTodos());
}
}