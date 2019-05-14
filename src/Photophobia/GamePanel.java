package Photophobia;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	final int menu=0, game=1, gameOver=2, end=3;
	int level=1;
	int current=menu;
	Font title;
	Font normal;
	Timer timer;
	GameObject object;
	Player player;
	Lights lights;
	public GamePanel() {
		title = new Font("Monospace", Font.BOLD, 48);
		normal = new Font("Somehting", Font.PLAIN, 20);
		timer = new Timer(1000/1000, this);
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
		}else if(current == end) {
			drawEnd(g);
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
		if(level==1) {
		Color gameColor = new Color(35,35,35);
		g.setColor(gameColor);
		g.fillRect(0, 0, 600, 500);
		Color groundColor = new Color(99, 72, 72);
		g.setColor(groundColor);
		g.fillRect(0, 420, 600, 80);
		g.fillRect(0, 0, 600, 80);
		g.fillRect(0,0,80,600);
		g.fillRect(520, 0, 80, 600);
		player.draw(g);
		g.setFont(normal);
		g.setColor(groundColor);
		g.drawString("This is the exit", 300, 200);
		g.setColor(Color.black);
		Rectangle exit = new Rectangle(350, 230, 30, 30);
		g.fillRect(350, 230, 30, 30);
		lights = new Lights(150,350, 400,350,"horizBeam");
		lights.draw(g);
		if(exit.intersects(player.playerRect)) {
			level++;
			JOptionPane.showMessageDialog(null, "Completed Level 1!");
		}else if(player.playerRect.intersects(lights.lightRect)) {
			level=1;
			current=gameOver;
		}
		}
	}
	public void drawGameOver(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, 600, 500);
		g.setColor(Color.yellow);
		g.setFont(title);
		g.drawString("Game Over", 150, 150);
		g.setFont(normal);
		g.drawString("Press any key to restart", 170, 350);
	}
	public void drawEnd(Graphics g) {
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(current==menu) {
			level=1;
			player = new Player(288, 238, 25, 25);
			current=game;
			System.out.println(current);
		}else if(current==gameOver) {
			current=menu;
			System.out.println(current);
		}else if(current==end) {
			current=menu;
			System.out.println(current);
		}else if(current==game) {
			if(key==KeyEvent.VK_W) {
				player.up();
			}else if(key==KeyEvent.VK_D) {
				player.right();
			}else if(key==KeyEvent.VK_A) {
				player.left();
			}else if(key==KeyEvent.VK_S) {
				player.down();
			}else if(key==KeyEvent.VK_ENTER) {
				current++;
			}
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
