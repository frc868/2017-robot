package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.commands.auton.AutonChooser.StartingPoint;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistance;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.TurnToAngleGyro;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearToNeutralCommandGroup extends CommandGroup {

    public GearToNeutralCommandGroup(StartingPoint selected) {
    	switch(selected) {
	    	case B1:
	    		addSequential(new DriveDistance(-RobotMap.AutonValues.distanceToBackupMoreFromPos1orPos3));
	    		addSequential(new TurnToAngleGyro(0));
	    		addSequential(new DriveDistance(RobotMap.AutonValues.distanceToNeutralZoneFromGear));
	    		break;
	    	case B2:
	    		addSequential(new TurnToAngleGyro(90));
	    		addSequential(new DriveDistance(RobotMap.AutonValues.distanceAcrossFromPos2));
	    		addSequential(new TurnToAngleGyro(0));
	    		addSequential(new DriveDistance(RobotMap.AutonValues.distanceToNeutralZoneFromGear));
	    		break;
	    	case B3:
	    		addSequential(new DriveDistance(-RobotMap.AutonValues.distanceToBackupMoreFromPos1orPos3));
	    		addSequential(new TurnToAngleGyro(0));
	    		addSequential(new DriveDistance(RobotMap.AutonValues.distanceToNeutralZoneFromGear));
	    		break;
	    	case R1:
	    		addSequential(new DriveDistance(-RobotMap.AutonValues.distanceToBackupMoreFromPos1orPos3));
	    		addSequential(new TurnToAngleGyro(0));
	    		addSequential(new DriveDistance(RobotMap.AutonValues.distanceToNeutralZoneFromGear));
	    		break;
	    	case R2:
	    		addSequential(new TurnToAngleGyro(-90));
	    		addSequential(new DriveDistance(RobotMap.AutonValues.distanceAcrossFromPos2));
	    		addSequential(new TurnToAngleGyro(0));
	    		addSequential(new DriveDistance(RobotMap.AutonValues.distanceToNeutralZoneFromGear));
	    		break;
	    	case R3:
	    		addSequential(new DriveDistance(-RobotMap.AutonValues.distanceToBackupMoreFromPos1orPos3));
	    		addSequential(new TurnToAngleGyro(0));
	    		addSequential(new DriveDistance(RobotMap.AutonValues.distanceToNeutralZoneFromGear));
	    		break;
	    	default: 
	    		break;
    	}
    }
}
