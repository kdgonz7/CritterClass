package com.todos;

import io.github.ollama4j.exceptions.OllamaException;

public class Main {
    public static void main(String[] args) throws OllamaException {
        Critter myCritter = new Critter("John");
        myCritter.setMood(1524);
        myCritter.setBoredom(0);
        myCritter.play();
    }
}