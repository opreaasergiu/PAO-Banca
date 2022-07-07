package Conturi;

public class Tranzactie {
	private String id;
    private final String expeditorIBAN, destinatarIBAN, descriere;
    private final long suma;
    private final String dataTranzactie;

    public Tranzactie(String id, String expeditorIBAN, String destinatarIBAN, long suma, String descriere, String dataTranzactie) {
    	this.id = id;
        this.expeditorIBAN = expeditorIBAN;
        this.destinatarIBAN = destinatarIBAN;
        this.suma = suma;
        this.descriere = descriere;
        this.dataTranzactie = dataTranzactie;
    }

    @Override
    public String toString() {
        return String.format("Informatii tranzactie:\nIBAN expeditor: " + this.expeditorIBAN + "\nIBAN destinatar: "
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

    public String getDataTranzactie() {
        return this.dataTranzactie;
    }
}