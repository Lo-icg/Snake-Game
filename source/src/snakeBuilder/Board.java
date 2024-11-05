package snakeBuilder;

public class Board {
	
	public static final int HEIGHT = 8;
	public static final int WIDTH = 15;

	public final char border = '#';
	public final char cell = ' ';
    
	// hide default creation
	private Board(Snake s) {
		this.snake = s;
	}
	
	/*  a static method to create a Board instance using: of(Snake Object);
	 *  just to improve readability
	 */
	public static Board of(Snake s) {
		return new Board(s);
	}
	
	private Snake snake;

	private StringBuilder v = new StringBuilder();

	private void boardBuilder() {

		for (int borderHeight = 0; borderHeight < HEIGHT; borderHeight++) {
			for (int borderWidth = 0; borderWidth < WIDTH; borderWidth++) {

				if (borderHeight == 0 || borderHeight == HEIGHT - 1) v.append(border); // draw border up and side
				else if (borderWidth == 0 || borderWidth == WIDTH - 1) v.append(border); // draw border left and right
				else {
					var isSnakeHead = false; // snake head tracker
					var isSnakeBody = false; // snake body tracker
					
					// get food position
					var isSnakeFood = snake.getFood().getX() == borderWidth && snake.getFood().getY() == borderHeight;

					// get snake head position
					if (snake.getBody().getFirst()[0] == borderWidth && snake.getBody().getFirst()[1] == borderHeight) {
						isSnakeHead = true; // track the head
					}

					// get snake body position
					for (int i = 1; i < snake.getBody().size(); i++) {
						if (snake.getBody().get(i)[0] == borderWidth && snake.getBody().get(i)[1] == borderHeight) {
							isSnakeBody = true; // track the body
						}
					}

					if (isSnakeHead) v.append(snake.sHead); // draw snake head
					else if (isSnakeBody) v.append(snake.sBody); // draw snake body
					else if (isSnakeFood) v.append(snake.getFood().icon); // draw food
					else v.append(cell); // draw cell inside border
				}
				
				// display score on board side
				if (borderHeight == 0 && borderWidth == WIDTH - 1) v.append(" Score: " + snake.getScore());
				// display game over on board side if snake was collided
				if (borderHeight == 1 && borderWidth == WIDTH - 1) if (!snake.isAlive()) v.append(" Game over");
				
			}
			v.append('\n'); // new line
		}
	}
	
	// show board
	public void show() {
		boardBuilder(); // invoke board builder
		
		// call the final representation of the board along with snake and food
		System.out.println(v.toString());
	}
}
