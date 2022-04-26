package Conturi;

import java.util.UUID;

public class Bunuri {
    private UUID clientUUID;
    private String nume;
    private long valoare;

    public Bunuri(String nume, long valoare, UUID clientUUID) {
        this.nume = nume;
        this.valoare = valoare;
        this.clientUUID = clientUUID;
    }

    public String getNume() {
        return this.nume;
    }

    public long getValoare() {
        return this.valoare;
    }

    public UUID getClientUUID() {
        return this.clientUUID;
    }

    @Override
    public String toString() {
        return String.format("%s%s%s",
                "Informatii proprietate:\nNume: " + this.nume + "\nValoare: " + this.valoare + "\nID client: "
                        + this.clientUUID + "\n");
    }

}