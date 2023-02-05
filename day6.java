import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day6 {

    private static boolean checkCharacters(String input){
        ArrayList<String> arr = new ArrayList<>();
        for(int i = 0; i < input.length(); i++){
            if(!arr.contains(input.charAt(i) + "")){
                arr.add(input.charAt(i) + "");
            } else {
                return false;
            }
        }
        if(arr.size() == input.length()) return true;
        return false;
    }

    private static void findMarker(String input, int uniqueCount){
        boolean result = false;
        for(int i = 0; i < input.length(); i++){
            result = checkCharacters(input.substring(i, i + uniqueCount));
            if(result) {
                System.out.println("First marker after " + (i + uniqueCount));
                break;
            }
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException{
        File file = new File("input6.txt");
        Scanner s = new Scanner(file);
        String input = s.next();

        int partOneRequirement = 4;
        int partTwoRequirement = 14;

        findMarker(input, partOneRequirement);
        findMarker(input, partTwoRequirement);

        s.close();
    }

}
