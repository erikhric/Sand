package sk.kebapp.sand.elements;

import com.badlogic.gdx.Gdx;

import sk.kebapp.sand.Sand;

public class Field {

	private boolean[][] data;
	private Sand sand;
	private int height;
	private int width;
	private int upperSpace;

	public Field(int width, int height, int upperSpace, Sand sand) {
		this.width = width;
		this.height = height;
		this.upperSpace = upperSpace;
		this.sand = sand;
		data = new boolean[width][height];
	}

	public void addSand(int x, int y) {
		if (!(x + 25 > Gdx.graphics.getWidth()) && !(y + 25 > Gdx.graphics.getHeight()) && !(x - 25 < 0) && !(y - 25 < 0))
			for (int i = x - 25; i < x + 25; i += 3) {
				for (int j = y - 25; j < y + 25; j += 3) {
					if (j < height - upperSpace - 1) {
						data[i][j] = true;
						sand.getSandStage().addActor(new Grain(i, j, sand));
					}
				}
			}
	}

	public void setXYvalue(int x, int y, boolean value) {
		data[x][y] = value;
	}

	public boolean getXYvalue(int x, int y) {
		if (y > 2 && y < Gdx.graphics.getHeight() - upperSpace && x > 2
				&& x < Gdx.graphics.getWidth() - 3)
			return data[x][y];
		else
			return true;
	}

	public void reset() {
		data = new boolean[width][height];
	}

	public void addClock() {
		try {
			int x = (Gdx.graphics.getWidth() - height) / 2 - 3;
			boolean zvysujX = true;
			for (int i = 0; i < height - upperSpace; i++) {
				data[x][i] = true;
				data[width - x - 1][i] = true;
				sand.getSandStage().addActor(new Wall(sand, (int) x, (int) i));
				sand.getSandStage().addActor(new Wall(sand, (int) width - x - 1, (int) i));
				if (i > Gdx.graphics.getHeight() / 2)
					zvysujX = false;
				if (zvysujX)
					x++;
				else
					x--;
			}
		} catch (Exception e) {

		}
	}

}
