
public class Sprite {
	private int[][] data;
	
	public Sprite(int width, int height) {
		data = new int[height][width];
	}
	
	public void setPixel(int x, int y, int color) {
		data[y][x] = color;
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
