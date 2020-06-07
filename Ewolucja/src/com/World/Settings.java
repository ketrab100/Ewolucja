package com.World;

import java.util.Scanner;

public class Settings{

    World workInProgress;

    public Settings(){
    }

    int check=0;
    Scanner scanner = new Scanner(System.in);

    void getWorldToSetUp(World world){
        this.workInProgress=world;
    }

    /**
     * Setting all possible options
     */
    void mainWindow(){
        while (check!=6) {
            System.out.flush();
            System.out.println("1 - Pick map size"); //jeśli wciśnie to wpisuje 2 rozmiary X Y
            System.out.println("2 - Change human strenght. Default value is 5"); //jeśli wciśnie to zmieni tą wartość
            System.out.println("3 - Create your predator, only one can be created, current quantity: "+ this.workInProgress.howMuchAnimals(10));
            System.out.println("4 - Create your herbivore, only one can be created, current quantity: "+ this.workInProgress.howMuchAnimals(20));
            System.out.println("5 - Change quantity of animals per type"); //jeśli wciścnie to zmieni tą wartość
            System.out.println("6 - Save changes and start simulation");

            this.check=this.scanner.nextInt();
            if (check == 1) {
                this.workInProgress.changeWorldSize();
            }
            else if (check == 2) {
                System.out.printf("Human streght: ");
                this.workInProgress.templates.changeHumanStrenght(scanner.nextInt());
            }
            else if(check == 3){
                System.out.println("Pick value - how much worth in while eaten");
                int value = this.scanner.nextInt();
                System.out.println("Pick search range - distance in which predator searches its prey");
                int searchRange = this.scanner.nextInt();
                System.out.println("Pick movement speed");
                int speed = this.scanner.nextInt();
                System.out.println("How much time until breed");
                int maxDelivery = this.scanner.nextInt();
                System.out.println("How much time until starved out");
                int maxStomach = this.scanner.nextInt();
                System.out.println("Strenght - can only eat animals with lower resistance");
                int strenght = this.scanner.nextInt();
                System.out.println("Resistance - can't be eaten by animals with lower strenght, can't be lower than strenght");
                int resistance = this.scanner.nextInt();
                resistance=Math.max(resistance,strenght);
                workInProgress.templates.createYourAnimal1Template(value,searchRange,speed,maxDelivery,maxStomach,resistance,strenght);
            }
            else if(check == 4){
                System.out.println("Pick value - how much worth in while eaten");
                int value = this.scanner.nextInt();
                System.out.println("Pick search range - distance in which predator searches its prey");
                int searchRange = this.scanner.nextInt();
                System.out.println("Pick movement speed");
                int speed = this.scanner.nextInt();
                System.out.println("How much time until breed");
                int maxDelivery = this.scanner.nextInt();
                System.out.println("How much time until starved out");
                int maxStomach = this.scanner.nextInt();
                System.out.println("Resistance - can't be eaten by animals with lower strenght");
                int resistance = this.scanner.nextInt();
                workInProgress.templates.createYourAnimal2Template(value,searchRange,speed,maxDelivery,maxStomach,resistance);
            }

            else if (check == 5) {
                this.animalQuantity();
            }
        }
    }

    /**
     * Setting quantity of each animal
     */
    void animalQuantity(){
        System.out.println(" Animal name | Current quantity");
        System.out.println(this.workInProgress.howMuchAnimals(0) + " | " + this.workInProgress.howMuchAnimals(0));
        this.workInProgress.changeAnimalQuantity(0, this.scanner.nextInt());

        for(int q=1; q<=20; q++){
            if(this.workInProgress.animalTypes[q]!=" Human      ") {
                System.out.println(this.workInProgress.animalTypes[q] + " | " + this.workInProgress.howMuchAnimals(q));
                this.workInProgress.changeAnimalQuantity(q, this.scanner.nextInt());
            }
        }
    }
}
