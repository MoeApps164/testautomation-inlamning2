package stegdefinitioner;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void startaBrowser() {
        System.out.println("HOOK: STARTAR CHROME");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @After
    public void stangBrowser() throws InterruptedException {
        System.out.println("HOOK: STÄNGER CHROME");
        Thread.sleep(1500);
        if (driver != null) {
            driver.quit();
        }
    }
}
