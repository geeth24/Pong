package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PongFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private PongPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println("Game Running");
					PongFrame frame = new PongFrame();
					frame.setVisible(true);

					// JMenuBar menuBar = new JMenuBar();
					// JMenu file = new JMenu("Settings");
					// menuBar.add(file);
					// JMenuItem exit = new JMenuItem("Exit");

					// exit.addActionListener(new ActionListener() {
					// 	@Override
					// 	public void actionPerformed(ActionEvent e) {
					// 		System.exit(0);
					// 	}
					// });
					// file.add(exit);

					// frame.setJMenuBar(menuBar);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PongFrame() {
		this.setTitle("Pong");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon("src/images/No Back.png");
		setIconImage(icon.getImage());
		this.setLayout(null);

		setBackground(Color.black);

		contentPane = new PongPanel();
		setContentPane(contentPane);
		pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
