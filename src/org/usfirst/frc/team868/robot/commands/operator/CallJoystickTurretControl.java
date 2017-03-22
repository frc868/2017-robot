package org.usfirst.frc.team868.robot.commands.operator;

import org.usfirst.frc.team868.robot.subsystems.TurretRotationSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CallJoystickTurretControl extends Command {
	
	TurretRotationSubsystem turret;
	
    public CallJoystickTurretControl() {
    	turret = TurretRotationSubsystem.getInstance();
    	requires(turret);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	new JoystickTurretControl().start();
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
