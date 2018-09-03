package exposed.source.geekycamp5.cowsnbulls.players;

import exposed.source.geekycamp5.cowsnbulls.contract.ChallengerResponse;
import exposed.source.geekycamp5.cowsnbulls.contract.GameRules;
import exposed.source.geekycamp5.cowsnbulls.contract.Player;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ConsolePlayer implements Player {
    private static final Scanner IN = new Scanner(System.in);
    private final Set<String> previousGuesses=new HashSet<>();
    private final GameRules rules;

    public ConsolePlayer(GameRules rules){
        this.rules = rules;
    }

    @Override
    public String makeGuess() {
        final int guessNumber = previousGuesses.size()+1;
        String guess = null;

        if(guessNumber == 1){
            System.out.printf("Good luck!\n\n");
        }

        System.out.printf("Guess #%s\n", Integer.toString(guessNumber));
        System.out.print("Make your guess: ");
        while (guess == null || previousGuesses.contains(guess)){
            guess = IN.nextLine();
            if(previousGuesses.contains(guess)){
                System.out.println("You've already guessed that string!");
                System.out.print("Please try again: ");
            }
        }

        previousGuesses.add(guess);
        return guess;
    }

    @Override
    public void receiveChallengerResponse(ChallengerResponse response) {
        System.out.printf("Challenger response: %s\n", response.toString());
        if(response.cows == rules.textLength){
            System.out.println("Coungratulations! You've won!");
        }
    }
}
