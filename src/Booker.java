package src;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Booker {
    public static void main(String[] args) {

        Aftaler a1=new Aftaler("Bob",LocalDate.of(2222,11,11), 11);
        a1.opretAftaler();
        a1.opretAftaler();
        a1.seLedigeTider();
        a1.fjerAftaler();
        a1.seLedigeTider();

    }
}

