package jrJava.gameTree_2;

import java.awt.Color;
import java.awt.Graphics;

public class GameBoard {

	public static final int SQ_SIZE = 50;
	public static final int X_LEFT = 40, Y_TOP = 40;
	
	private static GraphicsBoard board;
	private static Graphics g;
	
	private static ManualPlayer mP;
	private static AI_Player AI;
	private static boolean gameOver;
	
	
	 
	private int[][] matrix = {
			{-10,   -10, -10, -10, -10},
			{-10,   0,   0,   0,  -10},
			{-10,   0,   0,   0, -10},
			{  -10,   0,   0,   0, -10},
			{-10, -10, -10,   -10, -10}
	};
	
	
	public boolean isValid(int i, int j){
		// check i:
		if(i<0 || i>=matrix.length) return false;
		// check j:
		if(j<0 || j>=matrix[i].length) return false;
		
		if(matrix[i][j]==0) return true;
		return false;
	}
	
	
	public void placeMove(int turn, int i, int j){
		matrix[i][j] = turn;
		
		draw();
		board.repaint();
		
		if(turn==+1){
			AI.disable();
			mP.enable();
			
			ScoreChecker.check(matrix, +1);
			if(ScoreChecker.hasWon()){
				System.out.println("AI has won!");
				gameOver = true;
			}
		}
		else if(turn==-1){
			mP.disable();
			AI.enable();
			
			ScoreChecker.check(matrix, -1);
			if(ScoreChecker.hasWon()){
				System.out.println("manual player has won!");
				gameOver = true;
			}
		}
		
	}
	
	
	public void draw(){
		board.clear();
		
		int i, j; // i->y, j->x
		for(i=0; i<matrix.length; i++){
			for(j=0; j<matrix[i].length; j++){
				if(matrix[i][j]==-10) continue;
				
				g.setColor(Color.BLACK);
				g.drawRect(X_LEFT+SQ_SIZE*j, Y_TOP+SQ_SIZE*i, SQ_SIZE, SQ_SIZE);
				
				if(matrix[i][j]==1){
					g.setColor(Color.RED);
					g.fillOval(X_LEFT+SQ_SIZE*j+2, Y_TOP+SQ_SIZE*i+2, SQ_SIZE-4, SQ_SIZE-4);
				}
				else if(matrix[i][j]==-1){
					g.setColor(Color.BLUE);
					g.fillOval(X_LEFT+SQ_SIZE*j+2, Y_TOP+SQ_SIZE*i+2, SQ_SIZE-4, SQ_SIZE-4);
				}
			}
		}
	}
	
	
	public void runAI(){
		while(!gameOver){
			
			AI.makeMove(matrix);
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) { }
		}
	}
	
	
	
	public static void main(String[] args) {
		
		board = new GraphicsBoard(330, 330);
		g = board.getCanvas();
		
		GameBoard gameBoard = new GameBoard();
		gameBoard.draw();
		board.repaint();
		
		mP = new ManualPlayer(gameBoard, -1);
		mP.enable();
		board.addMouseListener(mP);
		
		AI = new AI_Player(gameBoard, +1);
		AI.disable();
		
		gameBoard.runAI();
	}

}
 



