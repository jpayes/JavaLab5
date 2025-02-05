package labs.lab5;

import java.util.Scanner;

/**
 * Allows the user to choose a game and play it
 */
public class GameDriver {
	public static void main(String[] args) {
		System.out.println("Which game do you want to play?\nEnter 1 for Tic-Tac-Toe, 2 for Connect Four: ");
		Scanner scanner = new Scanner(System.in);
		int option = scanner.nextInt();
		GameEngine gamePlayer;
		if (option == 1) {
			gamePlayer = new GameEngine(new TicTacToeGame());
		} 
		else {
			gamePlayer = new GameEngine(new ConnectFourGame());
		}
		gamePlayer.play();
	}
}