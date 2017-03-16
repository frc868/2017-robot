package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.commands.auton.AutonChooser.StartingPoint;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistance;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.TurnToAngle;
import org.usfirst.frc.team868.robot.commands.util.FeedAndShootCommandGroup;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GoToHopperCommandGroup extends CommandGroup {

    public GoToHopperCommandGroup(StartingPoint selected) {
    	double posOneAngle = CalculateGeometry.getAngle(541.589574413, 232.5);
    	double posTwoAngle = CalculateGeometry.getAngle(335.839574413, 232.5);
    	double posOneDistance = CalculateGeometry.getHypotenuse(541.589574413, 232.5);
    	double posTwoDistance = CalculateGeometry.getHypotenuse(335.839574413, 232.5);
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

