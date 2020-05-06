package golf.visualization.screen;

import golf.visualization.screen.LoadingScreen;
import golf.visualization.screen.*;
import com.badlogic.gdx.Game;

public class Box2DTutorial extends Game {

	private LoadingScreen loadingScreen;
	private CreateCourseScreen CreateCourseScreen;
	private MenuScreen menuScreen;
	/*private MainScreen mainScreen;
	private EndScreen endScreen;*/

	public final static int MENU = 0;
	public final static int CREATECOURSE = 1;
	/*public final static int APPLICATION = 2;
	public final static int ENDGAME = 3;*/

	public void changeScreen(int screen){
		switch(screen){
			case MENU:
				if(menuScreen == null) menuScreen = new MenuScreen(this); // added (this)
				this.setScreen(menuScreen);
				break;
			case CREATECOURSE:
				if(CreateCourseScreen == null) CreateCourseScreen = new CreateCourseScreen(this); // added (this)
				this.setScreen(CreateCourseScreen);
				break;
		/*	case APPLICATION:
				if(Option1Screen == null) Option1Screen = new Option1Screen(this); //added (this)
				this.setScreen(Option1Screen);
				break;
			case ENDGAME:
				if(endScreen == null) endScreen = new EndScreen(this);  // added (this)
				this.setScreen(endScreen);
				break;*/
		}
	}


	@Override
	public void create () {
		//loadingScreen = new LoadingScreen();
		loadingScreen = new LoadingScreen(this);//
		setScreen(loadingScreen);
	}



}
