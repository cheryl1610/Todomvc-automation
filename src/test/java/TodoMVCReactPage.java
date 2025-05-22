import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.File;

public class TodoMVCReactPage {
    protected WebDriver driver;
    private By todoBoxBy = By.id("todo-input");
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

    public String getToDoItemTextAtIndex(int index) {
        // index is 1-based: 1 = first item, 2 = second item, etc.
        String selector = "ul.todo-list li:nth-child(" + index + ") label";
        WebElement todoItem = driver.findElement(By.cssSelector(selector));
        return todoItem.getText();
    }

    public int getLengthOfTodos() {
        return driver.findElements(todoItemsBy).size();
    }


    public void addTextToTodoItem(int index, String text) {
        String selector = "ul.todo-list li:nth-child(" + index + ") label";
        WebElement todoItem = driver.findElement(By.cssSelector(selector));
        Actions actions = new Actions(driver);
        actions.doubleClick(todoItem).sendKeys(text).sendKeys(Keys.ENTER).perform();

    }

    public void cancelEditTodoItem(int index, String text) {
        String selector = "ul.todo-list li:nth-child(" + index + ") label";
        WebElement todoItem = driver.findElement(By.cssSelector(selector));
        Actions actions = new Actions(driver);
        actions.doubleClick(todoItem)
                .sendKeys(text)
                .sendKeys(Keys.ESCAPE).perform();
    }


    public void toggleToDoItem(int itemIndex) {
        String selector = String.format("li:nth-child(%d) .toggle", itemIndex);
        driver.findElement(By.cssSelector(selector)).click();
    }

    public String getItemsLeft() {
        WebElement toDoCount = driver.findElement(By.className("todo-count"));
        return toDoCount.getText();
    }

    public void clearCompleted() {
        WebElement clearComplete = driver.findElement(By.className("clear-completed"));
        clearComplete.click();
    }

    public void clickOutToDoItem(String text) {
        WebElement firstItemLabel = driver.findElement(By.cssSelector(".todo-list li:first-child label"));
        Actions actions = new Actions(driver);
        actions.doubleClick(firstItemLabel)
                .sendKeys(text);
        WebElement clickable = driver.findElement(By.cssSelector("html"));
        new Actions(driver)
                .click(clickable)
                .perform();
    }

    public void clickDeleteItem(int index) {
        String selector = "ul.todo-list li:nth-child(" + index + ") label";
        WebElement hoverable = driver.findElement(By.cssSelector(selector));
        new Actions(driver)
                .moveToElement(hoverable)
                .perform();
        String deleteButtonSelector = ".todo-list li:nth-child(" + index + ") button.destroy";
        WebElement clickable = driver.findElement(By.cssSelector(deleteButtonSelector));
        new Actions(driver)
                .click(clickable)
                .perform();
    }

    public void clickActive() {
        WebElement active = driver.findElement(By.linkText("Active"));
        new Actions(driver)
                .click(active)
                .perform();
    }


    public static void takeScreenshot(WebDriver webdriver, String desiredPath) throws Exception {
        TakesScreenshot screenshot = ((TakesScreenshot) webdriver);
        File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);
        File targetFile = new File(desiredPath);
        FileUtils.copyFile(screenshotFile, targetFile);
    }

}
