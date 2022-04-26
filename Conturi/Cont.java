package Conturi;

import Carduri.*;
import Clienti.Client;
import java.util.UUID;

public class Cont {
    protected UUID clientUUID;
    protected int numarCarduri = 0, numarBunuri = 0, numarTranzactii = 0;
    protected long valoare;
    protected String iban, status;

    protected Bunuri[] bunuri = new Bunuri[128];
    protected Card[] carduri = new Card[128];
    protected Tranzactie[] tranzactii = new Tranzactie[128];

    public Cont(Client client, String iban, String status) {
        this.clientUUID = client.getUUID();
        this.iban = iban;
        // Status = activ/inchis
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("%s%s%s%s%s",
                "Informatii cont: \nID client: " + this.clientUUID + "\nIBAN: " + this.iban + "\n",
                "Status cont: " + this.status + "\nValoare: " + this.valoare + "\n");
    }

    public UUID getClientUUID() {
        return this.clientUUID;
    }

    public String getIBAN() {
        return this.iban;
    }

    public String getStatus() {
        return this.status;
    }

    public long getValoare() {
        return this.valoare;
    }

    public int getNumarCarduri() {
        return this.numarCarduri;
    }

    public int getNumarBunuri() {
        return this.numarBunuri;
    }

    public int getNumarTranzactii() {
        return this.numarTranzactii;
    }

    public Bunuri[] getBunuri() {
        Bunuri[] bunuriClient = new Bunuri[this.getNumarBunuri()];
        for (int i = 0; i < bunuriClient.length; i++)
            bunuriClient[i] = this.bunuri[i];
        return bunuriClient;
    }

    public void afiseazaBunuri() {
        for (int i = 0; i < numarBunuri; i++) {
            System.out.println(this.bunuri[i].toString());
        }
    }

    public Card[] getCarduri() {
        Card[] carduri = new Card[this.getNumarCarduri()];
        for (int i = 0; i < carduri.length; i++)
            carduri[i] = this.carduri[i];
        return carduri;
    }

    public void afiseazaCarduri() {
        for (int i = 0; i < this.numarCarduri; i++) {
            System.out.println(this.carduri[i].toString());
        }
    }

    // tranzactii
    public Tranzactie[] getTranzactii() {
        Tranzactie[] tranzactii = new Tranzactie[this.numarTranzactii];
        for (int i = 0; i < tranzactii.length; i++)
            tranzactii[i] = this.tranzactii[i];
        return tranzactii;
    }

    public void setStatus(String nouStatus) {
        this.status = nouStatus;
    }

    public void adaugaMastercard(Mastercard mastercard) {
        this.carduri[this.getNumarCarduri()] = mastercard;
        this.valoare += mastercard.getValoare();
        this.numarCarduri++;

    }

    public void adaugaVisa(Visa visa) {
        this.carduri[this.getNumarCarduri()] = visa;
        this.valoare += visa.getValoare();
        this.numarCarduri++;
    }

    public void adaugaBunuri(Bunuri bunuri) {
        this.bunuri[this.numarBunuri] = bunuri;
        this.valoare += bunuri.getValoare();
        this.numarBunuri++;
    }

    public void adaugaTranzactie(Tranzactie tranzactie) {
        this.tranzactii[this.numarTranzactii] = tranzactie;
        this.valoare += tranzactie.getSuma();
        this.numarTranzactii++;

    }

    public void afiseazaTranzactii() {
        for (int i = 0; i < this.numarTranzactii; i++) {
            System.out.println(this.tranzactii[i].toString());
        }
    }

}
