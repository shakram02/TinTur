package com.company;

class MachineConfiguration implements Cloneable {
    private int stateNumber;
    private int headPosition;

    public int getStateNumber() {
        return stateNumber;
    }

    /**
     * Returns the 0-based index of the head on the tape
     *
     * @return the 0-based index of the head on the tape
     */
    public int getHeadPosition() {
        return headPosition;
    }


    MachineConfiguration(int stateNumber, int headPosition) {
        this.stateNumber = stateNumber;
        this.headPosition = headPosition;
    }

    @Override
    public String toString() {
        return String.format("Head Position: %s | State Number: %s", headPosition + 1, stateNumber);
    }
}
