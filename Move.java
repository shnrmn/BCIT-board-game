
/**
 * A move from one Location to another.
 * 
 * @author Shawn Norman
 * @version 2013.03.21
 */
public class Move
{
    private Location source;
    private Location destination;
    
    /**
     * Constructor for objects of class Move
     */
    public Move(Location source, Location destination)
    {
        this.source = source;
        this.destination = destination;
    }
    
    /** @return the source Location */
    public Location getSource()
    {
        return source;
    }
    
    /** @return the destination Location */
    public Location getDestination()
    {
        return destination;
    }

}
