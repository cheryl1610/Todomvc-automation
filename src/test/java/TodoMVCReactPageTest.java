import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

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
    public void shouldFindSearchResultsForJava() {
        TodoMVCReactPage searchPage = new TodoMVCReactPage(driver);
        searchPage.navigate();
        searchPage.inputToDo("java");
    }
}