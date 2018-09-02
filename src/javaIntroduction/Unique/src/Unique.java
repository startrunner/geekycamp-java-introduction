import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Unique {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        Set<Character> chars=new HashSet<>();
        for(int i=0;i<line.length();i++)chars.add(line.charAt(i));

        System.out.println(line.length() == chars.size());
    }
}
