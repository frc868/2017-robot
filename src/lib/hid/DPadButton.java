package lib.hid;

import edu.wpi.first.wpilibj.GenericHID;

public class DPadButton extends JoystickButton {
	
	protected boolean isEightDirectional;
	protected int direction;
	
	public interface Direction {
		int UP = 20, UP_RIGHT = 21, RIGHT = 22, DOWN_RIGHT = 23, 
				DOWN = 24, DOWN_LEFT = 25, LEFT = 26, UP_LEFT = 27;
	}
   
    public DPadButton(ControllerMap joystick, int direction) {
    	this(joystick, direction, false);
    }
    
    public DPadButton(ControllerMap joystick, int direction, boolean isEightDirectional) {
    	super(joystick);
    	this.isEightDirectional = isEightDirectional;
    	this.direction = direction;
    }

    @Override
    public boolean get() {

		if(off)
			return false;
    	
    	if(!isDPADButton(direction))
    		return super.get();
    	
    	int angle = controllerMap.getJoystick().getPOV();
    	
    	if(!isEightDirectional) {
            if (direction == Direction.LEFT) {
                return angle == 270 || angle == 315 || angle == 225;
            } else if (direction == Direction.RIGHT) {
                return angle == 90 || angle == 45 || angle== 135;
            } else if (direction == Direction.UP) {
                return angle == 0 || angle == 45 || angle == 315;
            } else if (direction == Direction.DOWN) {
                return angle == 180 || angle == 135 || angle == 225;
            } else {
                return false;
            }
    	} else {
    		if(direction == Direction.UP){
    			return angle == 0;
    		}else if(direction == Direction.UP_LEFT){
    			return angle == 45;
    		}else if(direction == Direction.LEFT){
    			return angle == 90;
    		}else if(direction == Direction.DOWN_LEFT){
    			return angle == 135;
    		}else if(direction == Direction.DOWN){
    			return angle == 180;
    		}else if(direction == Direction.DOWN_RIGHT){
    			return angle == 225;
    		}else if(direction == Direction.RIGHT){
    			return angle == 270;
    		}else if(direction == Direction.UP_RIGHT){
    			return angle == 315;
    		}else{
    			return false;
    		}
    	}
    }
    
    public static boolean isDPADButton(int buttonID) {
    	return !(buttonID < 20 || buttonID > 27);
    }
}
