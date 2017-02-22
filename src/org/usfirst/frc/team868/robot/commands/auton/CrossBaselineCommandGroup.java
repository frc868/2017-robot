package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.commands.AutonChooser.StartingPoint;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistance;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossBaselineCommandGroup extends CommandGroup {
    public CrossBaselineCommandGroup(StartingPoint selected) {
    	switch(selected) {
    		case B1:
    			addSequential(new DriveDistance(RobotMap.AutonValues.distanceToCrossBaselineFromPos1orPos3));
    			break;
    		case B2:
    			addSequential(new TurnToAngle(-45));
    			addSequential(new DriveDistance(RobotMap.AutonValues.distanceToCrossBaselineFromPos2));
    			break;
    		case B3:
    			addSequential(new DriveDistance(RobotMap.AutonValues.distanceToCrossBaselineFromPos1orPos3));
    			break;
    		case R1:
    			addSequential(new DriveDistance(RobotMap.AutonValues.distanceToCrossBaselineFromPos1orPos3));
    			break;
    		case R2:
    			addSequential(new TurnToAngle(-45));
    			addSequential(new DriveDistance(RobotMap.AutonValues.distanceToCrossBaselineFromPos2));
    			break;
    		case R3:
    			addSequential(new DriveDistance(RobotMap.AutonValues.distanceToCrossBaselineFromPos1orPos3));
    			break;
    		default:
    			break;
    	}
    }
}

