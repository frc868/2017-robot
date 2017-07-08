package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.commands.auton.AutonChooser.StartingPoint;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistance;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.TurnByAngleGyro;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearToNeutralAuton extends CommandGroup {

    public GearToNeutralAuton(StartingPoint selected) {
    	
    	addSequential(new GearAuton(selected));
    	
    	switch(selected) {
    	case B1:
		case R3:
//			addSequential(new OldDriveDistance(RobotMap.AutonValues.GEAR_AUTON_DIST_1+29));
//			addSequential(new TurnByAngleGyro(-58, 1.5));
//			addSequential(new OldDriveDistance(RobotMap.AutonValues.GEAR_AUTON_DIST_2, true));
//    		addSequential(new OldDriveDistance(-RobotMap.AutonValues.HOOK_BACKOFF));
    		
    		addSequential(new TurnByAngleGyro(60, 1.5));
    		addSequential(new DriveDistance.Builder(300).speed(0.6).time(5).build());
			break;
			
		case B3:
		case R1:
//			addSequential(new OldDriveDistance(RobotMap.AutonValues.GEAR_AUTON_DIST_1-11));
//			addSequential(new TurnByAngleGyro(62, 1.5));
//			addSequential(new OldDriveDistance(RobotMap.AutonValues.GEAR_AUTON_DIST_2-6, true));
//			addSequential(new OldDriveDistance(-RobotMap.AutonValues.HOOK_BACKOFF));
			
			addSequential(new TurnByAngleGyro(-60, 1.5));
    		addSequential(new DriveDistance.Builder(300).speed(0.6).time(5).build());
			break;
			
		case B2:
		case R2:
//			addSequential(new DriveDistance.Builder(RobotMap.AutonValues.WALL_TO_HOOK-30).speed(0.6).build());
//			addSequential(new DriveDistance.Builder(30).speed(0.35).plate(true).build());
//    		addSequential(new OldDriveDistance(-RobotMap.AutonValues.HOOK_BACKOFF));
			break;
			
		default:
			break;
    	}
    	
    	/*
    	 * B3/R3 (the ones by the boiler) shouldn't turn quite as much, as we want to start heading towards the
    	 * loading stations
    	 */
    }
}
