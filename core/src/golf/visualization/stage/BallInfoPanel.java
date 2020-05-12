package golf.visualization.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import golf.course.object.Ball;

public class BallInfoPanel extends Stage {

    Label positionLabel;
    Label velocityLabel;
    Label shotsLabel;
    Ball ball;

    public BallInfoPanel(Ball ball) {
        int fontSize = 15;
        this.ball = ball;
        positionLabel = new Label("hello", new Label.LabelStyle(new BitmapFont(),Color.BROWN));
        velocityLabel = new Label("hello", new Label.LabelStyle(new BitmapFont(),Color.BROWN));
        shotsLabel = new Label("hello", new Label.LabelStyle(new BitmapFont(),Color.BROWN));
        velocityLabel.setPosition(0,fontSize);
        shotsLabel.setPosition(0,fontSize*2);
        addActor(positionLabel);
        addActor(velocityLabel);
        addActor(shotsLabel);
    }

    @Override
    public void draw() {
        positionLabel.setText(ball.position.toString());
        velocityLabel.setText(ball.velocity.toString());
        shotsLabel.setText("Shots: " + ball.shotCount);
        super.draw();
    }

}