package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.commands.auton.AutonChooser.DoThis;
import org.usfirst.frc.team868.robot.commands.auton.AutonChooser.StartingPoint;

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
        		new BaselineAuton(start).start();
        		break;
        		
        	case DROP_GEAR:
        		new GearAuton(start).start();
        		break;
        		
        	case HOPPER: 
        		new HopperAuton(start).start();
        		break;
        		
        	case GEAR_TO_HOPPER:
        		new HopperAuton(start).start();
        		break;
        		
        	case GEAR_TO_NEUTRAL:
        	    new GearToNeutralAuton(start).start();
        		break;
        		
        	case SHOOT_AND_BASELINE:
        		new ShootAndBaselineAuton(start).start();
        		break;
        		
        	case GEAR_AND_SHOOT:
        		new GearToShootAuton().start();
        		break;
        		
			default:
				break;
        }
        	
    }
    
    
}
