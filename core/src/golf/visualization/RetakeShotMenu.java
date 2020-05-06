import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Gdx;

public class RetakeShotMenu implements Screen {

    Stage stage;
    TextButton retakeLastButton;
    TextButton keepGoingButton;
    TextButton.TextButtonStyle textButtonStyle;

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        textButtonStyle = new TextButton.TextButtonStyle();
        retakeLastButton = new TextButton("Retake From Last Position", textButtonStyle);
        keepGoingButton = new TextButton("Keep Going", textButtonStyle);
        stage.addActor(retakeLastButton);
        stage.addActor(keepGoingButton);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        // super.render();
        stage.draw();
    }


    @Override
    public void resize(int width, int height) {
        // stage.setViewport(width, height, false);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
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