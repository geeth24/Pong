package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class PongPaddle extends Rectangle {

	private static final long serialVersionUID = 1L;
	int id;
	int yVelocity;
	int speed = 10;
	Image img = new ImageIcon("src/images/Pong Blue.png").getImage();
	Image img2 = new ImageIcon("src/images/Pong Red.png").getImage();

	PongPaddle(int x, int y, int PWIDTH, int PHEIGHT, int id) {

		super(x, y, PWIDTH, PHEIGHT);
		this.id = id;
	}

	public void keyPressed(KeyEvent e) {

		switch (id) {
			case 1:
				if (e.getKeyCode() == KeyEvent.VK_W) {
					setYDirection(-speed);
					move();
				} else if (e.getKeyCode() == KeyEvent.VK_S) {
					setYDirection(speed);
					move();
				}
				break;

			case 2:
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					setYDirection(-speed);
					move();
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					setYDirection(speed);
					move();
				}
				break;

		}

	}

	public void keyReleased(KeyEvent e) {
		switch (id) {
			case 1:
				if (e.getKeyCode() == KeyEvent.VK_W) {
					setYDirection(0);
					move();
				} else if (e.getKeyCode() == KeyEvent.VK_S) {
					setYDirection(0);
					move();
				}
				break;

			case 2:
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					setYDirection(0);
					move();
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					setYDirection(0);
					move();
				}
				break;

		}
	}

	public void move() {
		y += yVelocity;

	}

	private void setYDirection(int i) {
		yVelocity = i;

	}

	public void draw(Graphics g) {

		if (id == 1) {

			g.drawImage(img, x, y, width, height, null);

		} else if (id == 2) {

			g.drawImage(img2, x, y, width, height, null);

		}


	}
}
