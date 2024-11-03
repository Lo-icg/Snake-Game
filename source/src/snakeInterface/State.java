package snakeInterface;

public interface State {
	
	boolean isAlive();
//	boolean gameOver();
	boolean hasCollided();
	boolean hasEatenFood();
	void checkPosition();
	
}
