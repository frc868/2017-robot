package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.RobotMap.State;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistance;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.TurnByAngleGyro;
import org.usfirst.frc.team868.robot.commands.subsystems.shooter.AgitatorCommand;
import org.usfirst.frc.team868.robot.commands.subsystems.shooter.FeederCommand;
import org.usfirst.frc.team868.robot.commands.subsystems.shooter.ShooterSetSpeed;
import org.usfirst.frc.team868.robot.commands.subsystems.shooter.ShooterSetVoltageCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class GearToShootAuton extends CommandGroup {

	public GearToShootAuton() {
		addParallel(new DriveDistance(RobotMap.AutonValues.WALL_TO_HOOK));
		addSequential(new WaitCommand(4));
		addSequential(new DriveDistance(-RobotMap.AutonValues.HOOK_BACKOFF));
		addSequential(new TurnByAngleGyro(-45));
		addSequential(new DriveDistance(RobotMap.AutonValues.WALL_TO_HOOK));
		addSequential(new TurnByAngleGyro(45));
		
		addParallel(new ShooterSetSpeed(75));
		addSequential(new WaitCommand(1));
    	addParallel(new AgitatorCommand(State.FORWARD));
    	addParallel(new FeederCommand(State.FORWARD));
    	addSequential(new WaitCommand(5));
    	
    	addParallel(new ShooterSetVoltageCommand(0));
    	addParallel(new AgitatorCommand(State.OFF));
    	addParallel(new FeederCommand(State.OFF));
	}
}
