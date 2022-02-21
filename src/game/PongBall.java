package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class PongBall extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Random random = new Random();
	int xVelocity;
	int yVelocity;
	int speed = 5;
	Image img = new ImageIcon("src/images/Pong Ball.png").getImage();

	public PongBall(int x, int y, int width, int height) {

		super(x, y, width, height);
		int x1 = random.nextInt(2);
		if (x1 == 0) {
			setXDirection(-speed);
		} else {
			setXDirection(speed);
		}

		int y1 = random.nextInt(2);
		if (y1 == 0) {
			setYDirection(-speed);
		} else {
			setYDirection(speed);
		}

	}

	public void setXDirection(int i) {

		xVelocity = i;

	}

	public void setYDirection(int i) {

		yVelocity = i;

	}

	public void move() {
		x += xVelocity;
		y += yVelocity;
	}

	public void draw(Graphics g) {
		g.drawImage(img, x, y, width, height, null);

	}

}
