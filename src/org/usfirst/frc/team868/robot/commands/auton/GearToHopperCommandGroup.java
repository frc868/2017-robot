package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.commands.AutonChooser.StartingPoint;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistanceCommand;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearToHopperCommandGroup extends CommandGroup {

    public GearToHopperCommandGroup(StartingPoint start) {
        switch(start) {
	        case R3:
	        	addSequential(new DriveDistanceCommand(-50));
	        	addSequential(new TurnToAngle(-90));
	        	addSequential(new DriveDistanceCommand(250));
	        	addSequential(new DriveDistanceCommand(-10));
	        	addSequential(new TurnToAngle(0));
	        	addSequential(new DriveDistanceCommand(-50));
	        	break;
	        case B3:
	        	addSequential(new DriveDistanceCommand(-50));
	        	addSequential(new TurnToAngle(90));
	        	addSequential(new DriveDistanceCommand(250));
	        	addSequential(new DriveDistanceCommand(-10));
	        	addSequential(new TurnToAngle(0));
	        	addSequential(new DriveDistanceCommand(-50));
	        	break;
			default:
				break;
        }
    }
}
