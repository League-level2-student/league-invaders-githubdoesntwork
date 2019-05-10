import javax.swing.*;

import java.awt.Dimension;
import java.util.*;
public class LeagueInvaders {
	GamePanel panel;
	public final int width=500, height=600;
	JFrame frame;
	public static void main(String[] args) {
LeagueInvaders game = new LeagueInvaders();
	}
	public LeagueInvaders() {
		panel = new GamePanel();
		frame = new JFrame();
		frame.addKeyListener(panel);
		setup();
	}
	public void setup() {
		frame.add(panel);
		frame.getContentPane().setPreferredSize(new Dimension(width, height));
        frame.pack();
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		panel.startGame();
	}
}
