package src;

import java.time.LocalDate;
import java.util.Scanner;

public class Salon {
    static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        int valg;
        Aftaler aftale = new Aftaler("Bob", LocalDate.of(2024,06,05), 13);

        while (true) {
            System.out.println("Velkommen til Harry's Salon");
            System.out.println("Hvad ønsker du at foretage dig?");
            System.out.println("Tast 1: Booking");
            System.out.println("Tast 2: Økonomi");
            System.out.println("Tast 0: Afslut");
            System.out.println("Indtast et tal [0-2]:");

            valg = checkOmTal();
            switch (valg) {
                case 0:
                    System.out.println("Tak for idag.");
                    break;
                case 1:
                    int bookerValg = checkOmTal();
                    while (true) {
                        System.out.println("Du har nu følgende muligheder:");
                        System.out.println("Tast 1: Se ledige tider");
                        System.out.println("Tast 2: Opret booking");
                        System.out.println("Tast 3: Slet en booking");
                        System.out.println("Tast 4: Gå til forrige side");
                        System.out.println("Indtast et tal [1-4]");
                        bookerValg = keyboard.nextInt();
                        keyboard.nextLine();
                        if (bookerValg == 4) break;

                        switch (bookerValg) {
                            case 1:
                                aftale.seLedigeTider();
                                System.out.println();
                                break;
                            case 2:
                                aftale.opretAftaler();
                                System.out.println();
                                while (true) {
                                    String svar;
                                    System.out.println("Vil du oprette flere aftaler?");
                                    svar = keyboard.nextLine();
                                    if (svar.equals("Ja")) {
                                        aftale.opretAftaler();
                                    } else break;       // Skal laves en catch, hvis de svarer andet end ja/nej
                                }

                                break;
                            case 3:
                            default:
                                System.out.println("Ugyldigt valg, prøv igen");
                                continue;
                        }
                    }
                case 2:
                    int revisorValg;
                    while (true) {
                        System.out.println("Du har nu følgende muligheder:");
                        System.out.println("Tast 1: Se regnskab");
                        System.out.println("Tast 2: Gå til forrige side");
                        System.out.println("Indtast et tal [1-2]");
                        revisorValg = keyboard.nextInt();
                        keyboard.nextLine();
                        if (revisorValg == 2) break;

                        if (revisorValg == 1) {

                        }
                    }
                default:
                    System.out.println("Ugyldigt valg, prøv igen");
                    System.out.println();
            }
        }

    }

    static int checkOmTal() {
        while (true) {
            String valg = keyboard.nextLine();
            try {
                return Integer.parseInt(valg);  // Checker at det kun indeholder tal
            } catch (NumberFormatException e) {
                System.out.println("Ugyldigt valg, prøv igen");
            }
        }
    }
}

