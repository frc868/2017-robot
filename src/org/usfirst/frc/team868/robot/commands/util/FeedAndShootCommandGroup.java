package org.usfirst.frc.team868.robot.commands.util;

import org.usfirst.frc.team868.robot.RobotMap.State;
import org.usfirst.frc.team868.robot.commands.subsystems.shooter.AgitatorCommand;
import org.usfirst.frc.team868.robot.commands.subsystems.shooter.FeederCommand;
import org.usfirst.frc.team868.robot.commands.subsystems.shooter.ShooterSetVoltageCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class FeedAndShootCommandGroup extends CommandGroup {

    public FeedAndShootCommandGroup() {
//    	addSequential(new RotateUsingIRPixy(1));
    	
//    	addParallel(new ShootCommand(80)); //TODO fix tihs wtf
//    	addSequential(new WaitCommand(2));
    	
    	addParallel(new AgitatorCommand(State.FORWARD));
    	addParallel(new FeederCommand(State.FORWARD));
    	addSequential(new WaitCommand(5));
    	
    	addParallel(new ShooterSetVoltageCommand(0));
    	addParallel(new AgitatorCommand(State.OFF));
    	addParallel(new FeederCommand(State.OFF));
    }
}
