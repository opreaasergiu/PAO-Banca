package Clienti;

import java.util.Scanner;

public class PersoanaJuridica extends Client {
    private String nume, codCAEN, codCIF, telefon;

    public PersoanaJuridica(String email, Adresa adresa, String nume, String codCAEN, String codCIF, String numarCont,
            String telefon) throws Exception {
        super(email, adresa/* , telefon */);
        this.nume = nume;
        this.codCAEN = codCAEN;
        this.codCIF = codCIF;

        if (codCAEN.length() != 4 && codCAEN.length() != 5)
            throw new Exception("Cod CAEN incorect - numar incorect de cifre.");
        if (telefon.length() != 10)
            throw new Exception("Numar de telefon incorect - numar incorect de cifre.");
    }

    public PersoanaJuridica(Scanner input) {
        super(input);

        System.out.println("Informatii persoana juridica:");
        System.out.println("Nume: ");
        this.nume = input.nextLine();

        System.out.println("Cod CAEN: ");
        this.codCAEN = input.nextLine();

        System.out.println("Cod CIF: ");
        this.codCIF = input.nextLine();

        System.out.println("Telefon: ");
        this.telefon = input.nextLine();
    }

    @Override
    public String toString() {
        String infoClient = super.toString();
        return String.format("%s%s%s%s%s",
                infoClient,
                "Informatii persoana juridica\nNume: " + this.nume + "\nCod CAEN: " + this.codCAEN + "\nCod CIF: "
                        + this.codCIF + "\nTelefon: " + this.telefon + "\n");
    }

    public String getNume() {
        return this.nume;
    }

    public String getCodCAEN() {
        return this.codCAEN;
    }

    public String getCodCIF() {
        return codCIF;
    }

    public String getTelefon() {
        return telefon;
    }
}
