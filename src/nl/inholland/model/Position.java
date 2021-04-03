package nl.inholland.model;

import java.io.Serializable;

/**
 * Represents a position on a game board. The game board position can be
 * represented by two numbers. The first is a number of a row. The second is a
 * number of the column.
 * 
 * @author NM Klimenko
 */
public class Position implements Serializable{

	private int row;
	private int column;

	/**
	 * Class constructor. Creates a new position.
	 * 
	 * @param row    is the row's number
	 * @param column is the column's number
	 */
	public Position(int row, int column) {
		this.row = row;
		this.column = column;

	}

	/**
	 * Returns the row of this Position.
	 * 
	 * @return row number
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Returns the column of this Position.
	 * 
	 * @return column number
	 */
	public int getColumn() {
		return column;
	}
}
