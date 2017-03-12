package org.usfirst.frc.team868.robot.commands.subsystems.turret;

import org.usfirst.frc.team868.robot.Robot;
import org.usfirst.frc.team868.robot.subsystems.IRPixySubsystem;
import org.usfirst.frc.team868.robot.subsystems.TurretRotationSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurretLookForTarget extends Command {

	private TurretRotationSubsystem turret;
	private IRPixySubsystem camera;
	private boolean isTurningLeft = true;
	
    public TurretLookForTarget() {
    	turret = Robot.turret;
    	requires(turret);
    	camera = Robot.irPixy;
    }

    protected void initialize() {
    }

    protected void execute() {
    	if(!turret.isALimitSwitchClosed()){
    		if(isTurningLeft)
    			turret.setPower(-.45);
    		else
    			turret.setPower(.45);
    	}else
    		isTurningLeft = !isTurningLeft;
    }

    protected boolean isFinished() {
        return camera.getTarget().getXAngleOffFromCenter() != 0;
    }

    protected void end() {
    	turret.setPower(0);
    	new RotateUsingIRPixy().start();
    }
}
