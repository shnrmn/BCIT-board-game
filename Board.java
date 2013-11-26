import java.util.ArrayList;

/**
 * The game board. A two-dimensional grid.
 * @author Shawn Norman
 * @version 2013.03.21
 */
public class Board 
{

    private GamePiece[][] grid;
    public static final String EMPTY = "*";
    public static final int ROWS = 5;
    public static final int COLUMNS = 5;
    public static final int NO_MOVE = 0;
    
    public Board()
    {
        grid = new GamePiece[ROWS][COLUMNS];
    }
    
    /** 
     * Adds a piece to the board.
     * @param piece to add
     * @param location the location to put it
     */
    public void populate(GamePiece piece, Location location)
    {
        grid[location.getRow()][location.getColumn()] = piece;
    }
    
    /**
     * @return a String representing the board layout and contents.
     */
    public String toString()
    {
        int i, j;
        String board = "  a b c d e\n";
        for(i=0; i < ROWS; i++) {
            board += (i + 1) + " ";
            for(j=0; j < COLUMNS; j++) {
                if(grid[i][j] == null) {
                    board += (EMPTY + " ");
                }
                else {
                    board += (grid[i][j] + " ");
                }
            }
            board += "\n";
        }
        board += "\n";
        return board;
    }
    
    /**
     * Moves a game piece.
     * @param source the starting Location
     * @param destination the new Location
     */
    public void movePiece(Location source, Location destination) throws InvalidMoveException
    {
        int sourceColumn = source.getColumn();
        int sourceRow = source.getRow();
        int destinationColumn = destination.getColumn();
        int destinationRow = destination.getRow();
        if(!pathClear(sourceColumn, sourceRow, destinationColumn, destinationRow)) {
            throw new InvalidMoveException("Path is not clear.");
        }
        GamePiece piece = grid[sourceRow][sourceColumn];
        grid[sourceRow][sourceColumn] = null;
        grid[destinationRow][destinationColumn] = piece;
    }
    
    /**
     * @return a piece from the location given.
     * @param location the location to check.
     */
    public GamePiece getPiece(Location location)
    {
        return grid[location.getRow()][location.getColumn()];
    }
    
    /**
     * Determines what direction the piece is moving and how far.
     * @return whether that path is clear.
     * @param source where the piece started.
     * @param destination where the piece ends up.
     */
    public boolean pathClear(int sourceCol, int sourceRow, int destCol, int destRow)
    {
        int verticalMoves = (sourceRow - destRow);
        
        if(verticalMoves == NO_MOVE) {
            if(sourceCol < destCol) {
                return checkPath(sourceCol, destCol, sourceRow, false);
            }
            else {
                return checkPath(destCol, sourceCol, sourceRow, false);
            }
        }
        else {
            if(sourceRow < destRow) {
                return checkPath(sourceRow, destRow, sourceCol, true);
            }
            else {
                return checkPath(destRow, sourceRow, sourceCol, true);
            }
        }
    }
    
    /**
     * Follows the path provided by pathClear() and checks each spot along the way for a piece.
     * @return whether there are any pieces in the way.
     * @param start the first position - where the piece was on the grid.
     * @param finish the last spot checked for a piece.
     * @param constant the row or column that will not change as the path it checked.
     * @param testingRow if the row is being tested, the grid will be checked horizontally, otherwise vertically.
     */
    private boolean checkPath(int start, int finish, int constant, boolean testingRow) 
    {
        int i = start + 1;
        boolean stillClear = true;
        while(i < finish) {
            if(!testingRow) {
                if(grid[constant][i] == null) {
                    i++;
                }   
                else {
                    return false;
                }
            }
            else {
                if(grid[i][constant] == null) {
                    i++;
                }
                else {
                    return false;
                }
            }
        }
        return stillClear;
    }   
    
    /**
     * @return all the pieces on the board as an ArrayList.
     */
    public ArrayList<GamePiece> getPieces()
    {
        ArrayList<GamePiece> pieces = new ArrayList<GamePiece>();
        int i, j;
        for(i=0; i < ROWS; i++) {
            for(j=0; j < COLUMNS; j++) {
                if(grid[i][j] != null) {
                    pieces.add(grid[i][j]);
                }
                
            }
        }
        return pieces;
    }
}
