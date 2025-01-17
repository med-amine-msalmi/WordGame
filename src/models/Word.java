package models;

public class Word {
    private String Name;
    private Difficulty difficulty;

    public Word(String Name, Difficulty difficulty) {
        this.Name = Name;
        this.difficulty = difficulty;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String toString() {
        return "Name: " + Name + " Difficulty: " + difficulty.toString();
    }

    public static enum Difficulty {
        Easy, Medium, Hard
    }
}
