import java.awt.Color;
import java.util.ArrayList;

import javax.swing.*;

public class Starter {

	
	public static Sprite sprite;
	
	public static ArrayList<Color> palette;
	
	public static int selectedColor = 1;
	
	public static SpritePanel spritePanel;
	
	public static void main(String[] args) {
		sprite = new Sprite(256, 256);
		palette = new ArrayList<Color>();
		palette.add(Color.white);
		palette.add(Color.black);
		palette.add(Color.blue);
		JFrame mainWindow = new JFrame();
		spritePanel = new SpritePanel();
		spritePanel.setSize(500, 500);
		spritePanel.setBackground(Color.BLUE);
		mainWindow.add(spritePanel);
		mainWindow.setSize(500, 500);
		mainWindow.setVisible(true);
	}

}
