package snakeGameLaunch;

import java.util.Scanner;

import snakeBuilder.Board;
import snakeBuilder.Snake;

public class Main {

	// command to use
	private enum Control { W, S, A, D; }

	// return a valid Control value with exception handling
	public static Control input(String label) {
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		var valid = false;

		do {		
			try {
				System.out.print(label);
				return Control.valueOf(read.nextLine().toUpperCase());
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid command\n");
			}
		} while (!valid);
		return null;
	}

	public static void main(String[] args) {

		// a snake object
		Snake snake = new Snake();
		// a board object with a parameter of snake object to track the position
		Board board = Board.of(snake);
		board.show(); // show the board

		while (snake.isAlive()) {

			Control c = input("MOVE: "); // prompt a user input to move the snake

			switch (c) {
			case Control.W -> snake.up();
			case Control.S -> snake.down();
			case Control.A -> snake.left();
			case Control.D -> snake.right();	
			}

			snake.checkPosition(); // check the state of snake every move
			board.show(); // show the board
		}
	}
}
