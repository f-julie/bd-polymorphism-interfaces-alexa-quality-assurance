package com.amazon.ata.interfaces.increment;

public class IncrementerSynchronizer {
    public static void main(String[] args) {
        Incrementable[] incrementables = {
                new SequentialIncrementer(),
                new FixedIncrementer(7),
                new RandomIncrementer()
        };

        for (int i = 0; i < 5; i++) {
            for (Incrementable incrementable : incrementables) {
                incrementable.increment();
                System.out.println(incrementable.getValue());
            }
        }
    }
}
