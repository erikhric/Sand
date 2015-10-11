package sk.kebapp.sand.elements;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import sk.kebapp.sand.Sand;

public class Grain extends Actor {

	private Sand sand;
	private static int gravity = 1;

	public Grain(int x, int y, Sand sand) {
		this.sand = sand;
		setPosition(x, y);
		setSize(2, 2);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(sand.getImg(), getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void act(float delta) {
		if (getY() > 2)
			if (!sand.getField().getXYvalue((int) getX(), (int) getY() - gravity)) {
				// padaj ak pod tebou nie je zrnko
				sand.getField().setXYvalue((int) getX(), (int) getY(), false);
				setY(getY() - gravity);
				sand.getField().setXYvalue((int) getX(), (int) getY(), true);
			} else {
				// inak ak je nad tebou zrnko
				if (sand.getField().getXYvalue((int) getX(), (int) getY() + gravity)) {
					// ak je vpravo volne chod tam
					if (!sand.getField().getXYvalue((int) getX() + 1,
							(int) getY())) {
						sand.getField().setXYvalue((int) getX(), (int) getY(),
								false);
						setX(getX() + 1);
						sand.getField().setXYvalue((int) getX(), (int) getY(),
								true);
					}
					else if (!sand.getField().getXYvalue((int) getX() - 1,
							(int) getY())) {// ak je vlavo chod tam
						sand.getField().setXYvalue((int) getX(), (int) getY(),
								false);
						setX(getX() - 1);
						sand.getField().setXYvalue((int) getX(), (int) getY(),
								true);
					} else {
						// ak nikde nreob nic
					}

				}

			}
	}
	
	public static void switchGravity() {
		if (gravity == 1){
			gravity = -1;
		} else {
			gravity = 1;
		}
	}
	
}
