import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class day1{
    // private static int findMax(ArrayList<Integer> arr){
    //     int max = arr.get(0);
    //     for(int i = 0; i < arr.size() - 1; i++){
    //         if(arr.get(i) < arr.get(i+1)){
    //             max = arr.get(i+1);
    //         }
    //     }
    //     return max;
    // }

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