package org.usfirst.frc.team868.robot.commands.subsystems.turret;

import org.usfirst.frc.team868.robot.Robot;
import org.usfirst.frc.team868.robot.subsystems.IRPixySubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class RotateBackToTarget extends Command {

	private IRPixySubsystem camera;
	private double distance_from_target,  angle_from_robot, movement_distance_locked, movement_distance_not_locked,  movement_angle;
	private boolean lockedOn = false;
	private boolean hasLockedOn = false;
	
    public RotateBackToTarget() {
    	camera = Robot.irPixy;
    	requires(camera);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	lockedOn();
    	if (lockedOn) {
    		updateLocked();
    	} else if (hasLockedOn) {
    		updateNotLocked();
        	new RotateTurretToAngle(angle()).start();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
    
  
	
    public void lockedOn() {
    	lockedOn = Math.abs((int) Robot.colorPixy.getTarget().getXAngleOffFromCenter()) < 1;
    	if (lockedOn && !hasLockedOn) hasLockedOn = true;
    	
    }
    
    public void updateLocked() {
    	distance_from_target = Robot.lidar.getDistance();
    	movement_distance_locked = Robot.drivetrain.getAvgEncoders();
    	angle_from_robot = Robot.turret.getAngle();
    	
    } 
    
    public void updateNotLocked() {
    	movement_angle = Robot.gyro.getRotation();
    	movement_distance_not_locked = Robot.drivetrain.getAvgEncoders();
    }

    public double angle() 
	{
    	double movement_distance = movement_distance_locked - movement_distance_not_locked;
		double p1_1 = Math.cos(movement_angle) * movement_distance - Math.cos(angle_from_robot) * distance_from_target;
		double p1_2 = Math.sin(movement_angle) * movement_distance - Math.sin(angle_from_robot) * distance_from_target;
		double p1 = Math.atan((p1_1/p1_2));
		double p2 = 180 - angle_from_robot - (90 - movement_angle);
		return p1 + p2;
	
	}
}
