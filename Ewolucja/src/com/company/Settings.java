package com.company;

import java.io.IOException;
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
        while (check!=4) {
            System.out.flush();
            System.out.println("1 - Pick map size"); //jeśli wciśnie to wpisuje 2 rozmiary X Y
        System.out.println("2 - Change human strenght. Default value is 5"); //jeśli wciśnie to zmieni tą wartość
        System.out.println("3 - Create your predator, only one can be created");
        System.out.println("4 - create your herbivore, only one can be created");
        System.out.println("5 - Change quantity of animals per type"); //jeśli wciścnie to zmieni tą wartość
        System.out.println("6 - Save changes and start simulation");

        this.check=this.scanner.nextInt();
            if (check == 1) {
                System.out.printf("X size: ");
                this.workInProgress.sizeX = scanner.nextInt();
                System.out.printf("Y size: ");
                this.workInProgress.sizeY = scanner.nextInt();
            }
            else if (check == 2) {
                System.out.printf("Human strnght: ");
                this.humanStrenght = scanner.nextInt();
            }
            /*
            else if(check == 3){
                this.workInProgress.yourAnimal1Template.id=10;
                System.out.println("Pick value - how much worth in while eaten");
                this.workInProgress.yourAnimal1Template.value = this.scanner.nextInt();
                System.out.println("Pick search range - distance in which predator searches its prey");
                this.workInProgress.yourAnimal1Template.searchRange = this.scanner.nextInt();
                System.out.println("Pick movement speed");
                this.workInProgress.yourAnimal1Template.speed = this.scanner.nextInt();
                System.out.println("How much time until breed");
                this.workInProgress.yourAnimal1Template.maxDelivery = this.scanner.nextInt();
                System.out.println("How much time until starved out");
                this.workInProgress.yourAnimal1Template.maxStomach = this.scanner.nextInt();
                System.out.println("Strenght - can only eat animals with lower resistance");
                this.workInProgress.yourAnimal1Template.strenght = this.scanner.nextInt();
                System.out.println("Resistance - can't be eaten by animals with lower strenght");
                this.workInProgress.yourAnimal1Template.resistance = this.scanner.nextInt();
            }
            else if(check == 4){
                this.workInProgress.yourAnimal2Template.id=20;
                System.out.println("Pick value - how much worth in while eaten");
                this.workInProgress.yourAnimal2Template.value = this.scanner.nextInt();
                System.out.println("Pick search range - distance in which predator searches its prey");
                this.workInProgress.yourAnimal2Template.searchRange = this.scanner.nextInt();
                System.out.println("Pick movement speed");
                this.workInProgress.yourAnimal2Template.speed = this.scanner.nextInt();
                System.out.println("How much time until breed");
                this.workInProgress.yourAnimal2Template.maxDelivery = this.scanner.nextInt();
                System.out.println("How much time until starved out");
                this.workInProgress.yourAnimal2Template.maxStomach = this.scanner.nextInt();
                System.out.println("Resistance - can't be eaten by animals with lower strenght");
                this.workInProgress.yourAnimal2Template.resistance = this.scanner.nextInt();
            }

             */
            else if (check == 5) {
                this.animalQuantity();
            }
        }
    }
    void animalQuantity(){
        System.out.println(" Animal name | Current quantity");
        System.out.println(this.workInProgress.animalTypes[0] + " | " + this.workInProgress.animalQuantity[0]);
        this.workInProgress.animalQuantity[0]=this.scanner.nextInt();

        for(int q=1; q<=20; q++){
            if(this.workInProgress.animalTypes[q]!=" Human      ") {
                System.out.println(this.workInProgress.animalTypes[q] + " | " + this.workInProgress.animalQuantity[q]);
                this.workInProgress.animalQuantity[q] = this.scanner.nextInt();
            }
        }
    }
}
