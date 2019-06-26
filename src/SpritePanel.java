import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import java.util.LinkedList;
import java.util.Queue;
import java.awt.Point;

public class SpritePanel extends JPanel {

	/**
	 * 
	 */
	
	public static int[][] neighborDeltas = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	
	public SpritePanel() {
		super();
		this.addMouseListener(new SpritePanelMouseListener());
		this.addMouseMotionListener(new SpritePanelMouseMotionListener());
		this.addKeyListener(new SpritePanelKeyListener());
		setFocusable(true);
	}
	
	int gridsize = 10;
	boolean isFillMode = false;
	
	private static final long serialVersionUID = -1570681401638494514L;
	public void paintComponent(Graphics g) {
		gridsize = Math.min(this.getWidth() / Starter.sprite.getWidth(), this.getHeight() / Starter.sprite.getHeight());
		for(int i = 0; i < Starter.sprite.getWidth(); i++) {
			for(int j = 0; j < Starter.sprite.getHeight(); j++) {
				g.setColor(Starter.palette.get(Starter.sprite.getPixel(i, j)));
				g.fillRect(i * gridsize, j * gridsize, gridsize, gridsize);
			}
		}
	}
	
	int lastx = 0;
	int lasty = 0;
	
	public void draw(int x, int y, boolean isStarting) {
		
		if(isFillMode) {
			isFillMode = false;
			System.out.println("FILL");
			boolean[][] hasVisited = new boolean[Starter.sprite.getHeight()][Starter.sprite.getWidth()];
			Queue<Point> visited = new LinkedList<Point>();
			int sx = x / gridsize;
			int sy = y / gridsize;
			int target = Starter.sprite.getPixel(sx, sy);
			visited.add(new Point(sx, sy));
			hasVisited[sy][sx] = true;
			while(!visited.isEmpty()) {
				Point curr = visited.remove();
				Starter.sprite.setPixel(curr.x, curr.y, Starter.selectedColor);
				for(int i = 0; i < SpritePanel.neighborDeltas.length; i++) {
					Point poss = new Point(curr.x + SpritePanel.neighborDeltas[i][0], curr.y + SpritePanel.neighborDeltas[i][1]);
					if(poss.x >= 0 && poss.x < Starter.sprite.getWidth() && poss.y >= 0 && poss.y < Starter.sprite.getHeight() && Starter.sprite.getPixel(poss.x, poss.y) == target && !hasVisited[poss.y][poss.x]) {
						visited.add(poss);
						hasVisited[poss.y][poss.x] = true;
					}
				}
			}
		} else {
			int sx = x / gridsize;
			int sy = y / gridsize;
			if(isStarting) {
				lastx = sx;
				lasty = sy;
			}
			Starter.sprite.drawLine(new Point(lastx, lasty), new Point(sx, sy), Starter.selectedColor);
			lastx = sx;
			lasty = sy;
		}
		
		Starter.spritePanel.repaint();
	}
	
	boolean isMouseDown = false;
	
	class SpritePanelMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			isMouseDown = false;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			draw(e.getX(), e.getY(), true);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			isMouseDown = false;
		}
	}
	
	class SpritePanelMouseMotionListener implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			draw(e.getX(), e.getY(), false);
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	class SpritePanelKeyListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			System.out.println("SEE KEY: " + e.getKeyChar());
			if(e.getKeyChar() == 'f') {
				System.out.println("TOGGLE");
				isFillMode = !isFillMode;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
		}
		
	}
}
