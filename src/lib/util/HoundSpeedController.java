package lib.util;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SpeedController;

public class HoundSpeedController implements SpeedController {

    private SpeedController[] _controllers;
    private static PowerDistributionPanel panel = new PowerDistributionPanel();
    private int[] _pdpPorts;

    private boolean _inverted;

    public HoundSpeedController(SpeedController controller, int pdpPort) {
        _controllers = new SpeedController[] { controller };
        _pdpPorts = new int[] { pdpPort };
    }

    public HoundSpeedController(SpeedController controller, int[] pdpPorts) {
        _controllers = new SpeedController[] { controller };
        _pdpPorts = pdpPorts;
    }

    public HoundSpeedController(SpeedController[] controllers, int[] pdpPorts) {
        assert (controllers.length == pdpPorts.length);

        _controllers = controllers;
        _pdpPorts = pdpPorts;
    }

    @Override
    public void pidWrite(double output) {
        set(output);
    }

    public double getCurrent() {
        double current = 0.0;
        for (int port : _pdpPorts) {
            current += HoundSpeedController.panel.getCurrent(port);
        }
        return current;
    }

    public double getSignedCurrent() {
        return getCurrent() * Math.signum(get());
    }

    @Override
    public double get() {
        return _controllers[0].get();
    }

    // @Override
    // public void set(double speed, byte syncGroup) {
    // for(SpeedController controller : _controllers)
    // controller.set(speed, syncGroup);
    // }

    @Override
    public void set(double speed) {
        for (SpeedController controller : _controllers) {
            controller.set(speed);
        }
    }

    @Override
    public void setInverted(boolean inverted) {
        _inverted = inverted;

        for (SpeedController controller : _controllers) {
            controller.setInverted(inverted);
        }
    }

    @Override
    public boolean getInverted() {
        return _inverted;
    }

    @Override
    public void disable() {
        for (SpeedController controller : _controllers) {
            controller.disable();
        }
    }

    @Override
    public void stopMotor() {
        for (SpeedController controller : _controllers) {
            controller.stopMotor();
        }
    }
}
