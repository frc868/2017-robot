package org.usfirst.frc.team868.robot.commands.operator;

import org.usfirst.frc.team868.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import lib.hid.ControllerMap;

/**
 *
 */
public class ArcadeDriveCommand extends Command {
	DriveSubsystem drive;
	ControllerMap controller;
	boolean direction;

    public ArcadeDriveCommand(ControllerMap ctrl, boolean direction) {
        drive = DriveSubsystem.getInstance();
        requires(drive);
        controller = ctrl;
        this.direction = direction;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putBoolean("Driver Direction", direction);
    	if(direction){
    		drive.setL(controller.getForwardsLeftPower());
    		drive.setR(controller.getForwardsRightPower());
    	}else{
        	drive.setL(controller.getBackwardsLeftPower());
        	drive.setR(controller.getBackwardsRightPower());
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	drive.setL(0);
    	drive.setR(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
