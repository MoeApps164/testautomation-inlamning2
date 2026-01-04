package stegdefinitioner;
//Cucumber-importer (Given / When / Then / And)
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
//JUnit – Assertions för sant eller falsk och om testet lyckades
import org.junit.jupiter.api.Assertions;
//Selenium – väntelogik  används för att programmet ska vänta på elementen innan klick
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//Duration används för att sätta hur länge Selenium ska vänta,
import java.time.Duration;

public class AllaDelmoment {
    // Här sparar vi webbläsaren (Chrome, Edge eller Firefox)
    // WebDriver används för att styra webbläsaren,exempel klicka och skriva text
    private WebDriver driver;
    // Adressen till registreringssidan som ska testas
    private final String registerUrl =
            "file:///C:/Users/kaldi/IdeaProjects/Inlammning/src/test/resources/html/Register.html";

    @Given("the user is on the registration page")
    public void the_user_is_on_the_registration_page() {
        // Hämtar webbläsaren som startas i Hooks-klassen
        driver = Hooks.driver;
        // Öppnar registreringssidan i webbläsaren
        driver.get(registerUrl);
    }

    @When("the user enters {string} {string} {string} {string} {string} {string}")

    public void the_user_enters(String firstname, String lastname, String email, String confirmEmail, String password, String confirmPassword) {
        // Fyller i födelsedatum
        skriv(By.id("dp"), "06/10/1993");
        // Fyller i förnamn
        skriv(By.id("member_firstname"), firstname);
        // Fyller i efternamn
        skriv(By.id("member_lastname"), lastname);
        // Fyller i e-postadress
        skriv(By.id("member_emailaddress"), email);
        // Fyller i e-postadress
        skriv(By.id("member_confirmemailaddress"), confirmEmail);
        // Fyller i lösenord
        skriv(By.id("signupunlicenced_password"), password);
        // Bekräftar lösenord
        skriv(By.id("signupunlicenced_confirmpassword"), confirmPassword);

        // Trycker tab för att stänga datumfältet och trigga eventuell validering
        driver.findElement(By.id("dp")).sendKeys(Keys.TAB);
    }

    @When("the user fills in all required fields correctly")
    public void the_user_fills_in_all_required_fields_correctly() {
        // Använder metoden ovan  the_user_enters
        // Här skickar vi in giltiga värden så att kontot ska kunna skapas
        the_user_enters("Kalle", "Test", "kalle@test.se", "kalle@test.se", "Test1234!", "Test1234!");
    }

    @And("the user accepts the terms")
    public void the_user_accepts_the_terms() {
        // Den här metoden klickar inte själv på checkboxen.
        // Den anropar istället metoden the_user_accepts_the_terms_state
        // där själva klick-logiken finns.

        // Vi skickar in värdet accepts som betyder
        // att användaren godkänner villkoren.
        the_user_accepts_the_terms_state("accepts");
    }

    @And("the user accepts the terms {string}")
    public void the_user_accepts_the_terms_state(String termsState) {
        // Först väljer vi en roll (basketball role).
        // Detta är ett obligatoriskt val för att kunna fortsätta registreringen.
        klickaLabelFor("signup_basketballrole_19");

        // Här kontrollerar vi vilket värde som skickats in från feature-filen.
        // Om värdet är "accepts" ska användaren godkänna alla villkor.

        if (termsState.equalsIgnoreCase("accepts")) {
            // Klickar på checkboxen för användarvillkor
            klickaLabelFor("sign_up_25");

            // Klickar på checkboxen för åldersbekräftelse
            klickaLabelFor("sign_up_26"); // age

            // Klickar på checkboxen för etik och uppförandekod
            klickaLabelFor("fanmembersignup_agreetocodeofethicsandconduct");
        }
        // Om värdet istället är t.ex. "does not accept"
        // klickas inga villkor alls.
        // Detta används för att testa att registreringen inte ska gå igenom.
    }

    @Then("the account should be created")
    public void the_account_should_be_created() {
// Klickar på submit-knappen för att skicka registreringsformuläret.
        // Knappen letas upp igen här för att undvika "stale element"-fel
        // (som kan uppstå om sidan har uppdaterats).
        // Knappen letas upp igen här för att undvika "stale element"-fel
        klicka(By.cssSelector("input[type='submit'][name='join']"));
        // Skapar en väntan som kan vänta upp till 10 sekunder
        // så att sidan hinner navigera till rätt resultat.

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Skapar en väntan som kan vänta upp till 10 sekunder
        // så att sidan hinner navigera till rätt resultat.
        wait.until(d -> d.getCurrentUrl().contains("Success.html"));
        // Väntar tills webbadressen innehåller "Success.html"
        // vilket betyder att kontot skapades korrekt.

        Assertions.assertTrue(driver.getCurrentUrl().contains("Success.html"),
                "Expected Success.html but got: " + driver.getCurrentUrl());
    }

    @Then("an error message should be shown")
    public void an_error_message_should_be_shown() {
        // Klickar på submit-knappen för att försöka skapa kontot
        // trots att alla obligatoriska uppgifter inte är korrekt ifyllda

        klicka(By.cssSelector("input[type='submit'][name='join']"));

// Kontrollerar att vi INTE hamnar på Success.html.
        // Om vi ändå hamnar där betyder det att formuläret
        // accepterade felaktig eller ofullständig inmatning.
        Assertions.assertFalse(
                driver.getCurrentUrl().contains("Success.html"),
                "Should not succeed, but ended up at: " + driver.getCurrentUrl()
        );
    }

    // ===== väntar och hjälpfunktioner =====
    private WebElement waitClickable(By locator) {

        // Skapar en väntan som kan vänta upp till 10 sekunder
        // Detta används för att ge sidan tid att ladda klart
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Väntar tills elementet:
        // - finns på sidan
        // - är synligt
        // - går att klicka på
        // När det är redo returneras elementet
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    private void skriv(By locator, String text) {
        // Väntar tills elementet finns och går att klicka på
        WebElement e = waitClickable(locator);
        // Rensar fältet så att ingen gammal text finns kvar
        e.clear();
        // Skriver in texten som skickas in till metoden
        // Detta motsvarar att användaren skriver på tangentbordet
        e.sendKeys(text);
    }

    private void klicka(By locator) {
        // Väntar tills elementet finns och går att klicka på
        // och klickar sedan på elementet
        waitClickable(locator).click();
    }

    private void klickaLabelFor(String inputId) {
        // Skapar en locator som pekar på en <label>
        // som hör ihop med ett input-fält via attributet "for"
        By label = By.cssSelector("label[for='" + inputId + "']");

        // Väntar tills labeln går att klicka på och klickar sedan
        waitClickable(label).click();
    }
}
