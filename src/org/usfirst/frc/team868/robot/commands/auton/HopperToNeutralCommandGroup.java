package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.commands.auton.AutonChooser.StartingPoint;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistance;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.TurnToAngleGyro;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class HopperToNeutralCommandGroup extends CommandGroup {

    public HopperToNeutralCommandGroup(StartingPoint selected) {
    	double distanceForward = 101;
    	double DistanceToNeutral = 33.5;
    	switch(selected) {
    		case B1:
    			addSequential(new DriveDistance(distanceForward));
    			addSequential(new TurnToAngleGyro(-90));
    			addSequential(new DriveDistance(DistanceToNeutral));
    			break;
    		case B2:
    			addSequential(new DriveDistance(distanceForward));
    			addSequential(new TurnToAngleGyro(-90));
    			addSequential(new DriveDistance(DistanceToNeutral));
    			break;
    		case B3: 
    			addSequential(new DriveDistance(distanceForward));
    			addSequential(new TurnToAngleGyro(-90));
    			addSequential(new DriveDistance(DistanceToNeutral));
    			break;
    		case R1:
    			addSequential(new DriveDistance(distanceForward));
    			addSequential(new TurnToAngleGyro(90));
    			addSequential(new DriveDistance(DistanceToNeutral));
    			break;
    		case R2:
    			addSequential(new DriveDistance(distanceForward));
    			addSequential(new TurnToAngleGyro(90));
    			addSequential(new DriveDistance(DistanceToNeutral));
    			break;
    		case R3:
    			addSequential(new DriveDistance(distanceForward));
    			addSequential(new TurnToAngleGyro(90));
    			addSequential(new DriveDistance(DistanceToNeutral));
    			break;
    	}
    }
}
