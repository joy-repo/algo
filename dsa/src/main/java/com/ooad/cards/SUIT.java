package com.ooad.cards;

public enum SUIT {

    Club(0), Diamond(1), Heart(2), Spade(3);
    private int value;

    private SUIT(int v) {
        value = v;
    }

    public int getValue() {
        return value;
    }


}
