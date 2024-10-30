package src;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Aftaler {
    static Scanner tastatur = new Scanner(System.in);
    static ArrayList<Aftaler> bookinger = new ArrayList<>();
    int id;
    static int nrAftaler = 0;
    String navn;
    LocalDate dato;
    public LocalTime bookingtid;
    double beløb;

    Aftaler(String navn, LocalDate dato, LocalTime bookingtid) {
        nrAftaler++;
        id = nrAftaler;
        this.navn = navn;
        this.dato = dato;
        this.bookingtid = bookingtid;
        beløb = 250;
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

        LocalTime bookingtid = seTid();
        bookinger.add(new Aftaler(navn, dato, bookingtid));
        LavTextfilfraBookingerArray();
    }

    public void fjernAftaler() throws IOException{
        seTider();
        System.out.println("Hvilken id har aftalen du vil slette?");
        int id = tastatur.nextInt();
        bookinger.removeIf(b -> b.getId() == id);
        tastatur.nextLine();
        LavTextfilfraBookingerArray();
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

    public void seTider4Dagefrem(){
        LocalDate dato = verficerDato();
        LocalTime tid = null;

        System.out.println(dato);
        for (int a = 10; a < 18; a++) {
            if (a == 10) tid = LocalTime.of(10,0);
            if (a == 11) tid = LocalTime.of(11,0);
            if (a == 12) tid = LocalTime.of(12,0);
            if (a == 13) tid = LocalTime.of(13,0);
            if (a == 14) tid = LocalTime.of(14,0);
            if (a == 15) tid = LocalTime.of(15,0);
            if (a == 16) tid = LocalTime.of(16,0);
            if (a == 17) tid = LocalTime.of(17,0);
            System.out.println("Klokken: " + a + ":00");
            boolean harBooking = false;

            for (Aftaler b : bookinger) {
                if(dato.isEqual(dato) && dato.isBefore(dato.plusDays(4)) && b.bookingtid.equals(tid)) {
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

    public void seTider() {
        LocalDate dato = verficerDato();
        LocalTime tid = null;

        System.out.println(dato);
        for (int a = 10; a < 18; a++) {
            if (a == 10) tid = LocalTime.of(10,0);
            if (a == 11) tid = LocalTime.of(11,0);
            if (a == 12) tid = LocalTime.of(12,0);
            if (a == 13) tid = LocalTime.of(13,0);
            if (a == 14) tid = LocalTime.of(14,0);
            if (a == 15) tid = LocalTime.of(15,0);
            if (a == 16) tid = LocalTime.of(16,0);
            if (a == 17) tid = LocalTime.of(17,0);
            System.out.println("Klokken: " + a + ":00");
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
            double samletBeløb = 0;
            LocalTime tid = null;

            if (dato.isBefore(LocalDate.now())) {
                System.out.println(dato);
                for (int a = 10; a < 18; a++) {
                    if (a == 10) tid = LocalTime.of(10, 0);
                    if (a == 11) tid = LocalTime.of(11, 0);
                    if (a == 12) tid = LocalTime.of(12, 0);
                    if (a == 13) tid = LocalTime.of(13, 0);
                    if (a == 14) tid = LocalTime.of(14, 0);
                    if (a == 15) tid = LocalTime.of(15, 0);
                    if (a == 16) tid = LocalTime.of(16, 0);
                    if (a == 17) tid = LocalTime.of(17, 0);
                    System.out.println("Klokken: " + a + ":00");

                    for (Aftaler b : bookinger) {
                        if (b.dato.isEqual(dato) && b.bookingtid.equals(tid)) {
                            System.out.println(b);
                            samletBeløb = b.beløb + samletBeløb;
                            System.out.println();
                        }
                    }
                }
                System.out.println("Samlet beløb for d." + dato + " er: " + samletBeløb + "kr.");
                break;
            } else {
                System.out.println("Det er ikke muligt at se regnskabet for fremtiden, vær sød at vælge en dato før dags dato.");
                System.out.println();
            }
        }
    }
    public void LavTextfilfraBookingerArray() throws IOException{
        FileWriter fil2 = new FileWriter("src//BookingList.txt", false);
        PrintWriter ud2 = new PrintWriter(fil2);
        for (Aftaler b : bookinger) {
        //  System.out.println(b); // vise hvad der er i den indlæste String
            int id = b.id; // Hele linjen er "0-Ole-2222,11,11-10,0-250" og her gemmer den "0"
            String Bnavn = b.navn; // Den gemmer "1556"
            LocalDate Bdato = b.dato; // Den gemmer "d"
            LocalTime Btid = b.bookingtid;
            Double beløb = b.beløb;

            ud2.println(id + "\t" + Bnavn + "\t" + Bdato + "\t" + Btid + "\t" + beløb); // Her putter den det ind i textfilen som String alle typer filer kan dog sættes ind
        }
        ud2.close();
    }
    public void LeasTextfilerogInputIBookinger() throws IOException{
        FileReader fil = new FileReader("src//BookingList.txt");
        BufferedReader ind = new BufferedReader(fil);
        String linje = ind.readLine(); // Laver det den læser om til en String
        DateTimeFormatter Datoformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter Tidformatter = DateTimeFormatter.ofPattern("HH:mm");

        while (linje != null) {

            //   System.out.println(linje); // vise hvad der er i den indlæste String
            String[] bidder = linje.split("\t");
            // String id = bidder[0]; // Hele linjen er "Peter Adams,1556,D" og her gemmer den "Peter Adams"
            String navn = bidder[1]; // Den gemmer "1556"
            String dato = bidder[2]; // Den gemmer "d"
            String tid = bidder[3];
            // String beløb = bidder[4];
            // Integer Sid;

            try {
                // Sid = Integer.valueOf(id);
                // int Cid = Sid;
                LocalDate Cdato = LocalDate.parse(dato, Datoformatter);
                LocalTime Ctid = LocalTime.parse(tid, Tidformatter);
                // Double Cbeløb = Double.parseDouble(beløb);
                // System.out.println("Converted integer: " +Cid+ " Dato:" +Cdato+ " Tid:" +Ctid+ " beløb:" +Cbeløb);
                Aftaler.bookinger.add(new Aftaler(navn,Cdato, Ctid));


            } catch (NumberFormatException e) {
                System.out.println("Invalid integer input");
            }
            linje = ind.readLine();


        }
    }
}

