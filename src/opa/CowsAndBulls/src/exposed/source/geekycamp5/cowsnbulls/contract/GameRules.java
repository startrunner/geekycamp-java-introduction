package exposed.source.geekycamp5.cowsnbulls.contract;

public class GameRules {
    public final String alphabet;
    public final int textLength;
    public final int maxRetryCount;

    public GameRules(String alphabet, int textLength, int maxRetryCount) {
        this.alphabet = alphabet;
        this.textLength = textLength;
        this.maxRetryCount = maxRetryCount;
    }
}
