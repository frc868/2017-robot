package org.usfirst.frc.team868.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class FeedAndShootCommandGroup extends CommandGroup {

    public FeedAndShootCommandGroup() {
    	addSequential(new ShootCommand(true));
    	addSequential(new WaitCommand(1));
    	
    	addParallel(new AgitatorCommand(true));
    	addSequential(new ShooterFeederCommand(true));
    	
    	addParallel(new ShootCommand(false));
    	addParallel(new AgitatorCommand(false));
    	addSequential(new ShooterFeederCommand(false));
    }
}
