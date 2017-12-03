package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TuringMachine {
    private MachineConfiguration currentConfig;
    private final ArrayList<Character> tape;
    private HashMap<String, MachineTransition> stateTable;

    public TuringMachine(final MachineConfiguration initialConfig, String tape) {

        ArrayList<Character> internalTape = new ArrayList<>();
        for (Character c : tape.toCharArray()) {
            internalTape.add(c);
        }

        this.currentConfig = initialConfig;
        this.tape = internalTape;
        this.stateTable = new HashMap<>();
    }

    public void addTransition(final MachineTransition transition)
            throws IllegalArgumentException {

        String stateKey = createStateKey(transition.getInitialStateNumber(), transition.getInputChar());

        if (stateTable.containsKey(stateKey)) {
            throw new IllegalArgumentException("State already added");
        }

        stateTable.put(stateKey, transition);
    }

    public void addTransitions(MachineTransition[] transitions) {
        for (MachineTransition t : transitions) {
            this.addTransition(t);
        }
    }

    public String getCurrentConfig() {
        Character[] chars = new Character[this.tape.size()];
        this.tape.toArray(chars);

        return currentConfig + "\n tape: " + Arrays.toString(chars);
    }

    /**
     * Moves the turing machine consuming one input
     * <p>
     * * @return Character written to the tape
     *
     * @throws IllegalArgumentException on invalid input
     */
    public char consumeInput() throws IllegalArgumentException {
        char internalAlphabetInput = tape.get(currentConfig.getHeadPosition());

        String stateKey = createStateKey(currentConfig.getStateNumber(), internalAlphabetInput);
        MachineTransition transition = stateTable.get(stateKey);

        char toBeWritten = transition.getWrittenToTape();


        // Write to tape
        char toBeDeleted = updateTape(toBeWritten);

        // Get next state params
        int position = getNextHeadPosition(transition.getAction());
        int nextStateNumber = transition.getNextStateNumber();

        // Update state
        this.currentConfig = new MachineConfiguration(nextStateNumber, position);

        return toBeDeleted;
    }

    private char updateTape(char toBeWritten) {
        char toBeDeleted = tape.get(currentConfig.getHeadPosition());

        int writeAtIndex = currentConfig.getHeadPosition();
        if (tape.size() < writeAtIndex) {
            tape.ensureCapacity(writeAtIndex);
        }
        tape.set(writeAtIndex, toBeWritten);

        return toBeDeleted;
    }

    private int getNextHeadPosition(final MachineAction action) {
        switch (action) {
            case Left:
                return currentConfig.getHeadPosition() - 1;
            case Right:
                return currentConfig.getHeadPosition() + 1;
            case Accept:
                return currentConfig.getHeadPosition();
            case Reject:
                return currentConfig.getHeadPosition();
            default:
                throw new IllegalStateException();
        }
    }

    private static String createStateKey(int stateNumber, char input) {
        return "" + stateNumber + input;
    }
}
