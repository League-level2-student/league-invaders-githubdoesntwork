package Photophobia;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	final int menu = 0, game = 1, gameOver = 2, end = 3, instructions = 4;
	int level = 1;
	int current = menu;
	Font title;
	Font normal;
	Timer timer;
	GameObject object;
	Player player;
	Lights lights;
	PowerUp shield;
	Exit exit;
	boolean isPowerUp;
	int rand;
	int x;
//	int formerX, formerY;
	Wall wall;
	boolean right = true, up = false, down = false, left = false;
	boolean intersectsLight = false;

	public GamePanel() {
		title = new Font("", Font.BOLD, 48);
		normal = new Font("", Font.PLAIN, 20);
		timer = new Timer(1000 / 60, this);
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
		} else if (current == end) {
			drawEnd(g);
		} else if (current == instructions) {
			drawInstructions(g);
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
		} else if (current == end) {
			updateEnd();
		} else if (current == instructions) {
			updateInstructions();
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

	public void updateInstructions() {

	}

	public void drawMenu(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, 600, 500);
		g.setFont(title);
		g.setColor(Color.yellow);
		g.drawString("Photophobia", 150, 150);
		g.setFont(normal);
		g.drawRect(185, 375, 235, 40);
		g.drawRect(155, 200, 295, 40);
		g.drawString("Press Enter for instructions", 175, 225);
		g.drawString("Press space to start", 210, 400);
	}

	public void drawGame(Graphics g) {
		Color gameColor = new Color(35, 35, 35);
		g.setColor(gameColor);
		g.fillRect(0, 0, 600, 500);
		Color groundColor = new Color(99, 72, 72);
		g.setColor(groundColor);
		g.fillRect(0, 420, 600, 80);
		g.fillRect(0, 0, 600, 80);
		g.fillRect(0, 0, 80, 500);
		g.fillRect(520, 0, 80, 500);
		if (isPowerUp) {
			g.setColor(Color.red);
		} else {
			Color playerColor = new Color(153, 153, 153);
			g.setColor(playerColor);
		}
		player.draw(g);
		if (level == 1) {
			exit.draw(g);
			lights.draw(g);
			shield.draw(g);
			if (player.playerRect.intersects(lights.horizRect)) {
				intersectsLight = true;
			} else {
				intersectsLight = false;
			}
		} else if (level == 2) {
			exit.draw(g);
			lights.draw(g);
			shield.draw(g);
			wall.draw(g); //
			if (player.playerRect.intersects(lights.vertRect)) {
				intersectsLight = true;
			} else {
				intersectsLight = false;
			}
		} else if (level == 3) {
			exit.draw(g);
			if (right) {
				x += 2;
			} else {
				x -= 2;
			}
			if (x > 200) {
				right = false;
			} else if (x < 150) {
				right = true;
			}
			lights = new Lights(x, 200, x, 300, "movingHoriz");
			lights.draw(g);
			shield.draw(g);
			if (player.playerRect.intersects(lights.movingRect)) {
				intersectsLight = true;
			} else {
				intersectsLight = false;
			}

		}
		if (player.playerRect.intersects(shield.rect)) {
			isPowerUp = true;
			shield = new PowerUp(0, 0, 0, 0); ///////////
		} else if (intersectsLight) {
			level = 1;
			rand++;
			if (rand > 2) {
				rand = 0;
			}
			current = gameOver;
		}
		if (exit.exitRect.intersects(player.playerRect)) {
			level++;
			if (level > 3) {
				level = 1;
			}
			if (level == 1) {
				createLvl1();
			} else if (level == 2) {
				createLvl2();
			} else if (level == 3) {
				createLvl3();
				x = 150;
			}
			isPowerUp = false;
			current = game;
		}
		if (wall != null) {
			if (player.futureRect.intersects(wall.wallRect)) {
				player.x = player.formerX;
				player.y = player.formerY;
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
		g.drawString("Press space to restart", 180, 350);
		if (rand == 1) {
			g.drawString("What a tragedy", 215, 250);
		} else if (rand == 0) {
			g.drawString("Try harder than that", 190, 250);
		} else if (rand == 2) {
			g.drawString("Life is hard", 230, 250);
		}
	}

	public void drawEnd(Graphics g) {
		// placeholder for the end
	}

	public void drawInstructions(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, 600, 500);
		g.setColor(Color.yellow);
		g.setFont(title);
		g.drawString("Instructions", 140, 100);
		g.setFont(normal);
		g.drawString("'Photophobia' is the fear of light. Avoid the light to survive.", 5, 220);
		g.drawString("The red rectangle gives the player a sizable speed boost.", 10, 250);
		g.drawString("Near the end of the level, the black square is the exit,", 5, 280);
		g.drawString("which is triggered upon contact. Finally, the yellow represents", 5, 310);
		g.drawString("a light, which kills the player.The player can navigate ", 5, 340);
		g.drawString("with 'WASD' keys.", 5, 370);
		g.drawString("Press space to return to menu", 150, 400);

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (current == menu && key == KeyEvent.VK_SPACE) {
			level = 1;
			isPowerUp = false;
			player = new Player(288, 238, 10, 10);
			current = game;
			createLvl1();
			System.out.println(current);
		} else if (current == instructions && key == KeyEvent.VK_SPACE) {
			current = menu;
		} else if (current == menu && key == KeyEvent.VK_ENTER) {
			current = instructions;
		} else if (current == gameOver && key == KeyEvent.VK_SPACE) {
			current = menu;
			System.out.println(current);
		} else if (current == end) {
			current = menu;
			System.out.println(current);
		} else if (current == game) {
			//formerX=player.x;
			//formerY=player.y;
			System.out.println(player.x + ", " + player.y);
			if (key == KeyEvent.VK_W) {
				player.up = true;
			} else if (key == KeyEvent.VK_ENTER) {
				current++;
			} else if (key == KeyEvent.VK_S) {
				player.down = true;
			} else if (key == KeyEvent.VK_A) {
				player.left = true;
			} else if (key == KeyEvent.VK_D) {
				player.right = true;
			}
			if (isPowerUp) {
				player.speed = 4;
			} else {
				player.speed = 2;
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_W:
			player.up = false;
			break;
		case KeyEvent.VK_S:
			player.down = false;
			break;
		case KeyEvent.VK_A:
			player.left = false;
			break;
		case KeyEvent.VK_D:
			player.right = false;
			break;
		}
	}

	public void createLvl1() {
		exit = new Exit(350, 230, 20, 20);
		lights = new Lights(150, 300, 400, 300, "horizBeam");
		shield = new PowerUp(150, 150, 15, 15);
		wall = null;
	}

	public void createLvl2() {
		exit = new Exit(420, 120, 20, 20);
		lights = new Lights(150, 200, 150, 300, "vertBeam");
		shield = new PowerUp(150, 150, 15, 15);
		wall = new Wall(200, 200, 100, 100); 
	}

	public void createLvl3() {
		exit = new Exit(420, 380, 20, 20);
		x = 150;
		shield = new PowerUp(150, 150, 30, 30);
		wall = null;
	}
}