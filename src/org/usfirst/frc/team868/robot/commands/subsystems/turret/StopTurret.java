package org.usfirst.frc.team868.robot.commands.subsystems.turret;

import org.usfirst.frc.team868.robot.subsystems.TurretRotationSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StopTurret extends Command {

    private TurretRotationSubsystem turret;

	public StopTurret() {
    	turret = TurretRotationSubsystem.getInstance();
    	requires(turret);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	turret.setPower(0);
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
}
