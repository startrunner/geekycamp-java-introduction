import java.util.Scanner;

public class PatternMatcher {
    static String text, pattern;

    static Boolean[][] memz;

    public  static  void main(String[] args){
        String[] line = new Scanner(System.in).nextLine().split("([ ])");
        text = line[0];
        pattern = trim(line[1], '*');

        memz=new Boolean[text.length()+1][pattern.length()+1];

        for(int i=0;i<text.length();i++){
            if(match(i, 0)){
                System.out.println("true");
                return;
            }
        }

        System.out.println("false");
    }

    static String trim(String str, char c){
        int firstNonStar = 0, lastNonStar = str.length()-1;
        while (firstNonStar<str.length() && str.charAt(firstNonStar)=='*')firstNonStar++;
        while (lastNonStar>=0 && str.charAt(lastNonStar)=='*')lastNonStar--;

        if(firstNonStar>=str.length() || firstNonStar>lastNonStar)return  "";
        return str.substring(firstNonStar, lastNonStar + 1);
    }

    public  static  boolean match(int textIndex, int patternIndex){
        if(patternIndex>=pattern.length())return  true;
        if(textIndex >= text.length()){
            for(int i=patternIndex;i<pattern.length();i++) {
                if (pattern.charAt(i) != '*') return memz[textIndex][patternIndex] = false;
            }
            return memz[textIndex][patternIndex]=true;
        }

        if(memz[textIndex][patternIndex]!=null)return  memz[textIndex][patternIndex].booleanValue();

        if(pattern.charAt(patternIndex) == '?'){
            return memz[textIndex][patternIndex] =   match(textIndex + 1, patternIndex + 1);
        }
        else if(pattern.charAt(patternIndex) == '*'){
            for(int i=textIndex; i<text.length();i++){
                if(match(i, patternIndex + 1))return memz[textIndex][patternIndex] =  true;
            }
            return memz[textIndex][patternIndex] =  false;
        }
        else{
            return memz[textIndex][patternIndex] =
                    pattern.charAt(patternIndex) == text.charAt(textIndex)
                            &&
                    match(textIndex+1, patternIndex + 1);
        }
    }
}
