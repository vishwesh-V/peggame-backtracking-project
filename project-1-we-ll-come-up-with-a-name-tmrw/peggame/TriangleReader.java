/*
Peg Game
Hamza Section 6

TriangleReader.java
*/

package peggame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TriangleReader {
    /**
     * This function will allow for the creation of a 
     * new triangle board, as well as an array of pegs.
     * 
     * @param filename This is the name of the file that will
     * be used to create the peg array.
     * 
     * @return This function will return a new triangle board.
     */
    public static TriangularBoard readTriangleBoard(String filename) {
        return new TriangularBoard(readTriangleArray(filename));
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
    public static boolean[][] readTriangleArray(String filename) {
        
        int index = 0;
        try{
            FileReader fr = new FileReader(filename); BufferedReader reader = new BufferedReader(fr);
            int rowLength = Integer.parseInt(reader.readLine().trim());      
            boolean[][] pegs = new boolean[rowLength][];  
            
            for (int i = 0; i < rowLength; i++) {
                String line = reader.readLine().trim();
                pegs[index] = new boolean[line.length()];
                for (int j = 0; j < line.length(); j++) {
                    if(line.charAt(j) == 'o'){
                        pegs[index][j] = true;
                    }else{
                        pegs[index][j] = false;
                    }
                }
                index++;
                //line = reader.readLine();
            }
            reader.close();
            return pegs;
        }catch(FileNotFoundException e){System.out.println(e);}catch(IOException e){System.out.println(e);} //maybe throw error, idk
        return null;
    }
    
}
