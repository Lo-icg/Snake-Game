package snakeFood;

import java.util.LinkedList;
import java.util.Random;

import snakeBuilder.Board;

public class Food {

	// hide default creation
	private Food(int x, int y) {
		this.x = x;
		this.y = y;
	}

	private int x; // x position
	private int y; // y position

	// encapsulate food position
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

	// food represent as 'x'
	public final char icon = 'x';

	// create and generate food instance in a valid position
	public static Food generate(LinkedList<int[]> snakeBody) {

		Random rand = new Random();
		var validPosition = false;
		var x = -1;
		var y = -1;

		do {
			/* generate in a valid position inside border based:
			 * WIDTH = 15, boundary: 0 & 14;
			 * HEIGHT = 8, boundary: 0 & 7;
			 */
			x = rand.nextInt(Board.WIDTH - 2) + 1; // expected position 1 - 13;
			y = rand.nextInt(Board.HEIGHT - 2) + 1; // expected position 1 - 6;

			// avoid the position of snake
			for (int[] snake : snakeBody) {
				if (!(x != snake[0] && y != snake[1])) {
					validPosition = true;
					break;
				}
			}

		} while (!validPosition);
		return new Food(x, y);
	}
}
