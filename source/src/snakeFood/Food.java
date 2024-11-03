package snakeFood;

import java.util.LinkedList;
import java.util.Random;

import snakeBuilder.Board;

public class Food {

	private int x;
	private int y;

	// encapsulate food position
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	// food represent as 'x'
	public final char icon = 'x';

	private Food(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static Food generate(LinkedList<int[]> snakeBody) {

		Random rand = new Random();
		var validPosition = false;
		var x = 0;
		var y = 0;

		// HEIGHT = 8;
		// WIDTH = 15;

		do {
			x = rand.nextInt(Board.WIDTH - 2) + 1;
			y = rand.nextInt(Board.HEIGHT - 2) + 1;

			for (int[] snake : snakeBody) {
				if (!(x == snake[0] && y == snake[1])) {
					validPosition = true;
				}
			}
			
//			if (snakeBody.getLast()[0] == x && snakeBody.getLast()[1] == y) {
//				validPosition = false;
//			}

		} while (!validPosition);
		return new Food(x, y);
	}
}
