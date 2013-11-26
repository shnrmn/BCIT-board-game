
/**
 * A ball piece can move up or down, one space per turn.
 * 
 * @author Shawn Norman
 * @version 2013.03.21
 */
public class BallPiece extends GamePiece
{
    private String display;
    public static final int MAX_DISTANCE = 1;
    
    /**
     * Constructor for BallPiece
     * @param display the string version of the piece
     */
    public BallPiece(String display)
    {
        this.display = display;
    }
    
    /**
     * Checks if a move is legal.
     * @param a the first location
     * @param b the new location
     * @return the check
     */
    public boolean isLegalMove(Location source, Location destination) 
    {
        if(source.getColumn() == destination.getColumn()) { 
            if((source.getRow() == (destination.getRow() + MAX_DISTANCE)) || (source.getRow() == (destination.getRow() - MAX_DISTANCE))) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
    
    /** @return the piece as a String */
    public String toString()
    {
        return display;
    }
}
