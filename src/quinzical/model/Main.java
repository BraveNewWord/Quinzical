package quinzical.model;

public class Main {
    public static void main(String[] args) throws Exception {
        //System.out.println("MIGHT");
        GameManager gm = new GameManager();
        gm.getCategories();
        Category randCat;
        for (int i = 0; i < 5; i++) {
            randCat = gm.getRandomCategory();
            System.out.println(randCat.getName());
            for (int j = 0; j < 5; j++) {
                Question randQuestion = randCat.getRandomQuestion();
                randQuestion.setPoints(j * 100 + 100);
                System.out.println("CLUE: " + randQuestion.getClue());
                System.out.println("PREFIX: " + randQuestion.getPrefix());
                System.out.println("ANSWERS: " + randQuestion.getAnswers());
                System.out.println("POINTS: " + randQuestion.getPoints());
            }
        }
    }
}