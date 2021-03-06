package org.usfirst.frc.team868.robot.subsystems;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.RobotMap.State;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The Agitator subsystem "stirs" the balls in the hopper so they can be picked
 * up by the {@link FeederSubsystem}.
 */
public class AgitatorSubsystem extends Subsystem {

	private static AgitatorSubsystem instance;
	private Spark motor;
	
	public State state = State.OFF;

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
	}
	
	/**
	 * Update information on SmartDashboard.
	 */
	public void updateSD() {
		double power = motor.get();
		boolean on = (power != 0);
		
		SmartDashboard.putBoolean("Agitator On", on);
		if (DEBUG) {
			SmartDashboard.putData("Agitator Subsystem", this);
			SmartDashboard.putNumber("Agitator Power", power);
			SmartDashboard.putNumber("Agitator Speed", RobotMap.Feeder.AGITATOR_SPEED); //TODO cleanup all of these debug checks
	    	SmartDashboard.putNumber("Agitator Shake Time", RobotMap.Feeder.SHAKE_PERIOD);
//			SmartDashboard.putData("Agitator Shake", new AgitatorFreeCommand(RobotMap.Feeder.SHAKE_PERIOD));
//			SmartDashboard.putData("Agitator On", new AgitatorCommand(State.FORWARD));
//			SmartDashboard.putData("Agitator Off", new AgitatorCommand(State.OFF));
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
	public void setAgitator(State state) {
		double speed = 0;
		this.state = state;
		
		switch (state) {
		case FORWARD:
			speed = RobotMap.Feeder.AGITATOR_SPEED;
			if (DEBUG) {
				speed = SmartDashboard.getNumber(SpeedLabel, speed);
			}
			break;
		case BACKWARD:
			speed = -RobotMap.Feeder.AGITATOR_SPEED;
			if (DEBUG) {
				speed = -SmartDashboard.getNumber(SpeedLabel, speed);
			}
			break;
		default:
			break;
		}
		
		setAgitatorSpeed(speed);
	}
	
	/**
	 * Turns agitator to foreward.
	 */
	public void setAgitatorForward() {
		setAgitator(State.FORWARD);
	}

	/**
	 * Turns off the agitator.
	 */
	public void setAgitatorOff() {
		setAgitator(State.OFF);
	}
	/**
	 * Turns agitator to forward.
	 */
	public void setAgitatorBackward() {
		setAgitator(State.OFF);
	}
	
	/**
	 * switches the direction of the agitator.
	 */
	public void switchDirection() {
		setAgitator(!(state == State.FORWARD) ? State.FORWARD : State.BACKWARD);
	}

	/**
	 * Toggles the state of the agitator (this will reset the power to the
	 * default setting when turned back on).
	 */
	public void toggleAgitator() {
		setAgitator(!(state == State.OFF) ? State.OFF : State.FORWARD);
	}

	/**
	 * Indicates whether the agitator is on or not.
	 * 
	 * @return true if agitator is running.
	 */
	public State getState() {
		return state;
	}

	/**
	 * Sets the agitator to a specific power level.
	 * 
	 * @param speed
	 *            Power level to apply in the range of [-1.0, +1.0] (0 stops,
	 *            negative values turn in reverse direction).
	 */
	public void setAgitatorSpeed(double speed) {
		motor.set(speed);
	}
}
