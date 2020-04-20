package com.company;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

public class World {
    int sizeX;
    int sizeY;
    PrintStream outStream;
    List<Tiger> tigers = new LinkedList<>();
    
    public World(int sizeX,int sizeY, PrintStream _outStream){
        this.outStream=_outStream;
        this.sizeX=sizeX;
        this.sizeY=sizeY;
    }
    void day(){
        outStream.println("kolenjy dzień");
    }
    void play(){
        day();
    }

}