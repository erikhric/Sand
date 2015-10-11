package sk.kebapp.sand;

import sk.kebapp.sand.elements.Field;
import sk.kebapp.sand.screens.MenuScreen;
import sk.kebapp.sand.screens.SandScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Sand extends Game {
	private Texture img;
	private Texture wall;
	private SandScreen sandscreen;
	private MenuScreen menu;

	@Override
	public void create() {
		img = new Texture("sand.jpg");
		wall = new Texture("wall.jpg");
		menu = new MenuScreen(this);
		setScreen(menu);

		sandscreen = new SandScreen(this);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(.5f, .5f, .8f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}

	public Field getField() {
		return sandscreen.getField();
	}

	public Texture getImg() {
		return img;
	}

	public Stage getSandStage() {
		return sandscreen.getStage();
	}
	
	public Texture getWall() {
		return wall;
	}
	
	public SandScreen getSandscreen() {
		return sandscreen;
	}
	
	public MenuScreen getMenu() {
		return menu;
	}
}
