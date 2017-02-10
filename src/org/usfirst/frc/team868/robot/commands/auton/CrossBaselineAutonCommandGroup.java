package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.commands.AutonChooser.StartingPoint;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveAndTurn;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistanceCommand;

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
	    	case R2:
	    		addSequential(new DriveAndTurn(100, true));
	    		addSequential(new DriveDistanceCommand(1000));
	    		break;
	    	default: 
	    		break;
    	}
    }
}
