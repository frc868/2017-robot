package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistanceCommand;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.TurnToAngle;
import org.usfirst.frc.team868.robot.commands.subsystems.gear.GearReleaseCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DropOffGearAutonCommandGroup extends CommandGroup {

    public DropOffGearAutonCommandGroup() {
       addSequential(new DriveDistanceCommand(1000));
       addSequential(new TurnToAngle(-90));
       addSequential(new DriveDistanceCommand(200));
       addSequential(new TurnToAngle(-90));
       addSequential(new DriveDistanceCommand(100));
       addSequential(new GearReleaseCommand());
    }
}
