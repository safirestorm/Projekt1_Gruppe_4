package src;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import static src.Salon.checkOmTal;

public class Aftaler {
    static Scanner tastatur = new Scanner(System.in);
    static ArrayList<Aftaler> bookinger = new ArrayList<>();
    int id;
    static int nrAftaler = 0;
    String navn;
    LocalDate dato;
    public LocalTime bookingtid;
    double beløb;
    boolean kredit;

    Aftaler(String navn, LocalDate dato, LocalTime bookingtid, boolean kredit) {
        nrAftaler++;
        id = nrAftaler;
        this.navn = navn;
        this.dato = dato;
        this.bookingtid = bookingtid;
        beløb = 250;
        this.kredit = kredit;
    }

    int getId() {
        return id;
    }

    public String toString() {
        return "Bookingid: "+id + "\t" + navn + ", " + dato + ", " + "kl. " + bookingtid + ":00, " + beløb + " kr.";
    }

    public boolean login() {
        String ADGANGSKODE = "hairyharry";
        System.out.println("Indtast adgangskode for at logge ind:");
        String adgangskode = tastatur.nextLine();

        if (ADGANGSKODE.equals(adgangskode)) {
            System.out.println("Login succesfuldt!");
            return true;
        } else {
            System.out.println("Forkert adgangskode. Prøv igen.");
            return false;
        }
    }

    public void opretAftaler() throws IOException{
        System.out.println("Hvad er kundens navn?");
        String navn = tastatur.nextLine();


        LocalDate dato = tastDato();
        boolean kredit;
        LocalTime bookingtid = seTid();
        System.out.println("Er bookingen Kredit? (true/false)");
        boolean optaget = false;
        try {
            kredit = tastatur.nextBoolean();

            for(Aftaler b: bookinger) {
                while (dato != b.dato && bookingtid != b.bookingtid) {
                    bookinger.add(new Aftaler(navn, dato, bookingtid, kredit));
                }
                if(optaget == false) {
                    System.out.println("Der er allerede en booking på den ønskede tid");
                    optaget = true;
                }
            }
        } catch (Exception e){
            System.out.println("Prøv igen");
        }

        lavTextfilfraBookingerArray();
    }

    public void fjernAftaler() throws IOException{
        seTider();
        System.out.println("Hvilken id har aftalen du vil slette?");
        int id = tastatur.nextInt();
        bookinger.removeIf(b -> b.getId() == id);
        tastatur.nextLine();
        lavTextfilfraBookingerArray();
    }

    public LocalDate tastDato() {
        LocalDate dato = null;
        boolean korrektdato = false;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (!korrektdato) {
            System.out.println("Skriv dato (yyyy-mm-dd):");
            String intastetDato = tastatur.nextLine();

            try {
                dato = LocalDate.parse(intastetDato, formatter);
                korrektdato = true;
            } catch (DateTimeParseException e) {
                System.out.println("Ugyldig dato, skriv dato (yyyy-mm-dd)");
            }
        }
        return dato;
    }

    public static LocalTime tastTid(){
        LocalTime tid = null;
        boolean korrektTid = false;

        DateTimeFormatter format = DateTimeFormatter.ofPattern("10:00");

        while (!korrektTid){
            System.out.println("Skriv tid (hh:mm)");
            String indtastetTid = tastatur.nextLine();

            try {
                tid = LocalTime.parse(indtastetTid);
                korrektTid = true;
            } catch (RuntimeException e) {
                System.out.println("Ugyldig tid, skriv tid (hh:mm)");
            }

        }
        return tid;
    }

    public LocalDate verficerDato() {
        LocalDate dato = null;
        boolean korrektdato = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (!korrektdato) {
            System.out.println("Skriv dato (yyyy-mm-dd):");
            String intastetDato = tastatur.nextLine();

            try {
                dato = LocalDate.parse(intastetDato, formatter);
                korrektdato = true;
            } catch (DateTimeParseException e) {
                System.out.println("Ugyldig dato, skriv dato (yyyy-mm-dd)");
            }
        }
        return dato;
    }

