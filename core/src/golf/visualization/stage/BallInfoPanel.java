package golf.visualization.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import golf.course.object.Ball;

public class BallInfoPanel implements Screen {

    Stage stage;
    Label positionLabel;
    Label velocityLabel;
    Ball ball;

    public BallInfoPanel(Ball ball) {
        this.ball = ball;
    }

    @Override
    public void show() {
        stage = new Stage();
        // Gdx.input.setInputProcessor(stage);

        positionLabel = new Label("hello", new Label.LabelStyle(new BitmapFont(),Color.BROWN));
        velocityLabel = new Label("hello", new Label.LabelStyle(new BitmapFont(),Color.BROWN));
        velocityLabel.setPosition(0,100);
        stage.addActor(positionLabel);
        stage.addActor(velocityLabel);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        positionLabel.setText(ball.position.toString());
        velocityLabel.setText(ball.velocity.toString());
        stage.draw();
    }


    @Override
    public void resize(int width, int height) {
        // stage.setViewport(width, height, false);
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
        // Irrelevant on desktop, ignore this
    }

    @Override
    public void resume() {
        // Irrelevant on desktop, ignore this
    }
}