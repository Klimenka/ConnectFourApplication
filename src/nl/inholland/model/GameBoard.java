package nl.inholland.model;

import java.awt.Color;
import java.io.Serializable;
import java.sql.Time;
import java.util.Observable;

/**
 * Represents a game board model. This is observable class.
 * 
 * @author NM Klimenko
 */
public class GameBoard extends Observable implements Serializable{

	/**
	 * The game board which consist of the array of cells. The board looks like 2D
	 * array.
	 */
	private Cell[][] gameBoard;
	/**
	 * The number of rows of the game board. It sets when the board is created.
	 */
	private int numberOfRows;
	/**
	 * The number of columns of the game board. It sets when the board is created.
	 */
	private int numberOfColumns;
	/**
	 * Stores the current player of the game.
	 */
	private Player currentPlayer;
	/**
	 * Indicates if the game is over (true/false).
	 */
	private boolean gameOver;
	/**
	 * Counts number of moves to end the game to indicate if all cells are filled in
	 * with colors.
	 */
	private int moveCounter;
	
	/**
	 * Starts clock when the game begins.
	 */
	private long timePassed;
	/**
	 * Class constructor. Creates a new game board with given numbers of rows and
	 * columns. numberOfRows and numberOfColumns set here. For each position a new
	 * cell constructor is called.
	 * 
	 * @param rows    represents number of rows.
	 * @param columns represents number of columns.
	 */
	public GameBoard(int rows, int columns) {
		gameBoard = new Cell[rows][columns];
		numberOfRows = rows;
		numberOfColumns = columns;
		for (int r = 0; r < numberOfRows; r++) {
			for (int c = 0; c < numberOfColumns; c++) {
				gameBoard[r][c] = new Cell(new Position(r, c));
			}
		}
		timePassed = System.currentTimeMillis();
	}

	/**
	 * Returns the color of the cell on this position of the game board. Calls the
	 * method getColor of the Cell class.
	 * 
	 * @param row    specific cell row' number
	 * @param column specific cell column' number.
	 * @return <code>ColorCell</code> color of one cell.
	 */
	public Color getColor(int row, int column) {
		return gameBoard[row][column].getColor();
	}

	/**
	 * Returns the number of rows of the game board.
	 * 
	 * @return number of rows of the current game board.
	 */
	public int getNumberOfRows() {
		return numberOfRows;
	}

	/**
	 * Returns the number of columns of the game board.
	 * 
	 * @return number of columns of the current game board.
	 */
	public int getNumberOfColumns() {
		return numberOfColumns;
	}

	/**
	 * Returns the cell on the given position of the game board.
	 * 
	 * @param row    specific cell row' number.
	 * @param column specific cell column' number.
	 * 
	 * @return <code>Cell</code> cell.
	 */
	public Cell getCell(int row, int column) {
		return gameBoard[row][column];
	}

	/**
	 * Returns the current player which is supposed to do the next move.
	 * 
	 * @return <code>Player</code> currentPlayer.
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * Returns the current player which is supposed to do the next move. This method
	 * is preferable when it is not clear if the current player is already set.
	 * 
	 * @param players is an array of players who play this game.
	 * 
	 * @return <code>Player</code> currentPlayer.
	 */
	public Player getCurrentPlayer(Player[] players) {
		for (Player player : players) {
			if (player.getTurn()) {
				currentPlayer = player;
				break;
			}
		}
		return getCurrentPlayer();
	}

	/**
	 * Returns the boolean which indicates if the game is over. It does not matter
	 * with which result.
	 * 
	 * @return true or false.
	 */
	public boolean getGameOver() {
		return gameOver;
	}

	/**
	 * Returns <code>int</code> number of chips have been placed on the Game board.
	 * 
	 * @return moveCounter.
	 */
	public int getMoveCounter() {
		return moveCounter;
	}
	
	/**
	 * Returns <code>long</code> number of milliseconds passed after the game have been started.
	 * 
	 * @return milliseconds
	 */
	public long getTimePassed() {
		return System.currentTimeMillis() - timePassed;
	}
		
	/**
	 * If the game is not over this method changes the color of a cell which is
	 * given as a row and column parameters to the current player's color. This move
	 * is counted. Then it checks if the game is over with draw.
	 * 
	 * If no one won, then the next player gets the right for the move. In any case,
	 * the observers are notified about these changes.
	 * 
	 * @param row     specific cell row' number.
	 * @param column  specific cell column' number.
	 * @param players is an array of players who play this game.
	 * 
	 */
	public void makeChanges(int row, int column, Player[] players) {
		if (!gameOver) {

			gameBoard[row][column].changeColor(getCurrentPlayer(players).getColor());
			moveCounter++;
			if (moveCounter >= (numberOfRows * numberOfColumns)) {
				isOver();
			}

			if (!isWin()) {
				getCurrentPlayer(players).changeTurn(players);
			} else {
				isOver();
			}

			setChanged();
			notifyObservers();
		}
	}

