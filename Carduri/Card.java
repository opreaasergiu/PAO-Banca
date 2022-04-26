package Carduri;

import java.util.Scanner;

public class Card {
    private int cvv;
    private long valoare;
    private String numar, nume, iban;
    private final String dataExpirare;

    // Constructor obisnuit
    public Card(String numar, String nume, String dataExpirare, String iban, long valoare, int cvv) {
        this.numar = numar;
        this.cvv = cvv;
        this.nume = nume;
        this.dataExpirare = dataExpirare;
        this.iban = iban;
        this.valoare = valoare;
    }

    // Citirea de la tastatura a unui card
    public Card(Scanner input) {
        System.out.println("Introduceti informatiile cardului:");
        System.out.println("Numar card: ");
        this.numar = input.nextLine();
        System.out.println("CVV: ");
        this.cvv = Integer.parseInt(input.nextLine());
        System.out.println("Dara expirarii: ");
        this.dataExpirare = input.nextLine();
        System.out.println("IBAN: ");
        this.iban = input.nextLine();
        System.out.println("Valoare: ");
        this.valoare = Integer.parseInt(input.nextLine());
    }

    @Override
    public String toString() {
        return String.format("%s%d%s%s%s%d",
                "Informatii card:\nNumar card: " + numar + "\nCVV: " + this.cvv + "\nNume detinator: " + this.nume
                        + "\nData expirarii: " + this.dataExpirare + "\nIBAN: " + this.iban + "\nValoare: "
                        + this.valoare + "\n");
    }

    public String getNumar() {
        return this.numar;
    }

    public int getCVV() {
        return this.cvv;
    }

    public String getNume() {
        return this.nume;
    }

    public String getDataExpirarii() {
        return this.dataExpirare;
    }

    public String getIban() {
        return this.iban;
    }

    public long getValoare() {
        return this.valoare;
    }

}