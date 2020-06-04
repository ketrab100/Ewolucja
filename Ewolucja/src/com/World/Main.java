package com.World;

import java.util.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        int check=0;

        Scanner scanner = new Scanner(System.in);

        World currentGame = new World();


        System.out.println("Welcome to EVOLUTION \n");
        System.out.println("This simulation let us check if people can dominate world under given circumstances. Simulation ends when population reaches 0 or there is no animals left");
        System.out.println("While animals have fixed statistics people get stronger over time."); //nowy ekran
        System.out.println("1 - Begin default game"); //startuje bez zmian
        System.out.println("2 - Make changes or add your animals"); //ekran zmian
        check=scanner.nextInt();

        if(check==1){
        }
        else{
            Settings settings = new Settings();
            settings.getWorldToSetUp(currentGame);
            settings.mainWindow();
            //możemy dodać opcję zmiany np. 2 zwierząt w sensie statystyk
            currentGame=settings.workInProgress;
        }

        currentGame.beginGame(); //najpierw tworzymy, dodajemy zwierzęta na mapę pierwszy raz, później używamy funkcji day

        currentGame.play();
    }
}