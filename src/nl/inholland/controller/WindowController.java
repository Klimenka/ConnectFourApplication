package nl.inholland.controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import nl.inholland.model.GameBoard;
import nl.inholland.model.Player;

public class WindowController extends WindowAdapter {
	private GameBoard gameBoard;
	private Player[] players;
	private JFrame parentComponent;

	public WindowController(JFrame parentComponent, GameBoard gameBoard, Player... players) {
		this.parentComponent = parentComponent;

		this.players = players;
		this.gameBoard = gameBoard;
	}

	@Override
	public void windowClosing(WindowEvent e) {
		int selection = JOptionPane.showConfirmDialog(parentComponent, "Do you want to save this game?", "Saving...",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (selection == JOptionPane.YES_OPTION) {
			try {
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("game.dat"));
				out.writeObject(gameBoard);
				for (Player p : players) {
					out.writeObject(p);
				}
				out.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.exit(0);
		} else if (selection == JOptionPane.NO_OPTION) {
			System.exit(0);
		} else if (selection == JOptionPane.CANCEL_OPTION) {
			
			parentComponent.isActive();
		}
	}
}
