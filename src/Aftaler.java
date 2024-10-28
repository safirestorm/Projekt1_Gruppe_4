package src;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Aftaler {
    static Scanner tastatur = new Scanner(System.in);
    private static ArrayList<Aftaler> bookinger= new ArrayList<>();
    int id;
    static int nrAftaler=0;
    String navn;
    LocalDate dato;
    public int bookingtid;
    double beløb;

    Aftaler(String navn, LocalDate dato, int bookingtid){
        nrAftaler++;
        id=nrAftaler;
        this.navn = navn;
        this.dato = dato;
        this.bookingtid = bookingtid;
        beløb = 250;
    }
    int getId(){
        return id;
    }

    public String toString() {
        return id+ " " + navn + " "  + dato + " " +"klokken "+ bookingtid + ":00 " + beløb +" kroner";
    }

    public void opretAftaler (){
        System.out.println("Hvad er kundens navn?");
        String navn = tastatur.nextLine();

        LocalDate dato=tastDato();

        int bookingtid= seTid();
        bookinger.add(new Aftaler(navn,dato, bookingtid));
        System.out.println(bookinger);

    }

    public void fjerAftaler(){
        seLedigeTider();
        System.out.println("Hvilken id har aftalen du vil slette?");
        int id = tastatur.nextInt();
        bookinger.removeIf(b -> b.getId()== id);
        tastatur.nextLine();

    }

    public LocalDate tastDato() {
        LocalDate dato=null;
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
    public LocalDate verficerDato(){
        LocalDate dato = null;
        boolean korrektdato = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (!korrektdato){
            System.out.println("Skriv dato (yyyy-mm-dd):" );
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
    public void seLedigeTider() {
        LocalDate dato = verficerDato();

        for (int a = 10; a < 18; a++) {
            System.out.println("Klokken: " + a + ":00");
            boolean harBooking = false;

            for (Aftaler b : bookinger) {
                if (b.dato.isEqual(dato) && b.bookingtid == a) {
                    System.out.println(b);
                    harBooking = true;
                }
            }
            if (!harBooking) {
                System.out.println("Ledig");
            }
        }
    }

    public int seTid() {
        System.out.println("Hvilken tid?");
        int bookingtid = tastatur.nextInt();
        tastatur.nextLine();
        while (bookingtid < 10 || bookingtid > 17) {
            System.out.println("Det er udenfor vores åbningstid, prøv igen.");
            bookingtid = tastatur.nextInt();
        }
        return bookingtid;
    }

    public double seBudget(){
        double samletBeløb = 0;

        for (Aftaler b : bookinger){
           samletBeløb = b.beløb+ samletBeløb;

        }
        return samletBeløb;
    }

}