	/**
	 * The gameOver status changes to <code>true</code>. The current player's status
	 * changes to <code>true</code> what makes one won.
	 */
	private void isOver() {
		gameOver = true;
		getCurrentPlayer().makeWon();
	}

	/**
	 * This method contains all the test methods for winning the game.
	 * 
	 * @return true if the game is won and false if not
	 */
	public boolean isWin() {
		if (horizontal()) {
			return true;
		} else if (vertical()) {
			return true;
		} else if (diagonalFromUpperLeft()) {
			return true;
		} else if (diagonalFromUpperRight()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method checks in which cell changes should be made. It should be last
	 * white-colored cell in the current column.
	 * 
	 * @param cell    of the board which was clicked on by a player.
	 * @param players is an array of players who play this game.
	 */
	public void makeMove(Player[] players, Cell cell) {

		int column = cell.getPosition().getColumn();

		for (int r = numberOfRows - 1; r >= 0; r--) {

			if (gameBoard[r][column].getColor() == Color.WHITE) {
				makeChanges(r, column, players);
				break;
			} else {
				continue;
			}
		}
	}

	/**
	 * This method checks if there are four colored cells in a row horizontally.
	 * 
	 * @return true if there are four cells of the same player's color in a row.
	 */
	public boolean horizontal() {
		Color prevColor;

		Color winColor = Color.WHITE;

		for (int r = 0; r < numberOfRows; r++) {

			prevColor = gameBoard[r][0].getColor();
			int counter = 0;

			for (int c = 0; c < numberOfColumns; c++) {

				if (gameBoard[r][c].getColor() == prevColor) {
					counter++;
					winColor = gameBoard[r][c].getColor();
					if (counter >= 4 && winColor != Color.WHITE) {
						return true;
					}
				} else {
					counter = 1;
					prevColor = gameBoard[r][c].getColor();
				}
			}
		}

		return false;
	}

	/**
	 * This method checks if there are four colored cells in a row vertically.
	 * 
	 * @return true if there are four cells of the same player's color in a column.
	 */
	public boolean vertical() {

		Color prevColor;

		Color winColor = Color.WHITE;

		for (int c = 0; c < numberOfColumns; c++) {

			prevColor = gameBoard[0][c].getColor();
			int counter = 0;

			for (int r = 0; r < numberOfRows; r++) {

				if (gameBoard[r][c].getColor() == prevColor) {
					counter++;
					winColor = gameBoard[r][c].getColor();
					if (counter >= 4 && winColor != Color.WHITE) {
						return true;
					}
				} else {
					counter = 1;
					prevColor = gameBoard[r][c].getColor();
				}
			}
		}

		return false;
	}

	/**
	 * This method checks if there are four colored cells in a row diagonally from
	 * the upper left to the lower right.
	 * 
	 * @return true if there are four cells of the same player's color in a row
	 *         diagonally.
	 */
	public boolean diagonalFromUpperLeft() {
		Color prevColor;

		Color winColor = Color.WHITE;

		int r, c, counter;

		for (int i = 0; i < (numberOfRows + numberOfColumns - 1); i++) {
			counter = 0;

			if (i < numberOfRows) {
				r = i;
			} else {
				r = numberOfRows - 1;
			}
			c = i - r;
			prevColor = gameBoard[r][c].getColor();

			while (r >= 0 && r < numberOfRows && c >= 0 && c < numberOfColumns) {
				if (gameBoard[r][c].getColor() == prevColor) {
					counter++;
					winColor = gameBoard[r][c].getColor();
					if (counter >= 4 && winColor != Color.WHITE) {
						return true;
					}
				} else {
					counter = 1;
					prevColor = gameBoard[r][c].getColor();
				}

				r--;
				c++;
			}
		}
		return false;
	}

	/**
	 * This method checks if there are four colored cells in a row diagonally from
	 * upper right to lower left.
	 * 
	 * @return true if there are four cells of the same player's color in a row
	 *         diagonally.
	 */
	public boolean diagonalFromUpperRight() {

		Color prevColor;

		Color winColor = Color.WHITE;

		int r, c, counter;

		for (int i = 0; i < numberOfRows; i++) {

			for (int k = numberOfColumns - 1; k >= 0; k--) {

				r = i;
				c = r > 0 ? 0 : k;

				counter = 0;

				prevColor = gameBoard[r][c].getColor();

				while (r >= 0 && r < numberOfRows && c >= 0 && c < numberOfColumns) {
					if (gameBoard[r][c].getColor() == prevColor) {
						counter++;
						winColor = gameBoard[r][c].getColor();
						if (counter >= 4 && winColor != Color.WHITE) {
							return true;
						}
					} else {
						counter = 1;
						prevColor = gameBoard[r][c].getColor();
					}
					r++;
					c++;
				}

				if (i != 0) {
					break;
				}
			}
		}
		return false;
	}
}
