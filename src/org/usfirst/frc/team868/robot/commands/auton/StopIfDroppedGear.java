package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.subsystems.GearEjectorSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StopIfDroppedGear extends Command {

    public StopIfDroppedGear() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !GearEjectorSubsystem.getInstance().isGearEjectorOpen();
    }

    // Called once after isFinished returns true
    protected void end() {
    }
}
