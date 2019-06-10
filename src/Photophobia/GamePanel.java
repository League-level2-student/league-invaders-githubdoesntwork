package Photophobia;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements ActionListener, KeyListener {
	final int menu = 0, game = 1, gameOver = 2, end = 3, instructions = 4, levelCompleted = 5;
	int level = 1;
	int completedX = -50;
	int completedPlayerX = 0;
	int current = menu;
	Font title;
	Font normal;
	Timer timer;
	GameObject object;
	Player player;
	ArrayList<Lights> lights = new ArrayList<Lights>();
	PowerUp shield;
	Exit exit;
	boolean isPowerUp;
	int rand;
	int x;
	// final String wallURL =
	// "https://freesound.org/people/simon.rue/sounds/49963/";
//	int formerX, formerY;
	ArrayList<Wall> walls = new ArrayList<Wall>();
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
		} else if (current == levelCompleted) {
			drawLevelCompleted(g);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
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
			for (Lights light : lights) {
				light.draw(g);
			}
			shield.draw(g);
			if (walls != null) {
				for (Wall wall : walls) {
					wall.draw(g);
				}
			}
		} else if (level == 2) {
			exit.draw(g);
			for (Lights light : lights) {
				light.draw(g);
			}
			shield.draw(g);
			for (Wall wall : walls) {
				wall.draw(g);
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
			lights.removeAll(lights);
			lights.add(new Lights(x, 200, x, 300, "movingHoriz", 0, 0));
			for (Lights light : lights) {
				light.draw(g);
				shield.draw(g);
			}

		} else if (level == 4) {
			for (Lights light : lights) {
				exit.draw(g);
				light.draw(g);
				shield.draw(g);
			}
		} else if (level == 5) {
			for (Lights light : lights) {
				exit.draw(g);
				light.draw(g);
				shield.draw(g);
			}

		}
		for (Lights light : lights) {
			if (light.getLightType() == "moving") {
				if (player.playerRect.intersects(light.movingRect)) {
					intersectsLight = true;
				} else {
					intersectsLight = false;
				}
			} else if (light.getLightType() == "vert") {
				if (player.playerRect.intersects(light.vertRect)) {
					intersectsLight = true;
				} else {
					intersectsLight = false;
				}
			} else if (light.getLightType() == "horiz") {
				if (player.playerRect.intersects(light.horizRect)) {
					intersectsLight = true;
				} else {
					intersectsLight = false;
				}
			} else if (light.getLightType() == "vertButton") {
				if (player.playerRect.intersects(light.buttonRectVert)) {
					intersectsLight = true;
				} else {
					intersectsLight = false;
				}
			} else if (light.getLightType() == "horizButton") {
				if (player.playerRect.intersects(light.buttonRectHoriz)) {
					intersectsLight = true;
				} else {
					intersectsLight = false;
				}
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
			completedX = -50;
			completedPlayerX = -440;
			current = levelCompleted; /////////////////// LEVEL COMPLETED///////////
			int delay = 3000; // milliseconds
			ActionListener taskPerformer = new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					level++;
					if (level > 5) {
						level = 1;
					}
					if (level == 1) {
						createLvl1();
					} else if (level == 2) {
						createLvl2();
					} else if (level == 3) {
						createLvl3();
						x = 150;
					} else if (level == 4) {
						createLvl4();
					} else if (level == 5) {
						createLvl5();
					}
					isPowerUp = false;
					current = game;
				}
			};
			Timer something = new Timer(delay, taskPerformer);
			something.start();
			something.setRepeats(false);

		}
		if (walls != null) {
			for (Wall wall : walls) {
				if (wall != null) {
					if (player.futureRect.intersects(wall.wallRect)) {
						player.x = player.formerX;
						player.y = player.formerY;
					}
				}
			}
		}
		for (Lights light : lights) {
			if (light.getLightType() == "vertButton") {
				if (player.playerRect.intersects(light.buttonVert)) {
					light.beamOnVert = false;
				}
			} else if (light.getLightType() == "horizButton") {
				if (player.playerRect.intersects(light.buttonHoriz)) {
					light.beamOnHoriz = false;
				}
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
			g.drawString("Dying is not the objective", 165, 250);
		}
	}

	public void drawEnd(Graphics g) {
		// placeholder for the end
	}

	public void drawLevelCompleted(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, 600, 500);
		g.setColor(Color.yellow);
		g.setFont(title);
		g.drawString("Level", 220, 100);
		g.drawString("Completed", completedX, 200);
		g.setColor(new Color(12, 12, 12));
		g.fillRect(completedPlayerX, 330, 70, 70);
		g.setColor(Color.gray);
		g.fillRect(completedPlayerX + 10, 340, 50, 50);
		g.setColor(Color.black);
		g.fillRect(completedPlayerX + 20, 350, 10, 10);
		g.fillRect(completedPlayerX + 50, 350, 10, 10);
		g.fillRect(completedPlayerX + 20, 370, 40, 10);
		completedX += 3;
		completedPlayerX += 6;
		if (completedX > 150) {
			completedX = 150;
		}
		if (walls != null) {
			walls.removeAll(walls);
		}
		lights.removeAll(lights);
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
		g.drawString("which is triggered upon contact. The yellow represents", 5, 310);
		g.drawString("a light, which kills the player. Lastly, the orange and green ", 5, 340);
		g.drawString("buttons deactivate horizonal and vertical lights, respectively.", 5, 370);
		g.drawString("The player can navigate with 'WASD' keys.", 5, 400);
		g.drawString("Press space to return to menu", 150, 430);

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
			if (walls != null) {
				walls.removeAll(walls);
			}
			lights.removeAll(lights);
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
			// formerX=player.x;
			// formerY=player.y;
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
		exit = new Exit(470, 230, 20, 20);
		lights.add(new Lights(400, 150, 400, 300, "vertBeam", 0, 0));
		shield = new PowerUp(0, 0, 0, 0);
		walls.add(new Wall(0, 0, 200, 200));
	}

	public void createLvl2() {
		exit = new Exit(420, 120, 20, 20);
		lights.add(new Lights(150, 200, 150, 300, "vertBeam", 0, 0));
		shield = new PowerUp(150, 150, 15, 15);
		walls.add(new Wall(200, 200, 100, 100));
	}

	public void createLvl3() {
		exit = new Exit(420, 380, 20, 20);
		x = 150;
		shield = new PowerUp(150, 150, 15, 15);
		walls.add(new Wall(0,0,0,0));
	}

	public void createLvl4() {
		exit = new Exit(470, 230, 20, 20);
		lights.add(new Lights(400, 150, 400, 300, "buttonBeamVert", 200, 100));
		shield = new PowerUp(100, 200, 15, 15);
		walls.add(new Wall(0,0,0,0));
	}

	public void createLvl5() {
		exit = new Exit(420, 120, 20, 20);
		lights.add(new Lights(100, 300, 300, 300, "buttonBeamHoriz", 300, 100));
		shield = new PowerUp(150, 150, 15, 15);
		walls.add(new Wall(0,0,0,0));
	}

}