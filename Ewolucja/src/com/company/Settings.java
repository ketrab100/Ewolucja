package com.company;

import java.util.Scanner;

public class Settings{

    World workInProgress;

    public Settings(){
    }

    int check=0;
    int humanStrenght=5;
    Scanner scanner = new Scanner(System.in);

    void getWorldToSetUp(World world){
        this.workInProgress=world;
    }
    void mainWindow(){
        System.out.println("1 Pick map size"); //jeśli wciśnie to wpisuje 2 rozmiary X Y
        System.out.println("2 Change human strenght. Default value is 5"); //jeśli wciśnie to zmieni tą wartość
        System.out.println("3 Change quantity of animals per type"); //jeśli wciścnie to zmieni tą wartość
        this.check=this.scanner.nextInt();
        if(check==1){
            this.workInProgress.sizeX = scanner.nextInt();
            this.workInProgress.sizeY = scanner.nextInt();
        }
        else if(check==2){
            this.humanStrenght=scanner.nextInt();
        }
        else if(check==3){
            this.animalQuantity();
        }
    }
    void animalQuantity(){
        System.out.println(" Animal name | Current quantity");

        for(int q=0; q<=20; q++){
            System.out.println(this.workInProgress.animalTypes[q] + " | " + this.workInProgress.animalQuantity[q]);
            this.workInProgress.animalQuantity[q]=this.scanner.nextInt();
        }
    }
}
