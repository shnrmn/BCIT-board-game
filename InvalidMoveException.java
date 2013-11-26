
/**
 * Thrown when a move cannot be made.
 * 
 * @author Shawn Norman 
 * @version 2013.03.16
 */
@SuppressWarnings("serial")
public class InvalidMoveException extends Exception
{
    /**
     * Constructor for objects of class InvalidMoveException
     * @param message the error message to display
     */
    public InvalidMoveException(String message)
    {
        super(message);
    }

}
