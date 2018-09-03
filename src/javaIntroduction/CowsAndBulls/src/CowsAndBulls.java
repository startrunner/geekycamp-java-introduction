import exposed.source.geekycamp5.cowsnbulls.challengers.HonestChallenger;
import exposed.source.geekycamp5.cowsnbulls.contract.Challenger;
import exposed.source.geekycamp5.cowsnbulls.contract.GameRules;
import exposed.source.geekycamp5.cowsnbulls.contract.Player;
import exposed.source.geekycamp5.cowsnbulls.players.StupidPlayer;
import exposed.source.geekycamp5.cowsnbulls.simulation.GameSimulator;
import exposed.source.geekycamp5.cowsnbulls.simulation.GameSummary;

import java.util.Random;

public class CowsAndBulls {
    public static void main(String[] args) throws Exception{
        GameRules rules=new GameRules("0123456789", 4, Integer.MAX_VALUE);
        Random random = new Random(123);

        for(;;){
            int number = random.nextInt(9000)+1000;
            String text = Integer.toString(number);
            Player player=new StupidPlayer(rules);
            Challenger challenger = new HonestChallenger(text);
            GameSummary summary = GameSimulator.Instance.SimulateGame(rules, challenger, player);
            System.out.println(summary);
            System.out.println("\n\n__________________________________________________\n\n");
            System.in.read();
        }
    }
}
