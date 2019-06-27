package squid;
import java.awt.Color;
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
		palette.add(Color.blue);
		JFrame mainWindow = new JFrame();
		spritePanel = new SpritePanel();
		spritePanel.setSize(500, 500);
		spritePanel.setBackground(Color.BLUE);
		mainWindow.add(spritePanel);
		mainWindow.setSize(500, 500);
		mainWindow.setVisible(true);
		undoStack = new Stack<squid.actions.Action>();
	}

}
