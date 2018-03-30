package snakegame2;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeGame extends Applet implements Runnable, KeyListener {

	// double buffering
	Graphics gfx;
	Image img;
	// runnable we need new thread as global
	Thread thread;
	// new snake object
	Snake snake;
	boolean gameOver;
	
	// define our token obj
	Token token;
	int speed;

	public void init() {
		this.resize(400, 400);
		// set the game over to false
		gameOver = false;
		speed = 30;
		// repersents off scren graphics
		img = createImage(400, 400);
		gfx = img.getGraphics();
		// listens for button clicks
		this.addKeyListener(this);
		// instananate snake object
		snake = new Snake();
		// instaneate the sname token pass it the snake as param
		token = new Token(snake);
		// instanate thread object
		// pass a runable object of this "SnakeGame" is a runnable object
		// so we call this reffuring to the SnakeGame object
		thread = new Thread(this);
		// start the thread
		thread.start();

	}

	public void paint(Graphics g) {
		// backround color
		gfx.setColor(Color.black);
		// top left courner
		gfx.fillRect(0, 0, 400, 400);

		// takes in a gfx and pass the off screen graphics
		// if game is not over draw snake else end game and call end screen
		if (!gameOver) {
			snake.draw(gfx);
			token.draw(gfx);
			gfx.drawString("Score: " + token.getScore(), 330, 395);
		} else {
			gfx.setColor(Color.RED);
			gfx.drawString("Game Over", 180, 150);
			// update the score
			gfx.drawString("Score: " + token.getScore(), 180, 170);
//			gfx.drawString("hit Q to start new game", 180, 190);
			
			
		}

		// want this to be last
		g.drawImage(img, 0, 0, null);
	}

	public void update(Graphics g) {
		// call paint method
		paint(g);
	}

	public void repaint(Graphics g) {
		paint(g);
	}

	// comes from Runnable

	public void run() {
		// what runs the game behind the scenes is a infinit for look.
		// draw over and over
		for (;;) {

			// only allow the snake to move if the game isnt over.
			if (!gameOver) {
				// tell our snake to move
				snake.move();
				// check to see if the game is over
				this.checkGameOver();
				// if the collision occurse move token
				token.snakeCollision();
			} 
			this.repaint();
			// put in try, catch
			try {
				// time delay
				// also the lower the faster the snake moves
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	// method to check if the game needs to end
	public void checkGameOver() {
		// first and second condition to end game
		// checks to see if the snake goes out of the bounds of the map
		if (snake.getX() < 0 || snake.getX() > 396) {
			gameOver = true;
		}
		if (snake.getY() < 0 || snake.getY() > 396) {
			gameOver = true;
		}

		// third condition to end game snake runs into itself
		if (snake.snakeCollision()) {
			gameOver = true;
		}
		
		
	}

	 
	
	
	
	// come from KeyListener

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		// if oyo press the key and teh snake is not moving
		// snake starts off moving left so it isnt added
		if (!snake.isMoving()) {
			if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_RIGHT
					|| e.getKeyCode() == KeyEvent.VK_DOWN) {
				// now we can tell the snake it is moving
				snake.setIsMoving(true);
			}
		}

		// when user hits up down left or right what happense
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (snake.getYDir() != 1) {
				// if oyu arnt going down already you can up
				snake.setYDir(-1);
				// cant og diaginal so set our x to 0 no left or right for this key
				snake.setXDir(0);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (snake.getYDir() != -1) {
				snake.setYDir(1);
				snake.setXDir(0);
			}

		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (snake.getXDir() != 1) {
				snake.setXDir(-1);
				snake.setYDir(0);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (snake.getXDir() != -1) {
				snake.setXDir(1);
				snake.setYDir(0);
			}

		}
			// speed controle
		if( e.getKeyCode() == KeyEvent.VK_F) {
			this.speed -= 10;
			}
		if( e.getKeyCode() == KeyEvent.VK_S) {
			this.speed += 10;
			}
		if( e.getKeyCode() == KeyEvent.VK_Q  ) {
			this.start();
			}
		
	}

	public void keyReleased(KeyEvent e) {

	}
}
