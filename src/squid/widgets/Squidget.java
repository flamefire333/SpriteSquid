package squid.widgets;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

// Squidget = Squid Widget
public interface Squidget {
	public void redraw(Graphics g);
	public void mouseDragged(MouseEvent e);
	public void mousePressed(MouseEvent e);
}
