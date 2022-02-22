/*
PegGame
Hamza Section 6

PegGame.java
*/
package peggame;

import java.util.Collection;

public interface PegGame {
    public GameState getGameState();
    public void setGameState(GameState state);
    public abstract boolean get(Location loc);
    public abstract void set(Location loc, boolean value);
    public abstract Collection<Move> getPossibleMoves();
    default public PegGame deepCopy(){throw new UnsupportedOperationException("Not implemented");}
    public abstract void makeMove(Move move);// throws PegGameException;
    public abstract String toString();
    public abstract boolean[][] getPegs();
}
