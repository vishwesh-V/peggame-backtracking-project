/*
Peg Game
Hamza Section 6

TriangularBoard.java
*/

package peggame;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is for project 1 part 3,
 * and will contain neccesary functions 
 * related to a triangular-shaped
 * peg game board.
 */

public class TriangularBoard implements PegGame {
    private boolean[][] board;
    private GameState state;

    /**
     * This is a construcotr for the
     * TriangularBoard class. It will
     * set the board and game state.
     * 
     * @param board The triangular board being
     * used.
     */

    public TriangularBoard(boolean[][] board) {
        this.state = GameState.NOT_STARTED;
        this.board = board;
    }

    /**
     * This function will return possible moves
     * in the game, using a triangular board.
     */
    @Override
    public Collection<Move> getPossibleMoves() {
        Set<Move> moves = new HashSet<Move>();

        for(int r = 0; r < board.length; r++){
            for(int c = 0; c < board[r].length; c++){

                Move[] movesFromThisLoc = new Move[8];
                int index = 0;
                Location from = new Location(r,c);
                if(!from.isValid(board)){
                    continue;
                }

                for(int i = -2; i <= 2; i+=2){
                    for(int j = -2; j <= 2; j+=2){

                        if(i==0 && j==0 || i==2 && j == 2 || i == -2 && j == -2)
                            continue;

                        if(r+i < 0 || r+i >= board.length-1)
                            continue;
                        if(c+j < 0 || c+j >= board[r].length-1)
                            continue;

                        Location to = new Location(r + i, c + j);
                        if(!to.isValid(board)){
                            continue;
                        }
                        movesFromThisLoc[index] = new Move(from, to);
                        index++;
                    }
                }

                for(Move potentialMove: movesFromThisLoc){
                    if(potentialMove == null)
                        continue;
                    if(potentialMove.isValid(this) &
                     potentialMove.getFrom().isValid(board.length, board[r].length) &&
                     potentialMove.getTo().isValid(board.length, board[r].length))
                        moves.add(potentialMove);
                }
            }
        }
        return moves;
    }

    /**
     * This function will return the
     * current game state when called.
     */

    public GameState getGameState() {
        return this.state;
    }

    /**
     * This function will be responsible for 
     * making moves in the game, after it checks
     * if the move is valid.
     */

    public void makeMove(Move move) {
        if(move.isValid(this)){
            set(move.getFrom(), false);
            set(move.getMiddle(), false);
            set(move.getTo(), true);
            updateState();
        }
    }

    private void updateState(){
        if(state == GameState.NOT_STARTED)
            state = GameState.IN_PROGRESS;
        
        int numPegs = 0;
        for(int r = 0; r < board.length; r++){
            for(int c = 0; c < board[r].length; c++){
                if(board[r][c])
                    numPegs++;
            }
        }

        if(numPegs == 1)
            state = GameState.WON;
        else{state = GameState.STALEMATE;}
    }


    /**
     * This function will set the peg at the specified
     * location, to the boolean value being used.
     * 
     * @param loc This is the location of the peg.
     * 
     * @param value This is the boolean value being assigned to the peg.
     */

    public void set(Location loc, boolean value) {
        if(board.length <= loc.getRow() || board[loc.getRow()].length <= loc.getCol())
            throw new IndexOutOfBoundsException("Board does not have "+loc);
        board[loc.getRow()][loc.getCol()] = value;
    }

    /**
     * This function will create a deep copy of
     * the board being used.
     */

    public PegGame deepCopy() {
        return new TriangularBoard(board);
    }

    /**
     * This function will allow you to get
     * the location of a peg, in terms
     * of it's row and column.
     * 
     * @param loc This is the current location of
     * the peg.
     * 
     * @return This function will return the row and
     * column of a peg.
     */

    public boolean get(Location loc) {
        return board[loc.getRow()][loc.getCol()];
    }



    public String toString(){
        String output = "";

        for(int r = 0; r < board.length; r++){

            for(int i = r; i < board.length-1; i++){
                output += " "; //add the spaces or whatever
            } 

            for(int c = 0; c < board[r].length; c++){
                if(board[r][c]){
                    output += "o "; 
                }else{
                    output += "- ";
                }
            }
            output+="\n";
        }
        return output;
    }

    public void setGameState(GameState currentState){
        this.state = currentState;
    }

    public boolean[][] getPegs(){
        return this.board;
    }
    
}
