
package golf.visualization.screen;

import golf.visualization.Golf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.math.Vector2;

import golf.physics.*;
import golf.course.*;

public class CreateCourseScreen implements Screen {
    public Stage stage;
    private Golf parent;

    public CreateCourseScreen(Golf box2dTutorial) {
        parent = box2dTutorial;
        stage = new Stage(new ScreenViewport());
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label function = new Label("height function", skin);
        Label friction = new Label("friction coefficient:", skin);
        Label start = new Label("starting point:", skin);
        Label target = new Label("target point", skin);
        Label radius = new Label("radius target", skin);
        Label velocity = new Label("maximum velocity:", skin);
        final TextField functiont= new TextField("sin(x)", skin);
        final TextField frictiont= new TextField("0.13", skin);
        final TextField startt= new TextField("(x,y)", skin);
        final TextField targett= new TextField("(x,y,x)", skin);
        final TextField radiust= new TextField("0.2", skin);
        final TextField velocityt = new TextField("3", skin);
        TextButton create = new TextButton("start", skin);
        create.addListener(new ChangeListener() {
            
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //should launch in visualization
                String formula       = functiont.getText();
                double friction      = Double.parseDouble(frictiont.getText());
                String startingpoint = startt.getText();
                String targetpoint   = targett.getText();
                double rad           = Double.parseDouble(radiust.getText());
                double maxVel       = Double.parseDouble(velocityt.getText());

                PuttingCourse c = new PuttingCourse();
                c.height = new Function2d(formula);
                c.frictionCoefficient = friction;
                c.flag = new Vector2(10,10);
                c.start = new Vector2(0,0);
                c.g = 9.81;
                c.m = 45;
                c.Vmax = maxVel;
                c.holeTolerance = rad;
            
                //parent.setScreen(new Visualization(new PuttingSimulator(c, new RungeKutta())));
                parent.setScreen(new Visualization(new PuttingSimulator(c, new Euler())));
            }
        });

        table.add(function);
        table.add(functiont);
        table.row();
        table.add(friction);
        table.add(frictiont);
        table.row();
        table.add(start);
        table.add(startt);
        table.row();
        table.add(target);
        table.add(targett);
        table.row();
        table.add(radius);
        table.add(radiust);
        table.row();
        table.add(velocity);
        table.add(velocityt);
        table.row().padTop(20);
        table.add(create);

        functiont.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                functiont.setText("");
            }
        });
        frictiont.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                frictiont.setText("");
            }
        });
        startt.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                startt.setText("");
            }
        });
        targett.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                targett.setText("");
            }
        });
        radiust.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                radiust.setText("");
            }
        });
        velocityt.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                velocityt.setText("");
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

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}