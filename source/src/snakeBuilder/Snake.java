package snakeBuilder;

import java.util.LinkedList;

import snakeFood.Food;
import snakeInterface.Controller;
import snakeInterface.State;

public class Snake implements Controller, State {

	private LinkedList<int[]> body = new LinkedList<>(); // snake body including the head
	private Food food; // food of snake
	
	// encapsulate fields
	public LinkedList<int[]> getBody() { return body; }
	public Food getFood() { return food; }

	public Snake() {
		this.x = Board.WIDTH / 2; // default position x
		this.y = Board.HEIGHT / 2; // default position y
		this.getBody().addFirst(new int[] {x, y}); // initialize snake body
		food = Food.generate(this.getBody()); // generate food when instance of snake instantiated
	}

	public int x, y;

	public final char sHead = 'O'; // snake head figure
	public final char sBody = 'o'; // snake body figure
	private boolean gameOver;

	@Override
	public boolean isAlive() {
		return !gameOver;
	}

	@Override
	public boolean hasCollided() {

		int snakeHead[] = {getBody().getFirst()[0], getBody().getFirst()[1]}; // snake head

		// check if snake collided his own body
		for (int each = 1; each < getBody().size(); each++) {
			if (snakeHead[0] == getBody().get(each)[0] && snakeHead[1] == getBody().get(each)[1]) {
				return true;
			}
		}

		// check if collided on board
		if (getBody().getFirst()[0] == 0 || getBody().getFirst()[0] == Board.WIDTH - 1) return true;
		if (getBody().getFirst()[1] == 0 || getBody().getFirst()[1] == Board.HEIGHT - 1) return true;
		
		return false; // return false otherwise
	}

	@Override
	public boolean hasEatenFood() {
		if (getBody().getFirst()[0] == getFood().getX() && getBody().getFirst()[1] == getFood().getY()) {
			return true;
		}
		return false;
	}

	/* when snake moved at a certain position,
	 * a position taken will add as a new position,
	 * and remove the last position, unless food has eaten
	 */
	@Override
	public void up() {
		y--;
		this.getBody().addFirst(new int[] {x, y});
	}
	@Override
	public void down() {
		y++;
		this.getBody().addFirst(new int[] {x, y});
	}
	@Override
	public void left() {
		x--;
		this.getBody().addFirst(new int[] {x, y});
	}
	@Override
	public void right() {
		x++;
		this.getBody().addFirst(new int[] {x, y});
	}

	// check the state of snake
	@Override
	public void checkPosition() {
		
		if (hasEatenFood()) food = Food.generate(this.getBody()); // generate another position of food
		else this.getBody().removeLast(); // remove last body of snake if not eaten the food
		
		if (hasCollided()) gameOver = true;
	}
}
