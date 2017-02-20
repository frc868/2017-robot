package org.usfirst.frc.team868.robot;

import org.usfirst.frc.team868.robot.commands.AutonChooser;
import org.usfirst.frc.team868.robot.subsystems.AgitatorSubsystem;
import org.usfirst.frc.team868.robot.subsystems.CameraSubsystem;
import org.usfirst.frc.team868.robot.subsystems.ClimberSubsystem;
import org.usfirst.frc.team868.robot.subsystems.ColorPixySubsystem;
import org.usfirst.frc.team868.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team868.robot.subsystems.GearCollectorSubsystem;
import org.usfirst.frc.team868.robot.subsystems.GearFlashlightSubsystem;
import org.usfirst.frc.team868.robot.subsystems.IRPixySubsystem;
import org.usfirst.frc.team868.robot.subsystems.LidarSubsystem;
import org.usfirst.frc.team868.robot.subsystems.FeederSubsystem;
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
		final ShooterSubsystem shooter = ShooterSubsystem.getInstance();
	}
	
	public interface Gyro {
		final org.usfirst.frc.team868.robot.subsystems.GyroSubsystem gyro = org.usfirst.frc.team868.robot.subsystems.GyroSubsystem.getInstance();
	}
	
	public interface Drive {
		final DriveSubsystem drive = DriveSubsystem.getInstance();
	}
	
	public interface Camera {
		final CameraSubsystem frontCamera = CameraSubsystem.getFrontInstance();
//		final CameraSubsystem rearCamera  = CameraSubsystem.getRearInstance();
	}
	
	public interface Turret {
		final TurretRotationSubsystem turretRotation = TurretRotationSubsystem.getInstance();
	}
	
	public interface Feeder {
		final AgitatorSubsystem agitator = AgitatorSubsystem.getInstance();
		final FeederSubsystem shooterFeeder = FeederSubsystem.getInstance();
	}
	
	public interface Climber {
		final ClimberSubsystem climber = ClimberSubsystem.getInstance();
	}
	
	public interface GearCollector {
		final GearCollectorSubsystem gearCollector = GearCollectorSubsystem.getInstance();
	}
	
	public interface Pixy {
		final IRPixySubsystem irPixy = IRPixySubsystem.getInstance();
		final ColorPixySubsystem colorPixy = ColorPixySubsystem.getInstance();
	}
	
	
	public static class RecordMovement {
		final RecordMotorMovement recordMotorMovement = RecordMotorMovement.getInstance();
	}
	
	public interface LIDAR {
		final LidarSubsystem lidar = LidarSubsystem.getInstance();
	}
	
	public interface Flashlight {
		final GearFlashlightSubsystem gearFlashlight = GearFlashlightSubsystem.getInstance();
		final ShooterFlashlightSubsystem shooterFlashlight = ShooterFlashlightSubsystem.getInstance();
	}
}
