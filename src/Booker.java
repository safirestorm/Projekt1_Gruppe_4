package src;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Booker {
    ArrayList<Aftaler> bookinger= new ArrayList<>();
    static Scanner tastatur = new Scanner(System.in);

    public static void main(String[] args) {
        /*
        Booker b1=new Booker();
        b1.opretAftaler();
        */

        Kalender b2 =new Kalender();
        b2.seLedigeTider();

    }

    void opretAftaler (){
        System.out.println("Hvad er kundens navn?");
        String navn = tastatur.nextLine();


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

        System.out.println("Hvilken tid?");
        int bookingtid = tastatur.nextInt();
        while (bookingtid < 10 || bookingtid > 17){
             System.out.println("Det er udenfor vores åbningstid, prøv igen.");
             bookingtid = tastatur.nextInt();
        }

      bookinger.add(new Aftaler(navn,dato, bookingtid));
        System.out.println(bookinger);


    }
    void seAftaler(){



    }

}

