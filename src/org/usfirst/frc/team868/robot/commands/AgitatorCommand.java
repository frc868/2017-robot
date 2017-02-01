package org.usfirst.frc.team868.robot.commands;

import org.usfirst.frc.team868.robot.subsystems.AgitatorSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AgitatorCommand extends Command {

	AgitatorSubsystem agitator;
	
	boolean usingTimeout;
	boolean finished; //happens to always be the opposite of usingTimeout, but added for code clarity
	long endTime;

	public AgitatorCommand() { //TODO take boolean as argument
		this(0);
	}

	public AgitatorCommand(long millis) {
		usingTimeout = millis > 0;
		finished = !usingTimeout; //if not using timeout, we don't need to run execute()
		endTime = millis + getTimeMillis();
		
		agitator = AgitatorSubsystem.getInstance();
		requires(agitator);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		agitator.setAgitatorOn();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(usingTimeout && getTimeMillis() > endTime) {
			agitator.setAgitatorOff();
			finished = true;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return usingTimeout;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
	
	//TODO make library method
	public long getTimeMillis() {
		return (long) (Timer.getFPGATimestamp() * 1000);
	}
}
