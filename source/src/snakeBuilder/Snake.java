package snakeBuilder;

import java.util.LinkedList;

import snakeFood.Food;
import snakeInterface.Controller;
import snakeInterface.State;

public class Snake implements Controller, State {

	private LinkedList<int[]> body = new LinkedList<>();
	private Food food;
	
	public LinkedList<int[]> getBody() { return body; }
	public Food getFood() { return food; }

	public Snake() {
		this.x = Board.WIDTH / 2;
		this.y = Board.HEIGHT / 2;
		this.getBody().addFirst(new int[] {x, y}); 
		food = Food.generate(this.getBody());
	}

	public int x, y;

	public final char sHead = 'O'; // snake head 
	public final char sBody = 'o'; // snake body
	private boolean gameOver;

	@Override
	public boolean isAlive() {
		return !gameOver;
	}

	@Override
	public boolean hasCollided() {

		int snakeHead[] = {getBody().getFirst()[0], getBody().getFirst()[1]};

		for (int each = 1; each < getBody().size(); each++) {
			if (snakeHead[0] == getBody().get(each)[0] && snakeHead[1] == getBody().get(each)[1]) {
				return true;
			}
		}

		if (getBody().getFirst()[0] == 0 || getBody().getFirst()[0] == Board.WIDTH - 1) return true;
		if (getBody().getFirst()[1] == 0 || getBody().getFirst()[1] == Board.HEIGHT - 1) return true;
		return false;
	}

	@Override
	public boolean hasEatenFood() {

		if (getBody().getFirst()[0] == getFood().getX() && getBody().getFirst()[1] == getFood().getY()) {
			return true;
		}
		return false;
	}

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
	
	@Override
	public void checkPosition() {
		
		if (hasEatenFood()) food = Food.generate(this.getBody());
		else this.getBody().removeLast(); 
		
		if (hasCollided()) gameOver = true;
	}



}
