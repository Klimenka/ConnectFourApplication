package nl.inholland.view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import nl.inholland.controller.*;
import nl.inholland.model.*;

/**
 * Represents a cell view on a game board. This class is an observer.
 * 
 * @author NM Klimenko
 */
public class CellView extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private int x, y;
	private Cell cell;

	/**
	 * Class constructor. Creates a new cell view on the <code>GraphicalView</code>.
	 * Adds this cell view as an observer. Adds <code>MouseListener</code>
	 * 
	 * @param players   <code>Players[]</code>
	 * @param GameBoard gameBoard
	 * @param Cell      cell
	 * @param x         coordinate
	 * @param y         coordinate
	 */
	public CellView(Player[] players, GameBoard gameBoard, Cell cell, int x, int y) {
		this.cell = cell;
		this.x = x;
		this.y = y;
		cell.addObserver(this);
		addMouseListener(new CellController(players, gameBoard, cell));
	}

	/**
	 * Paints a cell accordingly its color on the board
	 */
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		if (cell.getColor() == Color.WHITE) {
			g.setColor(Color.WHITE);
		} else {
			g.setColor(cell.getColor());
		}
		g.fillOval(x + 17, y + 17, 50, 50);

	}

	/**
	 * Repaints a cell accordingly its color on the board when observables are
	 * changed
	 */
	@Override
	public void update(Observable o, Object arg) {
		paint(getGraphics());

	}

}
