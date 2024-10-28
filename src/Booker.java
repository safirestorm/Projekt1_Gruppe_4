package src;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Booker {
    static Scanner tastatur = new Scanner(System.in);
    ArrayList<Aftaler> bookinger= new ArrayList<>();

    public static void main(String[] args) {

        Aftaler a1=new Aftaler("bob",LocalDate.of(2222,11,11),11);
        a1.seLedigeTider();



    }

    }

