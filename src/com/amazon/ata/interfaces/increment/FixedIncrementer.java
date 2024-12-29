package com.amazon.ata.interfaces.increment;

public class FixedIncrementer implements Incrementable {
    private int value;
    private int n;

    // constructors


    public FixedIncrementer() {
        //this.value = 0;
        //this.n = 1;
        this(1,0);
    }

    public FixedIncrementer(int n) {
        //this.value = 0;
        //this.n = n;
        this(n, 0);
    }

    public FixedIncrementer(int n, int startValue) {
        this.value = startValue;
        this.n = n;
    }

    @Override
    public int increment() {
        this.value += this.n;
        return this.getValue();
    }

    @Override
    public int getValue() {
        return this.value;
    }
}
