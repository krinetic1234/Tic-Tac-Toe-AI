package jrJava.gameTree_2;

import java.util.ArrayList;
import java.util.List;

public class AI_Player extends Player {

	private int[][] matrix;
	private Move bestMove;
	
	
	public AI_Player(GameBoard gameBoard, int turn) {
		super(gameBoard, turn);
	}

	
	public void makeMove(int[][] matrix) {
		if(!enabled) return;
		this.matrix = matrix;
		
		bestMove = null;
		miniMax(0, turn);
		
		if(bestMove!=null) gameBoard.placeMove(turn, bestMove.i, bestMove.j);
	}
	
	
	public int miniMax(int depth, int _turn){
		
		ScoreChecker.check(matrix, +1);
		if(ScoreChecker.hasWon()) return +10;
		
		ScoreChecker.check(matrix, -1);
		if(ScoreChecker.hasWon()) return -10;
		
		List<Move> availableMoves = getAvailableMoves();
		if(availableMoves.size()==0) return 0;
		
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		for(int i=0; i<availableMoves.size(); i++) {
			Move each = availableMoves.get(i);
			matrix[each.i][each.j] = _turn;
			
			if(_turn == turn) {
				int score = miniMax(depth + 1, -_turn);
				if(score>max) {
					max = score;
					if(depth==0) bestMove = each;
				}
			}	
			else if(_turn == -turn) {
				int score = miniMax(depth + 1, -_turn);
				if(score<min){
					min = score;
				}
			}
				
			matrix[each.i][each.j] = 0;
		}
		return _turn == turn? max:min;
	}
	
	
	private List<Move> getAvailableMoves(){
		List<Move> availableMoves = new ArrayList<Move>();
		int i, j;
		for(i=0; i<matrix.length; i++) {
			for(j=0; j<matrix[i].length; j++) {
				if(matrix[i][j]==0) {
					availableMoves.add(new Move(i, j));
				}
			}
		}
		return availableMoves;
	}
	
	
	
	private class Move {
		int i, j;
		
		public Move(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
	
}












