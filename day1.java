import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class day1{
    public static void main(String[] args) throws FileNotFoundException{
        File file = new File("input1.txt");
        Scanner s = new Scanner(file);
        int calories = 0;
        int maxCalories = 0;
        String value;
        while(s.hasNextLine()){
            value = s.nextLine();
            if(value != ""){
                calories += Integer.parseInt(value);
            } else {
                if(calories > maxCalories){
                    maxCalories = calories;
                }
                calories = 0;
            }
        }
        System.out.print(maxCalories);
        s.close();
    }
}