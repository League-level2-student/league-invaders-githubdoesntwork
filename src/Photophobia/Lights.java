package Photophobia;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Lights extends GameObject {
	int firstX, firstY, secondX, secondY, buttonX, buttonY;
	String type;
	Rectangle horizRect, vertRect, movingRect, buttonRectVert, buttonHoriz, buttonVert, buttonRectHoriz;
	boolean right = true;
	boolean beamOnVert = true, beamOnHoriz = true;
	int movingMax, movingMin;
	Color vertButtonColor = new Color(93, 255, 0);
	Color horizButtonColor = new Color(255, 182, 0);

	public Lights(int a, int b, int c, int d, String t, int y, int z, int f, int g) {
		super(a, b, c, d);
		firstX = a;
		firstY = b;
		secondX = c;
		secondY = d;
		type = t;
		buttonX = y;
		buttonY = z;
		movingMax = f;
		movingMin = g;
		x=g;
	}

	public void draw(Graphics g) {
		if (type == "horizBeam") {
			g.setColor(Color.yellow);
			horizRect = new Rectangle(firstX + 5, firstY + 5, secondX - firstX + 15, 5);
			g.fillRect(firstX + 5, firstY + 5, secondX - firstX + 15, 5);
			g.setColor(Color.DARK_GRAY);
			g.fillOval(firstX, firstY, 15, 15);
			g.fillOval(secondX + 15, secondY, 15, 15);
		} else if (type == "vertBeam") {
			g.setColor(Color.yellow);
			vertRect = new Rectangle(firstX + 5, firstY + 5, 5, secondY - firstY + 15);
			g.fillRect(firstX + 5, firstY + 5, 5, secondY - firstY + 15);
			g.setColor(Color.DARK_GRAY);
			g.fillOval(firstX, firstY, 15, 15);
			g.fillOval(secondX, secondY + 15, 15, 15);
		} else if (type == "movingHoriz") {
			g.setColor(Color.yellow);
			movingRect = new Rectangle(x + 3, firstY + 5, 7, secondY - firstY + 15);
			g.fillRect(x + 3, firstY + 5, 7, secondY - firstY + 15);
			g.setColor(Color.DARK_GRAY);
			g.fillOval(x - 1, firstY, 15, 15);
		} else if (type == "buttonBeamVert") {
			if (beamOnVert) {
				g.setColor(Color.yellow);
				buttonRectVert = new Rectangle(firstX + 5, firstY + 5, 5, secondY - firstY + 15);
				g.fillRect(firstX + 5, firstY + 5, 5, secondY - firstY + 15);
			} else {
				buttonRectVert = new Rectangle(0, 0, 0, 0);
			}

			g.setColor(Color.DARK_GRAY);
			g.fillOval(firstX, firstY, 15, 15);
			g.fillOval(secondX, secondY + 15, 15, 15);
			g.setColor(vertButtonColor);
			g.fillRect(buttonX, buttonY, 15, 15);
			buttonVert = new Rectangle(buttonX, buttonY, 15, 15);
		} else if (type == "buttonBeamHoriz") {
			if (beamOnHoriz) {
				g.setColor(Color.yellow);
				buttonRectHoriz = new Rectangle(firstX + 5, firstY + 5, secondX - firstX + 15, 5);
				g.fillRect(firstX + 5, firstY + 5, secondX - firstX + 15, 5);
			} else {
				buttonRectHoriz = new Rectangle(0, 0, 0, 0);
			}

			g.setColor(Color.DARK_GRAY);
			g.fillOval(firstX, firstY, 15, 15);
			g.fillOval(secondX + 15, secondY, 15, 15);
			g.setColor(horizButtonColor);
			g.fillRect(buttonX, buttonY, 15, 15);
			buttonHoriz = new Rectangle(buttonX, buttonY, 15, 15);
		}
	}

	public void update() {
		if (right) {
			x += 2;
		} else {
			x -= 2;
		}
		if (x > movingMax) {
			right = false;
		} else if (x < movingMin) {
			right = true;
		}
	}

	public String getLightType() {
		if (type == "horizBeam") {
			return "horiz";
		}
		if (type == "vertBeam") {
			return "vert";
		}
		if (type == "movingHoriz") {
			return "moving";
		}
		if (type == "buttonBeamVert") {
			return "vertButton";
		}
		if (type == "buttonBeamHoriz") {
			return "horizButton";
		}
		return "null";
	}
}
