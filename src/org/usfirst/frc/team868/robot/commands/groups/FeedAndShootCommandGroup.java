package org.usfirst.frc.team868.robot.commands.groups;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.commands.subsystems.AgitatorCommand;
import org.usfirst.frc.team868.robot.commands.subsystems.ShooterFeederCommand;
import org.usfirst.frc.team868.robot.commands.subsystems.shooter.ShootCommand;
import org.usfirst.frc.team868.robot.commands.subsystems.turret.RotateUsingIRPixy;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class FeedAndShootCommandGroup extends CommandGroup {

    public FeedAndShootCommandGroup() {
    	addSequential(new RotateUsingIRPixy(1));
    	
    	addParallel(new ShootCommand(75));
    	addSequential(new WaitCommand(1));
    	
    	addParallel(new AgitatorCommand(true));
    	addParallel(new ShooterFeederCommand(RobotMap.Feeder.State.FORWARD));
    	addSequential(new WaitCommand(3));
    	
    	addParallel(new ShootCommand(75));
    	addParallel(new AgitatorCommand(false));
    	addParallel(new ShooterFeederCommand(RobotMap.Feeder.State.OFF));
    }
}
