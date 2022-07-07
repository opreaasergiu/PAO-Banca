import java.util.*;

import Clienti.*;
import Carduri.*;
import Conturi.*;

import java.sql.*;

public class Main {

    private static final String[] operatiuni = {
            "0 - Iesire",
            "1 - Creare client",
            "2 - Creare card",
            "3 - Creare cont",
            "4 - Adauga bunuri in cont",
            "5 - Adauga card in cont",
            "6 - Adauga tranzactie in cont",
            "7 - Inchidere cont",
            "8 - Afiseaza informatii clienti",
            "9 - Afiseaza informatii cont",
            "10 - Afiseaza tranzactii",
            "11 - Stergere cont"
    };

    private static void afiseazaOperatiuni() {
        System.out.println("Alege operatiunea bancara...");
        for (String operatiune : operatiuni) {
            System.out.println(operatiune);
        }

    }

    public static void main(String[] args) throws Exception {
    	
    	// Conexiune la baza de date
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banca", "root", "");
        // Crare statement
        Statement stmt = myConn.createStatement();
        	
    	    	
    	// Variabila care arata daca programul ruleaza
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

        // Construim variabile "default" pentru a face operatiuni cu ele
//        Client clientObisnuit = new Client("client@yahoo.com", new Adresa("Cluj", "Nicolae Iorga", 16));
//
//        Client clientFizica = new PersoanaFizica("fizica@gmail.com", new Adresa("Iasi", "Decebal", 1), "Ghiveci",
//                "Mircea", "5010616134160", "0720865324");
//        Client clientJuridica = new PersoanaJuridica("companie@outlook.com", new Adresa("Bucuresti", "Unirii", 15),
//                "Serele S.A.", "5674", "352523", "4864574567", "0341441262");
//
//        Card cardObisnuit = new Visa("533155495849548", 234, "Oprea", "08/23", "RO0439509346", 299, "RCKS");
//        Card cardVisa = new Mastercard("53347436748", 633, "David", "4/22", "RO346346347", 14856, "RZRR");
//        Card cardMastercard = new Mastercard("533275648", 633, "Cristian", "4/22", "RO9764327", 14856, "RZCR");
//
//        Bunuri bun = new Bunuri("Lant aur", 1500, clientFizica.getUUID());
//
//        Cont cont1 = new Cont(clientFizica, "RO34084360634", "activ");
//        Cont cont2 = new Cont(clientObisnuit, "RO34098683460235", "activ");
//
//        Tranzactie tranzactie1 = new Tranzactie(cont2.getIBAN(), cont1.getIBAN(), 13, "Datoria",
//                new SimpleDateFormat("dd/mm/yyyy").parse("27/04/2022"));

        while (ruleaza != 0) {
            System.out.println("Alege operatiunea bancara...");
            int nrOperatiune = Integer.parseInt(input.nextLine());

            if (nrOperatiune < 0 || nrOperatiune > 11) {
                System.out.println("Operatiune bancara invalida...");
                break;

            }

            switch (nrOperatiune) {
                case 0 -> {
                    ruleaza = 0;
                    System.out.println("Iesire...");
                }
                case 1 ->{
                	Client temp_cli = operatiuneBancara.creazaClient();
                	
                	if (temp_cli.getClass() == PersoanaFizica.class) {
                		
                		String insert_sql_client = "INSERT INTO clienti VALUES('" + String.valueOf(temp_cli.getUUID()) + 
												   "', '" + temp_cli.getEmail() + "', '" + temp_cli.getTelefon() + "', '" +
												   String.valueOf(temp_cli.getAdresa()) + "', 'fizica');";
                		
                		stmt.executeUpdate(insert_sql_client);
                    } else if (temp_cli.getClass() == PersoanaJuridica.class) {
                    	
                    	String insert_sql_client = "INSERT INTO clienti VALUES('" + String.valueOf(temp_cli.getUUID()) + 
											  	   "', '" + temp_cli.getEmail() + "', '" + temp_cli.getTelefon() + "', '" +
												   String.valueOf(temp_cli.getAdresa()) + "', 'juridica');";
                    	
                    	stmt.executeUpdate(insert_sql_client);
                    }
                }
                // Stage 2: client = operatiuneBancara.creazaClient();
                
                case 2 -> 
                {
                	Card temp_card = operatiuneBancara.creazaCard();
                	
                	System.out.println("INSERT INTO carduri VALUES('" + String.valueOf(temp_card.getNumar()) + 
									  	 "', '" + temp_card.getCVV() + "', '" + temp_card.getNume() + "', '" +
										 String.valueOf(temp_card.getDataExpirarii()) + "', '" + temp_card.getIban() + "', '" + 
										 String.valueOf(temp_card.getValoare()) + "');");
                	
                	String insert_sql_card = "INSERT INTO carduri VALUES('" + String.valueOf(temp_card.getNumar()) + 
										  	 "', '" + String.valueOf(temp_card.getCVV()) + "', '" + 
										  	 String.valueOf(temp_card.getNume()) + "', '" +
											 String.valueOf(temp_card.getDataExpirarii()) + "', '" + 
										  	 String.valueOf(temp_card.getIban()) + "', " + 
											 String.valueOf(temp_card.getValoare()) + ");";
                	
                	stmt.executeUpdate(insert_sql_card);
                }
                
                // Stage 2: card = operatiuneBancara.creazaCard();
                case 3 -> {
                    System.out.println("IBAN:");
                    String iban = input.nextLine();
                    System.out.println("Status:");
                    String status = input.nextLine();
                    System.out.println("UUID Client:");
                    String cod_client = input.nextLine();
                    
                    Client temp_cli_adaugare_cont = clienti[0];
                    
//                    // Cautare in clienti
//                    for (int i = 0; i < clienti.length; i++)
//                    	if (String.valueOf(clienti[i].getUUID()) == cod_client)
//                    	{
//                    		temp_cli_adaugare_cont = clienti[i];
//							break;
//                    	}
//                    
//                    // Creare cont
//                    cont = operatiuneBancara.creazaCont(temp_cli_adaugare_cont, iban, status);
                    
					// Inseare cont in baza de date
					String insert_sql_cont = "INSERT INTO conturi VALUES('" + iban + "', '" + status + "', '" + cod_client + "');";
					stmt.executeUpdate(insert_sql_cont);

                }
                case 4 -> {
                	// Input bunuri
                	System.out.println("Nume bun:");
                    String temp_nume_bun = input.nextLine();
                    System.out.println("Valoare bun:");
                    long temp_val_bun = input.nextLong();
                	System.out.println("IBAN Cont client:");
                    String test = input.nextLine();
                    String temp_iban_cont = input.nextLine();
                    
                    // Inseare bun in baza de date
					String insert_sql_bun = "INSERT INTO bunuri VALUES('" + temp_nume_bun + "', '" + 
											 temp_val_bun + "', '" + temp_iban_cont + "');";
					stmt.executeUpdate(insert_sql_bun);
                	

                	// Cautare in clienti (stage 2)
//                    Cont temp_cont_adaug_bun;
//                    Bunuri bun = new Bunuri(temp_nume_bun, temp_val_bun, UUID.fromString(temp_iban_cont));
//                    for (int i = 0; i < conturi.length; i++)
//                    	if (String.valueOf(conturi[i].getIBAN()) == temp_iban_cont)
//                    	{
//                    		temp_cont_adaug_bun = conturi[i];
//							break;
//                    	}
//                	
//                    operatiuneBancara.adaugaBunuri(temp_cont_adaug_bun, bun);
                }
                case 5 -> {
                    Scanner scan = new Scanner(System.in);
                	System.out.println("Adaugare card in cont.\nIntrodu numarul cardului: ");
                	String temp_numar_card = scan.nextLine();
                	System.out.println("\nIntrodu numarul contului:  ");
                	String temp_numar_cont = scan.nextLine();
                	
                	
                	String card_in_cont = "UPDATE carduri SET iban_detinator = '"+ temp_numar_cont + "' WHERE numar = +"
                						   + temp_numar_card + ";";
                	
                	stmt.executeUpdate(card_in_cont);
                	
//                    operatiuneBancara.adaugaCard(cont1, cardVisa);
//                    operatiuneBancara.afiseazaCarduri(cont1);
                }
                case 6 -> {
                	// Input tranzactie
                	Scanner scan = new Scanner(System.in);
                	String temp_id = String.valueOf(UUID.randomUUID());
                	System.out.println("\nIBAN Expeditor:  ");
                	String temp_exp = scan.nextLine();
                	System.out.println("\nIBAN destinatar:  ");
                	String temp_dest = scan.nextLine();
                	System.out.println("\nValaore:  ");
                	Integer temp_val = scan.nextInt();
                	System.out.println("\nDescriere:  ");
                	String test = scan.nextLine();
                	String temp_descriere = scan.nextLine();
                	System.out.println("\nData:  ");
                	String temp_data = scan.nextLine();
                	
                	// Creare tranzactie
                	Tranzactie tranzactie1 = new Tranzactie(temp_id, temp_exp, temp_dest, temp_val, temp_descriere, temp_data);
                	
                	// Inserare in baza de date
                	String tranzactie_sql = "INSERT INTO tranzactii VALUES('" + temp_id + "', '" + temp_exp + "', '" + 
                							temp_dest + "', " + temp_val + ", '" + temp_descriere + "', '" + temp_data + "');";
 	
                	stmt.executeUpdate(tranzactie_sql);
                	
//                	tranzactii.add(tranzactie);
//                    operatiuneBancara.adaugaTranzactie(cont, tranzactie);
                }
                case 7 -> 
                {
                	
                	Scanner scan = new Scanner(System.in);
                	System.out.println("Introdu numarul contului: ");
                	String temp_numar_cont = scan.nextLine();
                	
                	String update_inchidere = "UPDATE conturi SET status = 'inchis' WHERE IBAN = " + temp_numar_cont + ";"; 
                	stmt.executeUpdate(update_inchidere);
                	                	

                }
                case 8 -> //Stage 2: operatiuneBancara.afiseazaInfoClient(clientFizica);
                {
                	ResultSet rs = stmt.executeQuery("SELECT * FROM Clienti;");
                	while (rs.next()) 
                	{
                        System.out.print("\nUUID: " + rs.getString("clientUUID"));
                        System.out.print("\nEmail: " + rs.getString("email"));
                        System.out.println("\nTelefon: " + rs.getString("telefon"));
                        System.out.println("Adresa: " + rs.getString("adresa"));
                        System.out.println("Tip persoana: " + rs.getString("tip"));
                	}
                }
                
                case 9 -> //operatiuneBancara.afiseazaInfoCont(cont2);
                {
                	ResultSet rs = stmt.executeQuery("SELECT * FROM Conturi;");
                	
                	while (rs.next()) 
                	{
                		// TO DO
                        System.out.print("\n\nIBAN: " + rs.getString("IBAN"));
                        System.out.print("\nStatus: " + rs.getString("status_"));
                        System.out.print("\nCod Client: " + rs.getString("cod_client"));
                	}
                }
                case 10 -> //operatiuneBancara.afiseazaTranzactii(cont2);
                {
                	ResultSet rs = stmt.executeQuery("SELECT * FROM Tranzactii;");
                	while (rs.next()) 
                	{
                        System.out.print("\nUUID: " + rs.getString("id"));
                        System.out.print("\nIBAN Expeditor: " + rs.getString("expeditor"));
                        System.out.print("\nIBAN Destinatar: " + rs.getString("destinatar"));
                        System.out.print("\nSuma: " + rs.getInt("suma"));
                        System.out.print("\nDescriere: " + rs.getString("descriere"));
                        System.out.print("\nData: " + rs.getString("data_"));
                        System.out.println("\n");
                        
                	}
                }
                case 11 -> {
//                	Stage 2: operatiuneBancara.inchideCont(cont1);
//                    System.out.println(cont1.getStatus());
                	// TO DO de testat
                	Scanner scan = new Scanner(System.in);
                	System.out.println("Introdu numarul contului: ");
                	String temp_numar_cont = scan.nextLine();
                	
                	String stergere_inchidere = "DELETE FROM conturi WHERE IBAN = '" + temp_numar_cont + "';"; 
                	stmt.executeUpdate(stergere_inchidere);
                }
            }
        }
    }
}
