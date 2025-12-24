package stegdefinitioner;

import io.cucumber.java.sv.Givet;
import io.cucumber.java.sv.När;
import io.cucumber.java.sv.Så;

public class AllaDelmoment {

    @Givet("att användaren är på registreringssidan")
    public void användarenArPaRegistreringssidan() {
        System.out.println("Användaren är på registreringssidan");
    }

    @När("användaren fyller i giltiga uppgifter")
    public void användarenFyllerIGiltigaUppgifter() {
        System.out.println("Användaren fyller i giltiga uppgifter");
    }

    @Så("ska kontot skapas")
    public void skaKontotSkapas() {
        System.out.println("Kontot skapades");
    }
}
