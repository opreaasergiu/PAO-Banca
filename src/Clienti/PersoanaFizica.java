package Clienti;

import java.util.Scanner;

public class PersoanaFizica extends Client {

    private String nume, prenume, telefon, cnp;
    private Adresa adresa;

    public PersoanaFizica(String email, Adresa adresa, String nume, String prenume, String cnp, String telefon)
            throws Exception {
        super(email, adresa/* , telefon */);

        if (cnp.length() != 13)
            throw new Exception("CNP incorect - numar incorect de cifre.");
        if (telefon.length() != 10)
            throw new Exception("Numar de telefon incorect - numar incorect de cifre.");
        this.nume = nume;
        this.prenume = prenume;
        this.cnp = cnp;
        this.telefon = telefon;
        this.adresa = adresa;
    }

    public PersoanaFizica(Scanner input) {
        super(input);

        System.out.println("Introduceti informatii persoana fizica:");
        System.out.println("Nume: ");
        this.nume = input.nextLine();

        System.out.println("Prenume: ");
        this.prenume = input.nextLine();

        System.out.println("CNP: ");
        this.cnp = input.nextLine();

        System.out.println("Telefon: ");
        this.telefon = input.nextLine();
    }

    @Override
    public String toString() {
        String infoClient = super.toString();
        return String.format(infoClient, "Informatii persoana fizica:\nNume: " + this.nume + "\nPrenume: " + this.prenume + "\nTelefon: "
        //return String(infoClient, "Informatii persoana fizica:\nNume: " + this.nume + "\nPrenume: " + this.prenume + "\nTelefon: "

//        return String.format("%s%s%s%s%s%s",
//                infoClient,
//                "Informatii persoana fizica:\nNume: " + this.nume + "\nPrenume: " + this.prenume + "\nTelefon: "
                        + this.telefon + "\nCNP: " + this.cnp + "\n");
    }

	// getteri
    public String getNume() {
        return this.nume;
    }

    public String getPrenume() {
        return this.prenume;
    }
    
    public String getNumePrenume()
    {
    	return this.nume + " " + this.prenume;
    }

    public String getCNP() {
        return this.cnp;
    }

    public String getTelefon() {
        return this.telefon;
    }
    
    public String getTest() {
        return this.getEmail();
    }
}