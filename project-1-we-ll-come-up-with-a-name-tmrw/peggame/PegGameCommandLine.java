/*
Peg Game
Hamza Section 6

PegGameCommandLine.java
*/

package peggame;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import backtracker.Configuration;

/**
 * This class is for accessing a commandline
 * interface, and allowing the user to access
 * various commands and see the results.
 */

public class PegGameCommandLine {
    private PegGame board;

    /**
     * This is the constructor
     * 
     * @param board This is the board currently being used.
     */

    public PegGameCommandLine(PegGame board) {
        this.board = board;

    }

    /**
     * This function will show a list of all the possible
     * commands if the user enter "help".
     */
    
    public void help() {
        System.out.println("List of commands:");
        System.out.println("help: Displays a list of commands");
        System.out.println("move r1 c1 r2 c2: Attemps to move a peg from r1 c1, to r2 c2");
        System.out.println("hint: Displays an availible move");
        System.out.println("solve: will print each move, list of moves that lead to a solution, then the final result");
        System.out.println("solve: will print each move, list of moves that lead to a solution, then the final result");
        System.out.println("quit: Displays a goodbye message and quits the method.");
    }

    /**
     * This function is resposible for moving a peg between
     * two locations, using values for the columns and
     * rows.
     * 
     * @param r1 This is starting row value.
     * 
     * @param c1 This is the starting column value.
     * 
     * @param r2 This is the ending row value.
     * 
     * @param c2 This is the ending column value.
     */

    public void move(int r1, int c1, int r2, int c2) {
        Location loc1 = new Location(r1, c1);
        Location loc2 = new Location(r2, c2);
        Move nextMove = new Move(loc1, loc2);

        System.out.println(nextMove);

        if(nextMove.isValid(board)) {
            board.makeMove(nextMove);
        }else{
            System.out.println("Move is not valid");
        }
    }

    /**
     * This function is responsible for converting the
     * various rows and columns into integers.
     * 
     * @param command This is the line of columns and rows
     * to be used.
     */

    public void parseMove(String command){
        String[] splitCommand = command.split(" ");
        int r1 = Integer.parseInt(splitCommand[0]);
        int c1 = Integer.parseInt(splitCommand[1]);
        int r2 = Integer.parseInt(splitCommand[2]);
        int c2 = Integer.parseInt(splitCommand[3]);
        move(r1, c1, r2, c2);
    }

    /**
     * Returns the first move from all
     * of the possible moves, if the solution does not equal null.
     * 
     * @return Returns 1 if the solution does not equal null,
     * and 0 otherwise.
     */

    public int hint(){
        PegBoardConfiguration solution =  PegBoardConfiguration.solver(board);
        if(solution == null){
            System.out.println("No winning solution found / No more hints available!");
            return 0;
        }
        System.out.println("Hint: "+solution.getFirstMove());
        return 1;
    }

    /**
     * This function solves the board using backtracker.
     * 
     * @return This function will return 1 if the solution does
     * not equal null, and 0 otherwise.
     */

    public int solve(){
        PegBoardConfiguration solution =  PegBoardConfiguration.solver(board);
        if(solution == null){
            System.out.println("No winning solution found!");
            return 0;
        }
        ArrayList<Move> moves = solution.getPreviousMoves();
        
        for(int i = 0; i < moves.size(); i++){
            Move move = moves.get(i);
            System.out.println(move);
            board.makeMove(move);
            System.out.println(board);
        }
        System.out.println("List of Moves Made:");
        for (Move move : moves) {
            System.out.println(move);
        }
        System.out.println("\nFinal Solution: \n"+solution);
        return 1;
    }
}
