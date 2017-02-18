package lib.hid;

/**
 * Implement our own Joystick Button class based on our custom Button class
 *
 * @author Atif Niyaz
 */
public class JoystickButton extends Button {

    protected ControllerMap controllerMap;
    protected int buttonNumber;
    protected boolean off;

    protected JoystickButton(ControllerMap joystick) {
        controllerMap = joystick;
        off = false;
    }

    public JoystickButton(ControllerMap joystick, int buttonNumber) {
        controllerMap = joystick;
        this.buttonNumber = buttonNumber;
        off = false;
    }

    public void setButtonNumber(int buttonNumber) {
        this.buttonNumber = buttonNumber;
    }

    public void setOff(boolean off) {
        this.off = off;
    }

    @Override
    public boolean get() {
        if (off) {
            return false;
        }

        if (controllerMap.getMultiButton(buttonNumber) != null) {
            if (controllerMap.getMultiButton(buttonNumber).get()) {
                return false;
            }
        }

        return controllerMap.getJoystick().getRawButton(buttonNumber);
    }
}
