package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws IOException {
//        boolean isValidInput = internalTape.stream().allMatch(internalAlphabet::contains);
//        if (!isValidInput) {
//            throw new IllegalArgumentException("Invalid characters in input tape");
//        }
        File file = new File("./config.txt");
        FileInputStream bs = new FileInputStream(file);

        SpecReader reader = new SpecReader(bs);

        int stateCount = reader.readStateCount();
        System.out.println(stateCount);

        MachineConfiguration initialConfig = reader.readInitialConfiguration();
        System.out.println(initialConfig);

        HashSet<Character> internalAlphabet = reader.readInternalAlphabet();
        System.out.println(Arrays.toString(internalAlphabet.toArray()));
        HashSet<Character> languageAlphabet = reader.readLanguageAlphabet();
        System.out.println(Arrays.toString(languageAlphabet.toArray()));

        MachineTransition[] transitionFunction = reader.readTransitionFunction();
        System.out.println(Arrays.toString(transitionFunction));

        TuringMachine machine = new TuringMachine(initialConfig, "AAA");
        machine.addTransitions(transitionFunction);

        machine.consumeInput();
        System.out.println(machine.getCurrentConfig());
        bs.close();

    }
}
