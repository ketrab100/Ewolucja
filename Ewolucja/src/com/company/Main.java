package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        World earth = new World(1000,1000,System.out);
        Tiger bb = new Tiger((int)(Math.random()*earth.sizeX), (int)(Math.random()*earth.sizeY));
        Tiger cc = new Tiger((int)(Math.random()*earth.sizeX), (int)(Math.random()*earth.sizeY));
        Goat dd = new Goat(((int)Math.random()*earth.sizeX), (int)(Math.random()*earth.sizeY));
        Goat ee = new Goat(((int)Math.random()*earth.sizeX), (int)(Math.random()*earth.sizeY));

        //Vector<Tiger> tigers =new Vector<Tiger>();
        List<Tiger> tigers=new LinkedList<>();
        List<Goat> goats= new LinkedList<>();
        tigers.add(bb);
        tigers.add(cc);
        goats.add(ee);
        goats.add(dd);
        tigers.remove(1);

        for (int j=0;j<100;j++){
            System.out.printf("\n" + "Dzień: " + j + "\n");
            for (int i=0;i<tigers.size();i++) {
                Tiger tiger;
                tiger = tigers.get(i);
                tiger.move();
                tiger.growth(1);

                if(tiger.maxDelivery == tiger.age){
                    tigers.add(tiger.lilTiger());
                }
                System.out.println("tygrys :" + i);
                System.out.println("pozycja x: " + tiger.positonX);
                System.out.println("pozycja y: " + tiger.positonY );
            }
            for (int i=0;i<goats.size();i++) {
                Goat goat = new Goat((int)(Math.random()*earth.sizeX), (int)(Math.random()*earth.sizeY));
                System.out.println("koza : " + i);
                goat=goats.get(i);
                goat.move();
                System.out.println("pozycja x: " + goat.positonX);
                System.out.println("pozycja y: " + goat.positonY);
            }
        }
    }
}