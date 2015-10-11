package sk.kebapp.sand.screens;

import sk.kebapp.sand.Sand;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MenuScreen implements Screen {

	private Stage stage;
	private TextButton startButton;
	private Sand sand;
	private Label info;
	private Label sandLabel;
	
	public MenuScreen(Sand sand) {
		this.sand = sand;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void show() {
		if (stage==null)
			stage = new Stage();
		
		TextButtonStyle ts = new TextButtonStyle();
		FreeTypeFontGenerator ftfg = new FreeTypeFontGenerator(
				Gdx.files.internal("font.ttf"));
		BitmapFont bf =ftfg.generateFont(Gdx.graphics.getHeight() / 15);
		bf.setColor(.5f, .5f, .8f, 1);
		ts.font = bf;
		//ts.up = new TextureRegionDrawable(new TextureRegion(sand.getImg()));
		startButton = new TextButton("Start", ts);

		startButton.setPosition(Gdx.graphics.getWidth() / 2 - startButton.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		startButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				sand.setScreen(sand.getSandscreen());
			}
		});
		startButton.setColor(.9f, .5f, .4f, 1);
		
		LabelStyle ls = new LabelStyle();
		ls.font = ftfg.generateFont(Gdx.graphics.getHeight() / 25);
		
		LabelStyle ls2 = new LabelStyle();
		ls2.font = ftfg.generateFont(Gdx.graphics.getHeight() / 7);
		
		
		sandLabel = new Label("Sand", ls2);
		sandLabel.setPosition(Gdx.graphics.getWidth()/2 - sandLabel.getWidth()/2, Gdx.graphics.getHeight()*2/3);
		info = new Label("(C) Erik Hric 2014", ls);
		info.setPosition(Gdx.graphics.getWidth()/2 -info.getWidth()/2,info.getHeight()/2);

		TextButton gitHub = new TextButton("Visit me on GitHub", ts);

		gitHub.setPosition(Gdx.graphics.getWidth()/2 - gitHub.getWidth()/2, Gdx.graphics.getHeight()/3);
		gitHub.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.net.openURI("https://github.com/erikhric");
			}
		});

		stage.addActor(startButton);
		stage.addActor(info);
		stage.addActor(sandLabel);
		stage.addActor(gitHub);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
