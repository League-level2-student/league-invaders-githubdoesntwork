import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	Timer timer;
	Font titleFont;
	Rocketship rocket;
	GameObject object;
	final int menu=0, game=1, end=2;
	int current=menu;

	public GamePanel() {
		rocket=new Rocketship(225,500,50,50);
		titleFont=new Font("Arial", Font.PLAIN, 48);
		object = new GameObject(0,0,0,0);
		timer = new Timer(1000 / 60, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		object.update();
		repaint();
	}

	public void startGame() {
		timer.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		object.draw(g);
		if(current==menu) {
			drawMenu(g);
		}else if(current==game) {
			updateGame(g);
		}else {
			drawEnd(g);
		}
	}
	public void drawMenu(Graphics g) {
		g.setFont(titleFont);
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 500, 600); 
		g.setColor(Color.yellow);
		g.drawString("LEEG INVADRZ©", 65, 200);
	}
	public void updateGame(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, 500, 600);
		rocket.draw(g);
	}
	public void drawEnd(Graphics g) {
		g.setFont(titleFont);
		g.setColor(Color.red);
		g.fillRect(0, 0, 500, 600);
		g.setColor(Color.black);
		g.drawString("Game Over", 130, 200);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("Key Typed");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		if(key==KeyEvent.VK_ENTER) {
			if(current==menu) {
				current=game;
			}else if(current==game){
				current=end;
			}else {
				current=menu;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("Key Released");
	}
}
