package quinzical;


import quinzical.model.GameManager;

public class NZInternationalPageController {
    private GameManager game;
    private StringSpeaker stringSpeaker;

    public void initData(GameManager game, StringSpeaker stringSpeaker) throws Exception {
        this.game = game;
        this.stringSpeaker = stringSpeaker;
    }
}

