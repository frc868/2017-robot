package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.commands.AutonChooser.StartingPoint;
import org.usfirst.frc.team868.robot.commands.groups.FeedAndShootCommandGroup;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistance;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearToHopperCommandGroup extends CommandGroup {

    public GearToHopperCommandGroup(StartingPoint selected) {
    	switch(selected) {
    	case B1:
    		addSequential(new GearToNeutralCommandGroup(selected));
    		break;
    	case B2:
    		addSequential(new TurnToAngle(-90));
    		addSequential(new DriveDistance(RobotMap.AutonValues.distanceAcrossFromPos2));
    		addSequential(new TurnToAngle(0));
    		addSequential(new DriveDistance(RobotMap.AutonValues.distanceForwardToHitHopperAfterDoingGearPos2));
    		addSequential(new TurnToAngle(RobotMap.AutonValues.angleToTurnToHitHopperAfterDoingGearPos2));
    		addSequential(new DriveDistance(-RobotMap.AutonValues.distanceToBackUpToHitHopperAfterDoingGearPos2));
    		addSequential(new TurnToAngle(0));
    		addSequential(new DriveDistance(-10 - 33.5));
    		addSequential(new FeedAndShootCommandGroup());
    		break;
    	case B3:
    		addSequential(new TurnToAngle(-135));
    		addSequential(new DriveDistance(230));
    		addSequential(new DriveDistance(-100));
    		addSequential(new TurnToAngle(-90));
    		addSequential(new DriveDistance(100));
    		addSequential(new FeedAndShootCommandGroup());
    		break;
    	case R1:
    		addSequential(new GearToNeutralCommandGroup(selected));
    		break;
    	case R2:
    		addSequential(new TurnToAngle(90));
    		addSequential(new DriveDistance(RobotMap.AutonValues.distanceAcrossFromPos2));
    		addSequential(new TurnToAngle(0));
    		addSequential(new DriveDistance(RobotMap.AutonValues.distanceForwardToHitHopperAfterDoingGearPos2));
    		addSequential(new TurnToAngle(-RobotMap.AutonValues.angleToTurnToHitHopperAfterDoingGearPos2));
    		addSequential(new DriveDistance(-RobotMap.AutonValues.distanceToBackUpToHitHopperAfterDoingGearPos2));
    		addSequential(new TurnToAngle(0));
    		addSequential(new DriveDistance(-10 - 33.5));
    		addSequential(new FeedAndShootCommandGroup());
    		break;
    	case R3:
    		addSequential(new DriveDistance(-5));
    		addSequential(new TurnToAngle(-90));
    		addSequential(new DriveDistance(-RobotMap.AutonValues.distanceToGoToHopperFromGearPos3 + (5 / Math.sqrt(2))));
    		addSequential(new TurnToAngle(-RobotMap.AutonValues.angleToTurnToHitHopperAfterDoingGearPos3));
    		addSequential(new DriveDistance(-RobotMap.AutonValues.distanceToBackUpToHitHopperAfterDoingGearPos3));
    		addSequential(new TurnToAngle(0));
    		addSequential(new FeedAndShootCommandGroup());
    		break;
    	default: 
    		System.err.println("R.I.P.");
    		break;
    	}
    }
}
