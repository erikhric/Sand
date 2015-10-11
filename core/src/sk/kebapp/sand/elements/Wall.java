package sk.kebapp.sand.elements;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import sk.kebapp.sand.Sand;

public class Wall extends Actor {

	private Sand sand;

	public Wall(Sand sand, int x, int y) {
		this.sand = sand;
		sand.getField().setXYvalue(x, y, true);
		sand.getField().setXYvalue(x+1, y, true);
		sand.getField().setXYvalue(x+1, y+1, true);
		sand.getField().setXYvalue(x, y+1, true);
		setBounds(x, y, 2, 2);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(sand.getWall(), getX(), getY(), getWidth(), getHeight());
	}

}
