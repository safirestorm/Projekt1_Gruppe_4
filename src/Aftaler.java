package src;

import java.time.LocalDate;



public class Aftaler {
    int id;
    static int nrAftaler=0;
    String navn;
    LocalDate dato;
    int bookingtid=0;
    double beløb;

    Aftaler(String navn, LocalDate dato, int bookingtid){
        nrAftaler++;
        id=nrAftaler;
        this.navn = navn;
        this.dato = dato;
        this.bookingtid = bookingtid;
        beløb = 250;
    }


    public String toString() {
        return id+ " " + navn + " "  + dato + " " +"klokken "+ bookingtid + ":00 " + beløb +" kroner";
    }
    public static void main(String[] args) {
        Aftaler a1 = new Aftaler("Bob",LocalDate.of(2024,10,23), 13);
        System.out.println( a1);
    }
}
