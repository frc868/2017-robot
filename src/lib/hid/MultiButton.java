package lib.hid;

import java.util.ArrayList;

public class MultiButton extends Button {

    private static ArrayList<JoystickButton> buttons = new ArrayList<>();
    private boolean off;

    public MultiButton(ControllerMap controller, Integer... keys) {
        for (int i = 0; i < keys.length; i++) {
            MultiButton.buttons.add(controller.getNewButton(keys[i]));
        }
        off = false;
    }

    @Override
    public boolean get() {

        if (off) {
            return false;
        }

        for (int i = 0; i < MultiButton.buttons.size(); i++) {
            if (MultiButton.buttons.get(i).get() == false) {
                return false;
            }
        }

        return true;
    }

    public void setOff(boolean off) {
        this.off = off;

        for (JoystickButton b : MultiButton.buttons) {
            b.setOff(off);
        }
    }
}
