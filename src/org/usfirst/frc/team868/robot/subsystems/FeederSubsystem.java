package org.usfirst.frc.team868.robot.subsystems;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.RobotMap.State;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FeederSubsystem extends Subsystem {
	private static FeederSubsystem instance;
	private Spark motor;
	
	public State state = State.OFF;
	private DigitalInput beamBreak;
	private static final boolean DEBUG = false;
	
	@Override
	protected void initDefaultCommand() {}
	
	private FeederSubsystem() {
		motor = new Spark(RobotMap.Feeder.CONVEYOR_MOTOR);
		motor.setInverted(RobotMap.Feeder.CONVEYOR_IS_INVERTED);
		beamBreak = new DigitalInput(RobotMap.Feeder.BEAM_BREAK_PORT);
		
		// Assign test mode group
		LiveWindow.addActuator("Feeder", "Motor", motor);
		LiveWindow.addSensor("Feeder", "Beam break", beamBreak);
	}
	
	/**
	 * Get the instance of this subsystem
	 * @return instance
	 */
	public static FeederSubsystem getInstance() {
		return instance == null ? instance = new FeederSubsystem() : instance;
	}
	
	/**
	 * Turns the feeder on or off.
	 * @param on
	 */
	public void setFeeder(State state) {
		this.state = state;
		switch(state) {
			case FORWARD:
				if(canFeed()) {
					setFeederSpeed(RobotMap.Feeder.CONVEYOR_SPEED);
				} else {
					setFeederSpeed(0);
				}
				break;
			case OFF:
				setFeederSpeed(0);
				break;
			case BACKWARD:
				setFeederSpeed(-RobotMap.Feeder.CONVEYOR_SPEED);
				break;
			default:
				break;
		}
	}
	
	public boolean canFeed() {
		if(DEBUG) return true;
		return ShooterSubsystem.getInstance().getSpeed() > 50;
	}
	
	/**
	 * Turns the feeder forward.
	 */
	public void setFeederForward() {
		setFeeder(State.FORWARD);
	}
	
	/**
	 * Turns the feeder off.
	 */
	public void setFeederOff() {
		setFeeder(State.OFF);
	}
	
	/**
	 * Turns the feeder Backward.
	 */
	public void setFeederBackward(){
		setFeeder(State.BACKWARD);
	}
	
	/**
	 * Switches the feeder's status.
	 */
	public void switchDirection() {
		setFeeder(!(state == State.FORWARD) ? State.FORWARD : State.BACKWARD);
	}

	/**
	 * Toggles the state of the agitator (this will reset the power to the
	 * default setting when turned back on).
	 */
	public void toggleFeeder() {
		setFeeder(!(state == State.OFF) ? State.OFF : State.FORWARD);
	}
	
	/**
	 * returns opposite of current state
	 */
	public State getOppositeState() {
		return !(state == State.FORWARD) ? State.FORWARD : State.BACKWARD;
	}
	
	/**
	 * Gets the feeder's status.
	 * @return whether or not the feeder is on
	 */
	public State getState() {
		return state;
	}
	
	/**
	 * Sets the feeder's speed
	 * @param speed in percentage from -1 to 1.
	 */
	private void setFeederSpeed(double speed) {
		motor.set(speed);
	}
	
	/**
	 * Gets whether or not a ball is detected.
	 */
	public boolean getBallBeamBreak(){
		return beamBreak.get();
	}
	
	/**
	 * Update information on SmartDashboard.
	 */
	public void updateSD(){
		SmartDashboard.putBoolean("Ball is ready", getBallBeamBreak());
		SmartDashboard.putBoolean("Feeder on", getState().equals(State.FORWARD));
		if(DEBUG) {
			SmartDashboard.putData("Feeder Subsystem", this);
		}
	}
}
