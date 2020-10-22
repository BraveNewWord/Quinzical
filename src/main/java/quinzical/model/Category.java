package main.java.quinzical.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Category class represent a category of questions e.g. Geography
 * Category class have chosenQuestions which are a selection of questions from the overall category
 * but are currently used in the current session of the game
 */
public class Category implements Serializable {
    private String name;
    private List<Question> questions = new ArrayList<>();
    private List<Question> chosenQuestions = new ArrayList<>();

    public Category(String name, List<Question> questions) {
        this.name = name;
        this.questions = questions;
    }
    public String getName() {
        return name;
    }

    public List<Question> getQuestions() {
        return this.questions;
    }

    /**
     * Gets a random unchosen question from all of the category's questions
     * Used to populate the chosenQuestions list
     * @return randQuestion
     */
    public Question getRandomQuestion() {
        Random rand = new Random();
        Question randQuestion = this.questions.get(rand.nextInt(this.questions.size()));
        while (chosenQuestions.contains(randQuestion)) {
            randQuestion = this.questions.get(rand.nextInt(this.questions.size()));
        }
        chosenQuestions.add(randQuestion);
        return randQuestion;
    }

    public void clearChosenQuestions() {
        chosenQuestions.clear();
    }

    public List<Question> getChosenQuestions() {
        return this.chosenQuestions;
    }

    /**
     * Checks if this Category has questions
     * A Category has questions if it still has questions remaning in chosenQuestions that are unanswered
     * @return
     */
    public boolean hasQuestions() {
        for (Question question : this.chosenQuestions) {
            if (!question.isAnswered()) {
                return true;
            }
        }
        return false;
    }

    public void resetCategory() {
        for (Question question : questions) {
            question.setAnswered(false);
        }
    }


}
