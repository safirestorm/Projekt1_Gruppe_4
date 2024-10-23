package src;

import java.time.LocalDate;
import java.util.ArrayList;

public class Kalender {
    public static void main(String[] args) {


        ArrayList<Aftaler> bookinger= new ArrayList<>();
        bookinger.add(new Aftaler("Bob",LocalDate.of(2024,03,22), 13));
        bookinger.add(new Aftaler("Bob",LocalDate.of(2024,03,22), 11));
        bookinger.add(new Aftaler("Bob",LocalDate.of(2024,03,22), 13));



        for(Aftaler b:bookinger){
            for(int a=10; a<18; a++) {
                if (b.dato.isEqual(LocalDate.of(2024, 03, 22)) && b.bookingtid == a) {
                    System.out.println("Klokken: "+a+":00");
                    System.out.println(b);
                }
            }
        }
    }
}
