/*
Peg Game
Hamza Section 6

Location.java
*/
package peggame;

/**
 * This class is based around recieving the locations
 * necessary for the game to continue.
 */

public class Location {
    private int row;
    private int col;

    /**
     * This function is the constructor for
     * the Location class.
     * 
     * @param row The current row being used
     * 
     * @param col The current column being used
     */

    public Location(int row, int col){
        if(col > 100)
            throw new IllegalArgumentException("Cannot have more than 100 columns");
        this.row = row;
        this.col = col;
    }

    /**
     * This function will get the current row.
     * 
     * @return This function will return the current row.
     */

    public int getRow(){
        return row;
    }

    /**
     * This function will get the current column
     * 
     * @return This function will return the current
     * column bring used.
     */

    public int getCol(){
        return col;
    }

    /**
     * This function will check if the
     * row and column are the correct value.
     * 
     * @param other This is a location, with a 
     * current row and column 
     * 
     * @return This function will return true or false, depending
     * on if row is equal to the new row, and column is equal to the new column.
     */

    public boolean equals(Location other){
        return row == other.getRow() && col == other.getCol();
    }

    /**
     * This function tells you if a move is allowed/valid
     * 
     * @param rowBound This is the current row.
     * 
     * @param colBound This is the current column being used.
     * 
     * @return This function will return true if the move is valid,
     * and false if it is not.
     */

    public boolean isValid(int rowBound, int colBound){
        if(row >= 0 && row < rowBound && col >= 0 && col < colBound){
            return true;
        }
        return false;
    }
    public boolean isValid(boolean[][] board){
        if(col < 0 || row < 0 || 
            row >= board.length ||
            col >= board[row].length)
            return false;

        return true;
    }

    /**
     * This function allows for the row and column to
     * be hashed.
     */

    public int hashCode(){ // This works so long as there are less than 101 cols
        return row*100 + col;
    }

    /**
     * This function is responsible for creating a string statement
     * as it relates to the current location concering the row, and column.
     */
    
    @Override
    public String toString(){
        return "("+this.row + ", " + this.col + ")";
    }
}