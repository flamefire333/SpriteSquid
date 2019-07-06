package squid.widgets;

import squid.SpritePanel;
import squid.Starter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class Brush implements Squidget {
	
	SpritePanel parent;
	
	public Brush(SpritePanel parent_) {
		parent = parent_;
	}
	
	public void redraw(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(parent.mouseX, parent.mouseY, 2, 2);
	}
	
	public void mouseDragged(MouseEvent e) {
		parent.draw(e.getX(), e.getY(), false);
	}
	
	public void mousePressed(MouseEvent e) {
		Starter.startTransaction();
		parent.draw(e.getX(), e.getY(), true);
	}

	
}
