package com.Lifeforms;

public class Target {

    public Position position = new Position(1,1);
    protected int value;
    protected int id;
    protected int numberOnTheList;
    protected int typeOf;
    protected boolean isInRange;

    public int myNumberonList(){
        return this.numberOnTheList;
    }

    public int myTypeIs(){
        return this.typeOf;
    }

    public int myID(){
        return this.id;
    }

    public int targetTypeID(){
        return this.id%100;
    }
}