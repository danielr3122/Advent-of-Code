import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day4 {

    private static int[] findValues(String section){
        int start1, start2, end1, end2;
        String s1, s2, e1, e2;
        s1 = s2 = e1 = e2 = "";
        int currValue = 0;
        for(int i = 0; i < section.length(); i++){
            if(section.charAt(i) != '-' && section.charAt(i) != ',' && section.charAt(i) != '\n' && currValue == 0){
                s1 += section.charAt(i);
            } else if(section.charAt(i) != '-' && section.charAt(i) != ',' && section.charAt(i) != '\n' && currValue == 1) {
                e1 += section.charAt(i);
            } else if(section.charAt(i) != '-' && section.charAt(i) != ',' && section.charAt(i) != '\n' && currValue == 2) {
                s2 += section.charAt(i);
            } else if(section.charAt(i) != '-' && section.charAt(i) != ',' && section.charAt(i) != '\n' && currValue == 3) {
                e2 += section.charAt(i);
            } else {
                currValue++;
            }
        }
        start1 = Integer.parseInt(s1);
        end1 = Integer.parseInt(e1);
        start2 = Integer.parseInt(s2);
        end2 = Integer.parseInt(e2);
        int arr[] = {start1, end1, start2, end2};
        return arr;
    }

    private static int findEngulfed(int start1, int end1, int start2, int end2){
        if((start1 >= start2 && end1 <= end2) || (start2 >= start1 && end2 <= end1)){
            return 1;
        }
        return 0;
    }

    private static int findOverlapped(int start1, int end1, int start2, int end2){
        if(start1 == end2 || start2 == end1 || start1 == start2 || end1 == end2 ||
        ((start1 > start2 || end1 > end2) && start1 < end2) ||
        ((end2 > end1 || start2 > start1) && start2 < end1)){
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) throws FileNotFoundException{
        File file = new File("input4.txt");
        Scanner s = new Scanner(file);
        String section;
        int engulfedCount = 0;
        int overlappedCount = 0;
        
        while(s.hasNextLine()){
            section = s.nextLine();
            int[] arr = findValues(section);
            overlappedCount += findOverlapped(arr[0], arr[1], arr[2], arr[3]);
        }
        System.out.println(overlappedCount);
    }
}
