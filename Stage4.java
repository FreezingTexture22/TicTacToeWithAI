package TicTacToeAI;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Stage4 {
	static boolean inputError = true;
	static boolean gameEnded = false;
	static boolean gameExit = false;
	static String[][] board = new String[3][3];
	static String input;
	static int c1 = 0;
	static int c2 = 0;
	static int countMoves = 0;
	static boolean playerXWon = false;
	static boolean playerOWon = false;
	static String gameMode;
	static String player1 = "";
	static String player2 = "";

	public static void main(String[] args) {

		do {

			resetParameters();

			theGame();

		} while (!gameExit);

	}

	private static void theGame() {

		switch (gameModeSelect()) {
		case "start":
			gameplay();
			break;

		case "exit":
			gameExit = true;
			break;

		default:
			System.out.println();
		}

	}

	private static void gameplay() {
		// start game, generate board, print board
		generateBoard();
		boardPrint(); // print board state

		do {

			// player1
			if (player1.equals("user")) {
				if (!gameEnded) {
					playerTurn("X"); // request coordinates (start from X)
					boardPrint(); // print updated board state
					checkGameState(); // check if any player win or game is draw
				}
			}

			if (player1.equals("easy")) {
				if (!gameEnded) {
					easyTurn("X");
					boardPrint(); // print updated board state
					checkGameState(); // check if any player win or game is draw
				}
			}

			if (player1.equals("medium")) {
				if (!gameEnded) {
					mediumTurn("X");
					boardPrint(); // print updated board state
					checkGameState(); // check if any player win or game is draw
				}
			}

			// player2
			if (player2.equals("user")) {
				if (!gameEnded) {
					playerTurn("O"); // request coordinates (start from X)
					boardPrint(); // print updated board state
					checkGameState(); // check if any player win or game is draw
				}
			}

			if (player2.equals("easy")) {
				if (!gameEnded) {
					easyTurn("O");
					boardPrint(); // print updated board state
					checkGameState(); // check if any player win or game is draw
				}
			}

			if (player2.equals("medium")) {
				if (!gameEnded) {
					mediumTurn("O");
					boardPrint(); // print updated board state
					checkGameState(); // check if any player win or game is draw
				}
			}

		} while (!gameEnded);

	}

	private static void mediumTurn(String xo) {
		System.out.println("Making move level \"medium\"");
		boolean inputError = true;
		String opponent = xo.equals("X") ? "O" : "X";

		do {
			// check if medium has two in a row and can win with one further move, it does
			// so.

			for (int i = 0; i < 3; i++) {
				// check horizontals
				if (board[i][0].equals(" ") && board[i][1].equals(xo) && board[i][2].equals(xo)) {
					board[i][0] = xo;
					countMoves++;
					inputError = false;
					break;

				} else if (board[i][0].equals(xo) && board[i][1].equals(" ") && board[i][2].equals(xo)) {
					board[i][1] = xo;
					countMoves++;
					inputError = false;
					break;

				} else if (board[i][0].equals(xo) && board[i][1].equals(xo) && board[i][2].equals(" ")) {
					board[i][2] = xo;
					countMoves++;
					inputError = false;
					break;
				}

				// check verticals
				else if (board[0][i].equals(" ") && board[1][i].equals(xo) && board[2][i].equals(xo)) {
					board[0][i] = xo;
					countMoves++;
					inputError = false;
					break;

				} else if (board[0][i].equals(xo) && board[1][i].equals(" ") && board[2][i].equals(xo)) {
					board[1][i] = xo;
					countMoves++;
					inputError = false;
					break;

				} else if (board[0][i].equals(xo) && board[1][i].equals(xo) && board[2][i].equals(" ")) {
					board[2][i] = xo;
					countMoves++;
					inputError = false;
					break;
				}
			}

			// check diagonals
			if (board[0][0].equals(xo) && board[1][1].equals(xo) && board[2][2].equals(" ")) {
				board[2][2] = xo;
				countMoves++;
				inputError = false;
				break;

			} else if (board[0][0].equals(xo) && board[1][1].equals(" ") && board[2][2].equals(xo)) {
				board[1][1] = xo;
				countMoves++;
				inputError = false;
				break;

			} else if (board[0][0].equals(" ") && board[1][1].equals(xo) && board[2][2].equals(xo)) {
				board[0][0] = xo;
				countMoves++;
				inputError = false;
				break;

			} else if (board[0][2].equals(xo) && board[1][1].equals(xo) && board[2][0].equals(" ")) {
				board[2][0] = xo;
				countMoves++;
				inputError = false;
				break;
			} else if (board[0][2].equals(xo) && board[1][1].equals(" ") && board[2][0].equals(xo)) {
				board[1][1] = xo;
				countMoves++;
				inputError = false;
				break;
			} else if (board[0][2].equals(" ") && board[1][1].equals(xo) && board[2][0].equals(xo)) {
				board[0][2] = xo;
				countMoves++;
				inputError = false;
				break;
			}

// check If its opponent can win with one move, it plays the move necessary to block this.
			else if (board[0][0].equals(" ") && board[1][1].equals(opponent) && board[2][2].equals(opponent)) {
				board[0][0] = xo;
				countMoves++;
				inputError = false;
				break;

			} else if (board[0][0].equals(opponent) && board[1][1].equals(" ") && board[2][2].equals(opponent)) {
				board[1][1] = xo;
				countMoves++;
				inputError = false;
				break;

			} else if (board[0][0].equals(opponent) && board[1][1].equals(opponent) && board[2][2].equals(" ")) {
				board[2][2] = xo;
				countMoves++;
				inputError = false;
				break;

			} else if (board[0][2].equals(opponent) && board[1][1].equals(opponent) && board[2][0].equals(" ")) {
				board[2][0] = xo;
				countMoves++;
				inputError = false;
				break;
			} else if (board[0][2].equals(opponent) && board[1][1].equals(" ") && board[2][0].equals(opponent)) {
				board[1][1] = xo;
				countMoves++;
				inputError = false;
				break;
			} else if (board[0][2].equals(" ") && board[1][1].equals(opponent) && board[2][0].equals(opponent)) {
				board[0][2] = xo;
				countMoves++;
				inputError = false;
				break;
			}

			else if (inputError) {
				for (int i = 0; i < 3; i++) {
					// check horizontals
					if (board[i][0].equals(" ") && board[i][1].equals(opponent) && board[i][2].equals(opponent)) {
						board[i][0] = xo;
						countMoves++;
						inputError = false;
						break;

					} else if (board[i][0].equals(opponent) && board[i][1].equals(" ")
							&& board[i][2].equals(opponent)) {
						board[i][1] = xo;
						countMoves++;
						inputError = false;
						break;

					} else if (board[i][0].equals(opponent) && board[i][1].equals(opponent)
							&& board[i][2].equals(" ")) {
						board[i][2] = xo;
						countMoves++;
						inputError = false;
						break;
					}

					// check verticals
					else if (board[0][i].equals(" ") && board[1][i].equals(opponent) && board[2][i].equals(opponent)) {
						board[0][i] = xo;
						countMoves++;
						inputError = false;
						break;
					} else if (board[0][i].equals(opponent) && board[1][i].equals(" ")
							&& board[2][i].equals(opponent)) {
						board[1][i] = xo;
						countMoves++;
						inputError = false;
						break;
					} else if (board[0][i].equals(opponent) && board[1][i].equals(opponent)
							&& board[2][i].equals(" ")) {
						board[2][i] = xo;
						countMoves++;
						inputError = false;
						break;
					}
				}

				// Otherwise make a random move
				if (inputError) {
					int randomNum1 = ThreadLocalRandom.current().nextInt(0, 3); // generate random number in range 0-2
					int randomNum2 = ThreadLocalRandom.current().nextInt(0, 3); // generate random number in range 0-2

					if (board[randomNum1][randomNum2].equals(" ")) {
						board[randomNum1][randomNum2] = xo;
						countMoves++;
						inputError = false;
					}
				}

			}

		} while (inputError);

	}

	//
//
//
// reset all parameters to default state
	private static void resetParameters() {

		gameEnded = false;
		countMoves = 0;
		playerXWon = false;
		playerOWon = false;

	}

	//

//
//
// select game mode:

	private static String gameModeSelect() {

		String output = "";
		boolean badParam = true;
		String[] gameModeInput;

		do {
			Scanner scanner = new Scanner(System.in);
			gameModeInput = scanner.nextLine().split(" ");

			// check for "exit" keyword
			if (gameModeInput.length == 1 && gameModeInput[0].matches("exit")) {
				badParam = false;
				output = "exit";
				continue;
			}

			// check if we get exactly 3 arguments
			if (gameModeInput.length != 3) {
				System.out.println("Bad parameters!");
				badParam = true;
				continue;
			}

			// check for "start" keyword
			if (gameModeInput[0].matches("start")) {
				badParam = false;
				output = "start";

				for (int i = 1; i < 3; i++) {

					if (gameModeInput[i].matches("user|easy|medium")) {
						badParam = false;

					} else {
						System.out.println("Bad parameters!");
						badParam = true;
						break;
					}

				}

				player1 = gameModeInput[1];
				player2 = gameModeInput[2];
				continue;

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

		if (playerXWon || playerOWon || (countMoves == 9)) {
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
				c1 = (int) coordinates.charAt(0) - 48;
				c2 = (int) coordinates.charAt(2) - 48;

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

			if (!inputError && board[c1 - 1][c2 - 1].equals(" ")) {
				board[c1 - 1][c2 - 1] = xo;
				countMoves++;
			} else if (!inputError) {
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
	private static void easyTurn(String xo) {
		System.out.println("Making move level \"easy\"");
		boolean inputError = true;

		do {
			int randomNum1 = ThreadLocalRandom.current().nextInt(0, 3); // generate random number in range 0-2
			int randomNum2 = ThreadLocalRandom.current().nextInt(0, 3); // generate random number in range 0-2

			if (board[randomNum1][randomNum2].equals(" ")) {
				board[randomNum1][randomNum2] = xo;
				countMoves++;
				inputError = false;
			}

		} while (inputError);

	}

	//
//
//
	// show game status when invoked
	private static void showMessage() {

		if ((countMoves == 9) && !playerOWon && !playerXWon) {
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

}
