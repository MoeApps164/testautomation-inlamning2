package stegdefinitioner;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Hooks {
    // Delad WebDriver som alla stegdefinitioner använder
    public static WebDriver driver;

    @Before
    public void startaBrowser() {


        // Hämtar vilken webbläsare som ska användas.
        // Om inget anges används Chrome som standard.
        String browser = System.getProperty("browser", "chrome").toLowerCase();

        // Skriver ut vilken webbläsare som används (bra för felsökning)
        System.out.println("BROWSER = " + browser);

        // Om Firefox valts, starta Firefox
        if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();

        }
        // Annars används Chrome (standard)
        else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
    }
    // Paus så att man hinner se resultatet i webbläsaren
    // (t.ex. Success-sidan eller valideringsfel)
    @After
    public void tearDown() {
        try {
            Thread.sleep(3000); // ⏸️ 3 sekunder paus så läraren ser resultatet
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Stänger webbläsaren efter varje scenario
        driver.quit();
    }
}
