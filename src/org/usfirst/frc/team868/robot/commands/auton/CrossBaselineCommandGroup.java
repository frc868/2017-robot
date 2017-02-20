package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.commands.AutonChooser.StartingPoint;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistance;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossBaselineCommandGroup extends CommandGroup {

	double distanceFromSides = 250;
	double distanceFromCenter = 180;
    public CrossBaselineCommandGroup(StartingPoint selected) {
    	switch(selected) {
    		case B1:
    			addSequential(new DriveDistance(distanceFromSides));
    			break;
    		case B2:
    			addSequential(new TurnToAngle(-45));
    			addSequential(new DriveDistance(distanceFromCenter));
    			break;
    		case B3:
    			addSequential(new DriveDistance(distanceFromSides));
    			break;
    		case R1:
    			addSequential(new DriveDistance(distanceFromSides));
    			break;
    		case R2:
    			addSequential(new TurnToAngle(-45));
    			addSequential(new DriveDistance(distanceFromCenter));
    			break;
    		case R3:
    			addSequential(new DriveDistance(distanceFromSides));
    			break;
    		default:
    			break;
    	}
    }
}

