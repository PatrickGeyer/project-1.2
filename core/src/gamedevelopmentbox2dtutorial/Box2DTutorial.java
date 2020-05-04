package blog.gamedevelopment.box2dtutorial;

import blog.gamedevelopmentbox2dtutorial.*;
import com.badlogic.gdx.Game;
import blog.gamedevelopment.box2dtutorial.views.MenuScreen;
import blog.gamedevelopment.box2dtutorial.views.LoadingScreen;
public class Box2DTutorial extends Game {

	private blog.gamedevelopment.box2dtutorial.views.LoadingScreen loadingScreen;
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
				if(mainScreen == null) mainScreen = new MainScreen(this); //added (this)
				this.setScreen(mainScreen);
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
