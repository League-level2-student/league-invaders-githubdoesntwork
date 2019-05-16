package Photophobia;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Lights extends GameObject{
	int firstX, firstY, secondX, secondY;
	String type;
	Rectangle lightRect;
	int max=0;
	int howMuch=0;
	boolean right=true;
	public Lights(int a, int b, int c, int d, int e, String t) {
		super(a, b, c, d);
		firstX = a;
		firstY=b;
		secondX=c;
		secondY=d;
		type = t;
		max=e;
	}
public void update() {
	
}
public void draw(Graphics g) {
	if(type=="horizBeam") {
	g.setColor(Color.yellow);
	lightRect = new Rectangle(firstX+10, firstY+10, secondX-firstX+30, 10);
	g.fillRect(firstX+10, firstY+10, secondX-firstX+30, 10);
	g.setColor(Color.DARK_GRAY);
	g.fillOval(firstX, firstY, 30, 30);
	g.fillOval(secondX+30, secondY, 30, 30);
	}else if(type=="vertBeam") {
		g.setColor(Color.yellow);
		lightRect = new Rectangle(firstX+10, firstY+10,10,secondY-firstY+30);
		g.fillRect(firstX+10, firstY+10, 10, secondY-firstY+30);
		g.setColor(Color.DARK_GRAY);
		g.fillOval(firstX, firstY, 30, 30);
		g.fillOval(secondX, secondY+30, 30, 30);
	}else if(type=="movingHoriz") {
		g.setColor(Color.yellow);
		lightRect = new Rectangle(firstX+10, firstY+10, 15, secondY-firstY+30);
		g.fillRect(firstX+10+howMuch, firstY+10, 15, secondY-firstY+30);
		g.fillOval(firstX+howMuch, firstY, 30, 30);
		if(right) {
			howMuch++;
		}else {
			howMuch--;
		}
		if(howMuch>max) {
			right=false;
		}else if(howMuch<1) {
			right=true;
		}
	}
}
}
