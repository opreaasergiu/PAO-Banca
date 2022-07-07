package Clienti;

import java.util.Scanner;
import java.util.UUID;

public class Client {
    // cream UUID pentru fiecare client
    UUID clientUUID;
    private String email;
    private String telefon;
    private Adresa adresa;

    public Client(String email, Adresa adresa/* , String telefon */) {
        this.clientUUID = UUID.randomUUID();
        this.email = email;
        this.adresa = adresa;
        // this.telefon = telefon;
    }

    // Citirea clientului de la tastatura
    public Client(Scanner scanner) {
        System.out.println("Introduceti informatiile clientului:");
        this.clientUUID = UUID.randomUUID();
        System.out.println("Email: ");
        this.email = scanner.nextLine();
        // System.out.println("Numar de telefon: ");
        // this.telefon = scanner.nextLine();
        this.adresa = new Adresa(scanner);
    }

    @Override
    public String toString() {
        return String.format("%s%s%s%s%s",
                "Informatii client: \n",
                "ID: " + clientUUID + "\n",
                "Email: " + this.email + "\n",
                "Telefon :" + this.telefon + "\n",
                "Adresa: " + this.adresa);
    }

    // getteri
    public UUID getUUID() {
        return this.clientUUID;
    }

    public String getEmail() {
        return this.email;
    }

    public Adresa getAdresa() {
        return this.adresa;
    }

    public String getTelefon() {
        return this.telefon;
    }

}
