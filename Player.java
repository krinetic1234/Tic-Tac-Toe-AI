package jrJava.gameTree_2;


public class Player {

	protected int turn;
	protected GameBoard gameBoard;
	protected boolean enabled;
	
	
	public Player(GameBoard gameBoard, int turn){
		this.gameBoard = gameBoard;
		this.turn = turn;
	}
	
	
	public void enable(){ enabled = true; }
	public void disable(){ enabled = false; }
	
}
