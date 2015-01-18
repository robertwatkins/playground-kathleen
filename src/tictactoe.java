import java.util.Scanner;

class tictactoe {
	static String[][] gameBoard = new String[3][3];
	static boolean debug = true;
	static Scanner in = new Scanner(System.in);
	static String currentPlayer = "";
	
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
		//assume the game is a draw, if any square is not an 'X' or an 'O', then it is not over
		//This method assumes no winner is found
		boolean result=true;
		for (int i=0;i<3;i++){
			for (int j=0;j<3;j++){
				if ((!gameBoard[j][i].equals("X") ) && (!gameBoard[j][i].equals("O"))){
					result=false;
				}
				if (debug) {
					System.out.print(", ("+j+"," + i + ")="+gameBoard[j][i]+":"+result);
				}
			}
		}
		if (debug){
			System.out.println();
		}
		return result;
	}
	
	private static void takeTurn(String player){
		
		//read input from the console and convert it to an integer
		//replace the corresponding square of the board with the player's mark
		//don't let a user put there mark where someone else already has one (that would be cheating) :)
		System.out.print("Enter a number player "+player+": ");
		String locationString="0";

		//the program will crash here if a user presses 'enter' without a value.
		//TODO-fix crashing bug :)
        locationString = in.nextLine();

        if (locationString.length()==0){
        	takeTurn(player);
        }
        if (debug) {System.out.println("read: "+locationString+ ", size: "+ locationString.length());  }
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
		//TODO: need to write code. This is just a stub
		return "-";
	}
	
	private static String switchPlayer(String player) {
		//given the mark of the current player, return the mark of the other player.
    	if (player=="X") {
				player = "O";
			} else {
				player ="X";
		}
		return player;
	}
		
	private static void printInstructions() {
		//These could be longer :)
		System.out.println("Select the number of the square you want to play and press [ENTER]");
		return;
	}
	
	private static void cleanUp(){
		//stop listening for input from the console.
		in.close();  
	}	
		
	public static void main(String[] args) {
    	initializeGameBoard();
    	printInstructions();
    	printGameBoard();
    	String currentPlayer="X";
    	while (!isAWinner() && !isADraw()){
    		currentPlayer = switchPlayer(currentPlayer);
    		takeTurn(currentPlayer);
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
