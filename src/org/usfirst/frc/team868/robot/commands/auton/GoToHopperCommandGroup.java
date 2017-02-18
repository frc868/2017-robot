package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.commands.AutonChooser.StartingPoint;
import org.usfirst.frc.team868.robot.commands.groups.FeedAndShootCommandGroup;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistanceCommand;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GoToHopperCommandGroup extends CommandGroup {

    public GoToHopperCommandGroup(StartingPoint selected) {
    	CalculateAnglesAndDistances posOne = new CalculateAnglesAndDistances(541.589574413, 232.5); 
    	CalculateAnglesAndDistances posTwo = new CalculateAnglesAndDistances(335.839574413, 232.5);
    	switch(selected) {
    		case R1:
    			addSequential(new TurnToAngle(90 - posOne.getAngle()));
    			addSequential(new DriveDistanceCommand(posOne.getHypotenuse()));
    			addSequential(new TurnToAngle(-90 - (90 - posOne.getAngle())));
    			addSequential(new DriveDistanceCommand(-75.660425587));
    			addSequential(new FeedAndShootCommandGroup());
    			break;
    		case R2:
    			addSequential(new TurnToAngle(90 - posTwo.getAngle()));
    			addSequential(new DriveDistanceCommand(posTwo.getHypotenuse()));
    			addSequential(new TurnToAngle(-90 - (90 - posTwo.getAngle())));
    			addSequential(new DriveDistanceCommand(-75.660425587));
    			addSequential(new FeedAndShootCommandGroup());
    			break;
    		case R3:
    			addSequential(new DriveDistanceCommand(232.5));
    			addSequential(new TurnToAngle(90));
    			addSequential(new DriveDistanceCommand(205.75));
    			addSequential(new FeedAndShootCommandGroup());
    			break;
    		case B1:
    			addSequential(new TurnToAngle(-(90 -posOne.getAngle())));
    			addSequential(new DriveDistanceCommand(posOne.getHypotenuse()));
    			addSequential(new TurnToAngle((-90 - (90 - posOne.getAngle()))));
    			addSequential(new DriveDistanceCommand(-75.660425587));
    			addSequential(new FeedAndShootCommandGroup());
    			break;
    		case B2:
    			addSequential(new TurnToAngle(90 - posTwo.getAngle()));
    			addSequential(new DriveDistanceCommand(posTwo.getHypotenuse()));
    			addSequential(new TurnToAngle((-90 - (90 - posTwo.getAngle()))));
    			addSequential(new DriveDistanceCommand(-75.660425587));
    			addSequential(new FeedAndShootCommandGroup());
    			break;
    		case B3:
    			addSequential(new DriveDistanceCommand(232.5));
    			addSequential(new TurnToAngle(-90));
    			addSequential(new DriveDistanceCommand(205.75));
    			addSequential(new FeedAndShootCommandGroup());
    			break;
    		default:
    			break;
    	}
    }
    
   
    
}

