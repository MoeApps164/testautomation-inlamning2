package stegdefinitioner;

import org.junit.Assert;
import io.cucumber.java.sv.Givet;
import io.cucumber.java.sv.När;
import io.cucumber.java.sv.Och;
import io.cucumber.java.sv.Så;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AllaDelmoment {

    private WebDriver driver;
    private final String registerUrl =
            "file:///C:/Users/kaldi/IdeaProjects/Inlammning/src/test/resources/html/Register.html";

    @Givet("att användaren är på registreringssidan")
    public void paRegistreringssidan() throws InterruptedException {
        driver = Hooks.driver; // driver skapas i Hooks @Before
        System.out.println("STEG: ÖPPNAR REGISTERSIDAN");
        driver.get(registerUrl);
        Thread.sleep(1500);
    }

    @När("användaren fyller i alla obligatoriska uppgifter korrekt")
    public void fyllerIKorrekt() {
        driver = Hooks.driver;

        driver.findElement(By.id("member_firstname")).sendKeys("Kalle");
        driver.findElement(By.id("member_lastname")).sendKeys("Test");
        driver.findElement(By.id("member_emailaddress")).sendKeys("kalle@test.se");
        driver.findElement(By.id("member_confirmemailaddress")).sendKeys("kalle@test.se");

        driver.findElement(By.id("signupunlicenced_password")).sendKeys("Test1234!");
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys("Test1234!");
    }

    @Och("användaren godkänner villkoren")
    public void godkannerVillkor() {
        driver = Hooks.driver;
        System.out.println("STEG: GODKÄNNER VILLKOR");

        driver.findElement(By.id("sign_up_25")).click();
        driver.findElement(By.id("sign_up_26")).click();
        driver.findElement(By.id("fanmembersignup_agreetocodeofethicsandconduct")).click();
        driver.findElement(By.id("signup_basketballrole_19")).click();
    }

    @Så("ska kontot skapas")
    public void kontoSkapas() throws InterruptedException {
        driver = Hooks.driver;

        driver.findElement(By.name("join")).click();
        Thread.sleep(1000);

        // Bevis: du hamnar på Success.html
        String url = driver.getCurrentUrl();
        Assert.assertTrue("Förväntade Success.html men fick: " + url, url.contains("Success.html"));
    }


    @När("användaren fyller i alla obligatoriska uppgifter utom efternamn")
    public void saknarEfternamn() {
        driver = Hooks.driver;

        driver.findElement(By.id("member_firstname")).clear();
        driver.findElement(By.id("member_firstname")).sendKeys("Kalle");

        driver.findElement(By.id("member_lastname")).clear();
        // lämnar efternamn tomt

        driver.findElement(By.id("member_emailaddress")).clear();
        driver.findElement(By.id("member_emailaddress")).sendKeys("kalle2@test.se");

        driver.findElement(By.id("member_confirmemailaddress")).clear();
        driver.findElement(By.id("member_confirmemailaddress")).sendKeys("kalle2@test.se");

        driver.findElement(By.id("signupunlicenced_password")).clear();
        driver.findElement(By.id("signupunlicenced_password")).sendKeys("Test1234!");

        driver.findElement(By.id("signupunlicenced_confirmpassword")).clear();
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys("Test1234!");
    }
    @När("användaren fyller i lösenord som inte matchar varandra")
    public void losenordMatcharInte() {
        driver = Hooks.driver;

        driver.findElement(By.id("member_firstname")).clear();
        driver.findElement(By.id("member_firstname")).sendKeys("Kalle");

        driver.findElement(By.id("member_lastname")).clear();
        driver.findElement(By.id("member_lastname")).sendKeys("Test");

        driver.findElement(By.id("member_emailaddress")).clear();
        driver.findElement(By.id("member_emailaddress")).sendKeys("kalle3@test.se");

        driver.findElement(By.id("member_confirmemailaddress")).clear();
        driver.findElement(By.id("member_confirmemailaddress")).sendKeys("kalle3@test.se");

        driver.findElement(By.id("signupunlicenced_password")).clear();
        driver.findElement(By.id("signupunlicenced_password")).sendKeys("Test1234!");

        driver.findElement(By.id("signupunlicenced_confirmpassword")).clear();
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys("ANNAT1234!");
    }

    @Och("användaren godkänner inte villkoren")
    public void godkannerInteVillkor() {
        driver = Hooks.driver;
        // Gör medvetet INGENTING
    }

    @Så("ska ett felmeddelande visas")
    public void felmeddelandeVisas() {
        driver = Hooks.driver;

        // klicka join
        driver.findElement(By.name("join")).click();

        // Bevis: vi ska INTE komma till Success.html
        String url = driver.getCurrentUrl();
        Assert.assertFalse("Borde inte lyckas, men hamnade på: " + url, url.contains("Success.html"));
    }

}