package lib.hid;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import lib.hid.ControllerMap.Key;

public class TriggerButton extends JoystickButton {
	
	private Hand hand;
	
	private static int LEFT_AXIS = 2, RIGHT_AXIS = 3;
	private static double MIN_POW_ACT = 0.6;

	public TriggerButton(ControllerMap joystick, int buttonID) {
		super(joystick);
		
		if(buttonID == Key.LT)
			hand = Hand.kLeft;
		else if(buttonID == Key.RT)
			hand = Hand.kRight;
		else
			hand = null;
	}
	
	@Override
	public boolean get() {
		
		if(off)
			return false;
		
		if(hand == Hand.kLeft)
			return controllerMap.getJoystick().getRawAxis(LEFT_AXIS) > MIN_POW_ACT;
		else if(hand == Hand.kRight)
			return controllerMap.getJoystick().getRawAxis(RIGHT_AXIS) > MIN_POW_ACT;
		else
			return false;
	}
}
