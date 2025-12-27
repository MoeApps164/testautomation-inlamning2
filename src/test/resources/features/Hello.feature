Feature: Skapa konto

  Scenario: Skapa konto med giltiga uppgifter
  Givet att användaren är på registreringssidan
  När användaren fyller i alla obligatoriska uppgifter korrekt
  Och användaren godkänner villkoren
  Så ska kontot skapas


  Scenario: Skapa konto utan efternamn
  Givet att användaren är på registreringssidan
  När användaren fyller i alla obligatoriska uppgifter utom efternamn
  Och användaren godkänner villkoren
  Så ska ett felmeddelande visas


  Scenario: Skapa konto med olika lösenord
  Givet att användaren är på registreringssidan
  När användaren fyller i lösenord som inte matchar varandra
  Och användaren godkänner villkoren
  Så ska ett felmeddelande visas


  Scenario: Skapa konto utan att godkänna villkoren
  Givet att användaren är på registreringssidan
  När användaren fyller i alla obligatoriska uppgifter korrekt
  Och användaren godkänner inte villkoren
  Så ska ett felmeddelande visas