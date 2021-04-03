package nl.inholland.view;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import nl.inholland.model.*;

/**
 * Represents a status bar on the bottom of the board. It shows whose is the
 * next turn and game over status if it is. This class is an observer.
 * 
 * @author NM Klimenko
 */
public class StatusBar extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private GameBoard gameBoard;
	private JLabel statusBarLabel1 = new JLabel();
	private JLabel statusBarLabel2 = new JLabel();
	private JLabel statusBarLabel3 = new JLabel();
	private Player[] players;

	/**
	 * Class constructor. Creates a new status bar on the bottom of the board.
	 * 
	 * @param GameBoard gameBoard
	 * @param players   <code>Players[]</code>
	 */
	public StatusBar(GameBoard gameBoard, Player... players) {
		this.gameBoard = gameBoard;
		this.players = players;
		setLayout(new GridLayout(1, 3));
		gameBoard.addObserver(this);

		// create a line border with the specified color and width
		Border border = BorderFactory.createLineBorder(Color.BLUE, 5);

		// set the border of this component
		statusBarLabel1.setBorder(border);
		statusBarLabel2.setBorder(border);
		statusBarLabel3.setBorder(border);
		add(statusBarLabel1);
		add(statusBarLabel2);
		add(statusBarLabel3);
		this.setFont(new Font("Serif", Font.PLAIN, 22));
		// statusBarLabel.setHorizontalAlignment(SwingConstants.CENTER);
		refresh();
	}

	/**
	 * Updates a current status of the game. Shows whose is the next turn. If the
	 * game is over it shows with which result.
	 * 
	 */
	private void refresh() {
		//display status and turn
		if (gameBoard.getGameOver()) {
			if (gameBoard.getCurrentPlayer(players).getStatus()) {
				statusBarLabel1.setText("Game over! " + gameBoard.getCurrentPlayer(players).getName() + " won!");
			} else {
				statusBarLabel1.setText("Game over! Draw!");
			}
		} else {
			statusBarLabel1.setText("It is " + gameBoard.getCurrentPlayer(players).getName() + "'s turn ");
		}

		System.out.println();

		// display move counter
		statusBarLabel2.setText("Total moves: " + gameBoard.getMoveCounter());

		//display time passed
		long timeInSeconds = TimeUnit.MILLISECONDS.toSeconds(gameBoard.getTimePassed());
		int minutes = (int) (timeInSeconds / 60);
		int seconds = (int) (timeInSeconds % 60);
		statusBarLabel3.setText("Time passed: " + minutes + ":" + seconds);
		
	}

	/**
	 * Updates a status when observables are changed
	 */
	@Override
	public void update(Observable o, Object arg) {
		refresh();

	}

}
