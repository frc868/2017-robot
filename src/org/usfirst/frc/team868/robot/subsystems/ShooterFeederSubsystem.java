package org.usfirst.frc.team868.robot.subsystems;

import org.usfirst.frc.team868.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShooterFeederSubsystem extends Subsystem {
	private static ShooterFeederSubsystem instance;
	private Spark motor;
	private boolean state;
	private static final boolean DEBUG = false;
	
	@Override
	protected void initDefaultCommand() {}
	
	private ShooterFeederSubsystem() {
		motor = new Spark(RobotMap.Feeder.CONVEYOR_MOTOR);
		motor.setInverted(RobotMap.Feeder.CONVEYOR_IS_INVERTED);
		
		// Assign test mode group
		LiveWindow.addActuator("Feeder", "Motor", motor);
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
	public void setFeeder(boolean on) {
		state = on;
		setFeederSpeed(RobotMap.Feeder.CONVEYOR_SPEED);
	}
	
	/**
	 * Turns the feeder on.
	 */
	public void setFeederOn() {
		setFeeder(true);
	}
	
	/**
	 * Turns the feeder off.
	 */
	public void setFeederOff() {
		setFeeder(false);
	}
	
	/**
	 * Switches the feeder's status.
	 */
	public void toggleFeeder() {
		setFeeder(!state);
	}
	
	/**
	 * Gets the feeder's status.
	 * @return whether or not the feeder is on
	 */
	public boolean isFeederOn() {
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
	 * Update information on SmartDashboard.
	 */
	public void updateSD(){
		if(DEBUG){
			SmartDashboard.putBoolean("Feeder is on", isFeederOn());
		}
	}
}
