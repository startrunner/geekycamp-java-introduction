import java.util.*;

public class Champion {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Integer> counts=new HashMap<>();
        List<Integer> unique = new ArrayList<>();

        String str = scanner.nextLine();

        int maxCount = 0;
        int maxNum = -1;

        for(String s : str.split("([ ])")){
            int x = Integer.parseInt(s);

            if(!counts.containsKey(Integer.valueOf(x))) {
                counts.put(Integer.valueOf(x), Integer.valueOf(0));
                unique.add(Integer.valueOf(x));
            }

            int currentCount = counts.put(
                    Integer.valueOf(x),
                    Integer.valueOf(counts.get(Integer.valueOf(x)).intValue() + 1)
            ) + 1;

            if(currentCount > maxCount){
                maxCount = currentCount;
                maxNum = x;
            }
            else if(currentCount == maxCount){
                if(x<maxNum)maxNum = x;
            }
        }


        System.out.printf("%d %d\n", maxNum, maxCount);
    }
}