    public void seTider4Dagefrem() {
        LocalDate startDato = verficerDato();

        for (int flereDage = 0; flereDage <= 4; flereDage++) {
            LocalDate dato = startDato.plusDays(flereDage);
            System.out.println(dato);
            for (int a = 10; a < 18; a++) {
                LocalTime tid = LocalTime.of(a, 0);
                System.out.println("Klokken: " + tid);
                boolean harBooking = false;

                for (Aftaler b : bookinger) {
                    if (b.dato.isEqual(dato) && b.bookingtid.equals(tid)) {
                        System.out.println(b);
                        System.out.println();
                        harBooking = true;
                    }
                }
                if (!harBooking) {
                    System.out.println("Ledig");
                    System.out.println();
                }
            }
        }
    }

    public void seTider() {
        LocalDate dato = verficerDato();

        System.out.println(dato);
        for (int a = 10; a < 18; a++) {
            LocalTime tid = LocalTime.of(a,0);
            System.out.println("Klokken: " + tid);
            boolean harBooking = false;

            for (Aftaler b : bookinger) {
                if (b.dato.isEqual(dato) && b.bookingtid.equals(tid)) {
                    System.out.println(b);
                    System.out.println();
                    harBooking = true;
                }
            }
            if (!harBooking) {
                System.out.println("Ledig");
                System.out.println();
            }
        }
    }

    public LocalTime seTid() {
        System.out.println("Hvilken tid?");
        LocalTime bookingtid = tastTid();
        while (bookingtid.isBefore(LocalTime.of(10,0))  || bookingtid.isAfter(LocalTime.of(17,0))) {
            System.out.println("Det er udenfor vores åbningstid, prøv igen.");
            bookingtid = tastTid();
        }
        return bookingtid;
    }

