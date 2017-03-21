package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.commands.auton.AutonChooser.StartingPoint;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BaselineAuton extends CommandGroup {
    public BaselineAuton(StartingPoint selected) {
    	switch(selected) {
    		case B1:
    			addSequential(new DriveDistance(RobotMap.AutonValues.DISTANCE_TO_BASELINE));
    			break;
    		case B2:
    			addSequential(new DriveDistance(RobotMap.AutonValues.BASELINE_MIDDLE_DISTANCE));
    			break;
    		case B3:
    			addSequential(new DriveDistance(RobotMap.AutonValues.DISTANCE_TO_BASELINE));
    			break;
    		case R1:
    			addSequential(new DriveDistance(RobotMap.AutonValues.DISTANCE_TO_BASELINE));
    			break;
    		case R2:
    			addSequential(new DriveDistance(RobotMap.AutonValues.BASELINE_MIDDLE_DISTANCE));
    			break;
    		case R3:
    			addSequential(new DriveDistance(RobotMap.AutonValues.DISTANCE_TO_BASELINE));
    			break;
    		default:
    			break;
    	}
    }
}

