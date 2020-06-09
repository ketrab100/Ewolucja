package com.World;

public class Statistics {

    private int[][] quantity = new int[50][200];


    void turnPassed(){

        for(int q=0; q<=20; q++){

            for(int w=105; w>0; w--){
                this.quantity[q][w]=this.quantity[q][w-1];
            }
        }
        for(int q=0; q<=20; q++)
            this.quantity[q][0]=0;
    }

    void countMeAs(int ID){
        this.quantity[ID][0]++;
    }

    void IwasEaten(int ID){
        this.quantity[ID][0]--;
    }

    int giveMyStats(int ID, int turn){
        return this.quantity[ID][turn];
    }

}
