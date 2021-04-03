package nl.inholland.model;

import java.awt.Color;
import java.io.Serializable;
import java.util.Observable;

/**
 * Represents a player model. This is observable class.
 * 
 * @author NM Klimenko
 */
public class Player extends Observable implements Serializable{

	private String name;
	/**
	 * The color with which this player plays.
	 */
	private Color color;
	/**
	 * It sets the turn. If it is true the player should make a move.
	 */
	private boolean turn;
	/**
	 * It sets the status. If it is true the player won.
	 */
	private boolean status;

	/**
	 * Class constructor. Creates a new player. Sets the given name and the given
	 * color.
	 * 
	 * @param name  name of this player. "John" or "Player 1" or etc.
	 * @param color with which this player will play.
	 */
	public Player(String name, Color color) {
		this.name = name;
		this.color = color;
		status = false;
	}

	/**
	 * Returns the name of this player.
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the color of this player.
	 * 
	 * @return color
	 */
	public Color getColor() {
		return this.color;
	}

	/**
	 * Returns the true if it is the turn of this player.
	 * 
	 * @return true or false.
	 */
	public boolean getTurn() {
		return this.turn;
	}

	/**
	 * Changes the turn of this player to the given value.
	 * 
	 * @param turn boolean
	 */
	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	/**
	 * If the current turn of a player is true it changes it to false and vice
	 * versa.
	 * 
	 * @param players is an array of current players.
	 */
	public void changeTurn(Player... players) {
		for (Player player : players) {
			if (player.turn == false) {
				player.setTurn(true);
			} else {
				player.setTurn(false);
			}
		}
	}

	/**
	 * Changes status of the player (make one won). The observers are notified about
	 * these changes.
	 */
	public void makeWon() {
		this.status = true;
		setChanged();
		notifyObservers();
	}

	/**
	 * Returns a status of this player
	 * 
	 * @return status of the this player.
	 */
	public boolean getStatus() {
		return status;
	}
}
