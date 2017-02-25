package org.usfirst.frc.team868.robot.commands.subsystems.turret;

import org.usfirst.frc.team868.robot.OI;
import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.subsystems.TurretRotationSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
	    	double x = OI.getInstance().getOperator().getAxis(ControllerMap.Direction.LEFT_HORIZONTAL);
	    	double y = -OI.getInstance().getOperator().getAxis(ControllerMap.Direction.LEFT_VERTICAL);
	    	double angle;
	    	if(x == 0 && y == 0){
	    		turret.stop();
	        	double x2 = OI.getInstance().getOperator().getAxis(ControllerMap.Direction.RIGHT_HORIZONTAL);
	        	SmartDashboard.putNumber("Right analog stick horizontal", x2);
	        	double mult = .25+(OI.getInstance().getOperator().getAxis(OI.Controls.ADJUSTMENT_MULTIPLIER)/2);
	        	if(x2 > 0)
	        		turret.setPower(RobotMap.Turret.MAX_VOLTAGE*(x2-.05)*mult+RobotMap.Turret.MIN_VOLTAGE-.5);
	        	else if(x2 < 0)
	        		turret.setPower(RobotMap.Turret.MAX_VOLTAGE*(x2+.05)*mult-RobotMap.Turret.MIN_VOLTAGE+.5);
	        	else
	        		turret.setPower(0);
		    }else{
		    	if(Math.abs(x) > Math.abs(y)){
		    		if(x > 0)
		    			angle = 90 - Math.asin(y/x);
		    		else
		    			angle = 270 - Math.asin(y/x);
		    	}else if(Math.abs(x) < Math.abs(y)){
		    		if(y > 0)
		    			angle = Math.asin(x/y);
		    		else
		    			angle = 180 + Math.asin(x/y);
		    	}else if(x == y && y > 0)
		    		angle = 45;
		    	else if(x == y && y < 0)
		    		angle = 225;
		    	else if(x == -y && x > 0)
		    		angle = 135;
		    	else
		    		angle = -45;
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
