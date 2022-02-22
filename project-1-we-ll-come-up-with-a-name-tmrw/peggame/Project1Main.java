/*
Peg Game
Hamza Section 6

Project1Main.java
*/

package peggame;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import backtracker.Configuration;

/**
 * This class marks the beggining of the program. All of the
 * other functions neccesary for the game to work are callled here.
 */

public class Project1Main {
    /**
     * This is the main method in which the program will
     * begin.
     * 
     * @param args Arguements
     * 
     * @throws FileNotFoundException FileNotFoundException is used
     */

    public static HashSet<Move> moves;
    public static RectangleBoard board;
    public static PegGameCommandLine commandLine;

    /**
     * This is the main function.
     * 
     * @param args Arguements
     * 
     * @throws FileNotFoundException This exception is thrown by
     * the method.
     */
    public static void main(String[] args) throws FileNotFoundException {
        moves = new HashSet<>(); //stores moves
        Scanner myScan = new Scanner(System.in);
        boolean userInputCorrect = false;
        String fileName = "";
        while(!userInputCorrect){ //gets file name
                System.out.println("Enter a filename: ");
                String tempStr = myScan.nextLine();
                tempStr.trim();
                fileName = tempStr;
                try{
                    board = PegReader.readBoardFromFile(fileName);
                    
                }catch(Exception e){
                    System.out.println("File not found!");
                    userInputCorrect = false;
                }
                userInputCorrect = true;
        }
        userInputCorrect = false;
        commandLine = new PegGameCommandLine(board);
        int movecount = 0;
        while(!userInputCorrect){ //main loop
            if(movecount >= 1){
                board.setGameState(GameState.IN_PROGRESS);
            }
            System.out.println(board.toString());
            System.out.println();
            String tempstr = myScan.nextLine();
            tempstr.trim();
            String[] tempString = tempstr.split(" ");

            Set<Move> possibleMoves = board.getPossibleMoves();
           
            if(tempString[0].equals("help")){
                commandLine.help();
                userInputCorrect = false;
                System.out.println();
            }
            /**
             * if user wants to quit
             */
            else if(tempString[0].equals("quit")){ 
                userInputCorrect = quit();
            }
            
            /**
             * If user wants to make a move
             */
            else if(tempString[0].equals("move")){
                movecount++;
                move(tempString);
                possibleMoves = board.getPossibleMoves();
                userInputCorrect = false;

            }
            else if(tempString[0].toLowerCase().equals("hint")){
                commandLine.hint(); 
                
            }else if(tempString[0].equals("solve")){
                int x = commandLine.solve();
                if(x == 1){
                    return;
                }else{
                    System.out.println("You can enter \"quit\" followed by \'y\' to quit or continue playing");
                }
            }

            updateState(possibleMoves);
            
        }
        myScan.close();
        System.out.println("goodbye :D");

    }

    /**
     * This function is responsible for making
     * a move.
     * 
     * @param tempString An array of strings.
     */

    public static void move(String[] tempString){
        int[] coords = new int[4];
        int count = 0;
        for (String string : tempString) {
            if(string.toLowerCase().equals("move")){
                continue;
            }else{
                coords[count] = Integer.parseInt(string);
                count++;
            }
        }
        int r1 = coords[0];
        int c1 = coords[1];
        int r2 = coords[2];
        int c2 = coords[3];
        Move curr = new Move(new Location(r1,c1), new Location(r2, c2));
        moves.add(curr);
        commandLine.move(r1, c1, r2, c2);
    }

    /**
     * This function will determine whether
     * or not to stop the game, depending on the input.
     * It prompts the user to enter y or n. if y is entered,
     * the game is stopped. if n is entered, the game continues.
     * 
     * @return true or false depending on the user input.
     */

    public static boolean quit(){
        Scanner reader = new Scanner(System.in);
        System.out.println("Are you sure? y/n");
        String tempStr = reader.nextLine();
        reader.close();
        tempStr.trim();
        String[] val = tempStr.split(" ");
        if(val[0].toLowerCase().equals("y")){
            System.out.println(board);
            return true;
        }else{
            return false;
        }
    }

    /**
     * This function is responsible for
     * updating the current game state.
     * Depending the number of possible moves
     * reamining.
     * 
     * @param possibleMoves The list of possible moves
     * you can make.
     */

    public static void updateState(Set<Move> possibleMoves){
        
        if(possibleMoves.isEmpty()) {
            int temp_count = 0;
            
            boolean[][] boardVals = board.getPegs();
            for (int i = 0; i < boardVals.length; i++) {
                for (int j = 0; j < boardVals.length; j++) {
                    if(boardVals[i][j] == false){
                        temp_count++;
                    }
                }
            }
            if(temp_count >= 2){
                board.setGameState(GameState.STALEMATE);
            }
            else if(temp_count == 1){
                board.setGameState(GameState.WON);
            }
            System.out.println(board);
        }
    }
}
