package nl.inholland;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JColorChooser;
import javax.swing.JFrame;

import nl.inholland.controller.WindowController;
import nl.inholland.model.*;
import nl.inholland.view.*;

/**
 * Represents the Connect Four game. This game is for two players. Every player
 * can make a move. The last cell of the clicked column will be painted in the
 * players' color. The player wins if a horizontal, vertical, or diagonal line
 * of four one player's color cells are made. If all moves made but no one won
 * the game ends with a draw.
 * 
 * @author NM Klimenko
 */
public class ConnectFourApplication extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Class constructor. Creates a new game. Creates graphical and console views.
	 */
	public ConnectFourApplication() {

		GameBoard gameBoard = new GameBoard(6, 7);
		
		Color newColor1 = JColorChooser.showDialog(this, "Choose color for Player 1", Color.RED);
		Color newColor2 = JColorChooser.showDialog(this, "Choose color for Player 2", Color.YELLOW);
		Player player1 = new Player("Player 1", newColor1);
		Player player2 = new Player("Player 2", newColor2);
		player1.setTurn(true);

		ConsoleView consoleView = new ConsoleView(gameBoard, player1, player2);

		add(new GraphicalView(gameBoard, player1, player2), BorderLayout.CENTER);
		add(new ButtonBar(gameBoard, player1, player2), BorderLayout.NORTH);
		add(new StatusBar(gameBoard, player1, player2), BorderLayout.SOUTH);

		setBounds(100, 100, 600, 700);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Connect Four");
		addWindowListener(new WindowController(this, gameBoard, player1, player2));
		setVisible(true);
		
		consoleView.display();

	}

	public static void main(String[] args) {
		new ConnectFourApplication();

	}

}
