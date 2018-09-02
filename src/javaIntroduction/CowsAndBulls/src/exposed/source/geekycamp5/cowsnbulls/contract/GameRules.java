package exposed.source.geekycamp5.cowsnbulls.contract;

public class GameRules {
    public final int digitCount;
    public final int maxRetryCount;

    public GameRules(int digitCount, int maxRetryCount) {
        this.digitCount = digitCount;
        this.maxRetryCount = maxRetryCount;
    }
}
