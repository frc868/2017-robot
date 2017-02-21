package org.usfirst.frc.team868.robot;

import org.usfirst.frc.team868.robot.commands.AutonChooser;
import org.usfirst.frc.team868.robot.subsystems.AgitatorSubsystem;
import org.usfirst.frc.team868.robot.subsystems.ClimberSubsystem;
import org.usfirst.frc.team868.robot.subsystems.ColorPixySubsystem;
import org.usfirst.frc.team868.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team868.robot.subsystems.FeederSubsystem;
import org.usfirst.frc.team868.robot.subsystems.GearCollectorSubsystem;
import org.usfirst.frc.team868.robot.subsystems.GearFlashlightSubsystem;
import org.usfirst.frc.team868.robot.subsystems.IRPixySubsystem;
import org.usfirst.frc.team868.robot.subsystems.LidarSubsystem;
import org.usfirst.frc.team868.robot.subsystems.MainGyroSubsystem;
import org.usfirst.frc.team868.robot.subsystems.ShooterFlashlightSubsystem;
import org.usfirst.frc.team868.robot.subsystems.ShooterSubsystem;
import org.usfirst.frc.team868.robot.subsystems.TurretRotationSubsystem;

import lib.util.RecordMotorMovement;

/**
 * essentially it acts like a factory 
 * (houses constructors and essentially getInstance() methods)
 * for various components such as Talons/Sparks/whatever. 
 * I think it also handles all of the getInstance()s for the subsystems as well.
 */

public interface RobotParts {
	
	public interface Commands {
		final AutonChooser autonChooser = AutonChooser.getInstance();
		
	}
	
	public interface Shoot {
		final ShooterSubsystem shooter = Robot.shooter;
	}
	
	public interface Gyro {
		final MainGyroSubsystem gyro = Robot.gyro;
	}
	
	public interface Drive {
		final DriveSubsystem drive = Robot.drivetrain;
	}
	
	public interface Camera {
	}
	
	public interface Turret {
		final TurretRotationSubsystem turretRotation = Robot.turret;
	}
	
	public interface Feeder {
		final AgitatorSubsystem agitator = Robot.agitator;
		final FeederSubsystem shooterFeeder = Robot.feeder;
	}
	
	public interface Climber {
		final ClimberSubsystem climber = Robot.climber;
	}
	
	public interface GearCollector {
		final GearCollectorSubsystem gearCollector = Robot.gearCollector;
	}
	
	public interface Pixy {
		final IRPixySubsystem irPixy = Robot.irPixy;
		final ColorPixySubsystem colorPixy = Robot.colorPixy;
	}
	
	
	public static class RecordMovement {
		final RecordMotorMovement recordMotorMovement = RecordMotorMovement.getInstance();
	}
	
	public interface LIDAR {
		final LidarSubsystem lidar = Robot.lidar;
	}
	
	public interface Flashlight {
		final GearFlashlightSubsystem gearFlashlight = Robot.gearFlashlight;
		final ShooterFlashlightSubsystem shooterFlashlight = Robot.shooterFlashlight;
	}
}
