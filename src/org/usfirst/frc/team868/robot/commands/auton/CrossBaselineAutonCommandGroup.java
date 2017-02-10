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
public class CrossBaselineAutonCommandGroup extends CommandGroup {

    public CrossBaselineAutonCommandGroup(StartingPoint selected) {
    	switch(selected) {
	    	case B1:
	    	case B3:
	    	case R1:
	    	case R3:
	    		addSequential(new DriveDistanceCommand(487.68));
	    		break;
	    	case B2:
	    		addSequential(new DriveDistanceCommand(-40));
	    		addSequential(new TurnToAngle(90));
	    		addSequential(new DriveDistanceCommand(409));
	    		addSequential(new TurnToAngle(90));
	    		addSequential(new DriveDistanceCommand(-50));
	    		addSequential(new DriveDistanceCommand(200));
	    		addSequential(new FeedAndShootCommandGroup());
	    		break;
	    	case R2:
	    		addSequential(new DriveDistanceCommand(-40));
	    		addSequential(new TurnToAngle(90));
	    		addSequential(new DriveDistanceCommand(409));
	    		addSequential(new TurnToAngle(90));
	    		addSequential(new DriveDistanceCommand(-50));
	    		addSequential(new DriveDistanceCommand(200));
	    		addSequential(new FeedAndShootCommandGroup());
	    		break;
	    	default: 
	    		break;
	    		return true;
    	}
    }
}
