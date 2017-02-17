package org.usfirst.frc.team868.robot.commands;

import org.usfirst.frc.team868.robot.commands.AutonChooser;
import org.usfirst.frc.team868.robot.commands.AutonChooser.DoThis;
import org.usfirst.frc.team868.robot.commands.AutonChooser.StartingPoint;
import org.usfirst.frc.team868.robot.commands.auton.CrossBaselineCommandGroup;
import org.usfirst.frc.team868.robot.commands.auton.DropOffGearAutonCommandGroup;
import org.usfirst.frc.team868.robot.commands.auton.GearToHopperCommandGroup;
import org.usfirst.frc.team868.robot.commands.auton.GoToHopperCommandGroup;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonLauncher extends CommandGroup {

    public AutonLauncher() {
        DoThis action = AutonChooser.getDoThis();
        StartingPoint start = AutonChooser.getStart();
        
        switch(action) {
        	case CROSS_BASE:
        		new CrossBaselineCommandGroup(start).start();
        		break;
        		
        	case DROP_GEAR:
        		new DropOffGearAutonCommandGroup(start).start();
        		break;
        		
        	case HOPPER: 
        		new GoToHopperCommandGroup(start).start();
        		break;
        		
        	case GEAR_TO_HOPPER:
        		new GearToHopperCommandGroup(start).start();
        		break;
        		
        	case GEAR_TO_NEUTRAL:
        		break;
        		
			default:
				break;
        }
        	
    }
    
    
}
