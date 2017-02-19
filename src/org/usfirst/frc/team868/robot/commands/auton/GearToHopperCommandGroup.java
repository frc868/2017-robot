package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.commands.AutonChooser.StartingPoint;
import org.usfirst.frc.team868.robot.commands.groups.FeedAndShootCommandGroup;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistanceCommand;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearToHopperCommandGroup extends CommandGroup {

    public GearToHopperCommandGroup(StartingPoint selected) {
    	switch(selected) {
    	case B1:
    		addSequential(new DriveDistanceCommand(-75));
    		addSequential(new TurnToAngle(45));
    		addSequential(new DriveDistanceCommand(300));
    		addSequential(new TurnToAngle(-90));
    		addSequential(new DriveDistanceCommand(600));
    		addSequential(new TurnToAngle(-90));
    		addSequential(new DriveDistanceCommand(250));
    		addSequential(new TurnToAngle(90));
    		addSequential(new DriveDistanceCommand(220));
    		break;
    	case B2:
    		addSequential(new TurnToAngle(-90));
    		addSequential(new DriveDistanceCommand(411.5 - (Math.sqrt(107) /2)));
    		addSequential(new TurnToAngle(90));
    		addSequential(new DriveDistanceCommand(33.5));
    		addSequential(new TurnToAngle(-90));
    		addSequential(new DriveDistanceCommand(Math.sqrt(107) /2));
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
    		addSequential(new DriveDistanceCommand(-75));
    		addSequential(new TurnToAngle(-45));
    		addSequential(new DriveDistanceCommand(300));
    		addSequential(new TurnToAngle(90));
    		addSequential(new DriveDistanceCommand(600));
    		addSequential(new TurnToAngle(90));
    		addSequential(new DriveDistanceCommand(250));
    		addSequential(new TurnToAngle(-90));
    		addSequential(new DriveDistanceCommand(220));
    		break;
    	case R2:
    		addSequential(new TurnToAngle(90));
    		addSequential(new DriveDistanceCommand(411.5 - (Math.sqrt(107) /2)));
    		addSequential(new TurnToAngle(-90));
    		addSequential(new DriveDistanceCommand(33.5));
    		addSequential(new TurnToAngle(90));
    		addSequential(new DriveDistanceCommand(Math.sqrt(107) /2));
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
