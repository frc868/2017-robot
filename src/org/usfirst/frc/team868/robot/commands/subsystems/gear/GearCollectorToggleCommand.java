package org.usfirst.frc.team868.robot.commands.subsystems.gear;

import org.usfirst.frc.team868.robot.Robot;
import org.usfirst.frc.team868.robot.subsystems.GearCollectorSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearCollectorToggleCommand extends Command {
	
	private GearCollectorSubsystem holder;

    public GearCollectorToggleCommand() {
    	holder = Robot.gearCollector;
    	requires(holder);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	holder.toggleGearCollector();
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
