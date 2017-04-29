package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.RobotMap.State;
import org.usfirst.frc.team868.robot.commands.auton.AutonChooser.StartingPoint;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistance;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistanceBuilder;
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
			addSequential(new DriveDistanceBuilder.Builder().setDistance(RobotMap.AutonValues.WALL_TO_HOOK-30).setSpeed(0.6).build());
			addSequential(new DriveDistanceBuilder.Builder().setDistance(30).setSpeed(0.35).usePlate(true).build());
    		addSequential(new DriveDistance(-RobotMap.AutonValues.HOOK_BACKOFF));
			addSequential(new TurnByAngleGyro(-60, 2)); //75 degrees?
			addParallel(new ShooterSetSpeed(84));
			addSequential(new DriveDistance(-RobotMap.AutonValues.WALL_TO_HOOK-40)); //TODO make this it's own constant
			
			addParallel(new TurretIRLockToTarget()); //TODO is this broken?
	    	addParallel(new AgitatorCommand(State.FORWARD)); //TODO make this a command group
	    	addParallel(new FeederCommand(State.FORWARD));
	    	
	    	break;
	    	
		case B2:
			addSequential(new DriveDistanceBuilder.Builder().setDistance(RobotMap.AutonValues.WALL_TO_HOOK-30).setSpeed(0.6).build());
			addSequential(new DriveDistanceBuilder.Builder().setDistance(30).setSpeed(0.35).usePlate(true).build());
    		addSequential(new DriveDistance(-RobotMap.AutonValues.HOOK_BACKOFF));
			addSequential(new TurnByAngleGyro(60, 2));
			addParallel(new ShooterSetSpeed(84));
			addSequential(new DriveDistance(-RobotMap.AutonValues.WALL_TO_HOOK-40));
			
			addParallel(new TurretIRLockToTarget());
	    	addParallel(new AgitatorCommand(State.FORWARD));
	    	addParallel(new FeederCommand(State.FORWARD));
	    	
	    	break;
	    	
		default:
			break; //TODO other cases
		}
		
	}
}
