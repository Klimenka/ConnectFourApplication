package nl.inholland.model;

import java.awt.Color;
import java.io.Serializable;
import java.util.Observable;

/**
 * Represents a cell model on a game board. This is observable class.
 * 
 * @author NM Klimenko
 */
public class Cell extends Observable implements Serializable {

	/**
	 * The color of the cell. It can be White/Red/Yellow.
	 */
	private Color color;

	/**
	 * The position of the cell on the board. It consists of a row and a column.
	 */
	private Position position;

	/**
	 * Class constructor. Creates a new cell on the given position. Gives the White
	 * color for the new cell.
	 * 
	 * @param Position position
	 */
	public Cell(Position position) {
		this.position = position;
		this.color = Color.WHITE;
	}

	/**
	 * Returns the color of this cell.
	 * 
	 * @return <code>ColorCell</code> color
	 */
	public Color getColor() {
		return this.color;
	}

	/**
	 * Returns the position of this cell.
	 * 
	 * @return <code>Position</code> position
	 */
	public Position getPosition() {
		return this.position;
	}

	/**
	 * Changes the color of the cell. A new color is the given color. Available just
	 * inside the class Cell.
	 * 
	 * @param color of the cell It can be White/Red/Yellow
	 */
	private void setColor(Color color) {
		this.color = color;
	}

	/**
	 * It initiates the changing of a color of the cell. Also it is used to set the
	 * cell as changed and notify observers.
	 * 
	 * @param color of the cell It can be White/Red/Yellow
	 */
	public void changeColor(Color color) {
		this.setColor(color);
		setChanged();
		notifyObservers();

	}

}
