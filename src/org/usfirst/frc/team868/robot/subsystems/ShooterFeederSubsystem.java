package org.usfirst.frc.team868.robot.subsystems;

import org.usfirst.frc.team868.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterFeederSubsystem extends Subsystem {
	public static ShooterFeederSubsystem instance;
	private Spark motor;
	private boolean state;
	
	@Override
	protected void initDefaultCommand() {}
	
	private ShooterFeederSubsystem() {
		motor = new Spark(RobotMap.Feeder.CONVEYOR_MOTOR);
		motor.setInverted(RobotMap.Feeder.CONVEYOR_IS_INVERTED);
	}
	
	public static ShooterFeederSubsystem getInstance() {
		return instance == null ? instance = new ShooterFeederSubsystem() : instance;
	}
	
	public void setFeeder(boolean on) {
		state = on;
		setFeederSpeed(RobotMap.Feeder.CONVEYOR_SPEED);
	}
	
	public void setFeederOn() {
		setFeeder(true);
	}
	
	public void setFeederOff() {
		setFeeder(false);
	}
	
	public void toggleFeeder() {
		setFeeder(!state);
	}
	
	public boolean isFeederOn() {
		return state;
	}
	
	private void setFeederSpeed(double speed) {
		motor.set(speed);
	}
}
