package com.company;

public class Cow extends Herbivore {
final int searchRange;
final int speed;
final int maxDelivery;
final int maxStomach;
    public Cow() {
        this.value=12;
        this.searchRange=20;
        this.name="COW ";
        this.speed = 4;
        this.age=0;
        this.delivery=0;
        this.maxDelivery=40;
        this.maxStomach=50;
        this.stomach=this.maxStomach;
        this.resistance=20;
    }
}
