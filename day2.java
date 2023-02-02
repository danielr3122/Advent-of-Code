import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class day2 {
    private static int score(String round){
        char you = round.charAt(0);
        char me  = round.charAt(2);
        int tie = 3;
        int win = 6;
        int rock = 1;
        int paper = 2;
        int scissors = 3;

        if(me == 'X'){
            if(you == 'A'){
                return tie + rock;
            } else if(you == 'C'){
                return win + rock;
            } else {
                return rock;
            }
        } else if(me == 'Y'){
            if(you == 'B'){
                return tie + paper;
            } else if(you == 'A'){
                return win + paper;
            } else {
                return paper;
            }
        } else {
            if(you == 'C'){
                return tie + scissors;
            } else if(you == 'B'){
                return win + scissors;
            } else {
                return scissors;
            }
        }
    }

    private static char choice(String round){
        char you = round.charAt(0);
        char me  = round.charAt(2);

        if(me == 'X'){
            if(you == 'A'){
                return 'Z';
            } else if(you == 'B'){
                return 'X';
            } else {
                return 'Y';
            }
        } else if(me == 'Y'){
            if(you == 'A'){
                return 'X';
            } else if(you == 'B'){
                return 'Y';
            } else {
                return 'Z';
            }
        } else {
            if(you == 'A'){
                return 'Y';
            } else if(you == 'B'){
                return 'Z';
            } else {
                return 'X';
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException{
        File file = new File("input2.txt");
        Scanner s = new Scanner(file);
        int totalScore = 0;
        String round;

        while(s.hasNextLine()){
            round = s.nextLine();
            char myChoice = choice(round);
            String currRound = "" + round.charAt(0) + " " + myChoice;
            totalScore += score(currRound); // For part A, change score(currRound) to score(round)
        }
        System.out.println(totalScore);
    }
}
