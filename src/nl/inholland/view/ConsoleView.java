package nl.inholland.view;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

import nl.inholland.model.*;

/**
 * Represents a console view with text representation of the game board and the
 * next turn player This is observable class.
 * 
 * @author NM Klimenko
 */
public class ConsoleView implements Observer {

	private GameBoard gameBoard;
	private Player[] players;

	/**
	 * Class constructor. Creates a new console view. Adds this observer for changes
	 * in GameBoard and Player classes
	 * 
	 * @param GameBoard gameBoard
	 * @param players   is an array of current players
	 */
	public ConsoleView(GameBoard gameBoard, Player... players) {

		this.gameBoard = gameBoard;
		this.players = players;
		gameBoard.addObserver(this);
		players[0].addObserver(this);
	}

	/**
	 * Shows the text representation of the game board and the next turn player.
	 */

	public void display() {
		// display the board
		for (int r = 0; r < gameBoard.getNumberOfRows(); r++) {
			System.out.println();

			for (int c = 0; c < gameBoard.getNumberOfColumns(); c++) {
				if (gameBoard.getColor(r, c) == Color.WHITE) {
					System.out.print("*" + " ");
				} else if (gameBoard.getColor(r, c) == players[0].getColor()) {
					System.out.print("1" + " ");
				} else {
					System.out.print("2" + " ");
				}
			}
		}
		System.out.println();
		System.out.println();

		// display the turn or game over'status
		if (gameBoard.getGameOver()) {
			if (gameBoard.getCurrentPlayer(players).getStatus()) {
				System.out.println("Game over! " + gameBoard.getCurrentPlayer(players).getName() + " won!");
			} else {
				System.out.println("Game over! Draw!");
			}
		} else {
			System.out.println("It is " + gameBoard.getCurrentPlayer(players).getName() + "'s turn");
		}

		System.out.println();

		// display number of chips have been placed
		System.out.println("Total number of chips placed: " + gameBoard.getMoveCounter());

		long timeInSeconds = TimeUnit.MILLISECONDS.toSeconds(gameBoard.getTimePassed());
		int minutes = (int) (timeInSeconds / 60);
		int seconds = (int) (timeInSeconds % 60);
		// display time passed
		System.out.println("Time passed: " + minutes + ":" + seconds);
	}

	/**
	 * Calls <code>display</code> method when the observables are changed next turn
	 * player.
	 */
	@Override
	public void update(Observable o, Object arg) {
		display();
	}
}
