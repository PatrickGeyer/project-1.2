import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Gdx;
import golf.visualization.Golf;

public class RetakeShotMenu extends Stage {

    TextButton retakeLastButton;
    TextButton keepGoingButton;
    TextButton.TextButtonStyle textButtonStyle;

    public RetakeShotMenu() {
        Gdx.input.setInputProcessor(this);

        textButtonStyle = new TextButton.TextButtonStyle();
        retakeLastButton = new TextButton("Retake From Last Position", textButtonStyle);
        keepGoingButton = new TextButton("Keep Going", textButtonStyle);
        addActor(retakeLastButton);
        addActor(keepGoingButton);
    }
}