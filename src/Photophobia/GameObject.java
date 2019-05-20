package Photophobia;

import java.awt.Rectangle;

public class GameObject {
int x,y,width,height,speed;
Rectangle rect;
public GameObject(int a,int b, int c, int d) {
	x=a;
	y=b;
	width=c;
	height=d;
	rect = new Rectangle(x,y,width,height);
}
public void update() {
	
}
}
