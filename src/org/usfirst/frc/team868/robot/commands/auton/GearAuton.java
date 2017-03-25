package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.commands.auton.AutonChooser.StartingPoint;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistance;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.TurnByAngleGyro;
import org.usfirst.frc.team868.robot.commands.subsystems.gear.GearCollectorOpenCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class GearAuton extends CommandGroup {

    public GearAuton(StartingPoint selected) {
    	switch(selected) {
    		case B1:
    		case R3:
    			addSequential(new DriveDistance(RobotMap.AutonValues.GEAR_AUTON_DIST_1));
    			addSequential(new WaitCommand(.1));
    			addSequential(new TurnByAngleGyro(-60));
//    			addSequential(new RotateUsingColorPixy());
    			addSequential(new WaitCommand(.1));
    			addSequential(new DriveDistance(RobotMap.AutonValues.GEAR_AUTON_DIST_2));
    			addSequential(new WaitCommand(.5));
    			addSequential(new GearCollectorOpenCommand());
    			addSequential(new WaitCommand(1));
	    		addSequential(new DriveDistance(-RobotMap.AutonValues.HOOK_BACKOFF));
    			break;
    		case B3:
    		case R1:
    			addSequential(new DriveDistance(RobotMap.AutonValues.GEAR_AUTON_DIST_1));
    			addSequential(new WaitCommand(.1));
    			addSequential(new TurnByAngleGyro(60));
//    			addSequential(new RotateUsingColorPixy());
    			addSequential(new WaitCommand(.1));
    			addSequential(new DriveDistance(RobotMap.AutonValues.GEAR_AUTON_DIST_2));
    			addSequential(new WaitCommand(.5));
    			addSequential(new GearCollectorOpenCommand());
    			addSequential(new WaitCommand(1));
    			addSequential(new DriveDistance(-RobotMap.AutonValues.HOOK_BACKOFF));
    			break;
    		case B2:
    		case R2:
    			addParallel(new DriveDistance(RobotMap.AutonValues.WALL_TO_HOOK/* - 30));
    			addSequential(new RotateUsingColorPixy());
    			addSequential(new WaitCommand(1));
    			addSequential(new DriveDistance(30*/));
    			addSequential(new WaitCommand(3.5));
//	    		addSequential(new GearCollectorOpenCommand());
    			addSequential(new WaitCommand(1));
	    		addSequential(new DriveDistance(-RobotMap.AutonValues.HOOK_BACKOFF));
    			break;
    		default:
    			break;
    	}
    }
}
