package jrJava.gameTree_2;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ManualPlayer extends Player implements MouseListener {

	
	public ManualPlayer(GameBoard gameBoard, int turn) {
		super(gameBoard, turn);
	}

	
	public void mousePressed(MouseEvent e) {
		if(!enabled) return;
		
		int i = (e.getY()-GameBoard.Y_TOP)/GameBoard.SQ_SIZE; 
		int j = (e.getX()-GameBoard.X_LEFT)/GameBoard.SQ_SIZE;
		
		if(gameBoard.isValid(i, j)) gameBoard.placeMove(turn, i, j); 
	}
	
	
	public void mouseClicked(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}

