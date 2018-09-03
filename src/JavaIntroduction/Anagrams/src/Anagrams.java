import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Anagrams {
    public  static  void main(String[] args){
        String[] line = new Scanner(System.in).nextLine().split("([ ])");
        if(areAnagrams(line[0], line[1]))System.out.println("true");
        else System.out.println("false");
    }

    static  boolean areAnagrams(String x, String y){
        List<Character> charsX=getChars(x), charsY=getChars(y);

        if(charsX.size()!=charsY.size())return  false;

        for(int i=0, len = charsX.size(); i<len ; i++){
            if(charsX.get(i).charValue()!=charsY.get(i).charValue())return  false;
        }


        return  true;
    }

   static List<Character> getChars(String str){
        str = str.toLowerCase();
        List<Character> chars=new ArrayList<>();


        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            if(Character.isAlphabetic(c))chars.add(Character.valueOf(c));
        }

       chars.sort(new Comparator<Character>() {
           @Override
           public int compare(Character o1, Character o2) {
               return Character.compare(o1.charValue(), o2.charValue());
           }
       });

        return  chars;
    }
}
