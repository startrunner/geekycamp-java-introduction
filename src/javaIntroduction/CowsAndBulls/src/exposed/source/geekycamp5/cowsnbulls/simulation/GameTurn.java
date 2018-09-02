package exposed.source.geekycamp5.cowsnbulls.simulation;

import exposed.source.geekycamp5.cowsnbulls.contract.ChallengerResponse;

public class GameTurn {
    public final int playerGuess;
    public final ChallengerResponse challengerResponse;

    public GameTurn(int playerGuess, ChallengerResponse challengerResponse) {
        this.playerGuess = playerGuess;
        this.challengerResponse = challengerResponse;
    }
}
