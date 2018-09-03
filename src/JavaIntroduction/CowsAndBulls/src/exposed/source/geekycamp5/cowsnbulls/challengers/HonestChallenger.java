package exposed.source.geekycamp5.cowsnbulls.challengers;

import exposed.source.geekycamp5.cowsnbulls.contract.Challenger;
import exposed.source.geekycamp5.cowsnbulls.contract.ChallengerResponse;

public class HonestChallenger implements Challenger {
    private final String text;

    public HonestChallenger(String text) {
        this.text = text;
    }

    @Override
    public ChallengerResponse gradeGuess(String guess) {

        int cows = 0, bulls = 0;
        for (int i = 0; i < guess.length(); i++) {
            char c = guess.charAt(i);
            int j = text.indexOf(c);
            if (j != -1) {
                if (i == j) bulls++;
                else cows++;
            }
        }

        return new ChallengerResponse(cows, bulls);
    }
}
