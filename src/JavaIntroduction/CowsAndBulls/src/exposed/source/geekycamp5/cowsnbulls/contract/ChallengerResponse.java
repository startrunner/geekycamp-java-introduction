package exposed.source.geekycamp5.cowsnbulls.contract;

public class ChallengerResponse {
    public final int cows;
    public final int bulls;
    public ChallengerResponse(int incorrectlyPlaced, int correctlyPlaced) {
        this.cows = incorrectlyPlaced;
        this.bulls = correctlyPlaced;
    }

    public @Override
    String toString() {
        return String.format("(cows: %d   bulls: %d)", cows, bulls);
    }
}
