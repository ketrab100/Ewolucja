package com.company;

import java.util.Vector;

public class Main {

    public static void main(String[] args) {
	    Tiger bb = new Tiger();
        Tiger cc = new Tiger();
        Vector<Tiger> tigers =new Vector<Tiger>();
        tigers.add(bb);
        tigers.add(cc);

        for (int i=0;i<2;i++) {
            Tiger tiger = new Tiger();
            tiger = tigers.get(i);
            tiger.move();
            System.out.println("tygrys :" + i);
            System.out.println("pozycja x: " + tiger.positonX);
            System.out.println("pozycja y: " + tiger.positonY);
            //System.out.println(Math.random());
        }
    }
}