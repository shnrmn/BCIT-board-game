
/**
 * A game piece. Movement rules implemented in subclasses.
 * @author Shawn Norman
 * @version 2013.03.21
 */
public abstract class GamePiece {
	
	public GamePiece()
	{
		
	}
	
	/**
	 * Checks if a move is legal.
	 * @param a the first location
	 * @param b the new location
	 * @return the check
	 */
	public abstract boolean isLegalMove(Location a, Location b);

}
