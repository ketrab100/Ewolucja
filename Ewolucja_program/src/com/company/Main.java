package com.company;

public class Main {

    public static void main(String[] args) {
	    Tiger bb = new Tiger();
        for (int i=0;i<100;i++){
            bb.move();
            System.out.println("pozycja x: "+bb.positonX);
            System.out.println("pozycja y: "+bb.positonY);
            //System.out.println(Math.random());
        }
    }
}