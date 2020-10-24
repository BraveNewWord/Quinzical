package main.java.quinzical.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HighScores implements Serializable {
    private List<Score> highScores = new ArrayList<>();

    public HighScores() {
        try {
            this.loadScores();
        } catch (IOException | ClassNotFoundException ex) {
            // File was not found
        }
    }

    public void addScore(Score score) throws Exception{
        this.highScores.add(score);
        this.saveScores();
    }

    public List<Score> getScores() {
        return this.highScores;
    }

    public void printScores() {
        for (Score score : this.highScores) {
            score.printScore();
        }
    }

    public void saveScores() throws Exception {
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("high-scores"));
        os.writeObject(this);
        os.close();
    }

    public void loadScores() throws IOException, ClassNotFoundException{
        ObjectInputStream is = new ObjectInputStream(new FileInputStream("high-scores"));
        HighScores highScores = (HighScores) is.readObject();
        this.highScores = highScores.getScores();
    }


}
