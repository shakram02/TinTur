package com.company;

class MachineTransition {
    private final int stateNumber;
    private final char inputChar;
    private int nextStateNumber;
    private char writtenToTape;
    private MachineAction action;

    public int getNextStateNumber() {
        return nextStateNumber;
    }

    public char getWrittenToTape() {
        return writtenToTape;
    }

    public MachineAction getAction() {
        return action;
    }

    public int getInitialStateNumber() {
        return this.stateNumber;
    }

    public char getInputChar() {
        return this.inputChar;
    }

    public MachineTransition(int stateNumber, char inputChar, int nextStateNumber, char writtenToTape, MachineAction action) {
        this.stateNumber = stateNumber;
        this.inputChar = inputChar;
        this.nextStateNumber = nextStateNumber;
        this.writtenToTape = writtenToTape;
        this.action = action;
    }

    @Override
    public String toString() {
        return String.format("[(%s, %s), (%s, %s, %s)]", stateNumber, inputChar, nextStateNumber, writtenToTape, action);
    }
}