    public void seBudgetPrDag() {
        while (true) {
            LocalDate dato = verficerDato();
            double betaltBeløb = 0;
            double mangledeBeløb = 0;
            double samletBeløb = 0;

            if (dato.isBefore(LocalDate.now())) {
                System.out.println(dato);
                for (int a = 10; a < 18; a++) {
                    LocalTime tid = LocalTime.of(a,0);
                    System.out.println("Klokken: " + tid);

                    for (Aftaler b : bookinger) {
                        if (b.dato.isEqual(dato) && b.bookingtid.equals(tid)) {

                            if (b.kredit == true ) {
                                betaltBeløb += b.beløb;
                                System.out.println(b);
                            }
                            if (b.kredit == false) {
                                mangledeBeløb += b.beløb;
                                System.out.println(b);
                                System.out.println("Mangler betaling: 250 kr. som ikke er betalt.");
                            }
                        }
                    }
                }
                samletBeløb = betaltBeløb + mangledeBeløb;
                System.out.println("Samlet beløb for d." + dato + " er: " + samletBeløb + "kr. Manglende beløb: " + mangledeBeløb + " ikke betalt");
                break;
            } else {
                System.out.println("Det er ikke muligt at se regnskabet for fremtiden, vær sød at vælge en dato før dags dato.");
                System.out.println();
            }
        }
    }
    public void lavTextfilfraBookingerArray() throws IOException{
        FileWriter fil2 = new FileWriter("src//BookingList.txt", false);                        // Her finder den BookingListen
        PrintWriter ud2 = new PrintWriter(fil2);                                                // Her laver jeg en variabel som printer alt ind filen som en String
        for (Aftaler b : bookinger) {                                                           // Her finder jeg alle bookinger i console
            int id = b.id;                                                                      // Her Laver jeg en variabel som sætter id'et fra en a bookingerne ind i den
            String Bnavn = b.navn;                                                              // samme her men med navn
            LocalDate Bdato = b.dato;                                                           // -||- dato
            LocalTime Btid = b.bookingtid;                                                      // -||- tid
            Double beløb = b.beløb;                                                             // -||- beløb
            boolean kredit = b.kredit;                                                          // -||- kredit

            ud2.println(id + "\t" + Bnavn + "\t" + Bdato + "\t" + Btid + "\t" + beløb + "\t" + kredit); // Her bruger jeg printer variablen til at putte alle variablerne ind i textfilen.
        }
        ud2.close();                                                                            // Her stopper jeg printeren
    }
    public void leasTextfilerogInputIBookinger() throws IOException{
        FileReader fil = new FileReader("src//BookingList.txt");                                // Her finder den en BookingListen og læser den
        BufferedReader ind = new BufferedReader(fil);                                           // Her laver jeg en variabel som kan læse det der er i BookingListen og oversætter det til en String
        String linje = ind.readLine();                                                          // Her putter jeg det BufferedReader har læst ind i en String
        DateTimeFormatter Datoformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");            // Her laver jeg en variabel som kan oversætte en String om til en Dato format
        DateTimeFormatter Tidformatter = DateTimeFormatter.ofPattern("HH:mm");                  // Her laver jeg en variabel som kan oversætte en String om til en tid format

        while (linje != null) {                                                                 //Her køre while løkken så længe der er noget i BufferedReader

            String[] bidder = linje.split("\t");                                                //Her laver jeg en Array som deler alle orderne op, hver gang der er et "Tap" i textfilen
            // String id = bidder[0];                                                           //Bruger den ikke fordi vi automatisk putter id i vores constructor
            String navn = bidder[1];
            String dato = bidder[2];
            String tid = bidder[3];
            // String beløb = bidder[4];                                                        //Samme her
            boolean kredit = Boolean.parseBoolean(bidder[5]);
            // Integer Sid;
            try {
                // Sid = Integer.valueOf(id);
                // int Cid = Sid;
                LocalDate Cdato = LocalDate.parse(dato, Datoformatter);                         //Her tager jeg Stringen dato og formattere om til en LocalDate som jeg putter i Cdato
                LocalTime Ctid = LocalTime.parse(tid, Tidformatter);                            //Her tager jeg Stringen tid og formattere om til en LocalTime som jeg putter i Ctid
                // Double Cbeløb = Double.parseDouble(beløb);
                Aftaler.bookinger.add(new Aftaler(navn,Cdato, Ctid, kredit));                   //Her putter jeg alle de navn og alle de formatteret ind i vores bookinger


            } catch (NumberFormatException e) {                                                 // Her fanger den hvis noget i Stringen ikke er rigtigt
                System.out.println("Invalid integer input");
            }
            linje = ind.readLine();                                                             //Her Starter den om igen
        }

    }
    public void ekstraKøb() throws IOException {
        double børste = 100;
        double shampoo = 150;
        double voks = 120;
        double samletEkstra = 0;
        double iAlt;
        int valg;

        seTider();
        System.out.println("Hvilken id har aftalen du vil redigerer?");
        int id = tastatur.nextInt();
        System.out.println("Har kunden tilkøbt ekstra produkter?");
        System.out.println("Tast 1: Ja");
        System.out.println("Tast 0: Nej");
        valg = checkOmTal();


        while (valg == 1 | valg == 2 | valg == 3) {
            System.out.println("Hvad har kunden tilkøbt?");
            System.out.println("Tast 1: Børste(100kr)");
            System.out.println("Tast 2: Shampoo(150kr)");
            System.out.println("Tast 3: Voks(120kr)");
            System.out.println("Tast 4: Gå til forrige side");
            valg = checkOmTal();

            if (valg == 1) {
                samletEkstra = samletEkstra + børste;
            }
            if (valg == 2) {
                samletEkstra = samletEkstra + shampoo;
            }
            if (valg == 3) {
                samletEkstra = samletEkstra + voks;
            }
            iAlt = samletEkstra + beløb;
            for (Aftaler aftale : bookinger) {
                if (aftale.getId() == id) {
                    aftale.beløb = iAlt;
                }
            }
            System.out.println("Ekstra tilkøb for kunden er: " + samletEkstra);
            System.out.println("Kundens samlede beløb er: " + iAlt);
            lavTextfilfraBookingerArray();

        }
        if (valg == 0) {
            System.out.println("Nærrig");
        }
    }
}