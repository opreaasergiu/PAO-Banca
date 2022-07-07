package Carduri;

import java.util.Scanner;

public class Visa extends Card {
    // Utilitatea seriei urmeaza sa fie implemtata
    private String seria;

    public Visa(String numarCard, int cvv, String numeClient, String dataExpirare,
            String iban, long valoare, String seria) {
        super(numarCard, numeClient, dataExpirare, iban, valoare, cvv);
        this.seria = seria;
    }

    public Visa(Scanner input) {
        super(input);
        System.out.println("Introduceti informatii Visa:");
        System.out.println("Seria: ");
        this.seria = input.nextLine();
    }

    @Override
    public String toString() {
        String infoVisa = super.toString();
        return String.format(infoVisa, "Seria: " + seria + "\n");
    }

    public String getSeria() {
        return this.seria;
    }

}