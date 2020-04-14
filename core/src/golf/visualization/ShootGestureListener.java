package golf.visualization;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.input.GestureDetector.GestureListener;

public class ShootGestureListener implements GestureListener  {

    @Override
   	public boolean touchDown(float x, float y, int pointer, int button) {
		System.out.println(x + ", " + y);
	   	return false;
   	}
	   	
	@Override
	public boolean tap(float x, float y, int count, int button) {
			
		return false;
	}
		
	@Override
	public boolean longPress(float x, float y) {
			
		return false;
	}
		
	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
			
		return false;
	}
		
	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// System.
		return false;
	}
		
	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		System.out.println(x + ", " + y);
		return false;
	}
		
   	@Override
   	public boolean zoom (float originalDistance, float currentDistance){
	   		
	   return false;
   	}

   	@Override
   	public boolean pinch (Vector2 initialFirstPointer, Vector2 initialSecondPointer, Vector2 firstPointer, Vector2 secondPointer){
	   		
	   return false;
   	}
   	@Override
	public void pinchStop () {
	}
}