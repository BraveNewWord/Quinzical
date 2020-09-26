package quinzical.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Category {
    private String name;
    private List<Question> questions;

    public Category(String name, List questions) {
        this.name = name;
        this.questions = questions;
    }
    public String getName() {
        return name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public Question getRandomQuestion() {
        Random rand = new Random();
        Question randQuestion = this.questions.get(rand.nextInt(this.questions.size()));
        return randQuestion;
        //System.out.println(randCat.getName());
    }

    public boolean hasQuestions() {
        for (Question question : questions) {
            if (question.isAnswered() == false) {
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
