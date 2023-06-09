package TicTacToeAI;

import java.util.Scanner;

import java.util.concurrent.ThreadLocalRandom;

public class Stage2 {
	static boolean inputError = true;
	static boolean gameEnded = false;
	static String[][] board = new String[3][3];
	static String input;
	static int c1 = 0;
	static int c2 = 0;
	static int countX = 0;
	static int countO = 0;
	static boolean firstPlayerActive = true;
	static boolean playerXWon = false;
	static boolean playerOWon = false;

	public static void main(String[] args) {
				
		// start game, generate board, print board
		generateBoard();
		boardPrint(); // print board state
		
		// loop until game not ended
		do {
			// player X turn, if game not ended
			if (!gameEnded) {
				
				enterCoordinates(); // request coordinates (start from X)
				boardPrint(); // print updated board state
				checkGameState(); // check if any player win or game is draw
			}

			// computers turn, if game not ended
			if (!gameEnded) {
				computerTurn();
				boardPrint(); // print updated board state
				checkGameState(); // check if any player win or game is draw
			}

		} while (!gameEnded);
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
	private static void enterCoordinates() {
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
			
			if (!inputError && firstPlayerActive && board[c1-1][c2-1].equals(" ")) {
				board[c1-1][c2-1] = "X";
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
	private static void computerTurn() {		
		System.out.println("Making move level \"easy\"");		
		boolean inputError = true;
		
		do {
			int randomNum1 = ThreadLocalRandom.current().nextInt(0, 3); // generate random number in range 0-2
			int randomNum2 = ThreadLocalRandom.current().nextInt(0, 3); // generate random number in range 0-2
			
			if (board[randomNum1][randomNum2].equals(" ")) {
				board[randomNum1][randomNum2] = "O";
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
		}
		
		if (playerOWon) {
			System.out.println("O wins");
		}
		
		if (playerXWon) {
			System.out.println("X wins");
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
