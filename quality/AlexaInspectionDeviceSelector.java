package com.amazon.ata.interfaces.classroom.devices.alexa.quality;

import com.amazon.ata.interfaces.classroom.increment.SequentialIncrementer;

/**
 * Logic to select an Amazon device for inspection. The logic will select every nth device to be inspected
 * before it is shipped to a customer to ensure high quality.
 */
public class AlexaInspectionDeviceSelector {

    private final SequentialIncrementer incrementer;

    /**
     * Instantiates a new Alexa inspection device selector with the given incrementer.
     *
     * @param incrementer the incrementer
     */
    public AlexaInspectionDeviceSelector(SequentialIncrementer incrementer) {
        this.incrementer = incrementer;
    }

    /**
     * This method returns the position of the device to be selected for quality checks. The position is
     * based on its location on the conveyor belt. For a batch of devices the first element on the belt is 1.
     *
     * @return the position of the device to be selected and checked for quality.
     */
    public int getSampleDevicePosition() {
        incrementer.increment();
        return incrementer.getValue();
    }
}
