package com.Lifeforms;

public class Fruit {
    public Position position = new Position();
    protected int value;

    public Fruit(int _positionX, int _positionY, int _value) {
        this.position.changeX(_positionX);
        this.position.changeY(_positionY);
        this.value = _value;
    }

}

