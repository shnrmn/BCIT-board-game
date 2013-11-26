import java.util.ArrayList;

/**
 * A player has pieces and makes moves on the board.
 * 
 * @author Shawn Norman 
 * @version 2013.03.21
 */
public class Player
{
    private ArrayList<GamePiece> pieces;
    private String name;
    private Board board;

    /**
     * Constructor for objects of class Player
     * @param name the player's name
     * @param board the game board
     */
    public Player(String name, Board board)
    {
        pieces = new ArrayList<GamePiece>();
        this.name = name;
        this.board = board;
    }

    /**
     * Add a piece to the player's collection
     * @param piece to add
     */
    public void addPiece(GamePiece piece)
    {
        pieces.add(piece);
    }
    
    /**
     * @return whether or not the player still has pieces.
     */
    public boolean hasPieces()
    {
        ArrayList<GamePiece> allPieces = board.getPieces();
        int pieces = 0;
        for(GamePiece piece : allPieces) {
            if(belongsTo(piece)) {
                pieces++;
            }
        }
        if(pieces > 0) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /** @return the player's name as a String. */
    public String toString()
    {
        return name;
    }
    
    /** 
     * Checks if a move is valid and moves one of the player's pieces.
     * @throws InvalidMoveException
     */
    public void move(Move move) throws InvalidMoveException
    {
        Location source = move.getSource();
        Location destination = move.getDestination();
        testMove(source, destination);
        board.movePiece(source, destination);
    }
    
    /**
     * Tests a move for validity.
     * @throw InvalidMoveException
     */
    private void testMove(Location source, Location destination) throws InvalidMoveException
    {
        boolean testingDestination = false;
        if(source.equals(destination)) {
            throw new InvalidMoveException("Invalid move - source and destination cannot be the same.");
        }
        try {
            GamePiece piece = board.getPiece(source);
            if(piece == null) {
                throw new InvalidMoveException("No piece at the source location.");
            }
            testingDestination = true;
            if(!belongsTo(piece)) {
                throw new InvalidMoveException("That's not your piece.");
            }
            else if(!piece.isLegalMove(source, destination)) {
                throw new InvalidMoveException("Invalid move for this piece.");
            }
            else if(belongsTo(board.getPiece(destination))) {
                throw new InvalidMoveException("You can't capture your own piece.");
            }
        }
        catch (ArrayIndexOutOfBoundsException exc) {
            if(!testingDestination) {
                throw new InvalidMoveException("Invalid input for source location.");
            }
            else {
                throw new InvalidMoveException("Invalid input for destination location.");
            }
        }
        catch (NullPointerException exc) {
            if(!testingDestination) {
                throw new InvalidMoveException("No piece at the source location.");
            }
            else {
                throw new InvalidMoveException("Invalid input for destination location.");
            }
        }
    }       
    
    /**
     * @return whether a not a piece on the board belongs to this player.
     */
    private boolean belongsTo(GamePiece piece)
    {
        return pieces.contains(piece);
    }
    
}
