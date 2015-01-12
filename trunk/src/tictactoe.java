
class tictactoe {
	static String[][] gameBoard = new String[3][3];
	
	private static void initializeGameBoard() {
		// set 2d array to sequence of numbers.
		// these numbers will be used to enter values in the
		// console for each player.
		int tempInt = 0;
		for (int i=0;i<3;i++){
			for (int j=0;j<3;j++){
				tempInt = (j+1)+(i*3);
				gameBoard[j][i] = String.valueOf(tempInt);
			}
		}
	}
	
	private static void printGameBoard() {
		// output the current state of the board to the console.
		for (int i=0;i<3;i++){
			for (int j=0;j<3;j++){
				System.out.print(" "+gameBoard[j][i]+" ");
				if (j<2) {
					System.out.print("|");
				}
			}
			System.out.println();
			if (i<2) {
				System.out.println("---+---+---");
			}
		}
	}
	
	public static void main(String[] args) {
    	initializeGameBoard();
    	printGameBoard();
    
    }
}
