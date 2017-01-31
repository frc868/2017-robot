package org.usfirst.frc.team868.robot.subsystems;

import org.usfirst.frc.team868.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class AgitatorSubsystem extends Subsystem {

	public static AgitatorSubsystem instance;
	private Spark motor;
	private boolean state;
	
	@Override
	protected void initDefaultCommand() {}
	
	private AgitatorSubsystem() {
		motor = new Spark(RobotMap.Feeder.AGITATOR_MOTOR);
		motor.setInverted(RobotMap.Feeder.AGITATOR_IS_INVERTED);
	}
	
	public static AgitatorSubsystem getInstance() {
		return instance == null ? instance = new AgitatorSubsystem() : instance;
	}
	
	public void setAgitator(boolean on) {
		state = on;
		setAgitatorSpeed(RobotMap.Feeder.AGITATOR_SPEED);
	}
	
	public void setAgitatorOn() {
		setAgitator(true);
	}
	
	public void setAgitatorOff() {
		setAgitator(false);
	}
	
	public void toggleAgitator() {
		setAgitator(!state);
	}
	
	public boolean isAgitatorOn() {
		return state;
	}
	
	private void setAgitatorSpeed(double speed) {
		motor.set(speed);
	}
}
