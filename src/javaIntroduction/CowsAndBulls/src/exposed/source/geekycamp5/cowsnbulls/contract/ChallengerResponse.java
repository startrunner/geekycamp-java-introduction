package exposed.source.geekycamp5.cowsnbulls.contract;

public class ChallengerResponse {
    public ChallengerResponse(int incorrectlyPlaced, int correctlyPlaced){
        this.cows = incorrectlyPlaced;
        this.bulls = correctlyPlaced;
    }

    public final int cows;
    public final  int bulls;
}
