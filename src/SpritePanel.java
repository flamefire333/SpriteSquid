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
		gridsize = Math.min(this.getWidth() / main.sprite[0].length, this.getHeight() / main.sprite.length);
		for(int i = 0; i < main.sprite[0].length; i++) {
			for(int j = 0; j < main.sprite.length; j++) {
				g.setColor(main.palette.get(main.sprite[j][i]));
				g.fillRect(i * gridsize, j * gridsize, gridsize, gridsize);
			}
		}
		/*g.setColor(Color.lightGray);
		int width = main.sprite[0].length * gridsize;
		int height = main.sprite.length * gridsize;
		int horis = height / gridsize + 1;
		int verts = width / gridsize + 1;
		for(int i = 0; i < horis; i++) {
			g.fillRect(gridsize * i, 0, 1, height);
		}
		for(int i = 0; i < verts; i++) {
			g.fillRect(0, gridsize * i, width, 1);
		}*/
		System.out.println(":D");
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
			isMouseDown = true;
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
			if(isFillMode) {
				isFillMode = false;
				System.out.println("FILL");
				boolean[][] hasVisited = new boolean[main.sprite.length][main.sprite[0].length];
				Queue<Point> visited = new LinkedList<Point>();
				int sx = e.getX() / gridsize;
				int sy = e.getY() / gridsize;
				int target = main.sprite[sy][sx];
				visited.add(new Point(sx, sy));
				hasVisited[sy][sx] = true;
				while(!visited.isEmpty()) {
					Point curr = visited.remove();
					main.sprite[curr.y][curr.x] = main.selectedColor;
					for(int i = 0; i < SpritePanel.neighborDeltas.length; i++) {
						Point poss = new Point(curr.x + SpritePanel.neighborDeltas[i][0], curr.y + SpritePanel.neighborDeltas[i][1]);
						if(poss.x >= 0 && poss.x < main.sprite[0].length && poss.y >= 0 && poss.y < main.sprite.length && main.sprite[poss.y][poss.x] == target && !hasVisited[poss.y][poss.x]) {
							visited.add(poss);
							hasVisited[poss.y][poss.x] = true;
						}
					}
				}
			} else {
				main.sprite[e.getY() / gridsize][e.getX() / gridsize] = main.selectedColor;
			}
			
			((SpritePanel)e.getSource()).repaint();
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
