import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;

public class day5{

    private static ArrayList<String> getInitialPiles(Scanner setupScanner){
        String setupString;
        ArrayList<String> setupArr = new ArrayList<>();
        while(setupScanner.hasNextLine()){
            setupString = setupScanner.nextLine();
            setupArr.add(setupString);
            if(setupString.charAt(1) == '1'){
                setupArr.remove(setupArr.size() - 1);
                return setupArr;
            }
        }
        return setupArr;
    }

    private static String[] parseSetup(String currRow){
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
            rowOfBoxes = parseSetup(setupArr.get(i));
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

    private static Stack<String>[] partOneMovement(int numOfMoves, int stackOne, int stackTwo, Stack<String>[] boxStacks){
        String popValue;
        for(int i = 0; i < numOfMoves; i++){
            if(!boxStacks[stackOne - 1].empty()){
                popValue = boxStacks[stackOne - 1].peek();
                System.out.println(popValue);
                boxStacks[stackTwo - 1].push(boxStacks[stackOne - 1].pop());
            }
        }
        return boxStacks;
    }

    private static Stack<String>[] partTwoMovement(int numOfMoves, int stackOne, int stackTwo, Stack<String>[] boxStacks){
        Stack<String> movedBoxes = new Stack<String>();
        for(int i = 0; i < numOfMoves; i++){
            if(!boxStacks[stackOne - 1].empty()){
                movedBoxes.push(boxStacks[stackOne - 1].pop());
            }
        }
        while(!movedBoxes.empty()){
            boxStacks[stackTwo - 1].push(movedBoxes.pop());
        }
        return boxStacks;
    }

    private static Stack<String>[] moveBoxes(String moveString, Stack<String>[] boxStacks){
        String[] inputNum = {"", "", ""};
        if(moveString != "" && moveString.charAt(0) == 'm'){
            int index = 0;
            boolean isPreviousANumber = false;
            for(int i = 0; i < moveString.length(); i++){
                char currChar = moveString.charAt(i);
                if(Character.isDigit(currChar)){
                    inputNum[index] += currChar + "";
                    isPreviousANumber = true;
                } else {
                    if(isPreviousANumber){
                        index++;
                        isPreviousANumber = false;
                    }
                }
            }
            int numOfMoves = Integer.parseInt(inputNum[0]);
            int stackOne = Integer.parseInt(inputNum[1]);
            int stackTwo = Integer.parseInt(inputNum[2]);

            // Part 1 Crate Rearrangement
            // boxStacks = partOneMovement(numOfMoves, stackOne, stackTwo, boxStacks);

            // Part 2 Crate Rearrangement
            boxStacks = partTwoMovement(numOfMoves, stackOne, stackTwo, boxStacks);

            inputNum[0] = inputNum[1] = inputNum[2] = "";
            numOfMoves = stackOne = stackTwo = 0;
        }
        return boxStacks;
    }

    public static void main(String[] args) throws FileNotFoundException{
        File file = new File("input5.txt");
        
        // Get initial crate setup
        Scanner setupScanner = new Scanner(file);
        ArrayList<String> setupArr = new ArrayList<>();
        setupArr = getInitialPiles(setupScanner);

        // Turn setupArr into an array of stacks
        Stack<String>[] boxStacks = boxSetup(setupArr);
        Scanner movesScanner = new Scanner(file);
        
        Stack<String>[] newPile = new Stack[boxStacks.length];
        String moveString;
        while(movesScanner.hasNextLine()){
            moveString = movesScanner.nextLine();
            newPile = moveBoxes(moveString, boxStacks);
        }

        printStack(setupArr, newPile);
        setupScanner.close();
        movesScanner.close();
    }
}