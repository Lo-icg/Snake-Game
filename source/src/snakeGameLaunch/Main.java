package snakeGameLaunch;

import java.util.Scanner;

import snakeBuilder.Board;
import snakeBuilder.Snake;

public class Main {
	
	private enum Command { W, S, A, D; }
	
	public static Command input(String label) {
		
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		var valid = false;
		
		do {		
			try {
				System.out.print(label);
				return Command.valueOf(read.nextLine().toUpperCase());
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid command\n");
			}
		} while (!valid);
		return null;
	}

	public static void main(String[] args) {
		
		Snake snakeObj = new Snake();
		Board board = Board.of(snakeObj);
		
		while (snakeObj.isAlive()) {
		
			Command c = input("MOVE: ");
			
			switch (c) {
			case Command.A -> snakeObj.left();
			case Command.W -> snakeObj.up();
			case Command.S -> snakeObj.down();
			case Command.D -> snakeObj.right();	
			}
			
			snakeObj.checkPosition();
			board.show();
		}
	}
}
