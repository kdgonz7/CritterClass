package com.todos;


import io.github.ollama4j.exceptions.OllamaException;
import io.github.ollama4j.models.chat.OllamaChatRequest;
import io.github.ollama4j.models.chat.OllamaChatResult;
import io.github.ollama4j.Ollama;
import io.github.ollama4j.models.generate.OllamaGenerateRequest;
import io.github.ollama4j.models.response.OllamaResult;



// gemma3:1b
public class Critter {
    private String name;
    private int mood;
    private int boredom;

    public Critter(String name) {
        this.name = name;
        this.mood = 50; // Default mood
        this.boredom = 50; // Default boredom
    }

    public String getName() {
        return name;
    }

    public int getMood() {
        return mood;
    }

    public int getBoredom() {
        return boredom;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public void setBoredom(int boredom) {
        this.boredom = boredom;
    }

    public void play() throws OllamaException {
        Ollama ollama = new Ollama();
        // We're just using our quick-setup utility here to instantiate Ollama. Use the following
        // to set it up with your Ollama configuration.
        // Ollama ollama = new Ollama("http://your-ollama-host:11434/");
        String model = "gemma3:4b";
        ollama.pullModel(model);

        System.out.println(name + " is playing with mood " + mood + " and boredom " + boredom + " and says....");

        String resp = talk("You are a critter. Make one sentence displaying how you feel. Your mood is " + mood + " and your boredom is " + boredom + ". A higher mood number means you are happier, and a higher boredom number means you are more bored. Make sure to include the mood and boredom numbers in your response.");
        System.out.println(name + ": " + resp);

        mood += 10;
        boredom -= 10;
        if (mood > 100) {
            mood = 100; // Cap mood at 100
        }
        if (boredom < 0) {
            boredom = 0; // Floor boredom at 0
        }
    }

    public String talk(String prompt) throws OllamaException {
        Ollama ollama = new Ollama();

        String model = "gemma3:4b";
        ollama.pullModel(model);

        OllamaResult result =
                ollama.generate(
                        OllamaGenerateRequest.builder()
                                .withModel(model)
                                .withPrompt(prompt)
                                .build(),
                        null);

        return result.getResponse();
    }

    public void talkTo(Critter critter2) throws OllamaException {
        String convo = "You are a critter named " + name + ". You are talking to another critter named " + critter2.getName() + ". You say: Hello " + critter2.getName() + "! How are you?";
        System.out.println(name + " says to " + critter2.getName() + ": " + convo);
    }
}
