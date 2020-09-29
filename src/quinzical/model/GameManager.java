package quinzical.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameManager implements Serializable {
    private List<Category> categories = new ArrayList<>();
    private List<Category> chosenCategories = new ArrayList<>();
    private Integer points = 0;
    private Question currentQuestion;
    private boolean gameStarted = false;

    public GameManager() throws Exception {

        try {
            ObjectInputStream is  = new ObjectInputStream(new FileInputStream("game-data"));
            GameManager game = (GameManager) is.readObject();
            this.categories = game.categories;
            this.chosenCategories = game.chosenCategories;
            this.points = game.points;
            this.gameStarted = game.gameStarted;
            is.close();
        } catch (IOException | ClassNotFoundException e) {
            //e.printStackTrace();
        }

    }

    public boolean getCategories() throws Exception{
        File categoryFolder = new File("categories");
        if (categoryFolder.exists() && categoryFolder.list().length > 0) {
            // thru all files in categories
            for (String file : categoryFolder.list()) {
                Scanner scanner = new Scanner(new File("categories/" + file));
                List questions = new ArrayList<Question>();
                // thru all lines in category file
                while (scanner.hasNextLine()) {
                    String[] data = scanner.nextLine().split("\\|");
                    questions.add(new Question(data[0], data[1], data[2]));


                }
                this.categories.add(new Category(file, questions));
                scanner.close();
            }
            this.gameStarted = true;

            return true;
        }
        return false;
    }

    public List<Category> categories() {
        return this.categories;
    }

    public Category getRandomCategory() throws Exception {
        Random rand = new Random();
        Category randCat = this.categories.get(rand.nextInt(this.categories.size()));
        while (chosenCategories.contains(randCat)) {
            randCat = this.categories.get(rand.nextInt(this.categories.size()));
        }
        chosenCategories.add(randCat);

        return randCat;
    }

    public void clearChosenCategories() {
        chosenCategories.clear();
    }

    public List<Category> getChosenCategories() {
        return this.chosenCategories;
    }

    public boolean questionsExist() {
        for (Category category : this.chosenCategories){
            if (category.hasQuestions()) {
                return true;
            }
        }
        return false;
    }

    public void setCurrentQuestion(Question question) {
        this.currentQuestion = question;
    }
    public Question getCurrentQuestion() {
        return this.currentQuestion;
    }

    public void changePoints(Integer points, boolean result) {
        if (result == true) {
            this.points = this.points + points;
        } else {
            this.points = this.points - points;
        }
    }
    public String dispPoints() {
        if (this.points >= 0) {
            return "$"+this.points;
        } else {
            return "-$"+-this.points;
        }
    }

    public void resetGame() {
        this.points = 0;
        for (Category category : categories) {
            category.resetCategory();
        }
    }

    public boolean gameStarted() {
        return this.gameStarted;
    }

    public String buildSaveString() {
        StringBuilder saveString = new StringBuilder("" + this.points);
        for (Category category : categories) {
            for (Question question : category.getQuestions()) {
                if (question.isAnswered() == true) {
                    saveString.append(",").append(category.getName()).append(question.getPoints());
                }
            }
        }
        return saveString.toString();
    }
    public void saveGame() throws Exception {
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("game-data"));
        os.writeObject(this);
        os.close();
    }



}
