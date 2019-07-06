package squid;
import java.awt.Color;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.*;

import squid.actions.MarkerAction;

public class Starter {

	
	public static Sprite sprite;
	
	public static ArrayList<Color> palette;
	
	public static int selectedColor = 1;
	
	public static SpritePanel spritePanel;
	
	private static Stack<squid.actions.Action> undoStack;
	
	public static void doAction(squid.actions.Action a) {
		a.apply();
		System.out.println(a);
		undoStack.push(a);
	}
	
	public static void undo() {
		while(!undoStack.empty()) {
			squid.actions.Action a = undoStack.pop();
			if(a instanceof MarkerAction) {
				break;
			}
			a.unapply();
		}
	}
	
	public static void startTransaction() {
		undoStack.push(new MarkerAction());
	}
	
	public static void main(String[] args) {
		sprite = new Sprite(256, 256);
		palette = new ArrayList<Color>();
		palette.add(Color.white);
		palette.add(Color.black);
		palette.add(Color.red);
		palette.add(Color.green);
		palette.add(Color.blue);
		palette.add(Color.cyan);
		palette.add(Color.darkGray);
		palette.add(Color.gray);
		palette.add(Color.LIGHT_GRAY);
		palette.add(Color.magenta);
		palette.add(Color.orange);
		palette.add(Color.pink);
		JFrame mainWindow = new JFrame();
		spritePanel = new SpritePanel();
		spritePanel.setSize(500, 500);
		spritePanel.setBackground(Color.BLUE);
		mainWindow.add(spritePanel);
		mainWindow.setSize(500, 500);
		mainWindow.setVisible(true);
		undoStack = new Stack<squid.actions.Action>();
	}
	
	public static void writeInt(FileWriter fw, int curr) throws IOException{
		for(int i = 0; i < 4; i++) {
			fw.write((curr >> (i * 8)) & 0xFF);
		}
	}
	
	public static int readInt(FileReader fr) throws IOException{
		int curr = 0;
		for(int i = 0; i < 4; i++) {
			curr |= (fr.read() & 0xFF) << (i * 8);
		}
		return curr;
	}
	
	public static void saveToFile(FileWriter fw) throws IOException {
		//Write Palette Size
		writeInt(fw, palette.size());
		//Write colors in palette
		for(int i = 0; i < palette.size(); i++) {
			Color curr = palette.get(i);
			writeInt(fw, curr.getRGB());
		}
		//Write image dims
		writeInt(fw, sprite.getWidth());
		writeInt(fw, sprite.getHeight());
		for(int x = 0; x < sprite.getWidth(); x++) {
			for(int y = 0; y < sprite.getHeight(); y++) {
				writeInt(fw, sprite.getPixel(x, y));
			}
		}
	}
	
	public static void loadFromFile(FileReader fr) throws IOException {
		int psize = readInt(fr);
		System.out.println("PSIZE: " + psize);
		palette.clear();
		for(int i = 0; i < psize; i++) {
			palette.add(new Color(readInt(fr)));
		}
		int imwidth = readInt(fr);
		int imheight = readInt(fr);
		System.out.println("W1: " + imwidth + " H1: " + imheight);
		sprite = new Sprite(imwidth, imheight);
		System.out.println("W: " + sprite.getWidth() + " " + " H: " + sprite.getHeight());
		for(int x = 0; x < sprite.getWidth(); x++) {
			for(int y = 0; y < sprite.getHeight(); y++) {
				sprite.setPixel(x, y, readInt(fr));
			}
		}
	}

}
