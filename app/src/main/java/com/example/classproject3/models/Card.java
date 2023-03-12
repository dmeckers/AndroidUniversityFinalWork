package com.example.classproject3.models;

public class Card {

    private final int value;
    private final int imageResource;
    private boolean isOpened;

    public Card(int value, int imageResource) {
        this.value = value;
        this.imageResource = imageResource;
        isOpened = false;
    }

    public int getValue() {
        return value;
    }

    public int getImageResource() {
        return imageResource;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }
}
