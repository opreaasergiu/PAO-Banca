import java.text.SimpleDateFormat;
import java.util.Scanner;

import Clienti.*;
import Carduri.*;
import Conturi.*;

public class Main {

    private static final String[] operatiuni = {
            "0 - Iesire",
            "1 - Creare client",
            "2 - Creare card",
            "3 - Creare cont",
            "4 - Adauga bunuri in cont",
            "5 - Adauga card in cont",
            "6 - Adauga tranzactie in cont",
            "7 - Afiseaza valoarea contului",
            "8 - Afiseaza informatii clienti",
            "9 - Afiseaza informatii cont",
            "10 - Afiseaza tranzactii",
            "11 - Inchide cont"
    };

    private static void afiseazaOperatiuni() {
        System.out.println("Alege operatiunea bancara...");
        for (String operatiune : operatiuni) {
            System.out.println(operatiune);
        }

    }

    public static void main(String[] args) throws Exception {
        int ruleaza = 1;
        Scanner input = new Scanner(System.in);
        OperatiuniBancare operatiuneBancara = new OperatiuniBancare();
        afiseazaOperatiuni();

        // Construium tipurile de variabile clasa
        Client[] clienti = new Client[128];
        Card[] carduri = new Card[128];
        Tranzactie[] tranzactii = new Tranzactie[128];
        Cont[] conturi = new Cont[128];

        Client client;
        Card card;
        Cont cont;
        Tranzactie tranzactie;
        Bunuri bunuri;

        // Construim variabile "defailt" pentru a face operatiuni cu ele
        Client clientObisnuit = new Client("client@yahoo.com", new Adresa("Cluj", "Nicolae Iorga", 16));

        Client clientFizica = new PersoanaFizica("fizica@gmail.com", new Adresa("Iasi", "Decebal", 1), "Ghiveci",
                "Mircea", "5010616134160", "0720865324");
        Client clientJuridica = new PersoanaJuridica("companie@outlook.com", new Adresa("Bucuresti", "Unirii", 15),
                "Serele S.A.", "5674", "352523", "4864574567", "0341441262");

        Card cardObisnuit = new Visa("533155495849548", 234, "Oprea", "08/23", "RO0439509346", 299, "RCKS");
        Card cardVisa = new Mastercard("53347436748", 633, "David", "4/22", "RO346346347", 14856, "RZRR");
        Card cardMastercard = new Mastercard("533275648", 633, "Cristian", "4/22", "RO9764327", 14856, "RZCR");

        Bunuri bun = new Bunuri("Lant aur", 1500, clientFizica.getUUID());

        Cont cont1 = new Cont(clientFizica, "RO34084360634", "activ");
        Cont cont2 = new Cont(clientObisnuit, "RO34098683460235", "activ");

        Tranzactie tranzactie1 = new Tranzactie(cont2.getIBAN(), cont1.getIBAN(), 13, "Datoria",
                new SimpleDateFormat("dd/mm/yyyy").parse("27/04/2022"));

        while (ruleaza != 0) {
            System.out.println("Alege operatiunea bancara...");
            int actionNumber = Integer.parseInt(input.nextLine());

            if (actionNumber < 0 || actionNumber > 11) {
                System.out.println("Operatiune bancara invalida...");
                break;

            }

            switch (actionNumber) {
                case 0 -> {
                    ruleaza = 0;
                    System.out.println("Iesire...");
                }
                case 1 -> client = operatiuneBancara.creazaClient();
                case 2 -> card = operatiuneBancara.creazaCard();
                case 3 -> {
                    System.out.println("Iban:");
                    String iban = input.nextLine();
                    System.out.println("Status:");
                    String status = input.nextLine();
                    cont = operatiuneBancara.creazaCont(clientObisnuit, iban, status);
                    System.out.println(cont.toString());

                }
                case 4 -> {
                    operatiuneBancara.adaugaBunuri(cont2, bun);
                    operatiuneBancara.afiseazaBunuri(cont2);
                }
                case 5 -> {
                    operatiuneBancara.adaugaCard(cont1, cardVisa);
                    operatiuneBancara.afiseazaCarduri(cont1);
                }
                case 6 -> {
                    operatiuneBancara.adaugaTranzactie(cont1, tranzactie1);
                    // operatiuneBancara.afiseazaTranzactii(cont1);
                }
                case 7 -> System.out.println(operatiuneBancara.currentAmount(cont2));
                case 8 -> operatiuneBancara.afiseazaInfoClient(clientFizica);
                case 9 -> operatiuneBancara.afiseazaInfoCont(cont2);
                case 10 -> operatiuneBancara.afiseazaTranzactii(cont2);
                case 11 -> {
                    operatiuneBancara.inchideCont(cont1);
                    System.out.println(cont1.getStatus());
                }
            }
        }
    }
}
