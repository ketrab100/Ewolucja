package com.company;

import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class Main {

<<<<<<< Updated upstream
    public static void main(String[] args) {
=======

    public static void main(String[] args) throws InterruptedException {
>>>>>>> Stashed changes

        int sizeX=100;
        int sizeY=100;
        int animalQuantity=1;
        int humanQuantity=2;
        int humanStrenght=5;

        //najpierw tworzymy, dodajemy zwierzęta na mapę pierwszy raz, później używamy funkcji day
        System.out.println("Welcome to EVOLUTION \n");
        System.out.println("jakies pierdoly ze start gry"); //nowy ekran
        System.out.println("Zacznij standardowo"); //startuje bez zmian
        System.out.println("dodkonaj zmian"); //ekran zmian
        {
            System.out.println("wybierz rozmiar mapy"); //jeśli wciśnie to wpisuje 2 rozmiary X Y
            System.out.println("liczba ludzi na poczatku"); //jeśli wciśnie to zmieni tą wartość
            System.out.println("sila czlowieka na poczatku"); //zmienia siłę
            System.out.println("liczba zwierząt na poczatku"); //jeśli wciścnie to zmieni tą wartość
            //możemy dodać opcję zmiany np. 2 zwirząt w sensie statystyk
        }
        World earth = new World(sizeX, sizeY,System.out);

        earth.beginGame(animalQuantity,humanQuantity,humanStrenght); //najpierw tworzymy, dodajemy zwierzęta na mapę pierwszy raz, później używamy funkcji day
<<<<<<< Updated upstream

        //earth.play();
        //PRE0 tiger = new PRE0(1,1,1);
        for (int i=0;i<100;i++){
            System.out.printf("day" + i + "\n");
            earth.turn();
            for (int j=0 ; j<earth.listofPredators.size();j++){
                Predator tiger1 = earth.listofPredators.get(j);
                System.out.println(tiger1.id);
            }
        }
=======
        earth.play();
>>>>>>> Stashed changes
    }
}