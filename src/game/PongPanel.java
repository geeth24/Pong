package game;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class PongPanel extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */

	static final int BALLDIAMETER = 20;
	static final int PWIDTH = 30;
	static final int PHEIGHT = 100;
	final static int PAUSE = 1;
	final static int RUNNING = 2;

	Thread gameThread;
	Image image;
	Graphics graphics;

	PongPaddle player1;
	PongPaddle player2;
	PongBall ball;
	PongScore score;

	final static int WIDTH = 1000;
	static final int HEIGHT = (int) (WIDTH * (5.0 / 9.0));
	private Dimension dimension = new Dimension(WIDTH, HEIGHT);
	static int state;

	public PongPanel() {

		this.setPreferredSize(dimension);
		this.setLayout(null);
		newPaddles();
		newBall();
		score = new PongScore(WIDTH, HEIGHT);
		this.setFocusable(true);
		addKeyListener(new AL());
		gameThread = new Thread(this);
		gameThread.start();
		state = RUNNING;

	}

	private void newBall() {
		ball = new PongBall(WIDTH / 2 - BALLDIAMETER / 2, HEIGHT / 2 - BALLDIAMETER / 2, BALLDIAMETER, BALLDIAMETER);

	}

	private void newPaddles() {

		player1 = new PongPaddle(3, (HEIGHT / 2) - (PHEIGHT / 2), PWIDTH, PHEIGHT, 1);
		player2 = new PongPaddle(WIDTH - PWIDTH - 3, (HEIGHT / 2) - (PHEIGHT / 2), PWIDTH, PHEIGHT, 2);

	}

	public void paint(Graphics g) {

		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		graphics.setColor(Color.blue);
		draw(graphics);
		g.drawImage(image, 0, 0, this);

	}

	public void draw(Graphics g) {

		player1.draw(g);
		player2.draw(g);
		ball.draw(g);
		score.draw(g);
	}

	private void checkCollision() {
		if (player1.y < 0) {
			player1.y = 0;
		}
		if (player1.y > HEIGHT - PHEIGHT) {

			player1.y = HEIGHT - PHEIGHT;
		}

		if (player2.y < 0) {
			player2.y = 0;
		}
		if (player2.y > HEIGHT - PHEIGHT) {

			player2.y = HEIGHT - PHEIGHT;
		}

		if (ball.y <= 0 || ball.y > HEIGHT - BALLDIAMETER) {

			ball.setYDirection(-ball.yVelocity);
		}

		if (ball.intersects(player1) || ball.intersects(player2)) {
			ball.setXDirection(-ball.xVelocity);
		}

		if (ball.x <= 0) {
			score.score2++;
			newBall();
		}

		if (ball.x >= WIDTH) {
			score.score1++;
			newBall();
		}

	}

	private void move() {
		player1.move();
		player2.move();
		ball.move();

	}

	public enum State {
		RUNNING,
		PAUSE
	}

	@Override
	public void run() {

		long lastTime = System.nanoTime();
		// int fps = 60;
		double ns = 1000000000 / 60;
		double delta = 0;

		switch (state) {
			case PAUSE:
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			case RUNNING:
				while (true) {
					long now = System.nanoTime();
					delta += (now - lastTime) / ns;
					lastTime = now;
					if (delta >= 1) {
						move();
						checkCollision();
						repaint();
						delta--;
					}
				}
		}

	}

	public class AL extends KeyAdapter {

		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyReleased(KeyEvent e) {

			player1.keyReleased(e);
			player2.keyReleased(e);

		}

		@Override
		public void keyPressed(KeyEvent e) {

			player1.keyPressed(e);
			player2.keyPressed(e);

		}
	}

}
