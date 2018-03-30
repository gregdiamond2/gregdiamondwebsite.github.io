package snakegame2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Snake {
	// list of points that can be modified

	List<Point> snakePoints;
	int xDir, yDir;
	// variable to keep track if the snake is currently moving'
	// snake starts off starting a certain direction
	// is moving is just for the start
	// elongate is to tell us when the snake should increase. when it hits token
	// hits this variable and snake gets longer
	boolean isMoving, elongate;
	// specifiy where the snake starts, and how big it will be
	final int STARTSIZE = 20, STARTX = 150, STARTY = 150;

	// if the x is directions is -1 its heading left,
	// if positive one heading right
	// if y is -1 going up
	// if y is 1 going down
	// if 0 not heading in that direction
	public Snake() {
		snakePoints = new ArrayList<Point>();
		// start at 0
		xDir = 0;
		yDir = 0;
		// start off set to false
		isMoving = false;
		elongate = false;
		// snakes starting point with set x and y values
		snakePoints.add(new Point(STARTX, STARTY));
		// rest of the points for start up
		// start at point 1 since we already have are first point set up
		for (int i = 1; i < STARTSIZE; i++) {
			// add points to snake
			// this pushes the starting snakes body off to the left

			snakePoints.add(new Point(STARTX - i * 4, STARTY));
		}

	}

	// take in graphics g, enables us to make a snake object in the game
	// and instanciate the object and call the draw method and passing the g method
	// in snakes game
	public void draw(Graphics g) {
		// set snakes color
		g.setColor(Color.white);
		// draw each point within the snake made up of a bunch of tiny rectangles
		for (Point p : snakePoints) {
			// 4 small rectangles size 4 by 4
			g.fillRect(p.getX(), p.getY(), 4, 4);
		}

	}
	// this method lets the snake move
	public void move() {
		// check to see if the snake is moving before changing what the snake looks like
		if(isMoving) {
			// record where the start of the snake is
			Point temp = snakePoints.get(0);
			// last point of the snake starting point
			Point last = snakePoints.get(snakePoints.size() -1);
			// create new starting point for snake
			//since rec are 4 by 4 we will add or subtract for when we move it around
			Point newStart = new Point(temp.getX() + xDir * 4, temp.getY() + yDir * 4);
			// getting every other point to shift by 4 to move the whole snake
			// increment throught the snake backwords
			//every snake point becomes the snake point it was infront of it inline
			// hopfully looks like it actually moving
			for(int i = snakePoints.size() - 1; i >= 1; i--) {
				// update the snake points body
				snakePoints.set(i, snakePoints.get(i -1));
			}
			//update the head of the snake
			snakePoints.set(0, newStart);
			// if the snake needs to get longer add on the last point of the snake
			if(elongate) {
				snakePoints.add(last);
				elongate = false;
			}
		}
		
	}
	
	// method to check if the snake has runs into itself
	public boolean snakeCollision() {
		int x = this.getX();
		int y = this.getY();
		// loop through the elements start at index 1 soo we dont check the head to itself
		for(int i = 1; i< snakePoints.size(); i++) {
			// if they are equal a snake collision has accured and we return true 
			if(snakePoints.get(i).getX() == x && snakePoints.get(i).getY() == y) {
				return true;
			}
				
		}
		return false;
	}
	
	//access and mutators 
	// we will be controlling this from snake game
	public boolean isMoving() {
		return isMoving;
	}
	public void setIsMoving(boolean b) {
		isMoving = b;
	}
	

	// create accesors for x and y directions
	public int getXDir() {
		return xDir;
	}

	public int getYDir() {
		return yDir;

	}

	// setters
	public void setXDir(int x) {
		xDir = x;
	}

	public void setYDir(int y) {
		yDir = y;
	}

	// methods to get front and back of the snake x and y
	// front of snake is always 0 of our list
	// position of the head of the snake gets first element in the list and returns
	// it
	public int getX() {
		return snakePoints.get(0).getX();
	}

	public int getY() {
		return snakePoints.get(0).getY();
	}
	public void setElongate(boolean b) {
		elongate = b;
	}

}
