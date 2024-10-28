package src;

import java.time.LocalDate;
import java.util.Scanner;

public class Salon {
    static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        int valg;
        Aftaler.bookinger.add(new Aftaler("Ole",LocalDate.of(2222,11,11),11));
        Aftaler.bookinger.add(new Aftaler("Bob",LocalDate.of(2222,11,11),11));
        Aftaler.bookinger.add(new Aftaler("Hans",LocalDate.of(2222,11,11),11));
        Aftaler.bookinger.add(new Aftaler("Mads",LocalDate.of(2222,11,11),11));
        Aftaler.bookinger.add(new Aftaler("Bent",LocalDate.of(2202,11,11),11));
        Aftaler.bookinger.add(new Aftaler("Sofie",LocalDate.of(2202,11,11),11));



        Aftaler aftale = new Aftaler("Bob", LocalDate.of(2024,06,05), 13);

        while (true) {
            System.out.println("Velkommen til Harry's Salon");
            System.out.println("Hvad ønsker du at foretage dig?");
            System.out.println("Tast 1: Booking");
            System.out.println("Tast 2: Økonomi");
            System.out.println("Tast 0: Afslut");
            System.out.println("Indtast et tal [0-2]:");

            valg = checkOmTal();
            if (valg == 0) break;

            switch (valg) {
                case 1:
                    bookingMenu();
                    break;
                case 2:
                    if(aftale.login()== true){
                        revisorMenu();
                    }
                default:
                    System.out.println("Ugyldigt valg, prøv igen");
                    System.out.println();
            }
        }
    }

    static void bookingMenu(){
        Aftaler aftale = new Aftaler("Bob", LocalDate.of(2024,06,05), 13);
        String svar;

        while (true) {
            System.out.println("Du har nu følgende muligheder:");
            System.out.println("Tast 1: Se ledige tider");
            System.out.println("Tast 2: Opret booking");
            System.out.println("Tast 3: Slet en booking");
            System.out.println("Tast 4: Gå til forrige side");
            System.out.println("Indtast et tal [1-4]");
            int bookerValg = checkOmTal();
            if (bookerValg == 4) break;

            switch (bookerValg) {
                case 1:
                    aftale.seTider();
                    System.out.println();
                    break;
                case 2:
                    aftale.opretAftaler();
                    System.out.println();
                    while (true) {
                        System.out.println("Vil du oprette flere aftaler? Ja/Nej");
                        svar = keyboard.nextLine();
                        if (svar.equalsIgnoreCase("Ja")) {
                            aftale.opretAftaler();
                        } else if (svar.equalsIgnoreCase("Nej")) {
                            break;
                        } else {
                            System.out.println("Det forstår jeg ikke, prøv igen.");
                        }
                    }
                    break;
                case 3:
                    aftale.fjernAftaler();
                    System.out.println();
                    break;
                default:
                    System.out.println("Ugyldigt valg, prøv igen");
                    continue;
            }
        }
    }

    static void revisorMenu(){
        Aftaler aftale = new Aftaler("Bob", LocalDate.of(2024,06,05), 13);

        while (true) {
            System.out.println("Du har nu følgende muligheder:");
            System.out.println("Tast 1: Se regnskab");
            System.out.println("Tast 2: Gå til forrige side");
            System.out.println("Indtast et tal [1-2]");
            int revisorValg = checkOmTal();
            if (revisorValg == 2) break;

            if (revisorValg == 1){
                aftale.seBudgetPrDag();
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

