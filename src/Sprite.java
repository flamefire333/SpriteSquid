import java.awt.Point;

public class Sprite {
	private int[][] data;
	
	public Sprite(int width, int height) {
		data = new int[height][width];
	}
	
	public void setPixel(int x, int y, int color) {
		if(y >= 0 && y < data.length && x >= 0 && x < data[0].length) {
			data[y][x] = color;
		} else {
			System.err.println("Tried drawing to " + x + " " + y);
		}
	}
	
	public void drawLine(Point p1, Point p2, int color) {
		int dx = p2.x - p1.x;
		int dy = p2.y - p1.y;
		int dx_abs = Math.abs(dx);
		int dy_abs = Math.abs(dy);
		int dx_unit = dx == 0 ? 0 : dx / dx_abs;
		int dy_unit = dy == 0 ? 0 : dy / dy_abs;
		if(dx == 0 && dy == 0) {
			setPixel(p1.x, p1.y, color);
		} else if(Math.abs(dx) > Math.abs(dy)) {
			for(int i = 0; i <= dx_abs; i++) {
				setPixel(p1.x + i * dx_unit, p1.y + (i * dy) / dx_abs, color);
			}
		} else {
			for(int i = 0; i <= dy_abs; i++) {
				setPixel(p1.x + (i * dx) / dy_abs, p1.y + i * dy_unit, color);
			}
		}
	}
	
	public int getPixel(int x, int y) {
		return data[y][x];
	}
	
	public int getWidth() {
		return data[0].length;
	}
	
	public int getHeight() {
		return data.length;
	}
}
