package quinzical.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * GameManager is class that acts to manage parts of the Games Module
 * Responsibilities include: keeping score, keeping track of questions answered
 * GameManager objects are used to pass game data between scenes when switching scenes
 */
public class GameManager implements Serializable {
    private List<Category> categories = new ArrayList<>();
    private List<Category> chosenCategories = new ArrayList<>();
    private Integer points = 0;
    private Question currentQuestion;
    private boolean gameStarted = false;

    private boolean internationalUnlocked = false;
    private boolean twoCategoriesComplete = false;

    public GameManager() throws Exception {
        try {
            ObjectInputStream is  = new ObjectInputStream(new FileInputStream("game-data"));
            GameManager game = (GameManager) is.readObject();
            this.categories = game.categories;
            this.chosenCategories = game.chosenCategories;
            this.points = game.points;
            this.gameStarted = game.gameStarted;
            this.twoCategoriesComplete = game.twoCategoriesComplete;
            is.close();
        } catch (IOException | ClassNotFoundException e) {
            //e.printStackTrace();
        }

    }

    public boolean gameStarted() {
        return this.gameStarted;
    }
    public void setStarted(boolean value) {
        this.gameStarted = value;
    }

    /**
     * This method detects for a categories folder and reads the category text files within them
     * These categories and questions are transcribed to Category and Question objects
     * @throws Exception
     */
    public void getCategories() throws Exception{
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
        }
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

    public void addChosenCategory(Category category) {
        this.chosenCategories.add(category);
    }
    public void removeChosenCategory(Category category) {
        this.chosenCategories.remove(category);
    }

    public void countCategoriesComplete() {
        if (!this.twoCategoriesComplete) {
            int nCategoriesComplete = 0;
            for (Category category : this.chosenCategories){
                if (!category.hasQuestions()) {
                    nCategoriesComplete++;
                }
            }
            this.twoCategoriesComplete = nCategoriesComplete >= 2;
        }
    }
    public boolean getTwoCategoriesComplete() {
        return this.twoCategoriesComplete;
    }

    public boolean getInternationalUnlocked() {
        return this.internationalUnlocked;
    }
    public void setInternationalUnlocked(boolean value) {
        this.internationalUnlocked = value;
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
        for (Category category : this.chosenCategories) {
            category.resetCategory();
            category.clearChosenQuestions();
        }
        this.clearChosenCategories();
        this.gameStarted = false;
    }
    public void saveGame() throws Exception {
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("game-data"));
        os.writeObject(this);
        os.close();
    }

}
