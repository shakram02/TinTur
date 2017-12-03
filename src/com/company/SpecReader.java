package com.company;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;

public class SpecReader {

    private final Scanner scanner;

    public SpecReader(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    public int readStateCount() {
        return readNumber("Enter the number of states");
    }

    public HashSet<Character> readInternalAlphabet() {
        return readAlphabet("Internal");
    }

    public HashSet<Character> readLanguageAlphabet() {
        return readAlphabet("Language");
    }

    public MachineTransition[] readTransitionFunction() {
        int count = readNumber("Enter the number of transitions");
        System.out.println("Enter the states on the form: q i q' i' A");

        MachineTransition[] transitionFunction = new MachineTransition[count];

        for (int i = 0; i < count; i++) {
            System.out.println("State #" + (i + 1) + ": ");
            // State, Input -> NextState, WrittenChar, Action
            int state = readNumber();
            char input = readChar();

            int nextStateNumber = readNumber();
            char writtenChar = readChar();

            MachineAction action = MachineAction.fromString(String.valueOf(readChar()));
            transitionFunction[i] = new MachineTransition(state, input, nextStateNumber, writtenChar, action);
        }

        return transitionFunction;
    }

    public MachineConfiguration readInitialConfiguration() {
        int stateNumber = readNumber("Enter the state number");
        int position = readInitialHeadPosition();
        return new MachineConfiguration(stateNumber, position);
    }

    /**
     * Reads a 1-based indexed head position
     *
     * @return A 0-based index head position, all internal indices are 0-based
     */
    private int readInitialHeadPosition() {
        return readNumber("Enter head initial position") - 1;
    }

    private int readNumber(String message) {
        System.out.print(message + ": ");
        return readNumber();
    }

    private int readNumber() {
        int number = 0;
        while (number <= 0) {
            number = scanner.nextInt();

            if (number <= 0) {
                System.err.print("Enter a positive number: ");
            }
        }

        return number;
    }

    private char readChar() {
        String input = "";

        while (input.length() != 1) {
            input = scanner.next().trim().toUpperCase();

            if (input.length() != 1) {
                System.err.println("Enter a single char");
            }
        }

        return input.charAt(0);
    }

    private HashSet<Character> readAlphabet(String alphabetName) {
        int internalAlphabetCount = readNumber("Enter the number of the " + alphabetName + "  alphabet");

        System.out.println("Enter the characters of the alphabet");
        HashSet<Character> alphabet = new HashSet<>(internalAlphabetCount);

        for (int i = 0; i < internalAlphabetCount; i++) {
            System.out.print(String.format("[#%s]: ", i));
            alphabet.add(readChar());
        }

        return alphabet;
    }
}
