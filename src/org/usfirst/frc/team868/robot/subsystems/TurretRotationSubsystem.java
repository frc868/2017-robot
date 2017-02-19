package org.usfirst.frc.team868.robot.subsystems;

import org.usfirst.frc.team868.robot.RobotMap;

import com.ctre.CANTalon;

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
	private final double P = 0, I = 0, D = 0;
	private final boolean DEBUG = false;

	private TurretRotationSubsystem(){
		turretRotator = new CANTalon(RobotMap.Turret.TURRET_MOTOR);
		turretRotator.setInverted(RobotMap.Turret.IS_INVERTED);
		// Make sure we stop if we hit a physical limit switch
		turretRotator.enableLimitSwitch(true, true);
		
		turretRotator.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		control = new PIDController(P, I, D, new PIDSource(){

			public void setPIDSourceType(PIDSourceType pidSource) {}

			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kDisplacement;
			}

			public double pidGet() {
				return getPosition();
			}
			
		}, new PIDOutput(){

			public void pidWrite(double output) {
				setPower(output);
			}
			
		});
		
		// Assign test mode group
    	LiveWindow.addActuator("Turret", "Motor", turretRotator);
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
	
	public void calibrateTurret() {
		while (!isALimitSwitchClosed()) {
			setPower(.3);
		}
		double startAngle = Math.abs(getAngle());
		
		while(!isALimitSwitchClosed()) {
			setPower(-.3);
		}
		double endAngle = Math.abs(getAngle());
		setAngle((startAngle + endAngle)/2);
		
		turretRotator.setForwardSoftLimit(startAngle-2);
		turretRotator.setReverseSoftLimit(endAngle+2);
		turretRotator.enableForwardSoftLimit(true);
		turretRotator.enableReverseSoftLimit(true);
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
		return turretRotator.getSpeed();
	}
	
	/**
	 * Gets the turret's current rotational position.
	 * @return angle in degrees
	 */
	public double getAngle() {
		return getPosition() * RobotMap.Turret.DEGREES_PER_COUNT;
	}
	
	/**
	 * Gets the turret's position according to the CANTalon.
	 * @return in encoder counts
	 */
	public double getPosition(){
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
		if(DEBUG ){
			SmartDashboard.putData("Turret PID", control);
			SmartDashboard.putNumber("Turret Power", getPower());
			SmartDashboard.putNumber("Turret Speed", getSpeed());
			SmartDashboard.putNumber("Turret Setpoint", getSetpoint());
			SmartDashboard.putBoolean("Turret at forward limit", turretRotator.isFwdLimitSwitchClosed());
			SmartDashboard.putBoolean("Turret at reverse limit", turretRotator.isRevLimitSwitchClosed());
		}
		SmartDashboard.putNumber("Turret Position", getPosition());
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

