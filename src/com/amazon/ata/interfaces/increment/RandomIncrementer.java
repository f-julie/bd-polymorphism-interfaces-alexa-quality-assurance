package com.amazon.ata.interfaces.increment;

import java.util.Random;

public class RandomIncrementer implements Incrementable {
    private int value;
    private Random random;
    // private int bound;

    public RandomIncrementer() {
        this(0);
    }

    public RandomIncrementer(int startValue) {
        this.value = startValue;
        this.random = new Random();
    }

    @Override
    public int increment() {
        this.value += this.random.nextInt(100);
        return this.getValue();
    }

    @Override
    public int getValue() {
        return this.value;
    }
}
