package nl.inholland.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import nl.inholland.model.*;

/**
 * Represents a cell controller which extends <code>MouseAdapter</code>.
 * 
 * @author NM Klimenko
 */
public class CellController extends MouseAdapter {
	private Cell cell;
	private GameBoard gameBoard;
	private Player[] players;

	/**
	 * Class constructor. Creates a cell controller.
	 * 
	 * @param players   <code>Player[]</code>
	 * @param gameBoard <code>GameBoard</code>
	 * @param cell      <code>Cell</code>
	 */
	public CellController(Player[] players, GameBoard gameBoard, Cell cell) {
		this.cell = cell;
		this.gameBoard = gameBoard;
		this.players = players;
	}

	/**
	 * It sends players and the clicked cell to the model layer to perform the move.
	 * 
	 * @param e <code>MouseEvent</code>
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		gameBoard.makeMove(players, cell);
	}
}
