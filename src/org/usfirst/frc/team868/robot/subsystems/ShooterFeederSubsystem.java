package org.usfirst.frc.team868.robot.subsystems;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.subsystems.AgitatorSubsystem.State;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShooterFeederSubsystem extends Subsystem {
	private static ShooterFeederSubsystem instance;
	private Spark motor;
	
	public RobotMap.Feeder.State state;
	private DigitalInput beamBreak;
	private static final boolean DEBUG = false;
	
	@Override
	protected void initDefaultCommand() {}
	
	private ShooterFeederSubsystem() {
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
	public static ShooterFeederSubsystem getInstance() {
		return instance == null ? instance = new ShooterFeederSubsystem() : instance;
	}
	
	/**
	 * Turns the feeder on or off.
	 * @param on
	 */
	public void setFeeder(RobotMap.Feeder.State state) {
		this.state = state;
		setFeederSpeed(RobotMap.Feeder.CONVEYOR_SPEED);
	}
	
	/**
	 * Turns the feeder forward.
	 */
	public void setFeederForward() {
		setFeeder(RobotMap.Feeder.State.FORWARD);
	}
	
	/**
	 * Turns the feeder off.
	 */
	public void setFeederOff() {
		setFeeder(RobotMap.Feeder.State.OFF);
	}
	
	/**
	 * Turns the feeder Backward.
	 */
	public void setFeederBackward(){
		setFeeder(RobotMap.Feeder.State.BACKWARD);
	}
	
	/**
	 * Switches the feeder's status.
	 */
	public void switchDirection() {
		setFeeder(!(state == RobotMap.Feeder.State.FORWARD) ? RobotMap.Feeder.State.FORWARD : RobotMap.Feeder.State.BACKWARD);
	}

	/**
	 * Toggles the state of the agitator (this will reset the power to the
	 * default setting when turned back on).
	 */
	public void toggleFeeder() {
		setFeeder(!(state == RobotMap.Feeder.State.OFF) ? RobotMap.Feeder.State.OFF : RobotMap.Feeder.State.FORWARD);
	}
	
	/**
	 * returns opposite of current state
	 */
	public RobotMap.Feeder.State getOppositeState() {
		return !(state == RobotMap.Feeder.State.FORWARD) ? RobotMap.Feeder.State.FORWARD : RobotMap.Feeder.State.BACKWARD;
	}
	
	/**
	 * Gets the feeder's status.
	 * @return whether or not the feeder is on
	 */
	public RobotMap.Feeder.State getState() {
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
		if(DEBUG){
			SmartDashboard.putBoolean("Feeder is on", getState().equals(State.FORWARD) || getState().equals(State.BACKWARD));
		}
	}
}
