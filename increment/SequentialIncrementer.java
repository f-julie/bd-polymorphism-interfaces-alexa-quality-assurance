package com.amazon.ata.interfaces.classroom.increment;

/**
 * Stores an integer value that can be incremented by one each time
 * {@code increment()} is called. The value can be retrieved at any
 * time by calling {@code getValue()}.
 */
public class SequentialIncrementer {

    private int value;

    /**
     * Creates a new {@code SequentialIncrementer} with zero as the
     * default start value.
     */
    public SequentialIncrementer() {
        this(0);
    }

    /**
     * Creates a new {@code SequentialIncrementer} with specified start
     * value as the initial integer value.
     * @param startValue The value to use for initial state of
     *                   {@code SequentialIncrementer}
     */
    public SequentialIncrementer(int startValue) {
        this.value = startValue;
    }

    /**
     * Increments the integer value by one.
     * @return The new integer value after incrementing.
     */
    public int increment() {
        this.value += 1;
        return getValue();
    }

    /**
     * Returns the current value without incrementing.
     * @return The incrementer's current value
     */
    public int getValue() {
        return this.value;
    }
}
