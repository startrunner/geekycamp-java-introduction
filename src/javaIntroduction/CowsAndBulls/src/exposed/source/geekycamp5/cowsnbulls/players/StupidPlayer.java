package exposed.source.geekycamp5.cowsnbulls.players;

import exposed.source.geekycamp5.cowsnbulls.contract.ChallengerResponse;
import exposed.source.geekycamp5.cowsnbulls.contract.GameRules;
import exposed.source.geekycamp5.cowsnbulls.contract.Player;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class StupidPlayer implements Player {
    private static final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());

    private final GameRules rules;
    private final Set<Character> excludedCharacters = new HashSet<>();
    private final Set<Character> notExcludedCharacters = new HashSet<>();
    private final Set<String> alreadyTried = new HashSet<>();
    private final Map<Integer, Character> knownPositions = new HashMap<>();
    private final Map<Integer, Character> excludedPositions = new HashMap<>();
    private boolean lastWasDense = false;

    private String lastGuess = null;
    
    public StupidPlayer(GameRules rules){
        this.rules = rules;
        for(int i=0;i<rules.alphabet.length();i++)notExcludedCharacters.add(rules.alphabet.charAt(i));
    }

    @Override
    public String makeGuess() {

        LOGGER.log(Level.INFO, String.format("Per cent excluded: %S", Double.toString( perCentExcludedCharacters())));

        if(perCentExcludedCharacters() < 70){
            lastGuess = guessFromNonExcluded();
            LOGGER.log(Level.INFO, String.format("Guessing from nonExcluded: %s", lastGuess));
            return lastGuess;
        }
        else if(perCentExcludedCharacters() < 80){
            lastGuess = guessFromNotExcludedDense();
            LOGGER.log(Level.INFO, String.format("Guessing from nonExcluded (dense): %s", lastGuess));
            return lastGuess;
        }
        else{
            lastWasDense=true;
            lastGuess = guessFinal();
            LOGGER.log(Level.INFO, String.format("Making final guess: %s", lastGuess));
            return lastGuess;
        }
    }

    @Override
    public void receiveChallengerResponse(ChallengerResponse response) {
        LOGGER.log(Level.INFO, String.format("Received challenger response: %s", response.toString()));
        if(response.bulls == rules.textLength){
            LOGGER.log(Level.INFO,"Yay, i winz :3");
            return;
        }
        if(response.cows == 0 && response.bulls==0){
            for (int i=0;i<lastGuess.length();i++){
                excludedCharacters.add(lastGuess.charAt(i));
                notExcludedCharacters.remove(lastGuess.charAt(i));
            }
        }

        if(lastWasDense && response.bulls == knownPositions.size()+1){
            for(int i=0;i<lastGuess.length();i++){
                char c = lastGuess.charAt(i);
                if(notExcludedCharacters.contains(c) && knownPositions.getOrDefault(c, null)==null){
                    knownPositions.put(i, c);
                }
            }
        }
    }

    String guessFromNonExcluded(){
        LOGGER.log(Level.INFO, "Guessing from non-excluded");
        List<Character> chars =  notExcludedCharacters.stream().collect(Collectors.toList());

        for(;;){
            StringBuilder builder = new StringBuilder();
            for(int i = 0; i<rules.textLength; i++)builder.append(chars.get(i));
            String guess = builder.toString();
            if(!alreadyTried.contains(guess)){
                alreadyTried.add(guess);
                return guess;
            }
            Collections.shuffle(chars);
        }
    }

    String guessFromNotExcludedDense(){
        List<Character> chars =  notExcludedCharacters.stream().collect(Collectors.toList());
        LOGGER.log(Level.INFO, String.format(
            "Guessing from non-excluded (dense) with %d non-excluded chars", chars.size())
        );

        for(;;){
            StringBuilder builder = new StringBuilder();
            for(int i=0;i<rules.textLength/2;i++)builder.append(chars.get(i));
            while (builder.length() < rules.textLength){
                builder.append(builder.charAt(ThreadLocalRandom.current().nextInt(builder.length())));
            }
            String guess = builder.toString();
            LOGGER.log(Level.INFO, String.format("Current guess: %s", guess));
            if(!alreadyTried.contains(guess)){
                alreadyTried.add(guess);
                return guess;
            }
            Collections.shuffle(chars);
        }
    }

    String guessFinal(){
        LOGGER.log(Level.INFO, "Making final guess");
        int firstUnknownPosition = 0;
        while (knownPositions.containsKey(firstUnknownPosition))firstUnknownPosition++;

        int position = -1;
        char character = '_';

        for(int i = 0; i<rules.textLength; i++)
        {
            for(Character c : notExcludedCharacters){
                if(!Objects.equals(excludedPositions.getOrDefault(i, null), c)){
                    position=i;
                    character=c;
                    break;
                }
            }
            if(i!=-1)break;;
        }

        StringBuilder builder=new StringBuilder();

        for(int i = 0; i<rules.textLength; i++){
            if(i==position){builder.append(character);}
            else {
                if(knownPositions.containsKey(i))builder.append(knownPositions.get(i));
                else builder.append(excludedCharacters.iterator().next());
            }
        }

        return builder.toString();
    }

    double perCentExcludedCharacters(){
        return (double)excludedCharacters.size()/rules.alphabet.length()*100;
    }
}
