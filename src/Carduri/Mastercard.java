package Carduri;

import java.util.Scanner;

public class Mastercard extends Card {
    // Utilitatea seriei urmeaza sa fie implemtata
    private String seria;

    public Mastercard(String numarCard, int cvv, String nume, String expirareData,
            String iban, long valoare, String seria) {
        super(numarCard, nume, expirareData, iban, valoare, cvv);
        this.seria = seria;
    }

    public Mastercard(Scanner input) {
        super(input);
        System.out.println("Introduceti informatii MasterCard:");
    }

    @Override
    public String toString() {
        String infoMasterCard = super.toString();
        return String.format("%s%s", infoMasterCard, "Seria : " + seria + "\n");
    }

    public String getSeria() {
        return this.seria;
    }

}
