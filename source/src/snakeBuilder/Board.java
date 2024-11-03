package snakeBuilder;

public class Board {

	public final static int HEIGHT = 8;
	public final static int WIDTH = 15;

	public final char border = '#';
	public final char cell = ' ';

	private StringBuilder v = new StringBuilder();

	private void boardBuilder() {

		for (int borderHeight = 0; borderHeight < HEIGHT; borderHeight++) {
			for (int borderWidth = 0; borderWidth < WIDTH; borderWidth++) {

				if (borderHeight == 0 || borderHeight == HEIGHT - 1) v.append(border); // draw border up and side
				else if (borderWidth == 0 || borderWidth == WIDTH - 1) v.append(border); // draw border left and right
				else {
					
					var isSnakeBody = false;
					var isSnakeHead = false;
					var isSnakeFood = snake.sFood.getX() == borderWidth && snake.sFood.getY() == borderHeight;
							
					if (snake.body.getFirst()[0] == borderWidth && snake.body.getFirst()[1] == borderHeight) isSnakeHead = true;
					
					for (int i = 1; i < snake.body.size(); i++) {
						if (snake.body.get(i)[0] == borderWidth && snake.body.get(i)[1] == borderHeight) isSnakeBody = true;
					}
					
					if (isSnakeHead) v.append(snake.sHead);
					else if (isSnakeBody) v.append(snake.sBody);
					else if (isSnakeFood) v.append(snake.sFood.icon);
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

	public static Board of(Snake s) {
		return new Board(s);
	}

	private Snake snake;

	private Board(Snake s) {this.snake = s;};

}
