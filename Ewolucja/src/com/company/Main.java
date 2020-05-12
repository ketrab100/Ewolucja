package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        int sizeX=100;
        int sizeY=100;
        int animalQuantity=1;
        int humanQuantity=10;
        int humanStrenght=5;

        //najpierw tworzymy, dodajemy zwierzęta na mapę pierwszy raz, później używamy funkcji day
        System.out.println("Welcome to EVOLUTION \n");
        System.out.println("jakies pierdoly ze start gry"); //nowy ekran
        System.out.println("Zacznij standardowo"); //startuje bez zmian
        System.out.println("dodkonaj zmian"); //ekran zmian
        /*Scanner scanner = new Scanner(System.in);
        {
            System.out.println("wybierz rozmiar mapy (oś x a potem oś y)"); //jeśli wciśnie to wpisuje 2 rozmiary X Y
            sizeX = scanner.nextInt();
            sizeY = scanner.nextInt();
            System.out.println("liczba ludzi na poczatku"); //jeśli wciśnie to zmieni tą wartość
            humanQuantity = scanner.nextInt();
            System.out.println("sila czlowieka na poczatku"); //zmienia siłę
            humanStrenght = scanner.nextInt();
            System.out.println("liczba zwierząt na poczatku"); //jeśli wciścnie to zmieni tą wartość
            animalQuantity = scanner.nextInt();
            //możemy dodać opcję zmiany np. 2 zwierząt w sensie statystyk
        }*/
        World earth = new World(sizeX, sizeY,System.out);

        earth.beginGame(animalQuantity,humanQuantity,humanStrenght); //najpierw tworzymy, dodajemy zwierzęta na mapę pierwszy raz, później używamy funkcji day

        earth.play();
    }
}