package Photophobia;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall extends GameObject{
	Color groundColor = new Color(99, 72, 72);
	int formerX, formerY;
	Player player;
	public Wall(int a, int b, int c, int d, Player p) {
		super(a, b, c, d);
		player = p;
	}
	public void draw(Graphics g) {
		g.setColor(groundColor);
		g.fillRect(x, y, width, height);
		player.draw(g);
		if(rect.intersects(player.rect)) {
			player.x=formerX;
			player.y=formerY;
		}
	}

}
