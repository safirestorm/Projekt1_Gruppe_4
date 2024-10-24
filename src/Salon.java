package src;

import java.util.Scanner;

public class Salon {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int valg;

        while (true) {
            System.out.println("Velkommen til Harry's Salon");
            System.out.println("Hvad ønsker du at foretage dig?");
            System.out.println("Tast 1: Booking");
            System.out.println("Tast 2: Økonomi");
            System.out.println("Tast 0: Afslut");
            System.out.println("Indtast et tal [0-2]:");
            valg = keyboard.nextInt();
            keyboard.nextLine();
            if (valg ==0) {
                System.out.println("Tak for idag.");
                break;
            }


            if (valg == 1){
                int bookerValg;
                while (true){
                    System.out.println("Du har nu følgende muligheder:");
                    System.out.println("Tast 1: Se ledige tider");
                    System.out.println("Tast 2: Opret booking");
                    System.out.println("Tast 3: Slet en booking");
                    System.out.println("Tast 4: Gå til forrige side");
                    System.out.println("Indtast et tal [1-4]");
                    bookerValg = keyboard.nextInt();
                    keyboard.nextLine();
                    if (bookerValg == 4) break;
                }
            }
            else {
                int revisorValg;
                while (true){
                    System.out.println("Du har nu følgende muligheder:");
                    System.out.println("Tast 1: Se regnskab");
                    System.out.println("Tast 2: Gå til forrige side");
                    System.out.println("Indtast et tal [1-2]");
                    revisorValg = keyboard.nextInt();
                    keyboard.nextLine();
                    if (revisorValg == 2) break;
                }
            }
        }

    }
}
