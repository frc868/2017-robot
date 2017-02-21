package org.usfirst.frc.team868.robot.commands.subsystems;

import org.usfirst.frc.team868.robot.Robot;
import org.usfirst.frc.team868.robot.subsystems.AgitatorSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AgitatorCommand extends Command {

	AgitatorSubsystem agitator;
	
	boolean state;
	
	/**
	 * Sets the agitator to the given argument
	 * @param on
	 */
	public AgitatorCommand(boolean on) {
		
		state = on;
		agitator = Robot.agitator;
		requires(agitator);
	}
    
    /**
     * Toggles the agitator's on/off status
     */
    public AgitatorCommand(){
    	agitator = Robot.agitator;
    	requires(agitator);
    }

	// Called just before this Command runs the first time
	protected void initialize() {
		agitator.toggleAgitator();;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}	
}
