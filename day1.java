import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class day1{

    public static void main(String[] args) throws FileNotFoundException{
        File file = new File("input1.txt");
        Scanner s = new Scanner(file);
        int calories = 0;
        int maxCalories = 0;
        int secondLargest = 0;
        int thirdLargest = 0;
        String value;
        ArrayList<Integer> topElves = new ArrayList<Integer>();
        
        while(s.hasNextLine()){
            value = s.nextLine();
            if(value != ""){
                calories += Integer.parseInt(value);
            } else {
                topElves.add(calories);
                if(calories > maxCalories){
                    thirdLargest = secondLargest;
                    secondLargest = maxCalories;
                    maxCalories = calories;
                } else if(calories > secondLargest){
                    thirdLargest = secondLargest;
                    secondLargest = calories;
                } else if(calories > thirdLargest){
                    thirdLargest = calories;
                }
                calories = 0;
            }
        }

        int sum = maxCalories + secondLargest + thirdLargest;

        System.out.println(sum);

        s.close();
    }
}