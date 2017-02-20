package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.commands.AutonChooser.StartingPoint;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistance;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearToNeutralCommandGroup extends CommandGroup {

    public GearToNeutralCommandGroup(StartingPoint selected) {
    	switch(selected) {
	    	case B1:
	    		addSequential(new DriveDistance(-75));
	    		addSequential(new TurnToAngle(45));
	    		addSequential(new DriveDistance(1000));
	    		break;
	    	case B2:
	    		addSequential(new TurnToAngle(90));
	    		addSequential(new DriveDistance(300));
	    		addSequential(new TurnToAngle(-90));
	    		addSequential(new DriveDistance(1000));
	    		break;
	    	case B3:
	    		addSequential(new DriveDistance(-75));
	    		addSequential(new TurnToAngle(-45));
	    		addSequential(new DriveDistance(1000));
	    		break;
	    	case R1:
	    		addSequential(new DriveDistance(-75));
	    		addSequential(new TurnToAngle(-45));
	    		addSequential(new DriveDistance(1000));
	    		break;
	    	case R2:
	    		addSequential(new TurnToAngle(-90));
	    		addSequential(new DriveDistance(300));
	    		addSequential(new TurnToAngle(90));
	    		addSequential(new DriveDistance(1000));
	    		break;
	    	case R3:
	    		addSequential(new DriveDistance(-75));
	    		addSequential(new TurnToAngle(45));
	    		addSequential(new DriveDistance(1000));
	    		break;
	    	default: 
	    		break;
    	}
    }
}
