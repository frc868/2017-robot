package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.commands.auton.AutonChooser.StartingPoint;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.OldDriveDistance;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.TurnToAngleGyro;
import org.usfirst.frc.team868.robot.commands.subsystems.gear.GearCollectorOpenCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class HopperToGearAuton extends CommandGroup {

    public HopperToGearAuton(StartingPoint selected) {
       switch(selected) {
       		case B1:
       			addSequential(new OldDriveDistance(196.5));
       			addSequential(new TurnToAngleGyro(-45));
       			addSequential(new OldDriveDistance(154.85638508));
       			addSequential(new GearCollectorOpenCommand());
       			break;
       		case B2:
       			addSequential(new OldDriveDistance(196.5));
       			addSequential(new TurnToAngleGyro(-45));
       			addSequential(new OldDriveDistance(154.85638508));
       			addSequential(new GearCollectorOpenCommand());
       			break;
       		case B3:
       			addSequential(new OldDriveDistance(196.5));
       			addSequential(new TurnToAngleGyro(-45));
       			addSequential(new OldDriveDistance(154.85638508));
       			addSequential(new GearCollectorOpenCommand());
       			break;
       		case R1:
       			addSequential(new OldDriveDistance(196.5));
       			addSequential(new TurnToAngleGyro(45));
       			addSequential(new OldDriveDistance(154.85638508));
       			addSequential(new GearCollectorOpenCommand());
       			break;
       		case R2:
       			addSequential(new OldDriveDistance(196.5));
       			addSequential(new TurnToAngleGyro(45));
       			addSequential(new OldDriveDistance(154.85638508));
       			addSequential(new GearCollectorOpenCommand());
       			break;
       		case R3:
       			addSequential(new OldDriveDistance(196.5));
       			addSequential(new TurnToAngleGyro(45));
       			addSequential(new OldDriveDistance(154.85638508));
       			addSequential(new GearCollectorOpenCommand());
       			break;
       }
    }
}
