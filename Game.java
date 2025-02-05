package labs.lab5;

/**
 * Interface for two-player board games.
 */
public interface Game {

	/**
	 * Check if a move is valid.
	 * 
	 * @param move move in String form: column num (0-6) followed by mark ('r' or
	 *             'y'), no space in between, e.g., 2r, 6y, etc.
	 * 
	 * @return validity of move
	 */
	boolean isValidMove(String move);


	/**
	 * Execute the specified move, if it is valid.
	 * 
	 * @param move move in String form: column num (0-6) followed by mark ('r' or
	 *             'y'), no space in between, e.g., 2r, 6y, etc.
	 */
	void executeMove(String move);


	/**
	 * Determine if game has been won.
	 * 
	 * @return true if game is over
	 */
	boolean gameOver();


	/**
	 * Returns a string representing the board game state.
	 */
	String displayBoard();


	/**
	 * Determine the winner if there is one.
	 * 
	 * @return 0 if no winner, player number (1 or 2) otherwise
	 */
	int determineWinner();

}