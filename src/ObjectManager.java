import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
Rocketship rocket;
Random r = new Random();
ArrayList<Alien> aliens=new ArrayList<Alien>(); //create projetile
public ObjectManager(Rocketship ship){
	rocket = ship;
}
public void addAlien() {
	aliens.add(new Alien(r.nextInt(500),0,50,50));
}
public void update() { //test Y
	for (int i = 0; i < aliens.size(); i++) {
		aliens.get(i).update();
	}
}
public void draw(Graphics g) {
	
}
}
