package TicTacToeAI;

import java.util.Scanner;

public class Stage1 {
	static boolean inputError = true;
	static boolean gameEnded = false;
	static String[][] board = new String[3][3];
	static String input;

	public static void main(String[] args) {

		
		input(); // user input
		do {
			
			boardPrint(); // print board state
			// request coordinates (start from X)
			// analyze and give output
			boardPrint(); // print board state
			// Output the state of the game
			gameEnded = true;
			
		} while (!gameEnded);
		

		
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

	}
}
