package exposed.source.geekycamp5.cowsnbulls.simulation;

import exposed.source.geekycamp5.cowsnbulls.contract.GameRules;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class GameSummary implements Iterable<GameTurn> {
    public final GameRules rules;
    public final GameResult result;
    public final List<GameTurn> turns;

    public GameSummary(GameRules rules, GameResult result, List<GameTurn> turns) {
        this.rules = rules;
        this.result = result;
        this.turns = Collections.unmodifiableList(turns);
    }

    @Override
    public Iterator<GameTurn> iterator() { return turns.iterator(); }
}
