package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        int check=0;
        int humanStrenght=5;

        Scanner scanner = new Scanner(System.in);

        World currentGame = new World(System.out);

        System.out.println("Welcome to EVOLUTION \n");
        System.out.println("jakies pierdoly ze start gry"); //nowy ekran
        System.out.println("Zacznij standardowo"); //startuje bez zmian
        System.out.println("dodkonaj zmian"); //ekran zmian
        check=scanner.nextInt();
        if(check==1){}
        else{
            Settings settings = new Settings(currentGame);
            settings.mainWindow();
            //możemy dodać opcję zmiany np. 2 zwierząt w sensie statystyk
            currentGame=settings.workInProgress;
            humanStrenght=settings.humanStrenght;
        }

        currentGame.beginGame(humanStrenght); //najpierw tworzymy, dodajemy zwierzęta na mapę pierwszy raz, później używamy funkcji day

        currentGame.play();
    }
    void animalQuantity(){

    }
}