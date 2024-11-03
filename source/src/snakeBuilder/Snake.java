package snakeBuilder;

import java.util.LinkedList;

import snakeFood.Food;
import snakeInterface.Controller;
import snakeInterface.State;

public class Snake implements Controller, State {

	public LinkedList<int[]> body = new LinkedList<>();

	public Food sFood;

	public Snake() {
		this.x = Board.WIDTH / 2;
		this.y = Board.HEIGHT / 2;
		this.body.addFirst(new int[] {x, y});
		sFood = Food.generate(this.body);
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

		int snakeHead[] = {this.body.getFirst()[0], this.body.getFirst()[1]};

		for (int each = 1; each < this.body.size(); each++) {
			if (snakeHead[0] == this.body.get(each)[0] && snakeHead[1] == this.body.get(each)[1]) {
				return true;
			}
		}

		if (this.body.getFirst()[0] == 0 || this.body.getFirst()[0] == Board.WIDTH - 1) return true;
		if (this.body.getFirst()[1] == 0 || this.body.getFirst()[1] == Board.HEIGHT - 1) return true;
		return false;
	}

	@Override
	public boolean hasEatenFood() {

		if (this.body.getFirst()[0] == this.sFood.getX() && this.body.getFirst()[1] == this.sFood.getY()) {
			return true;
		}
		return false;
	}

	@Override
	public void up() {
		y--; // index 1 is Y axis
		this.body.addFirst(new int[] {x, y});
	}

	@Override
	public void down() {
		y++; // index 1 is Y axis
		this.body.addFirst(new int[] {x, y});
	}

	@Override
	public void left() {
		x--; // index 0 is X axis
		this.body.addFirst(new int[] {x, y});
	}

	@Override
	public void right() {
		x++; // index 0 is X axis
		this.body.addFirst(new int[] {x, y});
	}
	
	@Override
	public void checkPosition() {
		
		if (hasEatenFood()) sFood = Food.generate(this.body);
		else this.body.removeLast(); 
		
		if (hasCollided()) gameOver = true;
	}



}
