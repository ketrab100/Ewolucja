package com.company;

<<<<<<< Updated upstream
public class World {

    void day(){

    }
    void play(){

    }

=======
import java.io.PrintStream;

public class World {
    int sizeX;
    int sizeY;
    PrintStream outStream;

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
>>>>>>> Stashed changes
}
