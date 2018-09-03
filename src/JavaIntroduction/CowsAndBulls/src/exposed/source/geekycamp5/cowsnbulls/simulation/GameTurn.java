package exposed.source.geekycamp5.cowsnbulls.simulation;

import exposed.source.geekycamp5.cowsnbulls.contract.ChallengerResponse;

public class GameTurn {
    public final String playerGuess;
    public final ChallengerResponse challengerResponse;

    public GameTurn(String playerGuess, ChallengerResponse challengerResponse) {
        this.playerGuess = playerGuess;
        this.challengerResponse = challengerResponse;
    }

    @Override
    public String toString() {
        return String.format("Player Guess: %s    Challenger Response: %s", playerGuess, challengerResponse.toString());
    }
}
