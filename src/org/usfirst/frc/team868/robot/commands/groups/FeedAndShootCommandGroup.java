package org.usfirst.frc.team868.robot.commands.groups;

import org.usfirst.frc.team868.robot.commands.AgitatorTestingCommand;
import org.usfirst.frc.team868.robot.commands.FeederTestingCommand;
import org.usfirst.frc.team868.robot.commands.ShooterCommandVoltage;
import org.usfirst.frc.team868.robot.subsystems.AgitatorSubsystem;
import org.usfirst.frc.team868.robot.subsystems.FeederSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class FeedAndShootCommandGroup extends CommandGroup {

    public FeedAndShootCommandGroup() {
//    	addSequential(new RotateUsingIRPixy(1));
    	
//    	addParallel(new ShootCommand(80));
//    	addSequential(new WaitCommand(2));
    	
    	addParallel(new AgitatorTestingCommand(AgitatorSubsystem.State.FORWARD));
    	addParallel(new FeederTestingCommand(FeederSubsystem.State.FORWARD));
    	addSequential(new WaitCommand(5));
    	
    	addParallel(new ShooterCommandVoltage(0));
    	addParallel(new AgitatorTestingCommand(AgitatorSubsystem.State.OFF));
    	addParallel(new FeederTestingCommand(FeederSubsystem.State.OFF));
    }
}
