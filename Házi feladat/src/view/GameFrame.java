package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public GameFrame(JPanel panel) {

		add(panel, BorderLayout.CENTER);

		setTitle("DU_KGB");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setResizable(false);
		pack();
		setVisible(true);
	}
}
