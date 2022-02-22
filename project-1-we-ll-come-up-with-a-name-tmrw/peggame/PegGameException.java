/*
Peg Game
Hamza Section 6

PegGameException.java
*/
package peggame;

/**
 * This class is responsible for throwing errors.
 */

public class PegGameException extends Exception{
    private static final long serialVersionUID = 1L;

    /**
     * This function is responsible for throwing an
     * exception
     * 
     * @param message This is the exception message being used.
     */
    
    public PegGameException(String message){
        super(message);
    }
}
