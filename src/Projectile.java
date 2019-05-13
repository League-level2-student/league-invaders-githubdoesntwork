import java.awt.Graphics;

public class Projectile extends GameObject{

	public Projectile(int a, int b, int c, int d) {
		super(a, b, c, d);
		speed=10;
	}
public void draw(Graphics g) {
	
}
public void update() {
	y-=speed;
}
}
