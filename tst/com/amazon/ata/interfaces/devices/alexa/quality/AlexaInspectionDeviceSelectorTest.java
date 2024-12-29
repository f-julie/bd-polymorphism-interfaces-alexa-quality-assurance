package com.amazon.ata.interfaces.devices.alexa.quality;

import com.amazon.ata.interfaces.increment.FixedIncrementer;
import com.amazon.ata.interfaces.increment.Incrementable;
import com.amazon.ata.interfaces.increment.RandomIncrementer;
import com.amazon.ata.interfaces.increment.SequentialIncrementer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlexaInspectionDeviceSelectorTest {

    private AlexaInspectionDeviceSelector selector;

    @Test
    public void getSampleDevicePosition_firstCallWithSequentialIncrementer_returnsOne() {
        // GIVEN - a selector object and a new incrementer
        final Incrementable incrementer = new SequentialIncrementer();
        selector = new AlexaInspectionDeviceSelector(incrementer);

        // WHEN - call getSampleDevicePosition for the first time
        int result = selector.getSampleDevicePosition();

        // THEN - returns 1, the first device
        assertEquals(1, result, "Expected first call to incremental device selector to return 1.");
    }

    @Test
    public void getSampleDevicePosition_firstCallWithSequentialIncrementerWithStartValue_returnsOne() {
        // GIVEN - a selector object and a new incrementer initialized with a start value
        int startValue = 10;
        final Incrementable incrementer = new SequentialIncrementer(startValue);
        selector = new AlexaInspectionDeviceSelector(incrementer);

        // WHEN - call getSampleDevicePosition for the first time
        int result = selector.getSampleDevicePosition();

        // THEN - returns 1 more than the starting value
        assertEquals(startValue + 1, result,
                     "Expected first call to incremental device selector to return 1 more than start value.");
    }

    @Test
    public void getSampleDevicePosition_secondCallWithSequentialIncrementerWithoutStartValue_returnsTwo() {
        // GIVEN - a selector object, a new incrementer, and an initial call to the selector
        final Incrementable incrementer = new SequentialIncrementer();
        selector = new AlexaInspectionDeviceSelector(incrementer);
        selector.getSampleDevicePosition();

        // WHEN - call getSampleDevicePosition
        int result = selector.getSampleDevicePosition();

        // THEN - returns 2, the second device
        assertEquals(2, result, "Expected the second call to incremental device selector to return 2.");
    }

    @Test
    public void getSampleDevicePosition_secondCallWithSequentialIncrementerWithStartValue_returnsTwoMoreThanStart() {
        // GIVEN - a selector object, a new incrementer initialized with a start value, and an initial call to the selector
        int startValue = 10;
        final Incrementable incrementer = new SequentialIncrementer(startValue);
        selector = new AlexaInspectionDeviceSelector(incrementer);
        selector.getSampleDevicePosition();

        // WHEN - call getSampleDevicePosition
        int result = selector.getSampleDevicePosition();

        // THEN - returns 2 more than the starting value
        assertEquals(startValue + 2, result,
                     "Expected the second call to incremental device selector to return 2 more than the start value.");
    }

    @Test
    public void getSampleDevicePosition_firstCallWithFixedIncrementer_returnsOne() {
        // GIVEN - a selector object and a new incrementer
        final Incrementable incrementer = new FixedIncrementer();
        selector = new AlexaInspectionDeviceSelector(incrementer);

        // WHEN - call getSampleDevicePosition for the first time
        int result = selector.getSampleDevicePosition();

        // THEN - returns 1, the first device
        assertEquals(1, result, "Expected first call to fixed device selector to return 1.");
    }

    @Test
    public void getSampleDevicePosition_firstCallWithFixedIncrementerWithN_returnsN() {
        // GIVEN - a selector object and a new incrementer
        int n = 5;
        final Incrementable incrementer = new FixedIncrementer(n);
        selector = new AlexaInspectionDeviceSelector(incrementer);

        // WHEN - call getSampleDevicePosition for the first time
        int result = selector.getSampleDevicePosition();

        // THEN - returns n, the first device
        assertEquals(n, result, "Expected first call to fixed device selector to return n.");
    }

    @Test
    public void getSampleDevicePosition_firstCallWithRandomIncrementer_returnsBetween1and100() {
        // GIVEN - a selector object and a new incrementer
        final Incrementable incrementer = new RandomIncrementer();
        selector = new AlexaInspectionDeviceSelector(incrementer);

        // WHEN - call getSampleDevicePosition for the first time
        int result = selector.getSampleDevicePosition();

        // THEN - returns between 1 and 100
        assertTrue((result >=1), "Expected first call to random device selector to be greater than or equal to 1.");
        assertTrue((result <=100), "Expected first call to random device selector to be less than or equal to 100.");

    }
}

