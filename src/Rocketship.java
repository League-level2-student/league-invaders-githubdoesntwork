import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Rocketship extends GameObject implements KeyListener{
	int speed;

	public Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed=5;
	}

	public void update() {

	}

	public void draw(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, width, height);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_UP:
			y--;
			break;
		case KeyEvent.VK_DOWN:
			y++;
			break;
		case KeyEvent.VK_LEFT:
			x--;
			break;
		case KeyEvent.VK_RIGHT:
			x++;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {	
	}
}
