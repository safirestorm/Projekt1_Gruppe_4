package src;

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

    public void opretAftaler() {
        System.out.println("Hvad er kundens navn?");
        String navn = tastatur.nextLine();

        LocalDate dato = tastDato();

        LocalTime bookingtid = seTid();
        bookinger.add(new Aftaler(navn, dato, bookingtid));
        System.out.println(bookinger);
    }

    public void fjernAftaler() {
        seTider();
        System.out.println("Hvilken id har aftalen du vil slette?");
        int id = tastatur.nextInt();
        bookinger.removeIf(b -> b.getId() == id);
        tastatur.nextLine();

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

        System.out.println(dato);
        for (int a = 10; a < 18; a++) {
            System.out.println("Klokken: " + a + ":00");
            boolean harBooking = false;

            for (Aftaler b : bookinger) {
                if(dato.isEqual(dato) && dato.isBefore(dato.plusDays(4))) {
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
}

