import java.util.Scanner;

public class Plato {
    public  static  void main(String[] args){
        String line = new Scanner(System.in).nextLine();

        int maxLen=1, currentLen = 0;
        char previousCharacter = 'a';

        for(int i=0;i<line.length();i++){
            char c = (line.charAt(i));
            if(i==0|| c !=previousCharacter)currentLen = 0;
            currentLen ++;
            maxLen = Math.max(maxLen, currentLen);
            previousCharacter = c;
        }

        System.out.println(maxLen);
    }
}
