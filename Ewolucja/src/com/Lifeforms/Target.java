package com.Lifeforms;

public class Target {
    protected int positionX;
    protected int positionY;
    protected int value;
    protected int id;
    protected int numberOnTheList;
    protected int typeOf;
    protected int isInRange;

    public int myNumberonList(){
        return this.numberOnTheList;
    }

    public int myTypeIs(){
        return this.typeOf;
    }

    public int myID(){
        return this.id;
    }
}