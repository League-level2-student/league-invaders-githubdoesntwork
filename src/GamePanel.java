import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	Timer timer;
	final int menu = 0, game = 1, end = 2;
	int current = menu;
	Font titleFont;
	Font otherFont;
	Rocketship rocket = new Rocketship(225,500,50,50);

	public GamePanel() {
		titleFont = new Font("Arial", Font.PLAIN, 48);
		otherFont = new Font("Arial", Font.PLAIN, 28);
		timer = new Timer(1000/30, this);
		timer.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		if (current == menu) {
			drawMenu(g);
		} else if (current == game) {
			drawGame(g);
		} else if (current == end) {
			drawEnd(g);
		}
	}

	public void updateGame() {

	}

	public void updateMenu() {

	}

	public void updateEnd() {

	}

	public void drawMenu(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, 0, 500, 600);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS", 30, 150);
		g.setFont(otherFont);
		g.drawString("Press ENTER to start", 110, 300);
		g.drawString("Press SPACE for instructions", 75, 430);
	}

	public void drawGame(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, 500, 600);
rocket.draw(g);
	}

	public void drawEnd(Graphics g) {
g.setColor(Color.RED);
g.fillRect(0, 0, 500, 600);
g.setFont(titleFont);
g.setColor(Color.yellow);
g.drawString("GAME OVER", 100, 150);
g.setFont(otherFont);
g.drawString("You killed enemies", 150, 300);
g.drawString("Press ENTER to try again", 100, 430);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (current == menu) {
			updateMenu();
		} else if (current == game) {
			updateGame();
		} else if (current == end) {
			updateEnd();
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (current == end) {
		        current = menu;
		    } else {
		        current++;
		    }
		}   
		if(current==game) {
			if (e.getKeyCode()==KeyEvent.VK_UP) {
			    rocket.up();
			}else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
				rocket.right();
			}else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
				rocket.left();
			}else if(e.getKeyCode()==KeyEvent.VK_DOWN){
				rocket.down();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
