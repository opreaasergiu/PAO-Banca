package Conturi;

import java.util.Date;

public class Tranzactie {
    private final String expeditorIBAN, destinatarIBAN, descriere;
    private final long suma;
    private final Date dataTranzactie;

    public Tranzactie(String expeditorIBAN, String destinatarIBAN, long suma, String descriere, Date dataTranzactie) {
        this.expeditorIBAN = expeditorIBAN;
        this.destinatarIBAN = destinatarIBAN;
        this.suma = suma;
        this.descriere = descriere;
        this.dataTranzactie = dataTranzactie;
    }

    @Override
    public String toString() {
        return String.format("%s%s%d%s%s",
                "Informatii tranzactie:\nIBAN expeditor: " + this.expeditorIBAN + "\nIBAN destinatar: "
                        + this.destinatarIBAN + "\nSuma: " + this.suma + "\nDescriere: " + this.descriere,
                "Data tranzactiei: " + this.dataTranzactie.toString());
    }

    // getters
    public String getExpeditorIBAN() {
        return this.expeditorIBAN;
    }

    public String getDestinatarIBAN() {
        return this.destinatarIBAN;
    }

    public long getSuma() {
        return this.suma;
    }

    public String getDescriere() {
        return this.descriere;
    }

    public Date getDataTranzactie() {
        return this.dataTranzactie;
    }
}