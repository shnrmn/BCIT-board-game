import java.util.Scanner;

/**
 * Takes input from the game and returns a Move corresponding to the input.
 * 
 * @author Shawn Norman
 * @version 2013.03.21
 */
public class Parser
{
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int TWO_LOCATIONS = 2;
    public static final int INVALID_COLUMN = 99;
    private Scanner reader;

    /**
     * Constructor for objects of class Parser
     */
    public Parser()
    {
        reader = new Scanner(System.in);
    }

    /**
     * Gets input from the user and returns a Move based on that input
     * @throws InvalidMoveException
     */
    public Move getInput() throws InvalidMoveException
    {
        String loc1 = null;
        String loc2 = null;

        System.out.print("> "); // print prompt

        String input = reader.nextLine();
        
        if(input.equals("quit")) {
            return null; // sending a null move to the Game ends the game
        }
        
        // first split the input into two parts, if more or less than two parts there will be an error
        String[] locations = splitInput(input);
        
        loc1 = locations[FIRST];
        loc2 = locations[SECOND];
        
        // get the rows
        int startRow = getRow(loc1, true);
        int finishRow = getRow(loc2, false);
        
        // get the columns
        int startColumn = getColumn(loc1, true);
        int finishColumn = getColumn(loc2, false);
        
        // create a move out of the columns and rows
        return createMove(startColumn, startRow, finishColumn, finishRow);
    }
    
    /** @return an Array of String based on the input */
    private String[] splitInput(String input) throws InvalidMoveException
    {
        String[] locations = input.split(" "); 
        if(locations.length != TWO_LOCATIONS) {
            throw new InvalidMoveException("Invalid input for destination location.");
        }
        return locations;
    }
    
    /** @return the row value as an int */
    private int getRow(String row, boolean checkingSource) throws InvalidMoveException
    {
        int rowInt = 0;
        try {
            rowInt = (Integer.parseInt(row.substring(FIRST, SECOND)) -1);
        }
        catch (NumberFormatException exc) {
            if(checkingSource) {
                throw new InvalidMoveException("Invalid input for source location.");
            }
            else {
                throw new InvalidMoveException("Invalid input for destination location.");
            }
        }
        return rowInt;
    }
    
    /** @return the int value of the column based on the input converted from a letter */
    private int getColumn(String column, boolean checkingSource) throws InvalidMoveException
    {
        String columnStr = column.substring(SECOND);
        
        int columnInt = getColumnInt(columnStr);
        if(columnInt == INVALID_COLUMN && checkingSource) {
            throw new InvalidMoveException("Invalid input for source location.");
        }
        else if(columnInt == INVALID_COLUMN && !checkingSource) {
            throw new InvalidMoveException("Invalid input for destination location.");
        }
        else {
            return columnInt;
        }
    }
    
    /** @return the column after converting is from a letter */
    private int getColumnInt(String s)
    {
        int columnInt = 0;
        switch(s){
            case "a": columnInt = 0;
            break;
            case "b": columnInt = 1;
            break;
            case "c": columnInt = 2;
            break;
            case "d": columnInt = 3;
            break;
            case "e": columnInt = 4;
            break;
            default: columnInt = INVALID_COLUMN;
        }
        return columnInt;
    }
    
    /** @return a Move made from the row and column values passed */
    private Move createMove(int sourceCol, int sourceRow, int destCol, int destRow)
    {
        Location source = (new Location(sourceCol, sourceRow));
        Location destination = (new Location(destCol, destRow));
                
        return new Move(source, destination);
    }
    
}
