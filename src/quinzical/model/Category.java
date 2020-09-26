package quinzical.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Category {
    private String name;
    private List<Question> questions;
    private List<Question> chosenQuestions;

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
        while (chosenQuestions.contains(randQuestion)) {
            randQuestion = this.questions.get(rand.nextInt(this.questions.size()));
        }
        chosenQuestions.add(randQuestion);
        return randQuestion;
        //System.out.println(randCat.getName());
    }
    public void clearChosenQuestions() {
        chosenQuestions.clear();
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
