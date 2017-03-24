package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistance;
import org.usfirst.frc.team868.robot.commands.subsystems.shooter.ShooterSetSpeed;
import org.usfirst.frc.team868.robot.commands.util.FeedAndShootCommandGroup;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class ShootAndBaselineAuton extends CommandGroup {

	public ShootAndBaselineAuton(AutonChooser.StartingPoint start) {
		switch(start) {
		default:
		case B1:
		case B2:
		case B3:
			addParallel(new ShooterSetSpeed(75));
			addSequential(new WaitCommand(2));
			addSequential(new FeedAndShootCommandGroup());
			addSequential(new DriveDistance(-280));
			break;
		case R1:
		case R2:
		case R3:
			addParallel(new ShooterSetSpeed(75));
			addSequential(new WaitCommand(2));
			addSequential(new FeedAndShootCommandGroup());
			addSequential(new DriveDistance(280));
			break;
			
		}
	}
}
