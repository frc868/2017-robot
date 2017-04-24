package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.RobotMap.State;
import org.usfirst.frc.team868.robot.commands.auton.AutonChooser.StartingPoint;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistance;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.TurnByAngleGyro;
import org.usfirst.frc.team868.robot.commands.subsystems.shooter.AgitatorCommand;
import org.usfirst.frc.team868.robot.commands.subsystems.shooter.FeederCommand;
import org.usfirst.frc.team868.robot.commands.subsystems.shooter.ShooterSetSpeed;
import org.usfirst.frc.team868.robot.commands.subsystems.turret.TurretIRLockToTarget;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearToShootAuton extends CommandGroup {

	public GearToShootAuton(StartingPoint start) {
		switch(start) {
		case R2:
			addSequential(new DriveDistance(RobotMap.AutonValues.WALL_TO_HOOK, 2));
			addSequential(new DriveDistance(-RobotMap.AutonValues.HOOK_BACKOFF));
			addSequential(new TurnByAngleGyro(-60, 2)); //75 degrees?
			addParallel(new ShooterSetSpeed(84));
			addSequential(new DriveDistance(-RobotMap.AutonValues.WALL_TO_HOOK-40)); //TODO make this it's own constant
			
			addParallel(new TurretIRLockToTarget()); //TODO is this broken?
	    	addParallel(new AgitatorCommand(State.FORWARD)); //TODO make this a command group
	    	addParallel(new FeederCommand(State.FORWARD));
	    	
	    	//I think this is unnecessary, as these commands should stop once teleop starts
//	    	addSequential(new WaitCommand(8));	    	
//	    	addParallel(new ShooterSetVoltageCommand(0));
//	    	addParallel(new AgitatorCommand(State.OFF));
//	    	addParallel(new FeederCommand(State.OFF)); 
	    	break;
	    	
		case B2:
			addSequential(new DriveDistance(RobotMap.AutonValues.WALL_TO_HOOK, 2));
			addSequential(new DriveDistance(-RobotMap.AutonValues.HOOK_BACKOFF));
			addSequential(new TurnByAngleGyro(60, 2));
			addParallel(new ShooterSetSpeed(84));
			addSequential(new DriveDistance(-RobotMap.AutonValues.WALL_TO_HOOK-40));
			
			addParallel(new TurretIRLockToTarget());
	    	addParallel(new AgitatorCommand(State.FORWARD));
	    	addParallel(new FeederCommand(State.FORWARD));
	    	
//	    	addSequential(new WaitCommand(8));	    	
//	    	addParallel(new ShooterSetVoltageCommand(0));
//	    	addParallel(new AgitatorCommand(State.OFF));
//	    	addParallel(new FeederCommand(State.OFF));
	    	break;
	    	
		default:
			break; //TODO other cases
		}
		
	}
}
