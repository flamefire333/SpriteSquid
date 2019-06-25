import java.awt.Color;
import java.util.ArrayList;

import javax.swing.*;

public class main {

	public static int[][] sprite;
	
	public static ArrayList<Color> palette;
	
	public static int selectedColor = 1;
	
	public static void main(String[] args) {
		sprite = new int[128][256];
		palette = new ArrayList<Color>();
		palette.add(Color.white);
		palette.add(Color.black);
		palette.add(Color.blue);
		JFrame mainWindow = new JFrame();
		SpritePanel mainPanel = new SpritePanel();
		mainPanel.setSize(200, 200);
		mainPanel.setBackground(Color.BLUE);
		mainWindow.add(mainPanel);
		mainWindow.setSize(200, 200);
		mainWindow.setVisible(true);
	}

}
