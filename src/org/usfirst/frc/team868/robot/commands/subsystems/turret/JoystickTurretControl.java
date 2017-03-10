package org.usfirst.frc.team868.robot.commands.subsystems.turret;

import org.usfirst.frc.team868.robot.OI;
import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.subsystems.TurretRotationSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import lib.hid.ControllerMap;

/**
 *
 */
public class JoystickTurretControl extends Command {

	private TurretRotationSubsystem turret;
	
	public JoystickTurretControl(){
    	turret = TurretRotationSubsystem.getInstance();
    	requires(turret);
	}

    protected void initialize() {
    	turret.toggleTargeting();
    }

    protected void execute() {
    	if(!turret.isPixyTargeting()){
	    	double x = OI.getInstance().getOperator().getAxis(ControllerMap.Direction.RIGHT_HORIZONTAL);
	    	double y = -OI.getInstance().getOperator().getAxis(ControllerMap.Direction.RIGHT_VERTICAL);
	    	double angle;
	    	if(x == 0 && y == 0){
	    		turret.stop();
	        	double x2 = OI.getInstance().getOperator().getAxis(ControllerMap.Direction.LEFT_HORIZONTAL);
	        	double mult = .25+(OI.getInstance().getOperator().getAxis(OI.Controls.ADJUSTMENT_MULTIPLIER)/2);
	        	if(x2 < 0)
	        		turret.setPower(RobotMap.Turret.MAX_VOLTAGE*(x2-.05)*mult+RobotMap.Turret.MIN_VOLTAGE-.5);
	        	else if(x2 > 0)
	        		turret.setPower(RobotMap.Turret.MAX_VOLTAGE*(x2+.05)*mult-RobotMap.Turret.MIN_VOLTAGE+.5);
	        	else
	        		turret.setPower(0);
		    }else{
		    	if(y == 0){
		    		if(x > 0)
		    			angle = 90;
		    		else
		    			angle = 270;
		    	}else if(y > 0){
		    		angle = Math.toDegrees(Math.atan(x/y));
		    	}else{
		    		angle = 180 + Math.toDegrees(Math.atan(x/y));
		    	}
		    	turret.setAngle(angle); 
	    	}
    	}else{
    		new RotateUsingIRPixy().start();
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	turret.setPower(0);
    }
}
