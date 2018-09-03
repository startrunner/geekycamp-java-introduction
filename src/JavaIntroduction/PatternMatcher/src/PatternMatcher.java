import java.util.Scanner;

public class PatternMatcher {
    private static String text, pattern;
    private static Boolean[][] matchCache;

    public static void main(String[] args) {
        String[] line = new Scanner(System.in).nextLine().split("([ ])");
        text = line[0];
        pattern = trim(line[1], '*');

        matchCache = new Boolean[text.length() + 1][pattern.length() + 1];

        boolean answer = false;
        for (int i = 0; i < text.length() && !answer; i++) {
            answer = answer || (match(i, 0));
        }

        System.out.println(answer);
    }

    static String trim(String str, char c) {
        int firstNonStar = 0, lastNonStar = str.length() - 1;
        while (firstNonStar < str.length() && str.charAt(firstNonStar) == '*') firstNonStar++;
        while (lastNonStar >= 0 && str.charAt(lastNonStar) == '*') lastNonStar--;

        if (firstNonStar >= str.length() || firstNonStar > lastNonStar) return "";
        return str.substring(firstNonStar, lastNonStar + 1);
    }

    public static boolean match(int textIndex, int patternIndex) {
        if (patternIndex >= pattern.length()) {
            return matchCache[textIndex][patternIndex] = true;
        }
        if (textIndex >= text.length()) {
            for (int i = patternIndex; i < pattern.length(); i++) {
                if (pattern.charAt(i) != '*') return matchCache[textIndex][patternIndex] = false;
            }
            return matchCache[textIndex][patternIndex] = true;
        }

        if (matchCache[textIndex][patternIndex] != null) {
            return matchCache[textIndex][patternIndex].booleanValue();
        }

        if (pattern.charAt(patternIndex) == '?') {
            return (
                matchCache[textIndex][patternIndex] =
                    match(textIndex + 1, patternIndex + 1)
            );
        } else if (pattern.charAt(patternIndex) == '*') {
            for (int i = textIndex; i < text.length(); i++) {
                if (match(i, patternIndex + 1)) {
                    return matchCache[textIndex][patternIndex] = true;
                }
            }
            return matchCache[textIndex][patternIndex] = false;
        } else {
            return (
                matchCache[textIndex][patternIndex]
                    =
                    pattern.charAt(patternIndex) == text.charAt(textIndex) &&
                        match(textIndex + 1, patternIndex + 1)
            );
        }
    }
}
