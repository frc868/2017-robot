package org.usfirst.frc.team868.robot.commands.operator;

import org.usfirst.frc.team868.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team868.robot.subsystems.GearEjectorSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import lib.hid.ControllerMap;

/**
 *
 */
public class ArcadeDriveCommand extends Command {
	private static final double RUMBLE_TIME = .4;
	DriveSubsystem drive;
	ControllerMap controller;
	boolean direction;
	boolean previousAutoEjectState;
	double timeSinceToggled = 0;
	boolean isRumbling = false;

    public ArcadeDriveCommand(ControllerMap ctrl, boolean direction) {
        drive = DriveSubsystem.getInstance();
        requires(drive);
        controller = ctrl;
        this.direction = direction;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	previousAutoEjectState = GearEjectorSubsystem.getInstance().willGearAutoEject();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putBoolean("Driver Direction", direction);
    	double L, R;
    	if(direction){
    		L = controller.getForwardsLeftPower();
    		R = controller.getForwardsRightPower();
    	}else{
    		L = controller.getBackwardsLeftPower();
    		R = controller.getBackwardsRightPower();
    	}
    	if(controller.getAxis(ControllerMap.Key.LT) > .3){
    		L *= .5;
    		R *= .5;
    	}
    	drive.setL(L);
    	drive.setR(R);
    	
    	// Now goes through rumbling logic:  will rumble for RUMBLE_TIME if the gear auto-eject is toggled
    	if(previousAutoEjectState != GearEjectorSubsystem.getInstance().willGearAutoEject()){
    		timeSinceToggled = timeSinceInitialized();
    		previousAutoEjectState = !previousAutoEjectState;
    		controller.startRumble();
    		isRumbling = true;
    	}else if(isRumbling && timeSinceToggled+RUMBLE_TIME < timeSinceInitialized()){
    		controller.stopRumble();
    		isRumbling = false;
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
    	if(isRumbling){
    		controller.stopRumble();
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
