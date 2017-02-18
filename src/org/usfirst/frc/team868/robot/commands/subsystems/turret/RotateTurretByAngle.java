package org.usfirst.frc.team868.robot.commands.subsystems.turret;

import org.usfirst.frc.team868.robot.subsystems.TurretRotationSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateTurretByAngle extends Command {

    private double angle;

    /**
     * Rotates the turret by angle relative to its position when this command
     * was called.
     * 
     * @param angle
     *            in degrees
     */
    public RotateTurretByAngle(double angle) {
        this.angle = TurretRotationSubsystem.getInstance().getAngle() + angle;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        new RotateTurretToAngle(angle).start();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
