package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        World earth = new World(10, 10,System.out);
        earth.day();
        Fruit owocyk;
        for (int i = 0; i <earth.fruits.size(); i++){
            owocyk=earth.fruits.get(i);
            for (int x=0;x<10;x++){
                for (int y=0;y<10;y++){
                    if (owocyk.positionX==x&&owocyk.positionY==y){
                        if(owocyk.calories==1){
                            System.out.printf("1");
                        }
                        else if(owocyk.calories==2){
                            System.out.printf("2");
                        }
                        else  if (owocyk.calories==3){
                            System.out.printf("3");
                        }

                    }

                }
                //System.out.printf("\n");
            }
        }
        System.out.printf("\n");
        System.out.println(earth.fruits.size());

    }
}