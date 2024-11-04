package snakeBuilder;

public class Board {
	
	public static final int HEIGHT = 8;
	public static final int WIDTH = 15;

	public final char border = '#';
	public final char cell = ' ';

	private Board(Snake s) {
		this.snake = s;
	}
	
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

					var isSnakeHead = false;
					var isSnakeBody = false;
					var isSnakeFood = snake.getFood().getX() == borderWidth && snake.getFood().getY() == borderHeight;

					if (snake.getBody().getFirst()[0] == borderWidth && snake.getBody().getFirst()[1] == borderHeight) {
						isSnakeHead = true;
					}

					for (int i = 1; i < snake.getBody().size(); i++) {
						if (snake.getBody().get(i)[0] == borderWidth && snake.getBody().get(i)[1] == borderHeight) {
							isSnakeBody = true;
						}
					}

					if (isSnakeHead) v.append(snake.sHead);
					else if (isSnakeBody) v.append(snake.sBody);
					else if (isSnakeFood) v.append(snake.getFood().icon);
					else v.append(cell);
				}
			}
			v.append('\n');
		}
	}

	public void show() {
		boardBuilder();
		System.out.println(v.toString());
	}
}
