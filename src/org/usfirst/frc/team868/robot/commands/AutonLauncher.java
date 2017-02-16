package org.usfirst.frc.team868.robot.commands;

import org.usfirst.frc.team868.robot.commands.AutonChooser;
import org.usfirst.frc.team868.robot.commands.AutonChooser.DoThis;
import org.usfirst.frc.team868.robot.commands.auton.DropOffGearAutonCommandGroup;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonLauncher extends CommandGroup {

    public AutonLauncher() {
        DoThis selected = AutonChooser.getDoThis();
        switch(selected) {
        	case CROSS_BASE:
        		break;
        	case DROP_GEAR:
        		new DropOffGearAutonCommandGroup(AutonChooser.getStart()).start();
        		break;
        	case HOPPER: 
        		break;
        }
        	
    }
    
    
}
