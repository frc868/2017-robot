package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.commands.auton.AutonChooser.StartingPoint;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistance;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.TurnByAngleGyro;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearAuton extends CommandGroup {

    public GearAuton(StartingPoint selected) {
    	switch(selected) {
    		case B1:
    		case R3:
    			addSequential(new DriveDistance(RobotMap.AutonValues.GEAR_AUTON_DIST_1+35));
    			addSequential(new TurnByAngleGyro(-58, 1.5));
    			addSequential(new DriveDistance(RobotMap.AutonValues.GEAR_AUTON_DIST_2, true));
	    		addSequential(new DriveDistance(-RobotMap.AutonValues.HOOK_BACKOFF));
    			break;
    			
    		case B3:
    		case R1:
    			addSequential(new DriveDistance(RobotMap.AutonValues.GEAR_AUTON_DIST_1-5));//Test decreasing this distance by 1 inch or so
    			addSequential(new TurnByAngleGyro(60, 1.5));
    			addSequential(new DriveDistance(RobotMap.AutonValues.GEAR_AUTON_DIST_2, true));
    			addSequential(new DriveDistance(-RobotMap.AutonValues.HOOK_BACKOFF));
    			break;
    			
    		case B2:
    		case R2:
    			addSequential(new DriveDistance(RobotMap.AutonValues.WALL_TO_HOOK, true));
	    		addSequential(new DriveDistance(-RobotMap.AutonValues.HOOK_BACKOFF));
    			break;
    			
    		default:
    			break;
    	}
    }
}
