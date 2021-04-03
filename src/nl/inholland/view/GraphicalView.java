package nl.inholland.view;
import nl.inholland.model.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

/**
 * Represents a graphical view of the game board where all game cells are
 * placed. This class is observer.
 * 
 * @author NM Klimenko
 */
public class GraphicalView extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private GameBoard gameBoard;
	private Player[] players;

	/**
	 * Class constructor. Creates a new graphical view of the game board.
	 * 
	 * @param GameBoard gameBoard
	 * @param players   <code>Players[]</code>
	 */
	public GraphicalView(GameBoard gameBoard, Player... players) {
		this.gameBoard = gameBoard;
		this.players = players;
		setLayout(new GridLayout(gameBoard.getNumberOfRows(), gameBoard.getNumberOfColumns()));
		setBackground(Color.BLUE);
		refresh();
	}

	/**
	 * For each position it calls <code>CellView</code> constructor which draws each
	 * cell on the board.
	 */
	private void refresh() {
		for (int r = 0; r < gameBoard.getNumberOfRows(); r++) {
			for (int c = 0; c < gameBoard.getNumberOfColumns(); c++) {

				add(new CellView(players, gameBoard, gameBoard.getCell(r, c), getX(), getY()));
			}
		}
	}

	/**
	 * Update the view if something is changed.
	 */
	@Override
	public void update(Observable o, Object arg) {
		refresh();
	}
}
