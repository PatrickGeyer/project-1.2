public class RetakeShotMenu extends Screen {

    Stage stage;
    TextButton retakeLastButton;
    TextButton keepGoingButton;

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        retakeLastButton = new TextButton("Retake From Last Position");
        keepGoingButton = new TextButton("Keep Going");
        stage.addActor(retakeLastButton);
        stage.addActor(keepGoingButton);
    }

    @Override
    public void render() {      
        super.render();
        stage.draw();
    }
}