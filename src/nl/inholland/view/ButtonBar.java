package nl.inholland.view;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

import nl.inholland.controller.*;
import nl.inholland.model.*;

/**
 * Represents a button bar on the top of the game board. This is a class
 * observer. This class will be extended with buttons functionalities.
 * 
 * @author NM Klimenko
 */
public class ButtonBar extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	private JButton firstButton, secondButton, thirdButton;

	/**
	 * Class constructor. Creates a button bar view. Adds ActionListener for each
	 * button.
	 * 
	 * @param GameBoard board
	 * @param players   is an array of current players
	 */
	public ButtonBar(GameBoard board, Player... players) {

		board.addObserver(this);

		ButtonController controller = new ButtonController();
		firstButton = new JButton("Button 1");
		firstButton.addActionListener(controller);

		secondButton = new JButton("Button 2");
		secondButton.addActionListener(controller);

		thirdButton = new JButton("Button 3");
		thirdButton.addActionListener(controller);

		setLayout(new GridLayout(1, 3));
		add(firstButton);
		add(secondButton);
		add(thirdButton);

	}

	/**
	 * Updates observer when a game board is changed
	 * 
	 */
	@Override
	public void update(Observable o, Object arg) {
		// for buttons' actions

	}

}
