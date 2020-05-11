package com.company;

import java.util.List;
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
    int searchrange;
    int value;
    int resistance;
    Target target;

    public Animal(int positionX, int positionY){
        this.positionX=positionX;
        this.positionY=positionY;
    }
    public Object clone() {
        Object newObject=null;
        try { newObject = super.clone(); } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return newObject;
    }
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
    boolean hunger(){
        if(this.stomach<=maxStomach/2)
            return true;
        else
            return false;
    }

    void eatFood(){
        int movementLeft;

        if(this.target.isInRange==1){
            this.stomach+=this.target.value;
            this.stomach=Math.max(this.stomach, this.maxStomach);
            this.positionY=this.target.positionY;
            this.positionX=this.target.positionX;
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
}
