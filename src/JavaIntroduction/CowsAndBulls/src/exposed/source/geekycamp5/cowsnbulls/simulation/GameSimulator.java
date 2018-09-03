package exposed.source.geekycamp5.cowsnbulls.simulation;

import exposed.source.geekycamp5.cowsnbulls.contract.Challenger;
import exposed.source.geekycamp5.cowsnbulls.contract.ChallengerResponse;
import exposed.source.geekycamp5.cowsnbulls.contract.GameRules;
import exposed.source.geekycamp5.cowsnbulls.contract.Player;

import java.util.ArrayList;
import java.util.List;

public class GameSimulator {
    public static final GameSimulator Instance = new GameSimulator();

    public GameSummary SimulateGame(GameRules rules, Challenger challenger, Player player) {
        List<GameTurn> turns = new ArrayList<>();

        for (int turn = 1; turn <= rules.maxRetryCount; turn++) {
            String guess = player.makeGuess();
            ChallengerResponse response = challenger.gradeGuess(guess);
            player.receiveChallengerResponse(response);
            turns.add(new GameTurn(guess, response));

            boolean playerWins = response.bulls == rules.textLength;
            if (playerWins) {
                return new GameSummary(
                    rules,
                    GameResult.PLAYER_WINS,
                    turns
                );
            }
        }

        return new GameSummary(rules, GameResult.CHALLENGER_WINS, turns);
    }
}
