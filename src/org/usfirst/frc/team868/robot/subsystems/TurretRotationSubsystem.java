package org.usfirst.frc.team868.robot.subsystems;

import org.usfirst.frc.team868.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurretRotationSubsystem extends Subsystem {

	private static TurretRotationSubsystem instance;
	private CANTalon turretRotator;
	private PIDController control;
	private Encoder count;
	private final double P = 0, I = 0, D = 0;

	private TurretRotationSubsystem(){
		turretRotator = new CANTalon(RobotMap.Turret.TURRET_MOTOR);
		turretRotator.setInverted(RobotMap.Turret.IS_INVERTED);
		// Make sure we stop if we hit a physical limit switch
		turretRotator.enableLimitSwitch(true, true);
		
		// TODO: Refactor to use encoder connected directly to CANTalon
		turretRotator.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		count = new Encoder(RobotMap.Turret.ENCODER_A, RobotMap.Turret.ENCODER_B);
		control = new PIDController(P , I, D, new PIDSource(){

			public void setPIDSourceType(PIDSourceType pidSource) {}

			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kDisplacement;
			}

			public double pidGet() {
				return getEncoderPosition();
			}
			
		}, new PIDOutput(){

			public void pidWrite(double output) {
				setPower(output);
			}
			
		});
		makeSoftLimits();
		
		// Assign test mode group
    	LiveWindow.addActuator("Turret", "Motor", turretRotator);
	}
	
	/**
	 * Generates the soft limits for the turret's rotation.
	 */
	private void makeSoftLimits(){
		turretRotator.setForwardSoftLimit(RobotMap.Turret.FORWARD_LIMIT);
		turretRotator.setReverseSoftLimit(RobotMap.Turret.REVERSE_LIMIT);
		turretRotator.enableForwardSoftLimit(true);
		turretRotator.enableReverseSoftLimit(true);
	}
	
	/**
	 * Sets the turret's position to the encoder count value of 0.
	 */
	public void resetPosition(){
		setPosition(0);
	}
	
	/**
	 * Sets the position of the turret.
	 * @param pos in encoder counts
	 */
	public void setPosition(double pos){
		turretRotator.setPosition(pos);
	}
	
	/**
	 * @return whether or not any limit switch has been closed.
	 */
	public boolean isALimitSwitchClosed(){
		return turretRotator.isFwdLimitSwitchClosed() || turretRotator.isRevLimitSwitchClosed();
	}
	
	/**
	 * Sets the power being output to the turret's motor.
	 * @param power in percentage from -1 to 1
	 */
	public void setPower(double power){
		turretRotator.set(power);
	}
	
	/**
	 * Sets the setpoint of the turret.
	 * @param point in encoder counts.
	 */
	public void setSetpoint(double point){
		control.setSetpoint(point);
		if(!control.isEnabled())
			control.enable();
	}
	
	/**
	 * Sets the setpoint of the turret.
	 * @param degree in degrees
	 */
	public void setAngle(double degree) {
		setSetpoint(degree * RobotMap.Turret.COUNTS_PER_DEGREE);
	}
	
	/**
	 * Gets the current power being put out to the turret's motor.
	 * @return power in percentage from -1 to 1
	 */
	public double getPower(){
		return turretRotator.get();
	}
	
	/**
	 * Gets the current rotational speed of the turret according to it's encoder.
	 * @return
	 */
	public double getSpeed(){
		return count.getRate();
	}
	
	/**
	 * Gets the turret's current position according to it's encoder.
	 * @return position in encoder counts.
	 */
	public double getEncoderPosition(){
		return count.get();
	}
	
	/**
	 * Gets the turret's current rotational position.
	 * @return angle in degrees
	 */
	public double getAngle() {
		return getEncoderPosition() * RobotMap.Turret.DEGREES_PER_COUNT;
	}
	
	/**
	 * Gets the turret's position according to it's CANTalon.
	 * @return
	 */
	public double getCANTalonPosition(){
		return turretRotator.getPosition();
	}
	
	/**
	 * Gets the turret's current setpoint.
	 * @return setpoint in degrees
	 */
	public double getSetpoint(){
		return turretRotator.getSetpoint() * RobotMap.Turret.DEGREES_PER_COUNT;
	}
	
	/**
	 * Update information on SmartDashboard.
	 */
	public void updateSD(){
		SmartDashboard.putData("Turret PID", control);
		SmartDashboard.putNumber("Turret Position", getEncoderPosition());
		SmartDashboard.putNumber("Turret Power", getPower());
		SmartDashboard.putNumber("Turret Speed", getSpeed());
		SmartDashboard.putNumber("Turret Setpoint", getSetpoint());
	}

	/**
	 * Get the instance of this subsystem
	 * @return instance
	 */
	public static TurretRotationSubsystem getInstance(){
		if(instance == null){
			instance = new TurretRotationSubsystem();
		}
		return instance;
	}

	public void initDefaultCommand() {
	}
}

