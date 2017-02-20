package org.usfirst.frc.team868.robot.commands.subsystems;

import org.usfirst.frc.team868.robot.subsystems.ShooterSubsystem;
import org.usfirst.frc.team868.robot.subsystems.TargetProvider;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Command to set the turret motor to a certain RPS value (sort of using PIDController class).
 */
public class SetShooterRPS2 extends Command implements PIDOutput {
	private final static boolean DEBUG = false;
	
	private ShooterSubsystem shooter;
	private TargetProvider rpsProvider;
	private double Kp = 0.0135;
	private double Kd = 0.0597;
	private static final String KP_LABEL = "Shooter PID Kp";
	private static final String KD_LABEL = "Shooter PID Kd";
	
	private double lastVolts;
	private int updateCnts;
	private double lastErrRps;
	private PIDController pid;
	//private double guessVolts;
	
	// maximum volts we will try to jump to
	private double punchItVolts = 10.0;

	// If we are off by a lot, we will PUNCH the accelerator with this
	//private static final double PUNCH_IT_VOLTS = 8.0;

	/**
	 * Construct a new instance indicating the source of the target RPS you want us to track.
	 * 
	 * @param name
	 *            Simple name for your command.
	 * 
	 * @param rps
	 *            The class that will provide the target RPS value that we will keep trying to seek.
	 */
	public SetShooterRPS2(String name, TargetProvider rps) {
		super(name);
		
		this.rpsProvider = rps;

		shooter = ShooterSubsystem.getInstance();

		PIDSource pidSrc = new PIDSource() {

			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
			}

			@Override
			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kRate;
			}

			@Override
			public double pidGet() {
				return 0;
			}
			
		};

		if (DEBUG) {
			// Get PID values from dashboard (if available)
			Kp = SmartDashboard.getNumber(KP_LABEL, Kp);
			Kd = SmartDashboard.getNumber(KD_LABEL, Kd);
			// Make certain values are on dashboard
			SmartDashboard.putNumber(KP_LABEL, Kp);
			SmartDashboard.putNumber(KD_LABEL, Kd);
		}

		pid = new PIDController(Kp, 0, Kd, pidSrc, this, 1.0 / 20.0);		
		
		SmartDashboard.putData("RPS2 PID", pid);

		requires(shooter);
	}
	
	public static SetShooterRPS2 createFixedRPS(double rps) {
		SetShooterRPS2 cmd = new SetShooterRPS2("RPS2 " + rps, () -> { return rps; });
		return cmd;
	}
	
	public static SetShooterRPS2 createDashboardRPS(final String sdKey, final double rps) {
		SmartDashboard.putNumber(sdKey, rps);
		SetShooterRPS2 cmd = new SetShooterRPS2("RPS2 " + rps, () -> {
			return SmartDashboard.getNumber(sdKey, rps);
		});
		return cmd;
	}

	@Override
	protected void initialize() {
		double rps = shooter.getSpeed();
		double tarRps = rpsProvider.getSetpoint();
		setSetpoint(tarRps);
		if (DEBUG) {
			// Get PID values from dashboard (if available)
			Kp = SmartDashboard.getNumber(KP_LABEL, Kp);
			Kd = SmartDashboard.getNumber(KD_LABEL, Kd);
		}
		updateCnts = 0;
		lastErrRps = (tarRps - rps);
		pid.enable();
	}

	@Override
	protected void execute() {
		double setpoint = rpsProvider.getSetpoint();
		if (setpoint != pid.getSetpoint()) {
			setSetpoint(setpoint);
		}
	}

	private void setSetpoint(double setpoint) {
		// Used for statistics
		pid.setSetpoint(setpoint);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		pid.disable();
		shooter.setPower(0);
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	public void pidWrite(double output) {
		pidWrite();
	}
	
	private void pidWrite() {
		double curRps = shooter.getSpeed();
		double tarRps = rpsProvider.getSetpoint();
		double errRps = tarRps - curRps;
		if ((updateCnts < 2) && (curRps < 0)) {
			setVolts(punchItVolts);
			// Pretend the last voltage setting was our predicted voltage
			//lastVolts = guessVolts;
			updateCnts = 0;
		} else {
			double scale = 1.0;
			double errRpsDelta = 0.0;
			if (updateCnts != 0) {
				errRpsDelta = errRps - lastErrRps;
			}
			double outVolts = lastVolts + scale * (Kp * errRps + Kd * errRpsDelta);
			setVolts(Math.min(outVolts, punchItVolts));
		}
		lastErrRps = errRps;
		updateCnts++;
	}
	
	private void setVolts(double volts) {
		lastVolts = volts;
		shooter.setPower(volts);
	}

}
