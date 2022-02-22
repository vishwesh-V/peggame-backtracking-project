/*
Peg Game
Hamza Section 6

RectangleBoard.java
*/
package peggame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import peggame.GameState;

/**
 * This class will be used for creating the boolean array of 
 * pegs, as well as the other functions that allow us to get
 * possible moves, as well as the locations of pegs, etc.
 */

public class RectangleBoard implements PegGame {
    private GameState state;
    boolean[][] pegs;
    
    /**
     * This function is responsible for creating a boolean array,
     * and setting some values for it.
     * 
     * @param rows The number of rows
     * 
     * @param cols The number of columns.
     */
    public RectangleBoard(int rows, int cols){
        this.pegs = new boolean[rows][cols];
        

        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                pegs[r][c] = true;
            }
        }

        state = GameState.NOT_STARTED;
    }

    /**
     * This is a constructor for the RectangleBoard class.
     * 
     * @param pegs The boolean array to represent pegs.
     */

    public RectangleBoard(boolean[][] pegs){
        state = GameState.NOT_STARTED;

        if(pegs.length<=0)
            System.out.println("Board init error");

        this.pegs = new boolean[pegs.length][pegs[0].length];
        for(int r = 0; r < pegs.length; r++){for(int c = 0; c < pegs[r].length; c++){
            this.pegs[r][c] = pegs[r][c];
        }}
    }

    /**
     * This function will return the boolean
     * array of pegs.
     * 
     * @return This function returns the peg
     * array.
     */

    public boolean[][] getPegs(){
        return this.pegs;
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

    public boolean get(Location loc){
        return pegs[loc.getRow()][loc.getCol()];
    }

    /**
     * This function will set the peg at the specified
     * location, to the boolean value being used.
     * 
     * @param loc This is the location of the peg.
     * 
     * @param value This is the boolean value being assigned to the peg.
     */

    public void set(Location loc, boolean value){
        pegs[loc.getRow()][loc.getCol()] = value;
    }

    /**
     * This function will be responsible for
     * making a move in the game.
     * 
     * @param board This is the current board
     * being used to play the game.
     */

    public void makeMove(Move newMove){
        if(newMove.isValid(this)){
            set(newMove.getFrom(), false);
            set(newMove.getMiddle(), false);
            set(newMove.getTo(), true);
        }

        //GameState stuff
        if(!getPossibleMoves().isEmpty())
            return;

        int numPegs = 0;
        for(int r = 0; r < pegs.length; r++){
            for(int c = 0; c < pegs[r].length; c++){
                if(pegs[r][c])
                    numPegs++;
            }
        }

        if(numPegs == 1)
            state = GameState.WON;
        else{state = GameState.STALEMATE;}
    }

    /**
     * This function will allow the user to recieve 
     * the next possible moves they can make in the game.
     */

    public Set<Move> getPossibleMoves(){
        Set<Move> moves = new HashSet<Move>();

        for(int r = 0; r < pegs.length; r++){
            for(int c = 0; c < pegs[r].length; c++){

                Move[] movesFromThisLoc = new Move[8];
                int index = 0;
                Location from = new Location(r,c);

                for(int i = -2; i <= 2; i+=2){
                    for(int j = -2; j <= 2; j+=2){

                        if(i==0 && j==0)
                            continue;

                        if(r+i < 0 || r+i >= pegs.length)
                            continue;
                        if(c+j < 0 || c+j >= pegs[r].length)
                            continue;

                        Location to = new Location(r + i, c + j);
                        movesFromThisLoc[index] = new Move(from, to);
                        index++;
                    }
                }

                for(Move potentialMove: movesFromThisLoc){
                    if(potentialMove == null)
                        continue;
                    if(potentialMove.isValid(this) &
                     potentialMove.getFrom().isValid(pegs.length, pegs[0].length) &&
                     potentialMove.getTo().isValid(pegs.length, pegs[0].length))
                        moves.add(potentialMove);
                }
            }
        }

        return moves;
    }

    /**
     * This function will make a new instance of
     * RectangleBoard with all of the same information.
     */

    @Override
    public RectangleBoard deepCopy(){
        return new RectangleBoard(pegs);
    }

    @Override
    public String toString(){
        String endStr = "";
        for (int i = 0; i < pegs.length; i++) {
            for (int j = 0; j < pegs[0].length; j++) {
                if(!pegs[i][j]){
                    endStr+= "- ";
                }else{
                    endStr+= "o ";
                }
            }
            endStr+= "\n";
        }
        return endStr;
    }

    /**
     * This function will return the current game state.
     */

    @Override
    public GameState getGameState() {
        return this.state;
    }

    /**
     * This function will set the gamestate to
     * a value given as the parameter.
     */

    @Override
    public void setGameState(GameState state) {
        this.state = state;
        
    }
}
