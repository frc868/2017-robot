package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.commands.AutonChooser.StartingPoint;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistanceCommand;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.TurnToAngle;

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
    			addSequential(new DriveDistanceCommand(distanceForward));
    			addSequential(new TurnToAngle(-90));
    			addSequential(new DriveDistanceCommand(DistanceToNeutral));
    			break;
    		case B2:
    			addSequential(new DriveDistanceCommand(distanceForward));
    			addSequential(new TurnToAngle(-90));
    			addSequential(new DriveDistanceCommand(DistanceToNeutral));
    			break;
    		case B3: 
    			addSequential(new DriveDistanceCommand(distanceForward));
    			addSequential(new TurnToAngle(-90));
    			addSequential(new DriveDistanceCommand(DistanceToNeutral));
    			break;
    		case R1:
    			addSequential(new DriveDistanceCommand(distanceForward));
    			addSequential(new TurnToAngle(90));
    			addSequential(new DriveDistanceCommand(DistanceToNeutral));
    			break;
    		case R2:
    			addSequential(new DriveDistanceCommand(distanceForward));
    			addSequential(new TurnToAngle(90));
    			addSequential(new DriveDistanceCommand(DistanceToNeutral));
    			break;
    		case R3:
    			addSequential(new DriveDistanceCommand(distanceForward));
    			addSequential(new TurnToAngle(90));
    			addSequential(new DriveDistanceCommand(DistanceToNeutral));
    			break;
    	}
    }
}
