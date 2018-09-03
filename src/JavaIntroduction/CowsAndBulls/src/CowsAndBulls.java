import exposed.source.geekycamp5.cowsnbulls.challengers.HonestChallenger;
import exposed.source.geekycamp5.cowsnbulls.contract.Challenger;
import exposed.source.geekycamp5.cowsnbulls.contract.GameRules;
import exposed.source.geekycamp5.cowsnbulls.contract.Player;
import exposed.source.geekycamp5.cowsnbulls.players.ConsolePlayer;
import exposed.source.geekycamp5.cowsnbulls.players.StupidPlayer;
import exposed.source.geekycamp5.cowsnbulls.simulation.GameSimulator;
import exposed.source.geekycamp5.cowsnbulls.simulation.GameSummary;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class CowsAndBulls {
    private static final Scanner IN = new Scanner(System.in);
    private static final GameRules rules=new GameRules(
        "0123456789",
        4,
        Integer.MAX_VALUE
    );

    private static final Map<String, Player> PLAYER_IMPLEMENTATIONS = new HashMap<>();

    static {
        PLAYER_IMPLEMENTATIONS.put("computer", new StupidPlayer(rules));
        PLAYER_IMPLEMENTATIONS.put("console", new ConsolePlayer(rules));
    }

    public static void main(String[] args) throws Exception{
        System.out.printf("Cows and bulls\n");
        System.out.printf("Alphabet: %s\n", rules.alphabet);
        System.out.printf("String Length: %s\n", Integer.toString(rules.textLength));
        Random random = new Random(123);

        for(;;){
            int number = random.nextInt(9000)+1000;
            String text = Integer.toString(number);
            Player player=selectPlayer();
            Challenger challenger = new HonestChallenger(text);
            GameSummary summary = GameSimulator.Instance.SimulateGame(rules, challenger, player);
            System.out.println(summary);
            System.out.println("\n\n__________________________________________________\n\n");
            System.in.read();
        }
    }

    private static Player selectPlayer(){
        System.out.printf(
            "Available players: [%s]\n",
            String.join(", ", PLAYER_IMPLEMENTATIONS.keySet())
        );
        System.out.print("Choose player implementation: ");

        String implementationName = null;
        while (implementationName == null || !PLAYER_IMPLEMENTATIONS.containsKey(implementationName)){
            implementationName = IN.nextLine();
            if(!PLAYER_IMPLEMENTATIONS.containsKey(implementationName)){
                System.out.printf(
                    "Player implementation '%s' does not exist." +
                    "Please try again: ",
                    implementationName
                );
            }
        }

        return PLAYER_IMPLEMENTATIONS.get(implementationName);
    }
}
