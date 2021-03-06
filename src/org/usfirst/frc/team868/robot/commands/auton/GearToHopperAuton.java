package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.commands.auton.AutonChooser.StartingPoint;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.OldDriveDistance;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.TurnToAngleGyro;
import org.usfirst.frc.team868.robot.commands.util.FeedAndShootCommandGroup;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearToHopperAuton extends CommandGroup {

    public GearToHopperAuton(StartingPoint selected) {
    	switch(selected) {
    	case B1:
    		addSequential(new GearToNeutralAuton(selected));
    		break;
    	case B2:
    		addSequential(new TurnToAngleGyro(-90));
    		addSequential(new OldDriveDistance(RobotMap.AutonValues.distanceAcrossFromPos2));
    		addSequential(new TurnToAngleGyro(0));
    		addSequential(new OldDriveDistance(RobotMap.AutonValues.distanceForwardToHitHopperAfterDoingGearPos2));
    		addSequential(new TurnToAngleGyro(RobotMap.AutonValues.angleToTurnToHitHopperAfterDoingGearPos2));
    		addSequential(new OldDriveDistance(-RobotMap.AutonValues.distanceToBackUpToHitHopperAfterDoingGearPos2));
    		addSequential(new TurnToAngleGyro(0));
    		addSequential(new OldDriveDistance(-10 - 33.5));
    		addSequential(new FeedAndShootCommandGroup());
    		break;
    	case B3:
    		addSequential(new TurnToAngleGyro(-135));
    		addSequential(new OldDriveDistance(230));
    		addSequential(new OldDriveDistance(-100));
    		addSequential(new TurnToAngleGyro(-90));
    		addSequential(new OldDriveDistance(100));
    		addSequential(new FeedAndShootCommandGroup());
    		break;
    	case R1:
    		addSequential(new GearToNeutralAuton(selected));
    		break;
    	case R2:
    		addSequential(new TurnToAngleGyro(90));
    		addSequential(new OldDriveDistance(RobotMap.AutonValues.distanceAcrossFromPos2));
    		addSequential(new TurnToAngleGyro(0));
    		addSequential(new OldDriveDistance(RobotMap.AutonValues.distanceForwardToHitHopperAfterDoingGearPos2));
    		addSequential(new TurnToAngleGyro(-RobotMap.AutonValues.angleToTurnToHitHopperAfterDoingGearPos2));
    		addSequential(new OldDriveDistance(-RobotMap.AutonValues.distanceToBackUpToHitHopperAfterDoingGearPos2));
    		addSequential(new TurnToAngleGyro(0));
    		addSequential(new OldDriveDistance(-10 - 33.5));
    		addSequential(new FeedAndShootCommandGroup());
    		break;
    	case R3:
    		addSequential(new OldDriveDistance(-5));
    		addSequential(new TurnToAngleGyro(-90));
    		addSequential(new OldDriveDistance(-RobotMap.AutonValues.distanceToGoToHopperFromGearPos3 + (5 / Math.sqrt(2))));
    		addSequential(new TurnToAngleGyro(-RobotMap.AutonValues.angleToTurnToHitHopperAfterDoingGearPos3));
    		addSequential(new OldDriveDistance(-RobotMap.AutonValues.distanceToBackUpToHitHopperAfterDoingGearPos3));
    		addSequential(new TurnToAngleGyro(0));
    		addSequential(new FeedAndShootCommandGroup());
    		break;
    	default: 
    		System.err.println("R.I.P.");
    		break;
    	}
    }
}
