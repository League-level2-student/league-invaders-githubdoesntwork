package Photophobia;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Lights extends GameObject{
	int firstX, firstY, secondX, secondY;
	String type;
	Rectangle horizRect, vertRect, movingRect;
	boolean right=true;
	public Lights(int a, int b, int c, int d, String t) {
		super(a, b, c, d);
		firstX = a;
		firstY=b;
		secondX=c;
		secondY=d;
		type = t; 
	}
public void draw(Graphics g) {
	if(type=="horizBeam") {
	g.setColor(Color.yellow);
	horizRect = new Rectangle(firstX+5, firstY+5, secondX-firstX+15, 5);
	g.fillRect(firstX+5, firstY+5, secondX-firstX+15, 5);
	g.setColor(Color.DARK_GRAY); 
	g.fillOval(firstX, firstY, 15, 15);
	g.fillOval(secondX+15, secondY, 15, 15);
	}else if(type=="vertBeam") {
		g.setColor(Color.yellow);
		vertRect = new Rectangle(firstX+5, firstY+5,5,secondY-firstY+15);
		g.fillRect(firstX+5, firstY+5, 5, secondY-firstY+15);
		g.setColor(Color.DARK_GRAY);
		g.fillOval(firstX, firstY, 15, 15);
		g.fillOval(secondX, secondY+15, 15, 15);
	}else if(type=="movingHoriz") {
		g.setColor(Color.yellow);
		movingRect = new Rectangle(firstX+3, firstY+5, 7, secondY-firstY+15);
		g.fillRect(firstX+3, firstY+5, 7, secondY-firstY+15);
		g.setColor(Color.DARK_GRAY);
		g.fillOval(firstX-1, firstY, 15, 15);
	}  
}
public void update() {
}
public String getLightType() {
	if(type=="horizBeam") {
		return "horiz";
		}if(type=="vertBeam") {
			return "vert";
		}if(type=="movingHoriz") {
			return "moving";
		}
		return "null";  
}
}
