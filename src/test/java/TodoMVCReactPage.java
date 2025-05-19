import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TodoMVCReactPage {
    protected WebDriver driver;
    private By todoBoxBy = By.id("todo-input");

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

}