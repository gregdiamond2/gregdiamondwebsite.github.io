package snakegame2;

import java.awt.Color;
import java.awt.Graphics;

public class Token {

	private int x, y, score;
	private Snake snake;
	
	
	// con
	public Token(Snake s) {
		// random number so we can randomly place the token for the snake to chase
		// my border is 400 so im setting the range slighly smaller so it doesnt get placed outside the bouds of teh map
		// this is for the random starting position
		x = (int)(Math.random() * 395);
		y = (int)(Math.random() * 395);
		// instantate the token
		snake = s;
		
	}
	// when a token is hit change its position
	public void changePosition() {
		x = (int)(Math.random() * 395);
		y = (int)(Math.random() * 395);
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int ss) {
		score = ss;
	}
	
	public void draw(Graphics g) {
		// set the tokens color and size
		g.setColor(Color.green);
		// 6x6 rec token 
		g.fillRect(x, y, 6, 6);
	}
	
	//tells us if the snake goes over a token 
	public boolean snakeCollision() {
		// since is drawing graphics we want to direct center so we add +2 so we go over two and down two
		int snakeX = snake.getX() + 2;
		int snakeY = snake.getY() + 2;
		// compare the x portion of the snake to the x portion of the token and part of the snake will count as hitting the token
		if (snakeX >= x-1 && snakeX <= (x + 7)) {
			if (snakeY >= y-1 && snakeY <= (y + 7)) {
				// calling method change position so once you hit it it goes to another position
				changePosition();
				// add to the score
				score++;
				// if the snake collides with a token get longer
				snake.setElongate(true);
				return true;
			}
			
		}
		return false;
	}
}
