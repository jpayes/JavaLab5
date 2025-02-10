package labs.lab5;

public class ConnectFourGame implements Game {
    private char[][] board;
    private final int rows = 6;
    private final int cols = 7;
    private char last;         // The symbol of the last move
    private char firstSymbol;  // The symbol of the first move (first player)

    // Constructor: initialize a 6x7 board with empty spaces.
    public ConnectFourGame() {
        board = new char[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                board[r][c] = ' ';
            }
        }
        last = ' ';
        firstSymbol = ' ';
    }

    @Override
    public boolean isValidMove(String move) {
        // The move must be exactly 2 characters: [column][symbol]
        if (move == null || move.length() != 2) {
            return false;
        }
        char colChar = move.charAt(0);
        char symbol = move.charAt(1);

        // The column must be a digit.
        if (!Character.isDigit(colChar)) {
            return false;
        }
        int col = colChar - '0';
        if (col < 0 || col >= cols) {
            return false;
        }

        // The symbol must be either 'r' (red) or 'y' (yellow).
        if (symbol != 'r' && symbol != 'y') {
            return false;
        }

        // Enforce alternate turns.
        if (last != ' ' && symbol == last) {
            return false;
        }

        // Check if the column is full (i.e. the top row is occupied).
        if (board[0][col] != ' ') {
            return false;
        }
        return true;
    }

    @Override
    public void executeMove(String move) {
        if (!isValidMove(move)) {
            return; // Do nothing if move is invalid.
        }
        char colChar = move.charAt(0);
        char symbol = move.charAt(1);
        int col = colChar - '0';

        // "Drop" the token into the column: find the lowest empty row.
        for (int r = rows - 1; r >= 0; r--) {
            if (board[r][col] == ' ') {
                board[r][col] = symbol;
                break;
            }
        }

        // On the first move, record the first player's symbol.
        if (firstSymbol == ' ') {
            firstSymbol = symbol;
        }
        last = symbol;
    }

    @Override
    public boolean gameOver() {
        // The game is over if a player has won or if the board is full.
        if (determineWinner() != 0) {
            return true;
        }
        // If any top cell is empty, the board is not full.
        for (int c = 0; c < cols; c++) {
            if (board[0][c] == ' ') {
                return false;
            }
        }
        return true;
    }

    @Override
    public String displayBoard() {
        StringBuilder sb = new StringBuilder();
        // Use the divider as specified in the test.
        String divider = System.lineSeparator() + "---------------------------" + System.lineSeparator();
        // Build each row from top (row 0) to bottom (row 5).
        for (int r = 0; r < rows; r++) {
            StringBuilder rowStr = new StringBuilder();
            for (int c = 0; c < cols; c++) {
                // Each cell is three characters wide.
                char token = board[r][c];
                String cell = (token == ' ') ? "   " : " " + token + " ";
                rowStr.append(cell);
                if (c < cols - 1) {
                    rowStr.append("|");
                }
            }
            sb.append(rowStr.toString());
            if (r < rows - 1) {
                sb.append(divider);
            } else {
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    @Override
    public int determineWinner() {
        // Check horizontal wins.
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c <= cols - 4; c++) {
                char token = board[r][c];
                if (token != ' ' &&
                    token == board[r][c + 1] &&
                    token == board[r][c + 2] &&
                    token == board[r][c + 3]) {
                    return (token == firstSymbol) ? 1 : 2;
                }
            }
        }
        // Check vertical wins.
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r <= rows - 4; r++) {
                char token = board[r][c];
                if (token != ' ' &&
                    token == board[r + 1][c] &&
                    token == board[r + 2][c] &&
                    token == board[r + 3][c]) {
                    return (token == firstSymbol) ? 1 : 2;
                }
            }
        }
        // Check diagonal down-right wins.
        for (int r = 0; r <= rows - 4; r++) {
            for (int c = 0; c <= cols - 4; c++) {
                char token = board[r][c];
                if (token != ' ' &&
                    token == board[r + 1][c + 1] &&
                    token == board[r + 2][c + 2] &&
                    token == board[r + 3][c + 3]) {
                    return (token == firstSymbol) ? 1 : 2;
                }
            }
        }
        // Check diagonal up-right wins.
        for (int r = 3; r < rows; r++) {
            for (int c = 0; c <= cols - 4; c++) {
                char token = board[r][c];
                if (token != ' ' &&
                    token == board[r - 1][c + 1] &&
                    token == board[r - 2][c + 2] &&
                    token == board[r - 3][c + 3]) {
                    return (token == firstSymbol) ? 1 : 2;
                }
            }
        }
        // No winner yet.
        return 0;
    }
}