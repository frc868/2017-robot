package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.commands.AutonChooser.StartingPoint;
import org.usfirst.frc.team868.robot.commands.groups.FeedAndShootCommandGroup;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistance;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GoToHopperCommandGroup extends CommandGroup {

    public GoToHopperCommandGroup(StartingPoint selected) {
    	double posOneAngle = CalculateAnglesAndDistances.getAngleFromSides(541.589574413, 232.5);
    	double posTwoAngle = CalculateAnglesAndDistances.getAngleFromSides(335.839574413, 232.5);
    	double posOneDistance = CalculateAnglesAndDistances.getHypotenuse(541.589574413, 232.5);
    	double posTwoDistance = CalculateAnglesAndDistances.getHypotenuse(335.839574413, 232.5);
    	switch(selected) {
    		case R1:
    			addSequential(new TurnToAngle(90 - posOneAngle));
    			addSequential(new DriveDistance(posOneDistance));
    			addSequential(new TurnToAngle(-90 - (90 - posOneAngle)));
    			addSequential(new DriveDistance(-75.660425587));
    			addSequential(new FeedAndShootCommandGroup());
    			break;
    		case R2:
    			addSequential(new TurnToAngle(90 - posTwoAngle));
    			addSequential(new DriveDistance(posTwoDistance));
    			addSequential(new TurnToAngle(-90 - (90 - posTwoAngle)));
    			addSequential(new DriveDistance(-75.660425587));
    			addSequential(new FeedAndShootCommandGroup());
    			break;
    		case R3:
    			addSequential(new DriveDistance(232.5));
    			addSequential(new TurnToAngle(90));
    			addSequential(new DriveDistance(205.75));
    			addSequential(new FeedAndShootCommandGroup());
    			break;
    		case B1:
    			addSequential(new TurnToAngle(-(90 -posOneAngle)));
    			addSequential(new DriveDistance(posOneDistance));
    			addSequential(new TurnToAngle((-90 - (90 - posOneAngle))));
    			addSequential(new DriveDistance(-75.660425587));
    			addSequential(new FeedAndShootCommandGroup());
    			break;
    		case B2:
    			addSequential(new TurnToAngle(90 - posTwoAngle));
    			addSequential(new DriveDistance(posTwoDistance));
    			addSequential(new TurnToAngle((-90 - (90 - posTwoAngle))));
    			addSequential(new DriveDistance(-75.660425587));
    			addSequential(new FeedAndShootCommandGroup());
    			break;
    		case B3:
    			addSequential(new DriveDistance(232.5));
    			addSequential(new TurnToAngle(-90));
    			addSequential(new DriveDistance(205.75));
    			addSequential(new FeedAndShootCommandGroup());
    			break;
    		default:
    			break;
    	}
    }
    
   
    
}

