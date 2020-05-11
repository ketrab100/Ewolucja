package com.company;

<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> huta
import java.util.Random;


public abstract class Animal implements Cloneable {
    String name;
    int id;
    int positionX;
    int positionY;
    int speed;
    int age;
    int maxStomach;
    int stomach;
    int delivery;
    int maxDelivery;
<<<<<<< HEAD
    int searchrange;
    int value;
    int resistance;
=======
    int searchRange;
    int value;
    int resistance;
    Target target;
>>>>>>> huta

    public Animal(int positionX, int positionY){
        this.positionX=positionX;
        this.positionY=positionY;
    }
<<<<<<< HEAD
    /*public Object clone() {
=======
    public Object clone() {
>>>>>>> huta
        Object newObject=null;
        try { newObject = super.clone(); } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return newObject;
<<<<<<< HEAD
    }*/
=======
    }
>>>>>>> huta
    void moveRandom (){
        Random rand=new Random();

        int directionY=rand.nextInt(speed*2+1)-speed;
        int help=speed-Math.abs(directionY);
        int directionX=rand.nextInt(help*2+1)-help;

        if(directionY<1) directionY=1;
        else if(directionY>100) directionY=100;

        if(directionX<1) directionX=1;
        else if(directionX>100) directionX=100;
        this.positionX=directionX;
        this.positionY=directionY;
    }

    boolean readyToDelivery(){
        if (this.delivery>=this.maxDelivery){ //to jest dziwne, nie wiem co znaczy dokładnie, ale chyba inaczej powinno być, w sumie zmienię
            return true;                      //chyba wiem co chiałeś zrobić, można po prostu ustawić ujemną wartość delivery na początku, opóźni to rozmnażanie
        }
        else {
            return false;
        }
    }
<<<<<<< HEAD
    boolean isHungry(){
=======
    boolean hunger(){
>>>>>>> huta
        if(this.stomach<=maxStomach/2)
            return true;
        else
            return false;
    }
<<<<<<< HEAD
=======

    void eatFood(List<Fruit> listofFruits, List<Predator> listofPredators, List<Herbivore> listofHerbivores, List<Human> listofPeople, int idCheckTab[], int iterator){
        int movementLeft;

        if(this.target.isInRange==1){ //tu się wyświetla żarcie na czerwono, ale nie ma błędu, bo jeśli się nie przerwie to food na pewno zostanie stworzony
            this.stomach+=this.target.value;
            this.stomach=Math.max(this.stomach, this.maxStomach);
            this.positionY=this.target.positionY;
            this.positionX=this.target.positionX;

            idCheckTab[this.target.id]=0;

            if(this.target.iteratorlist==0)
                listofFruits.remove(this.target.iterator);

            else if(this.target.iteratorlist==1) {
                listofPredators.remove(this.target.iterator);

                if((this.id-(this.id/100)*100)>0 && (this.id-(this.id/100)*100)<=10 && iterator>this.target.iterator)
                    iterator--;
            }
            else if(this.target.iteratorlist==2)
                listofHerbivores.remove(this.target.iterator);

            else if(this.target.iteratorlist==3){
                listofPeople.remove(this.target.iterator);
            }
        }
        else{
            movementLeft=this.speed;
            if(this.target.positionX>this.positionX){
                movementLeft-=Math.min(movementLeft, Math.abs(this.target.positionX-this.positionX));
                this.positionX+=speed-movementLeft;
                if(movementLeft!=0){
                    if(this.target.positionY>this.positionY)
                        this.positionY+=movementLeft;
                    else
                        this.positionY-=movementLeft;
                }
            }
            else{
                this.target.isInRange-=Math.min(movementLeft, Math.abs(this.target.positionX-this.positionX));
                this.positionX-=speed+movementLeft;
                if(movementLeft!=0){
                    if(this.target.positionY>this.positionY)
                        this.positionY+=movementLeft;
                    else
                        this.positionY-=movementLeft;
                }
            }
        }
    }
>>>>>>> huta
}
