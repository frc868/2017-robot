package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.commands.auton.AutonChooser.StartingPoint;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistance;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.TurnToAngleGyro;
import org.usfirst.frc.team868.robot.commands.util.FeedAndShootCommandGroup;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class HopperAuton extends CommandGroup {

    public HopperAuton(StartingPoint selected) {
    	double posOneAngle = CalculateGeometry.getAngle(541.589574413, 232.5);
    	double posTwoAngle = CalculateGeometry.getAngle(335.839574413, 232.5);
    	double posOneDistance = CalculateGeometry.getHypotenuse(541.589574413, 232.5);
    	double posTwoDistance = CalculateGeometry.getHypotenuse(335.839574413, 232.5);
    	switch(selected) {
    		case R1:
    			addSequential(new TurnToAngleGyro(90 - posOneAngle));
    			addSequential(new DriveDistance(posOneDistance));
    			addSequential(new TurnToAngleGyro(-90 - (90 - posOneAngle)));
    			addSequential(new DriveDistance(-75.660425587));
    			addSequential(new FeedAndShootCommandGroup());
    			break;
    		case R2:
    			addSequential(new TurnToAngleGyro(90 - posTwoAngle));
    			addSequential(new DriveDistance(posTwoDistance));
    			addSequential(new TurnToAngleGyro(-90 - (90 - posTwoAngle)));
    			addSequential(new DriveDistance(-75.660425587));
    			addSequential(new FeedAndShootCommandGroup());
    			break;
    		case R3:
    			addSequential(new DriveDistance(232.5));
    			addSequential(new TurnToAngleGyro(90));
    			addSequential(new DriveDistance(205.75));
    			addSequential(new FeedAndShootCommandGroup());
    			break;
    		case B1:
    			addSequential(new TurnToAngleGyro(-(90 -posOneAngle)));
    			addSequential(new DriveDistance(posOneDistance));
    			addSequential(new TurnToAngleGyro((-90 - (90 - posOneAngle))));
    			addSequential(new DriveDistance(-75.660425587));
    			addSequential(new FeedAndShootCommandGroup());
    			break;
    		case B2:
    			addSequential(new TurnToAngleGyro(90 - posTwoAngle));
    			addSequential(new DriveDistance(posTwoDistance));
    			addSequential(new TurnToAngleGyro((-90 - (90 - posTwoAngle))));
    			addSequential(new DriveDistance(-75.660425587));
    			addSequential(new FeedAndShootCommandGroup());
    			break;
    		case B3:
    			addSequential(new DriveDistance(232.5));
    			addSequential(new TurnToAngleGyro(-90));
    			addSequential(new DriveDistance(205.75));
    			addSequential(new FeedAndShootCommandGroup());
    			break;
    		default:
    			break;
    	}
    }
    
   
    
}

