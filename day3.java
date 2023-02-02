import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day3 {
    // Used in part 1
    private static char findChar(String rucksack){
        String comp1 = rucksack.substring(0, rucksack.length() / 2);
        String comp2 = rucksack.substring(rucksack.length() / 2);
        for(int i = 0; i < comp1.length(); i++){
            for(int j = 0; j < comp2.length(); j++){
                if(comp1.charAt(i) == comp2.charAt(j)){
                    return comp1.charAt(i);
                }
            }
        }
        return ' ';
    }

    // Used in part 2
    private static char findGroupChar(String rucksack, String rucksack2, String rucksack3){
        for(int i = 0; i < rucksack.length(); i++){
            for(int j = 0; j < rucksack2.length(); j++){
                for(int k = 0; k < rucksack3.length(); k++){
                    if(rucksack.charAt(i) == rucksack2.charAt(j) && rucksack2.charAt(j) == rucksack3.charAt(k)){
                        return rucksack.charAt(i);
                    }
                }
            }
        }
        return ' ';
    }

    public static void main(String[] args) throws FileNotFoundException{
        File file = new File("input3.txt");
        Scanner s = new Scanner(file);
        String rucksack;
        String rucksack2;
        String rucksack3;
        char repeatChar;
        int prioritySum = 0;

        while(s.hasNextLine()){
            rucksack = s.nextLine();
            rucksack2 = s.nextLine();
            rucksack3 = s.nextLine();
            repeatChar = findGroupChar(rucksack, rucksack2, rucksack3);
            int charValue = repeatChar;
            if(charValue >= 97){
                prioritySum += charValue - 96;
            } else {
                prioritySum += charValue - 38;
            }
        }
        System.out.println(prioritySum);
    }
}
