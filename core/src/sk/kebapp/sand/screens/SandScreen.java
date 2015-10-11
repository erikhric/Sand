package sk.kebapp.sand.screens;

import sk.kebapp.sand.elements.Field;
import sk.kebapp.sand.elements.Grain;
import sk.kebapp.sand.Sand;
import sk.kebapp.sand.elements.Wall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class SandScreen implements Screen {

    SpriteBatch batch;
    private Field field;
    private Stage stage;
    private Actor input;
    private boolean addingSand = true;
    private TextButton sandButton;
    private Sand sand;
    ///to start painting walls
    private TextButton wallButton;
    ///deletes everything
    private TextButton resetButton;
    ///changes gravity direction
    private TextButton gravityButton;
    //deletes everything and add walls in shape of sandclock
    private TextButton clockButton;

    public SandScreen(Sand sand) {
        this.sand = sand;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void show() {
        stage = new Stage();

        TextButtonStyle ts = new TextButtonStyle();
        FreeTypeFontGenerator ftfg = new FreeTypeFontGenerator(
                Gdx.files.internal("font.ttf"));
        ts.font = ftfg.generateFont(Gdx.graphics.getHeight() / 20);
        sandButton = new TextButton("Sand", ts);

        sandButton.setPosition(Gdx.graphics.getWidth() / 2,
                Gdx.graphics.getHeight() - sandButton.getHeight());
        sandButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                addingSand = true;
            }
        });

        clockButton = new TextButton("Clock", ts);


        clockButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                reset();
                field.addClock();
            }
        });

        this.field = new Field(Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight(), (int) (sandButton.getHeight() + 5),
                sand);
        this.gravityButton = new TextButton("Gravity", ts);
        gravityButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Grain.switchGravity();
            }
        });
        gravityButton.setPosition(sandButton.getX() - gravityButton.getWidth()
                        - sandButton.getHeight() * 2 + sandButton.getHeight(),
                sandButton.getY());
        clockButton.setPosition(gravityButton.getX()- clockButton.getWidth() - clockButton.getHeight(),
                Gdx.graphics.getHeight() - sandButton.getHeight());
        this.wallButton = new TextButton("Wall", ts);
        wallButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                addingSand = false;
            }
        });
        wallButton.setPosition(sandButton.getX() + sandButton.getWidth()
                + sandButton.getHeight(), sandButton.getY());

        this.resetButton = new TextButton("Reset", ts);
        resetButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                reset();
            }
        });
        resetButton.setPosition(wallButton.getX() + wallButton.getWidth()
                + wallButton.getHeight(), wallButton.getY());

        input = new Actor();
        input.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (addingSand)
                    field.addSand((int) x, (int) y);
            }
        });
        input.addListener(new DragListener() {
            @Override
            public void touchDragged(InputEvent event, float x, float y,
                                     int pointer) {
                if (!addingSand)
                    stage.addActor(new Wall(sand, (int) x, (int) y));
                else
                    stage.addActor(new Grain((int) x, (int) y, sand));
            }
        });

        input.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        addAllButtons();
        Gdx.input.setInputProcessor(stage);
    }

    private void addAllButtons() {
        stage.addActor(input);
        stage.addActor(sandButton);
        stage.addActor(wallButton);
        stage.addActor(resetButton);
        stage.addActor(gravityButton);
        stage.addActor(clockButton);//korigujuuuuuuuu
    }

    private void reset() {
        stage.clear();
        field.reset();
        addAllButtons();
    }

    @Override
    public void render(float delta) {
        stage.draw();
        stage.act();
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

    public Field getField() {
        return field;
    }

    public Stage getStage() {
        return stage;
    }
}
