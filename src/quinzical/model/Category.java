package jeopardy;

import java.util.ArrayList;
import java.util.List;

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
