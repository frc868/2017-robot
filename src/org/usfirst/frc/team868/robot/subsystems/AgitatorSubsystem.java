package org.usfirst.frc.team868.robot.subsystems;

import org.usfirst.frc.team868.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The Agitator subsystem "stirs" the balls in the hopper so they can be picked
 * up by the {@link ShooterFeederSubsystem}.
 */
public class AgitatorSubsystem extends Subsystem {

    private static AgitatorSubsystem instance;
    private Spark motor;
    private boolean state;

    /** Set this to true for tuning and diagnostic output. */
    private static final boolean DEBUG = false;
    private static final String SpeedLabel = "Agitator Speed";

    @Override
    protected void initDefaultCommand() {
    }

    private AgitatorSubsystem() {
        motor = new Spark(RobotMap.Feeder.AGITATOR_MOTOR);
        motor.setInverted(RobotMap.Feeder.AGITATOR_IS_INVERTED);
        LiveWindow.addActuator("Agitator", "Motor", motor);

        if (DEBUG) {
            SmartDashboard.putNumber(SpeedLabel, RobotMap.Feeder.AGITATOR_SPEED);
        }
    }

    /**
     * Update information on SmartDashboard.
     */
    public void updateSD() {
        double power = motor.get();
        boolean on = (power != 0);

        SmartDashboard.putBoolean("Agitator On", on);
        if (DEBUG) {
            SmartDashboard.putNumber("Agitator Power", power);
        }
    }

    /**
     * Get access to the single Agitator on the robot.
     * 
     * @return Reference to agitator singleton.
     */
    public static AgitatorSubsystem getInstance() {
        return instance == null ? instance = new AgitatorSubsystem() : instance;
    }

    /**
     * Turns the agitator on/off.
     * 
     * @param on
     *            If true, turns on the agitator at the default power level, if
     *            false, stops agitator.
     */
    public void setAgitator(boolean on) {
        state = on;
        double speed = 0;
        if (on) {
            speed = RobotMap.Feeder.AGITATOR_SPEED;
            // When debugging/tuning, pull value from dashboard
            if (DEBUG) {
                speed = SmartDashboard.getNumber(SpeedLabel, speed);
            }
        }
        setAgitatorSpeed(on ? RobotMap.Feeder.AGITATOR_SPEED : 0);
    }

    /**
     * Turns on agitator with the default power setting.
     */
    public void setAgitatorOn() {
        setAgitator(true);
    }

    /**
     * Turns off the agitator.
     */
    public void setAgitatorOff() {
        setAgitator(false);
    }

    /**
     * Toggles the state of the agitator (this will reset the power to the
     * default setting when turned back on).
     */
    public void toggleAgitator() {
        setAgitator(!state);
    }

    /**
     * Indicates whether the agitator is on or not.
     * 
     * @return true if agitator is running.
     */
    public boolean isAgitatorOn() {
        return state;
    }

    /**
     * Sets the agitator to a specific power level.
     * 
     * @param speed
     *            Power level to apply in the range of [-1.0, +1.0] (0 stops,
     *            negative values turn in reverse direction).
     */
    private void setAgitatorSpeed(double speed) {
        motor.set(speed);
    }
}
