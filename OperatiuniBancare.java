
import Carduri.*;
import Clienti.*;
import Conturi.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.nio.file.*;

public class OperatiuniBancare {
    // Constructor client

    public Client creazaClient() throws Exception {
        System.out.println("Tip client: persoana fizica sau juridica");
        Scanner input = new Scanner(System.in);
        String tip = input.nextLine();

        Client client;
        switch (tip) {
            case "fizica" -> {
                client = creazaPersoanaFizica(input);
                System.out.println(client.toString());
            }
            case "juridica" -> {
                client = creazaPersoanaJuridica(input);
                System.out.println(client.toString());
            }
            default -> throw new Exception("Tip invalid de client.");
        }

        // Scriere in fisier .csv
        FileWriter clientiCSV = new FileWriter("clienti.csv", true);
        PrintWriter out = new PrintWriter(clientiCSV);

        if (client.getClass() == PersoanaFizica.class) {
            out.printf("%s,%s,%s\n", client.getUUID(), client.getEmail(), client.getTelefon());
        } else if (client.getClass() == PersoanaJuridica.class) {
            out.printf("%s,%s,%s\n", client.getUUID(), client.getEmail(), client.getTelefon());
        }

        out.close();

        return client;
    }

    public Client creazaClient(Scanner input) {
        return new Client(input);
    }

    public PersoanaFizica creazaPersoanaFizica(Scanner input) {
        return new PersoanaFizica(input);
    }

    public PersoanaJuridica creazaPersoanaJuridica(Scanner input) {
        return new PersoanaJuridica(input);
    }

    // Constructor tipuri card
    public Card creazaCard() throws Exception {
        System.out.println("Tip card: visa, mastercard.");
        Scanner input = new Scanner(System.in);
        String tip = input.nextLine();
        Card card;
        if (tip.equals("visa")) {
            card = new Visa(input);

        } else if (tip.equals("mastercard")) {
            card = new Mastercard(input);
        } else
            throw new Exception("Tip invalid de card.");

        System.out.println(card.toString());

        // Introducere informatii card in fisier .csv
        FileWriter cardCSV = new FileWriter("carduri.csv", true);
        PrintWriter out = new PrintWriter(cardCSV);

        out.printf("%d,%s,%d,%s\n", card.getNumar(), card.getIban(), card.getCVV(), card.getDataExpirarii());
        out.close();

        return card;
    }

    // Creare cont pentru client
    public Cont creazaCont(Client client, String iban, String status) throws IOException {

        FileWriter contCSV = new FileWriter("conturi.csv", true);
        PrintWriter out = new PrintWriter(contCSV);

        Cont cont = new Cont(client, iban, status);

        out.printf("%s,%s,%d,%d,%d,%d,%s\n", cont.getClientUUID().toString(), cont.getIBAN(), cont.getNumarCarduri(),
                cont.getNumarBunuri(), cont.getNumarTranzactii(), cont.getValoare(), cont.getStatus());
        out.close();

        return cont;
    }

    // Adauga card in contul clientului
    public void adaugaCard(Cont cont, Card card) {
        if (card.getClass() == Visa.class) {
            cont.adaugaVisa((Visa) card);
        } else if (card.getClass() == Mastercard.class) {
            cont.adaugaMastercard((Mastercard) card);
        }

    }

    // Adauga bunuri in contul clientului
    public void adaugaBunuri(Cont cont, Bunuri bunuri) {
        cont.adaugaBunuri(bunuri);
    }

    // Adauga tranzactie in contul clientului
    public void adaugaTranzactie(Cont cont, Tranzactie tranzactie) throws IOException {

        // Introducere informatii card in fisier .csv
        FileWriter tranzactieCSV = new FileWriter("tranzactii.csv", true);
        PrintWriter out = new PrintWriter(tranzactieCSV);

        out.printf("%s,%s,%s,%d,%s\n", tranzactie.getExpeditorIBAN(), tranzactie.getDestinatarIBAN(),
                tranzactie.getDataTranzactie().toString(), tranzactie.getSuma(), tranzactie.getDescriere());
        out.close();

        cont.adaugaTranzactie(tranzactie);
    }

    // Afiseaza informatii despre utilizator
    public void afiseazaInfoClient(Client client) {
        if (client.getClass() == PersoanaFizica.class) {
            System.out.println(((PersoanaFizica) client).toString());
        } else if (client.getClass() == PersoanaJuridica.class) {
            System.out.println(((PersoanaJuridica) client).toString());
        }
    }

    // Afiseaza suma de bani a unui cont
    public long currentAmount(Cont cont) {
        return cont.getValoare();
    }

    public Tranzactie[] transactions(Cont cont) {
        return cont.getTranzactii();
    }

    // Afisare tranzactii
    public void afiseazaTranzactii(Cont cont) {
        cont.afiseazaTranzactii();
    }

    // Afisare carduri
    public void afiseazaCarduri(Cont cont) {
        cont.afiseazaCarduri();
    }

    // Afisare bunuri
    public void afiseazaBunuri(Cont cont) {
        cont.afiseazaBunuri();
    }

    // Afisare informatii cont
    public void afiseazaInfoCont(Cont cont) {
        System.out.println(cont.toString());
    }

    // Scgumba status cont -> inchis
    public void inchideCont(Cont cont) {
        cont.setStatus("inchis");
    }

}
