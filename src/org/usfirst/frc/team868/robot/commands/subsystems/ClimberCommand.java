package org.usfirst.frc.team868.robot.commands.subsystems;

import org.usfirst.frc.team868.robot.subsystems.ClimberSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimberCommand extends Command {

	ClimberSubsystem climber;
	
    public ClimberCommand() {
        climber = ClimberSubsystem.getInstance();
    	requires(climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	climber.startClimbing();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	climber.stopClimbing();
    }
}
