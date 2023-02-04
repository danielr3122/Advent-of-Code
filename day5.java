import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;

public class day5{

    private static String[] parseInput(String currRow){
        String[] returnArr = new String[currRow.length()];
        int index = 0;
        for(int i = 1; i < currRow.length(); i+=4){
            returnArr[index++] = currRow.charAt(i) + "";
        }
        return returnArr;
    }

    private static Stack<String>[] boxSetup(ArrayList<String> setupArr){
        // Initialize stacks
        int numOfStacks = Character.getNumericValue(setupArr.get(setupArr.size() - 1).charAt(setupArr.get(setupArr.size() - 1).length() - 2));
        Stack<String>[] reversedBoxStacks = new Stack[numOfStacks];
        Stack<String>[] boxStacks = new Stack[numOfStacks];
        for(int i = 0; i < numOfStacks; i++){
            reversedBoxStacks[i] = new Stack<String>();
            boxStacks[i] = new Stack<String>();
        }

        // Load boxes upside down
        String[] rowOfBoxes = new String[numOfStacks];
        for(int i = 0; i < setupArr.size(); i++){
            rowOfBoxes = parseInput(setupArr.get(i));
            for(int j = 0; j < numOfStacks; j++){
                reversedBoxStacks[j].push(rowOfBoxes[j]);
            }
        }

        // Flip boxes, right side up
        String poppedValue = "";
        for(int i = 0; i < setupArr.size(); i++){
            for(int j = 0; j < numOfStacks; j++){
                if(reversedBoxStacks[j].peek() != null){
                    poppedValue = reversedBoxStacks[j].pop();
                }
                if(poppedValue.charAt(0) != ' '){
                    boxStacks[j].push(poppedValue);
                }
            }
        }
        return boxStacks;
    }

    private static void printStack(ArrayList<String> setupArr, Stack<String>[] boxStacks){
        for(int i = 0; i < setupArr.size(); i++){
            for(int j = 0; j < 9; j++){
                if(!boxStacks[j].empty() && boxStacks[j].peek() != null){
                    System.out.print("[" + boxStacks[j].pop() + "] ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws FileNotFoundException{
        File file = new File("input5.txt");
        Scanner setupScanner = new Scanner(file);
        Scanner movesScanner = new Scanner(file);
        String setupString;
        int lineCount = 0;
        ArrayList<String> setupArr = new ArrayList<>();

        while(setupScanner.hasNextLine()){
            setupString = setupScanner.nextLine();
            setupArr.add(setupString);
            if(setupString.charAt(1) == '1'){
                break;
            } else {
                lineCount++;
            }
        }

        setupArr.remove(setupArr.size() - 1);
        Stack<String>[] boxStacks = boxSetup(setupArr);

        String moveString;
        String sNumOfMoves, sStackOne, sStackTwo;
        sNumOfMoves = sStackOne = sStackTwo = "";
        int numOfMoves, stackOne, stackTwo;
        numOfMoves = stackOne = stackTwo = 0;
        while(movesScanner.hasNextLine()){
            moveString = movesScanner.nextLine();
            if(moveString != "" && moveString.charAt(0) == 'm'){
                // move 3 from 4 to 6
                int index = 0;
                boolean isNumber = false;
                for(int i = 0; i < moveString.length(); i++){
                    char currChar = moveString.charAt(i);
                    if(Character.isDigit(currChar)){
                        if(index == 0){
                            sNumOfMoves += currChar + "";
                        } else if(index == 1){
                            sStackOne += currChar + "";
                        } else {
                            sStackTwo += currChar + "";
                        }
                        isNumber = true;
                    } else {
                        if(isNumber){
                            index++;
                            isNumber = false;
                        }
                    }
                }
                numOfMoves = Integer.parseInt(sNumOfMoves);
                stackOne = Integer.parseInt(sStackOne);
                stackTwo = Integer.parseInt(sStackTwo);

                // Part 1 Crate Rearrangement
                // String popValue;
                // for(int i = 0; i < numOfMoves; i++){
                //     if(!boxStacks[stackOne - 1].empty()){
                //         popValue = boxStacks[stackOne - 1].peek();
                //         System.out.println(popValue);
                //         boxStacks[stackTwo - 1].push(boxStacks[stackOne - 1].pop());
                //     }
                // }

                // Part 2 Crate Rearrangement
                Stack<String> movedBoxes = new Stack<String>();
                for(int i = 0; i < numOfMoves; i++){
                    if(!boxStacks[stackOne - 1].empty()){
                        movedBoxes.push(boxStacks[stackOne - 1].pop());
                    }
                }
                while(!movedBoxes.empty()){
                    boxStacks[stackTwo - 1].push(movedBoxes.pop());
                }

                sNumOfMoves = sStackOne = sStackTwo = "";
                numOfMoves = stackOne = stackTwo = 0;
            }
        }
        printStack(setupArr, boxStacks);
    }
}