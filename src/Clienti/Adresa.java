package Clienti;

import java.util.Scanner;

public class Adresa {

    private String strada, oras;
    private int numar;

    public Adresa(String oras, String strada, int numar) {
        this.strada = strada;
        this.oras = oras;
        this.numar = numar;
    }

    // Citirea adresei de la tastatura
    public Adresa(Scanner scanner) {
        System.out.println("Scrieti adresa...");
        System.out.println("Oras: ");
        this.oras = scanner.nextLine();

        System.out.println("Strada: ");
        this.strada = scanner.nextLine();

        System.out.println("Numar: ");
        this.numar = Integer.parseInt(scanner.nextLine());
    }

    @Override
    public String toString() {
        return String.format("Orasul " + this.oras + " Strada " + this.strada + " Numarul " + Integer.toString(this.numar));
    }
}
