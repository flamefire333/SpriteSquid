package squid.actions;

import squid.Starter;

public class SetPixelAction implements Action{

	int x, y, newcolor, oldcolor;
	
	public SetPixelAction(int x_, int y_, int color_) {
		x = x_;
		y = y_;
		newcolor = color_;
	}
	
	@Override
	public void apply() {
		// TODO Auto-generated method stub
		oldcolor = Starter.sprite.getPixel(x, y);
		System.out.println(oldcolor + " " + newcolor);
		Starter.sprite.setPixel(x, y, newcolor);
	}

	@Override
	public void unapply() {
		// TODO Auto-generated method stub
		Starter.sprite.setPixel(x, y, oldcolor);
	}

}
