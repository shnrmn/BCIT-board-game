

/**
 * The controlling class for the game. 
 * 
 * @author Shawn Norman 
 * @version 2013.03.21
 */
public class Game
{
    private Board board;
    private Parser parser;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    public static final Location[] START = {
        new Location(1, 0),
        new Location(2, 0),
        new Location(3, 0),
        new Location(1, 1),
        new Location(2, 1),
        new Location(3, 1),
        new Location(1, 3),
        new Location(2, 3),
        new Location(3, 3),
        new Location(1, 4),
        new Location(2, 4),
        new Location(3, 4),
    };
    public static final int PLAYER_ONE_TORCHES = 3;
    public static final int PLAYER_ONE_BALLS = 6;
    public static final int PLAYER_TWO_BALLS = 9;
    public static final int PLAYER_TWO_TORCHES = 12;
    
    public static final String PLAYER_ONE_TORCH = "T";
    public static final String PLAYER_ONE_BALL = "O";
    public static final String PLAYER_TWO_TORCH = "t";
    public static final String PLAYER_TWO_BALL = "o";
    
    public Game()
    {
        board = new Board();
        parser = new Parser();
        player1 = new Player("Player 1", board);
        player2 = new Player("Player 2", board);
        currentPlayer = player1;
        play();
    }
    
    public static void main(String[] args) {
    	new Game();
    }
    
    /**
     * Displays the rules, sets up the game and starts the game.
     */
    public void play()
    {
        fillBoard();
        printWelcome();
        boolean playing = true;
        while(player1.hasPieces() && player2.hasPieces() && playing) {
            displayBoard();
            System.out.print(currentPlayer + " enter your move: ");
            try {
                Move move = parser.getInput();
                if(move == null) {
                    playing = false;
                }
                else {
                    currentPlayer.move(move);
                    System.out.println();
                    switchPlayers();
                }
            }
            catch (InvalidMoveException exc) {
                System.out.println(exc.getMessage());
                System.out.println();
            }
            
        }
        endGame();
    }
    
    /** Prints the welcome message. */
    private void printWelcome()
    {
        System.out.println("Welcome to Battle of the Juggling Objects!");
        System.out.println();
        System.out.println("Each player has three torches and three balls.");
        System.out.println("Take turns moving and try to capture each other's pieces by landing on them.");
        System.out.println("Torches can move any number of spaces either horizontally or vertically.");
        System.out.println("Balls can move one space vertically. Pieces can not jump over each other and you can't land on your own piece.");
        System.out.println("Make your moves by entering the starting and ending location for the piece you'd like to move");
        System.out.println("'4b 3b' for example.");
        System.out.println("The game ends when one player runs out of pieces. Good luck!");
        System.out.println("The upper case pieces belong to Player 1.");
        System.out.println("Type 'quit' to quit.");
        System.out.println();
    }
    
    /**
     * Fills the game board with pieces. Uses the locations entered in the static constant ArrayList of locations START.
     */
    private void fillBoard()
    {
        int i = 0;
        while(i < PLAYER_ONE_TORCHES) {
            GamePiece piece = new TorchPiece(PLAYER_ONE_TORCH);
            board.populate(piece, START[i]);
            player1.addPiece(piece);
            i++;
        }
        while(i < PLAYER_ONE_BALLS) {
            GamePiece piece = new BallPiece(PLAYER_ONE_BALL);
            board.populate(piece, START[i]);
            player1.addPiece(piece);
            i++;
        }
        while(i < PLAYER_TWO_BALLS) {
            GamePiece piece = new BallPiece(PLAYER_TWO_BALL);
            board.populate(piece, START[i]);
            player2.addPiece(piece);
            i++;
        }
        while(i < PLAYER_TWO_TORCHES) {
            GamePiece piece = new TorchPiece(PLAYER_TWO_TORCH);
            board.populate(piece, START[i]);
            player2.addPiece(piece);
            i++;
        }
    }
    
    /** Displays the board on the terminal. */
    private void displayBoard()
    {
        System.out.print(board);
    }
    
    /** Switches the current player. */
    private void switchPlayers()
    {
        if(currentPlayer == player1) {
            currentPlayer = player2;
        }
        else {
            currentPlayer = player1;
        }
    }
    
    /** Ends the game. */
    private void endGame()
    {
        if(!player1.hasPieces() || !player2.hasPieces()) {
            switchPlayers();
            displayBoard();
            System.out.println(currentPlayer + " is the winner!");
        }
        System.out.println("Thanks for playing!");
        board = new Board(); // resets the board
    }
}
