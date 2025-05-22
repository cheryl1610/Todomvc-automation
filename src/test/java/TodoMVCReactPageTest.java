import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals("Buy Sweets", todopage.getToDoItemTextAtIndex(1));

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
        System.out.println(toDoPage.getToDoItemTextAtIndex(3));
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
        assertEquals("@£%*!^?~", todopage.getToDoItemTextAtIndex(1));

    }

    @Test
    public void shouldAddTwoCharactersToTheToDoList() {
        TodoMVCReactPage toDoPage = new TodoMVCReactPage(driver);
        toDoPage.navigate();
        toDoPage.inputToDo("AB");
        assertEquals("AB", toDoPage.getToDoItemTextAtIndex(1));

    }
    @Test
    public void shouldAddOneCharactersToTheToDoList() {
        TodoMVCReactPage toDoPage = new TodoMVCReactPage(driver);
        toDoPage.navigate();
        toDoPage.inputToDo("A");
        assertEquals("A", toDoPage.getToDoItemTextAtIndex(1));
/// known failure
    }



    @Test
    public void modifyAddTextFirstItemToTheToDoList() {
        TodoMVCReactPage todoPage = new TodoMVCReactPage(driver);
        todoPage.navigate();
        todoPage.inputToDo("Buy Sweets");
        todoPage.addTextToTodoItem(1, " now!");
        assertEquals("Buy Sweets now!", todoPage.getToDoItemTextAtIndex(1));


    }

    @Test
    public void escModifyItemToDoList()  {
        TodoMVCReactPage todoPage = new TodoMVCReactPage(driver);
        todoPage.navigate();
        todoPage.inputToDo("Buy Sweets");
        todoPage.cancelEditTodoItem(1,"now");
      assertEquals("Buy Sweets", todoPage.getToDoItemTextAtIndex(1));
      /// This is an expected fail as the esc doesn't esc the edit mode
    }
    @Test
    public void clickOutModifyItemToDoList()  {
        TodoMVCReactPage todoPage = new TodoMVCReactPage(driver);
        todoPage.navigate();
        todoPage.inputToDo("Buy Sweets");
        todoPage.clickOutToDoItem("now");
        assertEquals("Buy Sweets", todoPage.getToDoItemTextAtIndex(1));}

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
    toDoPage.clickDeleteItem(1);
    TodoMVCReactPage.takeScreenshot(driver,"deleted.png");
    assertEquals(1, toDoPage.getLengthOfTodos());
}
@Test
    public void checkStatusBar(){
        TodoMVCReactPage todopage = new TodoMVCReactPage(driver);
        todopage.navigate();
        todopage.inputToDo("Buy Sweets");
        todopage.inputToDo("Walk the dog");
        todopage.inputToDo("Talk to Gary");
        assertEquals("3 items left!", todopage.getItemsLeft());

    }
   @Test
   public void checkStatusBarOne(){
       TodoMVCReactPage todopage = new TodoMVCReactPage(driver);
       todopage.navigate();
       todopage.inputToDo("Buy Sweets");
       assertEquals("1 item left!", todopage.getItemsLeft());

   }
    @Test
    public void oneLeftToDoStatusCheck() throws Exception {
        TodoMVCReactPage toDoPage = new TodoMVCReactPage(driver);
        toDoPage.navigate();
        toDoPage.inputToDo("Buy Sweets");
        toDoPage.inputToDo("Walk the dog");
        toDoPage.inputToDo("Read a book");
        toDoPage.clickDeleteItem(1);
        TodoMVCReactPage.takeScreenshot(driver,"deleted.png");
        assertEquals("2 items left!", toDoPage.getItemsLeft());
    }

@Test
    public void activeTabCheck()throws Exception {
    TodoMVCReactPage toDoPage = new TodoMVCReactPage(driver);
    toDoPage.navigate();
    toDoPage.inputToDo("Buy Sweets");
    toDoPage.inputToDo("Walk the dog");
    toDoPage.toggleToDoItem(1);
    toDoPage.clickActive();
    TodoMVCReactPage.takeScreenshot(driver,"activeView.png");
    assertEquals("Walk the dog", toDoPage.getToDoItemTextAtIndex(1));


}


}