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
			turret.setPower(RobotMap.Turret.MIN_VOLTAGE);
		}
		turret.stop();
		double rightLimit = turret.getPosition();
		while(!turret.isLeftLimitSwitchClosed()) {
			turret.setPower(-RobotMap.Turret.MIN_VOLTAGE);
		}
		turret.stop();
		double leftLimit = turret.getPosition();
		turret.setAngle(turret.getAngle()+RobotMap.Turret.LEFT_LIMIT_TO_FORWARD);
		while(!turret.isPIDEnabled()){}
		double endPos = turret.getPosition();
		turret.stop();
		turret.resetPosition();
		turret.setAngle(0);
		turret.stop();
		turret.setSoftLimits(rightLimit-endPos+RobotMap.Turret.SOFT_LIMIT_OFFSET,
							leftLimit-endPos-RobotMap.Turret.SOFT_LIMIT_OFFSET);
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
