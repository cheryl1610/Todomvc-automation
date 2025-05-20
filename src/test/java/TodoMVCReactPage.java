import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class TodoMVCReactPage {
    protected WebDriver driver;
    private By todoBoxBy = By.id("todo-input");
    private By firstTodoBy = By.xpath("//*[@class=\"todo-list\"]/li[1]/div/label");
    private By todoItemsBy = By.xpath("//*[@class=\"todo-list\"]/li/div/label");

    public TodoMVCReactPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigate() {
        driver.get("https://todomvc.com/examples/react/dist/");
    }

    public void inputToDo(String toDoItem) {
        WebElement todoBox = driver.findElement(todoBoxBy);
        todoBox.sendKeys(toDoItem);
        todoBox.sendKeys(Keys.ENTER);

    }

    public String getFirstToDoItem() {
        WebElement firstToDo = driver.findElement(firstTodoBy);
        return firstToDo.getText();
    }

    public int getLengthOfTodos() {
        return driver.findElements(todoItemsBy).size();
    }

    public void doubleClickFirstTodoItem() {
        WebElement firstItem = driver.findElement(By.cssSelector(".todo-list li:first-child label"));
        Actions actions = new Actions(driver);
        actions.doubleClick(firstItem).perform();
    }

    public void toggleToDoItem(int itemIndex) {
        String selector = String.format("li:nth-child(%d) .toggle", itemIndex);
        driver.findElement(By.cssSelector(selector)).click();
}

    public String getItemsLeft (){
        WebElement toDoCount = driver.findElement(By.className("todo-count"));
        return toDoCount.getText();
    }
}