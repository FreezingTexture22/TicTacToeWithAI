package TicTacToeAI;

import java.util.Scanner;

import java.util.concurrent.ThreadLocalRandom;

public class Stage3 {
	static boolean inputError = true;
	static boolean gameEnded = false;
	static boolean gameExit = false;
	static String[][] board = new String[3][3];
	static String input;
	static int c1 = 0;
	static int c2 = 0;
	static int countX = 0;
	static int countO = 0;
	static boolean playerXWon = false;
	static boolean playerOWon = false;
	static String gameMode;

	public static void main(String[] args) {

		do {
			
			resetParameters();
			
			switch (gameModeSelect()) {
			case "PvP":
				modePvP();
				break;

			case "CvC":
				modeCvC();
				break;

			case "PvC":
				modePvC();
				break;

			case "CvP":
				modeCvP();
				break;

			case "exit":
				gameExit = true;
				break;

			default:
				System.out.println();
			}
			
		} while (!gameExit);

	}

//
//
//
// reset all parameters to default state
	private static void resetParameters() {

		gameEnded = false;
		countX = 0;
		countO = 0;
		playerXWon = false;
		playerOWon = false;

	}

//
//
//
// game mode CompX vs PlayerO
	private static void modeCvP() {
		// start game, generate board, print board
		generateBoard();
		boardPrint(); // print board state

		// loop until game not ended
		do {
			// comp X turn, if game not ended
			if (!gameEnded) {
				computerTurn("X");
				boardPrint(); // print updated board state
				checkGameState(); // check if any player win or game is draw
			}

			// player O turn, if game not ended
			if (!gameEnded) {
				playerTurn("O"); // request coordinates (start from X)
				boardPrint(); // print updated board state
				checkGameState(); // check if any player win or game is draw
			}
		} while (!gameEnded);
	}


//
//
//
// game mode PlayerX vs CompO
	private static void modePvC() {
		// start game, generate board, print board
		generateBoard();
		boardPrint(); // print board state

		// loop until game not ended
		do {
			// player X turn, if game not ended
			if (!gameEnded) {
				playerTurn("X"); // request coordinates (start from X)
				boardPrint(); // print updated board state
				checkGameState(); // check if any player win or game is draw
			}

			// comp O turn, if game not ended
			if (!gameEnded) {
				computerTurn("O");
				boardPrint(); // print updated board state
				checkGameState(); // check if any player win or game is draw
			}
		} while (!gameEnded);
	}


//
//
//
// game mode CompX vs CompO
private static void modeCvC() {
	// start game, generate board, print board
	generateBoard();
	boardPrint(); // print board state

	// loop until game not ended
	do {
		// comp X turn, if game not ended
		if (!gameEnded) {
			computerTurn("X"); // request coordinates (start from X)
			boardPrint(); // print updated board state
			checkGameState(); // check if any player win or game is draw
		}

		// comp O turn, if game not ended
		if (!gameEnded) {
			computerTurn("O");
			boardPrint(); // print updated board state
			checkGameState(); // check if any player win or game is draw
		}
	} while (!gameEnded);
}
		



//
//
//
// game mode PlayerX vs PlayerO
	private static void modePvP() {
		// start game, generate board, print board
		generateBoard();
		boardPrint(); // print board state
	
		// loop until game not ended
		do {
			// player X turn, if game not ended
			if (!gameEnded) {	
				playerTurn("X"); // request coordinates (start from X)
				boardPrint(); // print updated board state
				checkGameState(); // check if any player win or game is draw
			}
	
			// player O turn, if game not ended
			if (!gameEnded) {
				playerTurn("O");
				boardPrint(); // print updated board state
				checkGameState(); // check if any player win or game is draw
			}
		} while (!gameEnded);
	
	}

//
//
//
// select game mode: 
	// - playerX vs playerO (PvP)
	// - compX vs compO (CvC)
	// - playerX vs compO (PvC)
	// - compX vs playerO (CvP)
	private static String gameModeSelect() {
		
		String output = "";
		boolean badParam = false;
		
		do {
			badParam = false;
			
			Scanner scanner = new Scanner(System.in);
			System.out.print("Input command: ");
			String input = scanner.nextLine();
			
			if (input.equals("start user user")) {
				output = "PvP";
								
			} else if (input.equals("start easy easy")) {
				output = "CvC";
				
			} else if (input.equals("start user easy")) {
				output = "PvC";
				
			} else if (input.equals("start easy user")) {
				output = "CvP";
				
			} else if(input.equals("exit")) {
				output = "exit";
				
			} else {
				System.out.println("Bad parameters!");
				badParam = true;
			}
		
		} while (badParam);
		
		return output;
		
	}




//
//
//
	// check game state and decide if game ended
	private static void checkGameState() {
		if (board[0][0].equals("X") && board[1][1].equals("X") && board[2][2].equals("X")) {
			playerXWon = true;

		} else if (board[0][2].equals("X") && board[1][1].equals("X") && board[2][0].equals("X")) {
			playerXWon = true;

		} else if (board[0][0].equals("O") && board[1][1].equals("O") && board[2][2].equals("O")) {
			playerOWon = true;

		} else if (board[0][2].equals("O") && board[1][1].equals("O") && board[2][0].equals("O")) {
			playerOWon = true;
		}

		if (!playerXWon && !playerOWon) {
			for (int i = 0; i < 3; i++) {
				if (board[i][0].equals("X") && board[i][1].equals("X") && board[i][2].equals("X")) {
					playerXWon = true;
					break;
					
				} else if (board[0][i].equals("X") && board[1][i].equals("X") && board[2][i].equals("X")) {
					playerXWon = true;
					break;					
				}
				
				if (board[i][0].equals("O") && board[i][1].equals("O") && board[i][2].equals("O")) {
					playerOWon = true;
					break;
					
				} else if (board[0][i].equals("O") && board[1][i].equals("O") && board[2][i].equals("O")) {
					playerOWon = true;
					break;
				}
			}
		}
				
		if (playerXWon || playerOWon || (countO + countX == 9)) {
			gameEnded = true;
		} 
		
		showMessage();
	}
	
//
//
//
	// human player enter coordinates
	private static void playerTurn(String xo) {
		boolean inputError = true;
		System.out.print("Enter the coordinates: ");

		do {
			
			Scanner scanner = new Scanner(System.in);
			String coordinates = scanner.nextLine();
			
			if (coordinates.length() != 3) {
				System.out.println("You should enter numbers!");
				inputError = true;
				continue;				
			}
						
			for (int i = 0; i < coordinates.length(); i++) {
				if (Character.isDigit(coordinates.charAt(i)) || coordinates.charAt(i) == ' ') {					
					inputError = false;					
				} else {
					System.out.println("You should enter numbers!");
					inputError = true;
					break;					
				}
			}
			
			if (!inputError) {
				c1 = (int)coordinates.charAt(0) - 48;
				c2 = (int)coordinates.charAt(2) - 48;
				
				if (coordinates.charAt(1) != ' ') {
					System.out.println("Between 1st and 2nd coordinates must be a SPACE character!");
					inputError = true;
					continue;
				}
				
				if (c1 < 1 || c1 > 3 || c2 < 1 || c2 > 3) {
					System.out.println("Coordinates should be from 1 to 3!");
					inputError = true;
					continue;
				} 								
			}
			
			if (!inputError && board[c1-1][c2-1].equals(" ")) {
				board[c1-1][c2-1] = xo;
				countX++;
			} else if (!inputError){
				System.out.println("This cell is occupied! Choose another one!");
				inputError = true;
				continue;
			}
			
		} while (inputError);	
	}

//
//
//
	// computer makes a move
	private static void computerTurn(String xo) {		
		System.out.println("Making move level \"easy\"");		
		boolean inputError = true;
		
		do {
			int randomNum1 = ThreadLocalRandom.current().nextInt(0, 3); // generate random number in range 0-2
			int randomNum2 = ThreadLocalRandom.current().nextInt(0, 3); // generate random number in range 0-2
			
			if (board[randomNum1][randomNum2].equals(" ")) {
				board[randomNum1][randomNum2] = xo;
				countO++;
				inputError = false;
			} 
			
		} while (inputError);
				
	}
	
//
//
//	
	// show game status when invoked
	private static void showMessage() {
//		if ((countX + countO < 9) && !playerOWon && !playerXWon) {
//			System.out.println("Game not finished");
//		}
		
		if ((countX + countO == 9) && !playerOWon && !playerXWon) {
			System.out.println("Draw");
			System.out.println();
		}
		
		if (playerOWon) {
			System.out.println("O wins");
			System.out.println();
		}
		
		if (playerXWon) {
			System.out.println("X wins");
			System.out.println();
		}
		
	}
	
//
//
//	
	// print out gameboard
	private static void boardPrint() {
		System.out.println("---------");

		for (int i = 0; i < 3; i++) {
			System.out.print("| ");
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.print("|");
			System.out.println();
		}

		System.out.println("---------");
	}

	

	// initial board generation with empty spaces
	private static void generateBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = " ";				
			}
		}
		
	}

	
	
	
////////////////////////////////////////////////////////
// NOT USED METHODS ARE BELOW THIS LINE
	

	
	
	

	
}
