package Photophobia;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{
	int speed;
	boolean up=false,down=false,left=false,right=false;
	Wall wall= new Wall(0,0,0,0, null);		//
	public Player(int a, int b, int c, int d) {
		super(a, b, c, d);
	}
	public void draw(Graphics g) {
		if(up) {
			wall.formerY=y;
			y-=speed;
			if(y<80) {
				y=80;
			}
		}if(down) {
			wall.formerY=y;
			y+=speed;
			if(y+25>420) {
				y=395;
			}
		}if(left) {
			wall.formerX=x;
			x-=speed;
			if(x<80) {
				x=80;
			}

		}if(right) {
			wall.formerX=x;
			x+=speed;
			if(x+25>520) {
				x=495;
			}
		}
		g.fillRect(x, y, width, height);
	}
	
}
