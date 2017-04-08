package org.usfirst.frc.team868.robot.commands.subsystems.turret;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.subsystems.IRPixySubsystem;
import org.usfirst.frc.team868.robot.subsystems.TurretRotationSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurretIRLockToTarget extends Command {

	TurretRotationSubsystem turret;
	IRPixySubsystem pixy;
	double xDegreesOff;
	final double MARGIN = .1;
	final double MULTIPLIER = .2;
	
    public TurretIRLockToTarget() {
    	pixy = IRPixySubsystem.getInstance();
    	turret = TurretRotationSubsystem.getInstance();
    	requires(turret);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	xDegreesOff = pixy.getTarget().getXAngleOffFromCenter();
    	if(xDegreesOff > MARGIN){
    		turret.setPower(RobotMap.Turret.MIN_VOLTAGE+(xDegreesOff*MULTIPLIER));
    	}else if(xDegreesOff < -MARGIN){
    		turret.setPower(-RobotMap.Turret.MIN_VOLTAGE-(xDegreesOff*MULTIPLIER));
    	}else{
    		turret.setPower(0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timeSinceInitialized() > 1;
    }

    // Called once after isFinished returns true
    protected void end() {
    	turret.setAngle(turret.getAngle());
    }
}
