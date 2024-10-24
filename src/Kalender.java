package src;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Kalender {
    static Scanner tastatur = new Scanner(System.in);

    public static void main(String[] args) {

/*
        ArrayList<Aftaler> bookinger= new ArrayList<>();
        bookinger.add(new Aftaler("Bob",LocalDate.of(2024,03,22), 13));
        bookinger.add(new Aftaler("Bob",LocalDate.of(2024,03,22), 13));

        bookinger.add(new Aftaler("Bob",LocalDate.of(2024,03,22), 11));
        bookinger.add(new Aftaler("Bob",LocalDate.of(2024,03,22), 10));

        for(int a=10; a<18; a++) {
            System.out.println("Klokken: "+a+":00");
            boolean harBooking = false;

        for(Aftaler b:bookinger){
                if (b.dato.isEqual(LocalDate.of(2024, 03, 22)) && b.bookingtid == a) {
                    System.out.println(b);
                    harBooking = true;
                }
            }
            if (!harBooking) {
                System.out.println("Ledig");
            }
        }
*/

    }
    void seLedigeTider(){
        ArrayList<Aftaler> bookinger= new ArrayList<>();
        bookinger.add(new Aftaler("Bob",LocalDate.of(2024,03,22), 13));
        bookinger.add(new Aftaler("Bob",LocalDate.of(2024,03,22), 13));
        bookinger.add(new Aftaler("Bob",LocalDate.of(2024,03,23), 13));



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


        for(int a=10; a<18; a++) {
            System.out.println("Klokken: "+a+":00");
            boolean harBooking = false;

            for(Aftaler b:bookinger){
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
}
