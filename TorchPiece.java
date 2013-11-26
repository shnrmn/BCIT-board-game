
/**
 * A torch piece can move up or down, side-to-side and any number of spaces, but only one direction each turn.
 * 
 * @author Shawn Norman
 * @version 2013.03.21
 */
public class TorchPiece extends GamePiece
{
    private String display;
	
    /**
     * Constructor for TorchPiece
     * @param display the string version of the piece
     */
	public TorchPiece(String display)
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
 		if((source.getColumn() == destination.getColumn()) || (source.getRow() == destination.getRow())) {
 			return true;
 		}
 		else {
 			return false;
 		}
	}
	
	/** @return the piece as a String. */
	public String toString()
	{
		return display;
	}
}
