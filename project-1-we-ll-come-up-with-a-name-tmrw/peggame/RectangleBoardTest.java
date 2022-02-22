/*
Peg Game
Hamza Section 6

RectangleBoardTest.java
*/

package peggame;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

import peggame.PegBoardConfiguration;
import peggame.PegGameCommandLine;
import peggame.RectangleBoard;

@Testable

/**
 * This class is for testing the 
 * RectangleBoard class.
 */
public class RectangleBoardTest {

    /**
     * This function will create a test
     * board, and compare it to the expected value.
     * It is for the RectangularBoard class.
     */
    
    @Test
    public void toStringTest(){
        System.out.println();
        System.out.println("Beggining of RectangleBoard Test");
        RectangleBoard board = new RectangleBoard(2,2);
        String expected = "o o \no o \n";
        String actual = board.toString();
        assert(expected.equals(actual)); 
    }

    /**
     * This test is for the PegReader class.
     * it will send a file that does not exist
     * as a parameter, and it print a message 
     * saying the test was successful. if the 
     * result ends up being null. It will
     * print a message saying the test failed otherwise.
     */

    @Test
    public void pegReaderTest(){
        System.out.println();
        System.out.println("Beggining of PegReader Test");
        boolean[][] newResult = PegReader.readArrayFromFile("fakeFile");
        boolean[][] expected = null;
        if(newResult.equals(expected)) {
            System.out.println("Test successful");
        }

        else {
            System.out.println("Test failed");
        }
    }

    /**
     * This is a manual test for the PegGameCommandLine
     * class. It will test the help function, and the user will
     * determine if the output was correct.
     */

    @Test
    public void commandLineTest() {
        System.out.println();
        System.out.println("Beggining of PegGameCommandLine Test");

        System.out.println("This function should print a list of commands.");
        System.out.println("Is it correct?");
        RectangleBoard testBoard;
        PegGameCommandLine helpTest = new PegGameCommandLine(testBoard);
        helpTest.help();
        
    }

    /**
     * This is a testing function for the 
     * PegBoardConfiguration class. It will
     * determine if the GameState is equal to
     * WON. 
     */

    @Test
    public void PegBoardConfigurationTest() {
        System.out.println();
        System.out.println("Beggining of PegBoardConfiguration Test");
        RectangleBoard testBoardTwo;
        PegBoardConfiguration newConfig = new PegBoardConfiguration(testBoardTwo);
        boolean nextConfig = newConfig.isGoal();
        if(nextConfig == false) {
            System.out.println("Test successful");
        }

        else {
            System.out.println("Test falied");
        }
    }

    /**
     * This function is a test for the
     * Move class. It will test the
     * getMiddle function, and compare the
     * result to a test value.
     */

    @Test
    public void moveTest() {
        System.out.println();
        System.out.println("Beggining of Move Test");

        Location first = new Location(5, 6);
        Location second = new Location(7, 8);
        Location testMid = new Location(6, 7);

        Move newMove = new Move(first, second);
        Location newLoc = newMove.getMiddle();
        
        if(newLoc.equals(testMid)) {
            System.out.println("Test successful");
        }

        else {
            System.out.println("Test falied");
        }
        
    }

    /**
     * This is a testing function for the location class.
     * It will test the isValid function, and see if the
     * row and column being used are in the correct range
     * of values.
     */

    @Test
    public void locationTest() {
        System.out.println();
        System.out.println("Beggining of Location Test");

        int rowBound = 10;
        int colBound = 10;

        Location first = new Location(5, 6);
        boolean result = first.isValid(rowBound, colBound);
        
        if(result == true) {
            System.out.println("Test successful");
        }

        else {
            System.out.println("Test falied");
        }
    }

}
