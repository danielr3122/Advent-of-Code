import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day3 {
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

    public static void main(String[] args) throws FileNotFoundException{
        File file = new File("input3.txt");
        Scanner s = new Scanner(file);
        String rucksack;
        char repeatChar;
        int prioritySum = 0;
        
        while(s.hasNextLine()){
            rucksack = s.nextLine();
            repeatChar = findChar(rucksack);
            System.out.println(repeatChar);
            int charValue = repeatChar;
            if(charValue >= 97){
                System.out.println(charValue - 96);
                prioritySum += charValue - 96;
            } else {
                System.out.println(charValue - 38);
                prioritySum += charValue - 38;
            }
        }
        System.out.println(prioritySum);
    }
}
