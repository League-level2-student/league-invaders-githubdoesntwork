package Photophobia;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{
	int gravity=10;
	Rectangle playerRect;
	public Player(int a, int b, int c, int d) {
		super(a, b, c, d);
	}
	public void draw(Graphics g) {
		playerRect = new Rectangle(x,y,width,height);
		g.fillRect(x, y, width, height);
		
	}
	public void right(int speed) {
		x+=speed;
		//if(level==1) {
		if(x+25>520) {
			x=495;
		}
		//}
	}
	public void left(int speed){
		x-=speed;
		//if(level==1) {
		if(x<80) {
			x=80;
		}
		//}
	}
	public void up(int speed) {
		y-=speed;
		//if(level==1) {
		if(y<80) {
			y=80;
		}
		//}
	}
	public void down(int speed) {
		y+=speed;
		//if(level==1) {
		if(y+25>420) {
			y=395;
		}
		//}
	}
}
