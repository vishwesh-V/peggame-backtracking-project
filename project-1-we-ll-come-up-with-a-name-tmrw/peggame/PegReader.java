/*
Peg Game
Hamza Section 6

PegReader.java
*/

package peggame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class is responsible for creating a new rectangle
 * board, as well as a a boolean array of pegs.
 */

public class PegReader {

    /**
     * This function will allow for the creation of a 
     * new rectangle board, as well as an array of pegs.
     * 
     * @param filename This is the name of the file that will
     * be used to create the peg array.
     * 
     * @return This function will return a new rectangle board.
     */

    public static RectangleBoard readBoardFromFile(String filename) {
        return new RectangleBoard(readArrayFromFile(filename));
    }

    

    /**
     * This function is responsible for creating a boolean array to represent
     * pegs for the game.
     * 
     * @param filename This is the file that will be used to 
     * obtain data neccesary for creating the peg array.
     * 
     * @return This function will return the peg array, or null
     * if an exception occurs.
     */
    public static boolean[][] readArrayFromFile(String filename) {
        boolean[][] pegs;
        try{
            FileReader fr = new FileReader(filename); BufferedReader reader = new BufferedReader(fr);
            int rowLength = Integer.parseInt(reader.readLine().trim()); //read a line, parse it to an int
            String line = reader.readLine().trim(); //get the first line of pegs
            pegs = new boolean[rowLength][line.length()]; // make peg array using the info we just got, yknow?

            int index = 0;
            while(line != null){
                for(int i = 0; i < line.length(); i++){
                    if(line.charAt(i) == 'o'){
                        pegs[index][i] = true;
                    }else{
                        pegs[index][i] = false;
                    }
                }index++;
                line = reader.readLine();
            }
            reader.close();
            return pegs;
        }catch(FileNotFoundException e){System.out.println(e);}catch(IOException e){System.out.println(e);} //maybe throw error, idk
        return null;
    }

    
}
