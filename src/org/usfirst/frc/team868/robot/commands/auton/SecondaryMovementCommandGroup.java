package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.commands.AutonChooser.StartingPoint;
import org.usfirst.frc.team868.robot.commands.groups.FeedAndShootCommandGroup;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveAndTurn;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistanceCommand;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.TurnToAngle;
import org.usfirst.frc.team868.robot.commands.subsystems.gear.GearReleaseCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SecondaryMovementCommandGroup extends CommandGroup {

    public SecondaryMovementCommandGroup(StartingPoint selected) {
    	switch(selected) {
	    	case B1:
	    		addSequential(new TurnToAngle(45));
	    		addSequential(new DriveDistanceCommand(800));
	    		break;
	    	case B2:
	    		addSequential(new TurnToAngle(-90));
	    		addSequential(new DriveDistanceCommand(310));
	    		addSequential(new TurnToAngle(90));
	    		addSequential(new DriveDistanceCommand(120));
	    		addSequential(new TurnToAngle(-90));
	    		addSequential(new DriveDistanceCommand(100));
	    		addSequential(new DriveDistanceCommand(-100));
	    		addSequential(new TurnToAngle(-90));
	    		addSequential(new DriveDistanceCommand(100));
	    		addSequential(new FeedAndShootCommandGroup());
	    		break;
	    	case B3:
	    		addSequential(new TurnToAngle(-135));
	    		addSequential(new DriveDistanceCommand(230));
	    		addSequential(new DriveDistanceCommand(-100));
	    		addSequential(new TurnToAngle(-90));
	    		addSequential(new DriveDistanceCommand(100));
	    		addSequential(new FeedAndShootCommandGroup());
	    		break;
	    	case R1:
	    		addSequential(new TurnToAngle(-45));
	    		addSequential(new DriveDistanceCommand(800));
	    		break;
	    	case R2:
	    		addSequential(new TurnToAngle(90));
	    		addSequential(new DriveDistanceCommand(310));
	    		addSequential(new TurnToAngle(-90));
	    		addSequential(new DriveDistanceCommand(120));
	    		addSequential(new TurnToAngle(90));
	    		addSequential(new DriveDistanceCommand(100));
	    		addSequential(new DriveDistanceCommand(-100));
	    		addSequential(new TurnToAngle(90));
	    		addSequential(new DriveDistanceCommand(100));
	    		addSequential(new FeedAndShootCommandGroup());
	    		break;
	    	case R3:
	    		addSequential(new TurnToAngle(135));
	    		addSequential(new DriveDistanceCommand(230));
	    		addSequential(new DriveDistanceCommand(-100));
	    		addSequential(new TurnToAngle(90));
	    		addSequential(new DriveDistanceCommand(100));
	    		addSequential(new FeedAndShootCommandGroup());
	    		break;
	    	default: 
	    		break;
    	}
    }
}
