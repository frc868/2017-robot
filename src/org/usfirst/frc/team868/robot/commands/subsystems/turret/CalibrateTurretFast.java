package org.usfirst.frc.team868.robot.commands.subsystems.turret;

import org.usfirst.frc.team868.robot.subsystems.TurretRotationSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class CalibrateTurretFast extends Command {
	
	private TurretRotationSubsystem turret;
	public CalibrateTurretFast() {
		turret = TurretRotationSubsystem.getInstance();
		requires(turret);
	}
	
	@Override
	protected void initialize() {
		turret.calibrationBegin();;
	}

	@Override
	protected boolean isFinished() {
		return turret.isCalibrationDone();
	}

	@Override
	protected void end() {
		turret.stop();		
	}

	@Override
	protected void interrupted() {
		// Interrupted before calibration completed
		turret.calbrationCancel();
		end();	
	}
}
