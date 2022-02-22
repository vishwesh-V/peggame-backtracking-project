/*
Peg Game
Hamza Section 6

Move.java
*/
package peggame;

/**
 * This class will be responsible for engaging
 * in operations regarding moves made in the game.
 */

public class Move  {
    private Location from;
    private Location to;

    /**
     * This is the constructor for the Move class.
     * @param from This is the starting location of a move.
     * 
     * @param to This is the ending location of a move.
     */

    public Move(Location from, Location to) {
        this.from = from;
        this.to = to;
    }

    /**
     * This function will allow you to obtain the
     * starting location of a move.
     * 
     * @return This function will return the
     * starting location of a move.
     */

    public Location getFrom() {
        return from;
    }

    /**
     * This function will allow you to obtain the
     * ending location of a move.
     * 
     * @return This function will return the
     * ending location of a move.
     */

    public Location getTo() {
        return to;
    }

    /**
     * This function will allow you to obtain the
     * middle value on the board between the starting
     * and ending locations of a move.
     * 
     * @return This function will return the middle location,
     * between the start and end of a move.
     */

    public Location getMiddle(){
        int avgCol = (from.getCol() + to.getCol())/2;
        int avgRow = (from.getRow() + to.getRow())/2;

        return new Location(avgRow, avgCol);
    }

    /**
     * This function will determine if a move on
     * the board is valid or not.
     * 
     * @param board This is the current board
     * being used.
     * 
     * @return This function will return true if the
     * move is valid, and false if the move is invalid.
     */

    public boolean isValid(PegGame board){
        int colDelta = from.getCol() - to.getCol();
        int rowDelta = from.getRow() - to.getRow();

        if(from != to &&
            (Math.abs(rowDelta) == 0 && Math.abs(colDelta) ==2) || // horizontal only
            (Math.abs(rowDelta) == 2 && Math.abs(colDelta) ==0) || // vertical only
            (Math.abs(rowDelta) == 2 && Math.abs(colDelta) ==2) ) {// diagonal

            if( board instanceof TriangularBoard )
                if( rowDelta == -2 && colDelta == 2 || rowDelta == 2 && colDelta == -2 ) 
                    return false;

            if(board.get(from) && !board.get(to)){// there is a peg from where we are trying to move from and there isnt a peg where we are trying to go
                if(board.get(getMiddle())){ //there is a peg to jump over
                    return true;
                }
            }
        }
        return false;
    }

    

    @Override
    public String toString(){
        return "Move from " + getFrom() + " to " + getTo();
    }

    @Override
    public int hashCode(){
        return from.hashCode()*10000+to.hashCode();
    }
}
