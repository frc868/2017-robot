package org.usfirst.frc.team868.robot.commands.subsystems.turret;

import org.usfirst.frc.team868.robot.subsystems.TurretRotationSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateTurretToAngle extends Command {

    TurretRotationSubsystem turret;
    double angle;

    /**
     * Rotates the turret to the position value of the given angle.
     * (Note that this is not relative to where the turret is when this command
     * is called)
     * 
     * @param absoluteAngle
     *            in degrees
     */
    public RotateTurretToAngle(double absoluteAngle) {
        turret = TurretRotationSubsystem.getInstance();
        requires(turret);
        angle = absoluteAngle;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        turret.setPosition(angle); // convert to encoder counts?
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
