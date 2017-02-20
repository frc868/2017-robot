package org.usfirst.frc.team868.robot.commands.subsystems.shooter;

import java.util.TimerTask;

import org.usfirst.frc.team868.robot.subsystems.ShooterSubsystem;
import org.usfirst.frc.team868.robot.subsystems.TargetProvider;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Command to set the turret motor to a certain RPS value.
 */
public class SetShooterRPS3 extends Command {
	/** Maximum output volts we will set. */
	private final static double MAX_VOLTS = 10.0;

	// Set to true to tune
	private final static boolean DEBUG = true;
	private static final String KP_LABEL = "Shooter PID Kp";
	private static final String KD_LABEL = "Shooter PID Kd";
	
	// Values determined from DEBUG mode
	private double Kp = 0.0135;
	private double Kd = 0.0597;
	
	private ShooterSubsystem shooter;
	private TargetProvider rpsProvider;
	
	// Last volts we set on controller
	private double lastVolts = 0.0;
	
	// Last error in RPS
	private double lastErrRps = 0.0;
	
	// Change in error last computed
	private double errRpsDelta;

	private java.util.Timer controller = null;
	private boolean controllerEnabled = false;

	/**
	 * Construct a new instance indicating the source of the target RPS you want us to track.
	 * 
	 * @param name
	 *            Simple name for your command.
	 * 
	 * @param rps
	 *            The class that will provide the target RPS value that we will keep trying to seek.
	 */
	private SetShooterRPS3(String name, TargetProvider rps) {
		super(name);
		
		this.rpsProvider = rps;

		shooter = ShooterSubsystem.getInstance();

		if (DEBUG) {
			// Get PID values from dash board (if available)
			Kp = SmartDashboard.getNumber(KP_LABEL, Kp);
			Kd = SmartDashboard.getNumber(KD_LABEL, Kd);
			// Make certain values are on dash board
			SmartDashboard.putNumber(KP_LABEL, Kp);
			SmartDashboard.putNumber(KD_LABEL, Kd);
		}
		
		requires(shooter);
	}
	
	public static SetShooterRPS3 createFixedRPS(double rps) {
		SetShooterRPS3 cmd = new SetShooterRPS3("RPS3 " + rps, () -> { return rps; });
		return cmd;
	}
	
	public static SetShooterRPS3 createDashboardRPS(final String sdKey, final double rps) {
		SmartDashboard.putNumber(sdKey, rps);
		SetShooterRPS3 cmd = new SetShooterRPS3("RPS3 " + rps, () -> {
			return SmartDashboard.getNumber(sdKey, rps);
		});
		return cmd;
	}

	@Override
	protected void initialize() {
		lastErrRps = 0;
		lastVolts = 0;

		if (DEBUG) {
			// Get PID values from dashboard (if available)
			Kp = SmartDashboard.getNumber(KP_LABEL, Kp);
			Kd = SmartDashboard.getNumber(KD_LABEL, Kd);
		}
		
		// Update at a rate of 20 times a second
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				synchronized (this) {
					if (controllerEnabled) {
						pidWrite();
					}
				}
			}
		};
		controllerEnabled = true;
		controller = new java.util.Timer(getName() + " RPS");
		controller.schedule(task, 0, 1000 / 20);
	}

	@Override
	protected void execute() {

	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		synchronized(controller) {
			controllerEnabled = false;
			controller.cancel();
		}
		shooter.setPower(0);
	}

	@Override
	protected void interrupted() {
		end();
	}

	private void pidWrite() {		
		double curRps = shooter.getSpeed();
		double tarRps = rpsProvider.getSetpoint();
		double errRps = tarRps - curRps;		
		errRpsDelta = errRps - lastErrRps;
		
		// TODO: Think about adding Ki once error is small, tends to
		// accumulate too much during spin up

		double outVolts = lastVolts + (Kp * errRps + Kd * errRpsDelta);
		setVolts(Math.min(outVolts, MAX_VOLTS));
		
		lastErrRps = errRps;
	}
	
	private void setVolts(double volts) {
		lastVolts = volts;
		shooter.setPower(volts);
	}

}
