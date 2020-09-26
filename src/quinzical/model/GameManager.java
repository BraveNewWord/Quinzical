package quinzical.model;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameManager {
    private List<Category> categories = new ArrayList<Category>();
    private Integer points = 0;
    private Question currentQuestion;

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
            return true;
        }
        return false;
    }

    public List<Category> categories() {
        return this.categories;
    }

    public Category getRandomCategory() {
        Random rand = new Random();
        Category randCat = this.categories.get(rand.nextInt(this.categories.size()));
        return randCat;
        //System.out.println(randCat.getName());
    }

    public boolean questionsExist() {
        for (Category category : categories){
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
        String saveString = buildSaveString();
        File gameData = new File("game-data");
        gameData.createNewFile();
        FileWriter myWriter = new FileWriter("game-data");
        myWriter.write(saveString);
        myWriter.close();
    }

    public void loadGame() throws Exception {
        File gameData = new File("game-data");
        if (gameData.exists()) {
            Scanner myReader = new Scanner(gameData);
            String data[] = myReader.nextLine().split(",");
            this.points = Integer.parseInt(data[0]);
            for (String savedQuestion : data) {
                for (Category category : categories) {
                    if (savedQuestion.startsWith(category.getName())) {
                        for (Question question : category.getQuestions()) {
                            if (savedQuestion.endsWith(question.getPoints().toString())) {
                                question.setAnswered(true);
                            }
                        }
                    }
                }
            }
        }
    }

}
