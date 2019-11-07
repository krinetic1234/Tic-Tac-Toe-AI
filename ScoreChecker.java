package jrJava.gameTree_2;


public class ScoreChecker {

	private static boolean hasWon;
	
	/*public static int[][] winnings = {
			{6, 7, 8}, {11, 12, 13}, {16, 17, 18},
			{6, 11, 16}, {7, 12, 17}, {8, 13, 18},
			{6, 12 ,18}, {8, 12, 16}, {1, 6, 11}, {1, 7, 13},
			{7, 8, 9}, {9, 13, 17}, {11, 17, 23}, {13, 18, 23},
			{7, 11, 15}, {15, 16, 17}
	};*/
	
	public static int[][] winnings = {
			{6, 7, 8}, {11, 12, 13}, {16, 17, 18},
			{6, 11, 16}, {7, 12, 17}, {8, 13, 18},
			{6, 12 ,18}, {8, 12, 16}
	};
	
	
	public static boolean hasWon(){ return hasWon; }
	
	
	public static void check(int[][] matrix, int turn){
		
		hasWon = false;
		
		int i, j, row, col;
		boolean win;
		
		for(i=0; i<winnings.length; i++){
			win = true;
			for(j=0; j<3; j++){
				row = winnings[i][j]/matrix.length;
				col = winnings[i][j]%matrix.length;
				
				if(matrix[row][col]!=turn){
					win = false;
					break;
				}
			}
			
			if(win){
				hasWon = true;
				return;
			}
		}
	}
}





