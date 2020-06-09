package com.Lifeforms;

import java.util.Random;

public class Position {
    private int X;
    private int Y;

    public int getX(){
        return this.X;
    }

    public int getY(){
        return this.Y;
    }

    void changeX(int newX){
        this.X=newX;
    }

    void changeY(int newY){
        this.Y=newY;
    }
}
