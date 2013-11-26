
/**
 * A loacation on the board, has an x and y position.
 * @author Shawn Norman
 * @version 2013.03.21
 */
public class Location 
{

    private int column;
    private int row;
    
    /**
     * Constructor
     * @param column the position on the horizontal
     * @param row the position on the veritcal
     */
    public Location(int column, int row) {
        super();
        this.column = column;
        this.row = row;
    }

    /**
     * @return the column
     */
    public int getColumn() {
        return column;
    }

    /**
     * @param column the column to set
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * @param row the row to set
     */
    public void setRow(int row) {
        this.row = row;
    }
    
    /**
     * @return whether two Locations are equal.
     */
    public boolean equals(Location location)
    {
        if(this.column == location.getColumn() && this.row == location.getRow()) {
            return true;
        }
        else {
            return false;
        }
    }
    
}
