import java.util.*;

public class HowManyTimes {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Integer> counts=new HashMap<>();
        List<Integer> unique = new ArrayList<>();

        String str = scanner.nextLine();

        for(String s : str.trim().split("([ ])")){
            int x = Integer.parseInt(s);

            if(!counts.containsKey(Integer.valueOf(x))) {
                counts.put(Integer.valueOf(x), Integer.valueOf(0));
                unique.add(Integer.valueOf(x));
            }

            counts.put(
              Integer.valueOf(x),
              Integer.valueOf(counts.get(Integer.valueOf(x)).intValue() + 1)
            );
        }

        for(int i=0;i<unique.size();i++) {
            System.out.print(unique.get(i).toString() + " " + counts.get(Integer.valueOf(unique.get(i))));
            if(i+1 < unique.size())System.out.print(" ");
            else System.out.println();
        }
    }
}
