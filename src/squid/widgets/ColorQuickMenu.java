package squid.widgets;

import squid.SpritePanel;
import squid.Starter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;

public class ColorQuickMenu implements Squidget {
	
	SpritePanel parent;
	
	int x, y;
	int selected = -1;
	public ColorQuickMenu(SpritePanel parent_) {
		parent = parent_;
		x = parent.mouseX;
		y = parent.mouseY;
	}
	
	public void mouseDragged(MouseEvent e) {

	}
	
	public void mousePressed(MouseEvent e) {
		if(selected != -1) {
			Starter.selectedColor = selected;
			parent.currentWidget = new Brush(parent);
		}
	}
	
	public void redraw(Graphics g) {
		int sides = Starter.palette.size();
		int inradius = 70;
		int outradius = 80;
		int circrad = inradius / 4;
		double mouseDist2 = Math.pow(parent.mouseX - x, 2) + Math.pow(parent.mouseY - y, 2);
		double mouseAng = 0;
		if(mouseDist2 >= circrad * circrad) {
			mouseAng = Math.atan2(parent.mouseY - y, parent.mouseX - x);
		}
		double arc = (2 * Math.PI) / sides;
		selected = -1;
		for(int i = 0; i < sides; i++) {
			g.setColor(Starter.palette.get(i));
			Polygon p = new Polygon();
			double sub = arc / 8;
			double ang1 = arc * i - Math.PI;
			double ang2 = ang1 + arc;
			int inradiusgoto = inradius;
			if(mouseAng > ang1 && mouseAng < ang2) {
				selected = i;
				sub = 0;
				inradiusgoto -= 5;
			}
			double deg1 = ang1 + sub;
			double deg2 = ang2 - 2 * sub;
			p.addPoint((int)(Math.cos(deg1) * inradiusgoto), (int)(Math.sin(deg1) * inradiusgoto));
			p.addPoint((int)(Math.cos(deg1) * outradius), (int)(Math.sin(deg1) * outradius));
			p.addPoint((int)(Math.cos(deg2) * outradius), (int)(Math.sin(deg2) * outradius));
			p.addPoint((int)(Math.cos(deg2) * inradiusgoto), (int)(Math.sin(deg2) * inradiusgoto));
			p.translate(x, y);
			g.fillPolygon(p);
			g.setColor(Color.BLACK);
			g.drawPolygon(p);
		}
		if(selected != -1) {
			g.setColor(Starter.palette.get(selected));
			g.fillOval(x - circrad, y - circrad, circrad * 2, circrad * 2);
			g.setColor(Color.BLACK);
			g.drawOval(x - circrad, y - circrad, circrad * 2, circrad * 2);
		}
	}

}
