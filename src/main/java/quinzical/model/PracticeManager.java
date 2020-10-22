package main.java.quinzical.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PracticeManager {
	private List<Category> categories = new ArrayList<Category>();
	private List<Question> questions = new ArrayList<Question>();
	private Category category;
    private Question question;

    public void setCategories(){
        File categoryFolder = new File("categories");
        if (categoryFolder.exists() && categoryFolder.list().length > 0) {
            // thru all files in categories
            for (String file : categoryFolder.list()) {
                Scanner scanner = null;
                try {
                    scanner = new Scanner(new File("categories/" + file));
                    List questions = new ArrayList<Question>();
                    // thru all lines in category file
                    while (scanner.hasNextLine()) {
                        String[] data = scanner.nextLine().split("\\|");
                        questions.add(new Question(data[0], data[1], data[2]));
                    }
                    this.categories.add(new Category(file, questions));
                    scanner.close();
                } catch (FileNotFoundException e) {
                    //e.printStackTrace();
                }
            }
        }
    }

    public List<Category> getCategories() {
        return this.categories;
    }
    
    public void chooseCategory(Category category) {
    	this.category=category;
    }
    
    public void setQuestions() {
    	questions=category.getQuestions();
    }
    
    public void chooseQuestion() {
    	int num=questions.size();
    	Random random = new Random();
    	int rand =random.nextInt(num);
    	question=questions.get(rand);
    }
    
    public Question getQuestion() {
    	return question;
    }
}
