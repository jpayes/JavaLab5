package labs.lab5;

public class TicTacToeGame implements Game {
	StringBuilder board;
	int row_len;
	int col_len;
	char last;
	
	// Constructor
	public TicTacToeGame() {
		this.board = new StringBuilder("         ");
		this.row_len = 3;
		this.col_len = 3;
		this.last = ' ';
		
	}
	
	@Override
	public boolean isValidMove(String move) {
		if (move.length() != 3) {
			return false;
		} 
		char row = move.charAt(0);
		char col = move.charAt(1);
		char symbol = move.charAt(2);
		
		int row_int = row - '0';
		int col_int = col - '0';



		// check if row is negative
		// or if it's Out of Bounds
		if (row_int < 0 || col_int < 0) {
			return false;
		} else if (row_int >= this.row_len || col_int >= this.col_len) {
			return false;
		} else if (symbol != 'x' && symbol != 'o') {
			return false;
		} 
		// checking valid logic
		else {
				// if first move, then valid any spot
			if (last == ' ') {
				last = symbol;
				return true;
				// if 'o' played twice in a row
			} else if (last == 'o' && symbol == 'o') {
				return false;
				// if 'x' played twice in a row
			} else if (last == 'x' && symbol == 'x') {
				return false;
			}
			
			// bytes = row * num_cols + col 
			int index = row_int * 3;
			index += col_int;
			char current = this.board.charAt(index);
			// if spot isn't empty, then invalid
			if (current != ' ') {
				return false;
			}
			
			// update last player symbol
			// valid move : valid indexes for row/col,
			// no double turn, no placement on taken spot,
			// valid symbol
			return true;
		}
	}
	
	/* the last thing that i remember is that my code
	 * that checks for validity doesn't work because i
	 * havent added this method yet. my validity checker
	 * fails because i never executed the move so it never
	 * updated board with the new symbol in place, 
	 * therefore when it should be FALSE, i return TRUE 
	 		*/
	@Override
	public void executeMove(String move){
		if(isValidMove(move)) {
			char row = move.charAt(0);
			char col = move.charAt(1);
			char symbol = move.charAt(2);
			
			int row_int = row - '0';
			int col_int = col - '0';
			int index = row_int * 3;
			index += col_int;
			
			this.board.setCharAt(index, symbol);
			
			

		}
	}
		
	@Override
	public boolean gameOver() {
		return false;
	}
	
	@Override
	public String displayBoard() {
		String row_one = this.board.substring(0, 3);
		String row_two = this.board.substring(2, 5);
		String row_three = this.board.substring(5);
		return this.board.toString();
	}
	
	@Override
	public  int determineWinner() {
		return 0;
	}
}
