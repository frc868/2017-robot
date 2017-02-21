package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.commands.AutonChooser.StartingPoint;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistance;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.RotateAngle;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.RotateUsingColorPixy;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.TurnToAngle;
import org.usfirst.frc.team868.robot.commands.subsystems.gear.GearReleaseCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DropOffGearAutonCommandGroup extends CommandGroup {

    public DropOffGearAutonCommandGroup(StartingPoint selected) {
    	switch(selected) {
    		case B1:
    			addSequential(new DriveDistance(RobotMap.AutonValues.distanceToGearBeforeAngleFromPos1orPos3));
    			addSequential(new RotateAngle(-(90 - RobotMap.AutonValues.angleToGearFromPos1orPos3)));
    			addSequential(new RotateUsingColorPixy());
    			addSequential(new DriveDistance(RobotMap.AutonValues.distanceToGearAfterAngleFromPos1orPos3));
    			addSequential(new GearReleaseCommand());
    			addSequential(new DriveDistance(-RobotMap.AutonValues.distanceToBackup));
    			break;
    		case B2:
    			addSequential(new DriveDistance(RobotMap.AutonValues.distanceToGearFromPos2 - 30));
    			addSequential(new RotateUsingColorPixy());
    			addSequential(new DriveDistance(30));
	    		addSequential(new GearReleaseCommand());
	    		addSequential(new DriveDistance(-RobotMap.AutonValues.distanceToBackup));
    			break;
    		case B3:
    			addSequential(new DriveDistance(RobotMap.AutonValues.distanceToGearBeforeAngleFromPos1orPos3));
    			addSequential(new RotateAngle(90 - RobotMap.AutonValues.angleToGearFromPos1orPos3));
    			addSequential(new RotateUsingColorPixy());
    			addSequential(new DriveDistance(RobotMap.AutonValues.distanceToGearAfterAngleFromPos1orPos3));
    			addSequential(new GearReleaseCommand());
    			break;
    		case R1:
    			addSequential(new DriveDistance(RobotMap.AutonValues.distanceToGearBeforeAngleFromPos1orPos3));
    			addSequential(new RotateAngle(90 - RobotMap.AutonValues.angleToGearFromPos1orPos3));
    			addSequential(new RotateUsingColorPixy());
    			addSequential(new DriveDistance(RobotMap.AutonValues.distanceToGearAfterAngleFromPos1orPos3));
    			addSequential(new GearReleaseCommand());
    			addSequential(new DriveDistance(-RobotMap.AutonValues.distanceToBackup));
    			break;
    		case R2:
    			addSequential(new DriveDistance(RobotMap.AutonValues.distanceToGearFromPos2 - 30));
    			addSequential(new RotateUsingColorPixy());
    			addSequential(new DriveDistance(30));
	    		addSequential(new GearReleaseCommand());
	    		addSequential(new DriveDistance(-RobotMap.AutonValues.distanceToBackup));
    			break;
    		case R3:
    			addSequential(new DriveDistance(RobotMap.AutonValues.distanceToGearBeforeAngleFromPos1orPos3));
    			addSequential(new RotateAngle(-(90 - RobotMap.AutonValues.angleToGearFromPos1orPos3)));
    			addSequential(new RotateUsingColorPixy());
    			addSequential(new DriveDistance(RobotMap.AutonValues.distanceToGearAfterAngleFromPos1orPos3));
    			addSequential(new GearReleaseCommand());
    			break;
    		default:
    			break;
    	}
    }
}
