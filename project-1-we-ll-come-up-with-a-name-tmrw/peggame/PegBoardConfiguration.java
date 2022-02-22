package peggame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import backtracker.Backtracker;
import backtracker.Configuration;
import peggame.PegGame;

/**
 * This class will be responsible for the configuration of a 
 * peg board.
 */

public class PegBoardConfiguration implements Configuration{
    private PegGame board;
    private ArrayList<Move> previousMoves;

    /**
     * This is a constructor for the PegBoardConfiguration class.
     * It will create a new array list for a board.
     * 
     * @param board This is a PegBoard instance.
     */

    public PegBoardConfiguration(PegGame board){
        this(board, new ArrayList<Move>());
    }

     /**
     * This is a constructor for the PegBoardConfiguration class.
     * It will create a deep copy of a board, and a new arraylist
     * to keep track of previous moves.
     * 
     * @param board This is a PegBoard instance.
     * 
     * @param previousMoves This is an arraylist ot represent
     * a list of the previous moves made in the game.
     */

    
    public PegBoardConfiguration(PegGame board, ArrayList<Move> previousMoves){
        this.board = board.deepCopy();
        this.previousMoves = new ArrayList<>();
        for(Move move: previousMoves){
            this.previousMoves.add(move);
        }
    }

    /**
     * This function will check the goal of the
     * current game.
     * 
     * @return It returns true or false
     * depending on if the goal has been reached.
     */

    public boolean isGoal(){
        return board.getGameState() == GameState.WON;
    }

    /**
     * This function returns the true or false
     * depending on what the current gameState is.
     * 
     * @return This function will return true or false, depending
     * on the current gameState.
     */

    public boolean isValid(){
        return board.getGameState() != GameState.STALEMATE;
    }

    public void makeMove(Move move){
        board.makeMove(move);
        previousMoves.add(move);
        
    }

    /**
     * This function will get a successor of the
     * current configuration.
     * 
     * @return This function will return the group
     * of all our successors.
     */

    public Collection<Configuration> getSuccessors(){
        Collection<Configuration> successors = new HashSet<>();
        Collection<Move> moves = board.getPossibleMoves();
        for(Move move: moves){
            PegBoardConfiguration tempConfig = new PegBoardConfiguration(board, previousMoves);
            tempConfig.makeMove(move);
            if(tempConfig.isValid())
                successors.add(tempConfig);
        }

        return successors;
    }

    /**
     * This function will allow you to obtain
     * the previous moves made.
     * 
     * @return This function returns a list of the 
     * moves made previously.
     */

    public ArrayList<Move> getPreviousMoves(){
        return previousMoves;
    }

    /**
     * This function alls you to obtain the first move,
     * in the list of previous moves.
     * 
     * @return This function returns the move at the
     * beggining of the list of previous moves.
     */

    public Move getFirstMove(){
        return previousMoves.get(0);
    }

   

    @Override
    public String toString(){
        return board.toString();
    }

    /**
     * This function will create a new backtracker, and allow
     * for a new peg board configuration to be created by using the
     * backtracker.
     * 
     * @param starter This is an instance of PegBoard, used for
     * creating a new configuration.
     * 
     * @return This function will return a new configuration using backtracker.
     */

    public static PegBoardConfiguration solver(PegGame starter){
        
        PegBoardConfiguration config = new PegBoardConfiguration(starter);

        Backtracker btracker = new Backtracker(false);
        return (PegBoardConfiguration) btracker.solve(config);
    }

    /**
     * This is the main method
     * 
     * @param args Arguements.
     */

    public static void main(String args[]){
        PegGame starter = PegReader.readBoardFromFile("data/4_4.txt");
        starter = TriangleReader.readTriangleBoard("data/5.txt");
        System.out.println(starter);
        System.out.println(starter.getPossibleMoves());
        System.out.println(PegBoardConfiguration.solver(starter));
        
    }

    
}
