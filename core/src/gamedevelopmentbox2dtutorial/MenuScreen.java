package gamedevelopmentbox2dtutorial;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen implements Screen{

    private Box2DTutorial parent;
    private Stage stage;

    public MenuScreen(Box2DTutorial box2dTutorial){
        parent = box2dTutorial;

        /// create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

        // temporary until we have asset manager in
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        //create buttons
        TextButton createCourse = new TextButton("Create new Course", skin);
        TextButton playSelf = new TextButton("choose a course to play", skin);
        TextButton playAI = new TextButton("choose a course for the AI to play", skin);
        table.add(createCourse).fillX().uniformX();
        table.row();
        table.add(playSelf).fillX().uniformX();
        table.row();
        table.add(playAI).fillX().uniformX();


        // create button listeners
        createCourse.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //parent.changeScreen(Box2DTutorial.APPLICATION);
                parent.changeScreen(Box2DTutorial.CREATECOURSE);
            }
        });

        playSelf.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Open file chooser dialog
                // fc.open() or similar

                //reads course from a textfile
                // f.addActionListener( {
                //     filename = "C:/sdadas";
                //     InputOutput io = new InputOutput();
                //     this.course = io.read(filename);

                // })
                //let's user play course

                // LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                // new LwjglApplication(new Visualization(new PuttingSimulator(this.course, new EulerSolver(0.01))), config);
                Gdx.app.exit();//exits the UI (for now)
            }
        });

        playAI.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();//exits the UI (for now)
            }
        });

    }

    @Override
    public void render(float delta) {
        // clear the screen ready for next set of images to be drawn
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell our stage to do actions and draw itself
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // change the stage's viewport when the screen size is changed
        stage.getViewport().update(width, height, true);
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
        // dispose of assets when not needed anymore
        stage.dispose();
    }

}