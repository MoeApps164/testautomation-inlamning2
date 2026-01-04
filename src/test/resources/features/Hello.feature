Feature: Create account
# Feature beskriver vad som testas på en övergripande nivå.
# Här testar vi funktionen att skapa ett konto.

  Scenario: Create account with valid data
# Detta scenario testar det lyckade flödet.
# Alla obligatoriska fält fylls i korrekt
# och användaren ska hamna på Success.html.

    Given the user is on the registration page
  # Öppnar registreringssidan innan testet startar

    When the user fills in all required fields correctly
  # Fyller i alla obligatoriska fält med giltiga värden

    And the user accepts the terms
  # Användaren godkänner villkoren

    Then the account should be created
  # Testet kontrollerar att kontot faktiskt skapas
  # genom att verifiera att Success.html visas


  Scenario Outline: Create account - validation errors
# Detta scenario testar negativa fall (validering).
# Kontot ska INTE skapas när något är fel eller saknas.

    Given the user is on the registration page
  # Startar alltid från registreringssidan

    When the user enters "<firstname>" "<lastname>" "<email>" "<confirmEmail>" "<password>" "<confirmPassword>"
  # Fyller i formuläret med olika kombinationer av felaktig data

    And the user accepts the terms "<termsState>"
  # Beroende på testfall godkänns eller godkänns inte villkoren

    Then an error message should be shown
  # Testet kontrollerar att kontot inte skapas
  # (dvs att man inte hamnar på Success.html)


    Examples:
# Tabellen innehåller olika testdata som Scenario Outline kör igenom.
# Varje rad är ett eget testfall.

      | firstname | lastname | email      | confirmEmail | password  | confirmPassword | termsState        |
      | Kalle     |          | k1@test.se | k1@test.se   | Test1234! | Test1234!       | accepts           |
# Saknar efternamn → ska misslyckas

      | Kalle     | Test     | k2@test.se | k2@test.se   | Test1234! | ANNAT1234!      | accepts           |
# Lösenorden matchar inte → ska misslyckas

      | Kalle     | Test     | k3@test.se | k3@test.se   | Test1234! | Test1234!       | does not accept   |
# Villkoren godkänns inte → ska misslyckas