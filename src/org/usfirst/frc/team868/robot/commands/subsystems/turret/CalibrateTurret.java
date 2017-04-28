package org.usfirst.frc.team868.robot.commands.subsystems.turret;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.subsystems.TurretRotationSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CalibrateTurret extends Command {

	TurretRotationSubsystem turret;
	
    public CalibrateTurret() {
    	turret = TurretRotationSubsystem.getInstance();
    	requires(turret);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	turret.disableSoftLimits();
		while(!turret.isRightLimitSwitchClosed()){
			turret.setPower(RobotMap.Turret.MIN_VOLTAGE+.2);
		}
		turret.stop();
		double rightLimit = turret.getPosition();
		while(!turret.isLeftLimitSwitchClosed()) {
			turret.setPower(-RobotMap.Turret.MIN_VOLTAGE-.2);
		}
		turret.stop();
		double leftLimit = turret.getPosition();
		double rightMinusleft = rightLimit-leftLimit;
		turret.resetPosition();
		turret.setAngle(RobotMap.Turret.LEFT_LIMIT_TO_FORWARD);
		while(!turret.isOnTarget()){
			turret.setPower(RobotMap.Turret.MIN_VOLTAGE);
		}
		turret.stop();
		turret.resetPosition();
		double reverseLimit = -RobotMap.Turret.LEFT_LIMIT_TO_FORWARD*RobotMap.Turret.COUNTS_PER_DEGREE;
		turret.setSoftLimits(-reverseLimit-RobotMap.Turret.SOFT_LIMIT_OFFSET,
							 -reverseLimit+rightMinusleft+RobotMap.Turret.SOFT_LIMIT_OFFSET);
		if(turret.isPIDEnabled())
			turret.stop();
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
