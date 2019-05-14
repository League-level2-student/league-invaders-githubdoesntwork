package Photophobia;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	final int menu=0, game=1, gameOver=2, end=3;
	int current=menu;
	Font title;
	Font normal;
	Timer timer;
	public GamePanel() {
		title = new Font("Monospace", Font.BOLD, 48);
		normal = new Font("Somehting", Font.PLAIN, 20);
		timer = new Timer(1000/10, this);
		timer.start();
	}
	@Override
	public void paintComponent(Graphics g) {
		if (current == menu) {
			drawMenu(g);
		} else if (current == game) {
			drawGame(g);
		} else if (current == gameOver) {
			drawGameOver(g);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (current == menu) {
			updateMenu();
		} else if (current == game) {
			updateGame();
		} else if (current == gameOver) {
			updateGameOver();
		}else if(current == end){
			updateEnd();
		}
		repaint();
	}
	public void updateGame() {
		
	}
	public void updateMenu() {
		
	}
	public void updateGameOver() {
		
	}
	public void updateEnd() {
		
	}
	public void drawMenu(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, 600, 500);
		g.setFont(title);
		g.setColor(Color.yellow);
		g.drawString("Photophobia", 150, 150);
		g.setFont(normal);
		g.drawRect(185, 375, 235, 40);
		g.drawString("Press any key to start", 200, 400);
	}
	public void drawGame(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, 600, 500);
	}
	public void drawGameOver(Graphics g) {
		
	}
	public void drawEnd(Graphics g) {
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(current==menu) {
			current=game;
			System.out.println(current);
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
