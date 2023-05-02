package TicTacToeAI;

import java.util.Scanner;

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

		
		input(); // user input
		boardPrint(); // print board state
		System.out.print("Enter the coordinates: ");
		
		do {
			enterCoordinates(); // request coordinates (start from X)
			checkGameState(); // check if any player win or game is draw
			boardPrint(); // print updated board state
			showMessage(); // Output the state of the game
			
			
		} while (!gameEnded);
		

		
	}

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

	}

	private static void enterCoordinates() {
		boolean inputError = true;
//		System.out.print("Enter the coordinates: ");

		
		// check input for chars - must be 'X', 'O', or '_'
		
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
				firstPlayerActive = false;
			} else if (!inputError && !firstPlayerActive && board[c1-1][c2-1].equals(" ")) {
				board[c1-1][c2-1] = "O";
				countO++;
				firstPlayerActive = true;
			} else if (!inputError){
				System.out.println("This cell is occupied! Choose another one!");
				inputError = true;
				continue;
			}
			
		} while (inputError);
	
	}

	private static void showMessage() {
		if ((countX + countO < 9) && !playerOWon && !playerXWon) {
			System.out.println("Game not finished");
		}
		
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

	private static void input() {
		Scanner scanner = new Scanner(System.in);

		do {
			inputError = false;

			System.out.print("Enter the cells: ");
			input = scanner.nextLine();

			// check input length - must me 9 chars
			if (input.length() != 9) {
				System.out.println("Input should be exactly 9 characters. Please, try again.");
				inputError = true;
				continue;
			}

			// check input for chars - must be 'X', 'O', or '_'
			for (int i = 0; i < 9; i++) {
				if (input.charAt(i) != 'X' && input.charAt(i) != 'O' && input.charAt(i) != '_') {
					System.out.println("Wrong character, must be 'X', 'O', or '_'. Try again.");
					inputError = true;
					continue;
				}
			}

		} while (inputError);

		int charNumber = 0; // set char number for loop

		input = input.replace("_", " ");

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = String.valueOf(input.charAt(charNumber));
				charNumber++;
			}
		}
		
		
		// counting X and O to choose first player
		
		for (int i = 0; i < input.length(); i++){
			if (input.charAt(i) == 'X') {
				countX++;
			} else if (input.charAt(i) == 'O') {
				countO++;
			}
		}
		
		if (countO < countX) {
			firstPlayerActive = false;
		}


	}
}
