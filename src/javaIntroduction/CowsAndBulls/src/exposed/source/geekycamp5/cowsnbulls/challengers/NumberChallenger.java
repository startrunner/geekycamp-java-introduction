package exposed.source.geekycamp5.cowsnbulls.challengers;

import exposed.source.geekycamp5.cowsnbulls.contract.Challenger;
import exposed.source.geekycamp5.cowsnbulls.contract.ChallengerResponse;

public class NumberChallenger  implements Challenger {
    private final int number;
    private final String numberText;

    public NumberChallenger(int number){
        this.number = number;
        this.numberText = Integer.toString(number);
    }

    @Override
    public ChallengerResponse gradeGuess(int guess) {
        String guessText = Integer.toString(guess);

        int cows=0, bulls=0;
        for(int i=0;i<guessText.length();i++){
            char c = guessText.charAt(i);
            int j = numberText.indexOf(c);
            if(j!=-1){
                if(i==j)bulls++;
                else cows++;
            }
        }

        return new ChallengerResponse(cows, bulls);
    }
}
