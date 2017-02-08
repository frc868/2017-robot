package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.commands.AutonChooser.DoThis;
import org.usfirst.frc.team868.robot.commands.AutonChooser.StartingPoint;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveAndTurn;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistanceCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossBaselineAutonCommandGroup extends CommandGroup {

    public CrossBaselineAutonCommandGroup(StartingPoint selected) {
    	if(selected == StartingPoint.B1 || selected == StartingPoint.B3 || selected == StartingPoint.R1 || selected == StartingPoint.R3) {
    		addSequential(new DriveDistanceCommand(487.68));
    	}
    	if(selected == StartingPoint.B2 || selected == StartingPoint.R2) {
    		addSequential(new DriveAndTurn(100, true));
    		addSequential(new DriveDistanceCommand(1000));
    	}
    }
}
