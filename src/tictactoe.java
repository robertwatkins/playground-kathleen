import java.util.Scanner;

class tictactoe {
	static String[][] gameBoard = new String[3][3];
	static boolean debug = true;
	static Scanner in = new Scanner(System.in);
	
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
	
	private static boolean isAWinner () {
		//This is where we will write code to determine if there is a winner
		return false;
	}
	
	private static boolean isADraw () {
		
		boolean result=true;
		for (int i=0;i<3;i++){
			for (int j=0;j<3;j++){
				if ((!gameBoard[j][i].equals("X") ) && (!gameBoard[j][i].equals("O"))){
					result=false;
				}
			}
		}
		
		return result;
	}
	
	private static void takeTurn(String player){
		System.out.print("Enter a number player "+player+": ");
		
        String locationString = in.nextLine();
        if (locationString.length()==0){
        	takeTurn(player);
        }
        int location = Integer.parseInt(locationString);
           	
		if (debug) { System.out.println("user selected :"+location); }
		if (location <1 || location >9 ){
			System.out.println("That is not a valid number, please try again.5");
			takeTurn(player);
		}
		else if ((gameBoard[(location-1) % 3][(location-1) / 3].equals("X")) || (gameBoard[(location-1) % 3][(location-1) / 3].equals("O"))){
			System.out.println("That space is taken, please enter a value that is not alreay taken");
			takeTurn(player);
			return;
		} else {
			gameBoard[(location-1) % 3][(location-1) / 3] = player;
			return;
		}
		
	}
	
	private static String getWinner() {
		//when we have a winner, find that winner
		return "-";
	}
		
	private static void cleanUp(){
		in.close();  
	}	
	public static void main(String[] args) {
    	initializeGameBoard();
    	printGameBoard();
    	while (!isAWinner() && !isADraw()){
    		takeTurn("X");
    		printGameBoard();
    		takeTurn("O");
    		printGameBoard();
    	}
    	if (isAWinner()){
    		System.out.println("Winner:"+getWinner());
    	} else {
    		System.out.println("The game is a draw");
    	}
    	cleanUp();
    }
}
