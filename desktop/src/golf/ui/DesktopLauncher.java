package golf.ui.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import golf.visualization.Visualization;
import golf.course.*;
import golf.physics.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Visualization(new Course(), new EulerSolver(0.01)), config);
	}
}
