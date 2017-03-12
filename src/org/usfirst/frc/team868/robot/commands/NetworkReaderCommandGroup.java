package org.usfirst.frc.team868.robot.commands;

import org.usfirst.frc.team868.robot.commands.subsystems.*;
import org.usfirst.frc.team868.robot.commands.subsystems.shooter.*;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.*;
import org.usfirst.frc.team868.robot.commands.subsystems.gear.*;
import org.usfirst.frc.team868.robot.commands.subsystems.turret.*;
import org.usfirst.frc.team868.robot.subsystems.TCPSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class NetworkReaderCommandGroup extends CommandGroup {

	TCPSubsystem input;

	public NetworkReaderCommandGroup() {
		
		input = TCPSubsystem.getInstance();
		requires(input);

		int command;

		do {
			command = input.readInt();

			switch(command) {
				case 0:
					//exit while loop
					break;
					
				case 1:
					addSequential(new DriveDistance(input.readDouble()));
					break;
					
				case 2:
					addSequential(new TurnToAngle(input.readDouble()));
					break;
					
				case 3:
					addSequential(new ShootCommand(input.readDouble()));
					break;
					
				case 4:
					addSequential(new RotateTurretToAngle(input.readDouble()));
					break;
					
				case 5:
					addSequential(new GearCollectorToggleCommand());
					break;
					
				case 6:
					addSequential(new GearFlashlightCommand());
					break;
					
				default:
					System.err.println("Invalid command argument read from networks!");
					break;
			}
		} while (command != 0);


	}
}
