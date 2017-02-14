package org.usfirst.frc.team868.robot.subsystems;

import org.usfirst.frc.team868.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
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
	}
	
	private void makeSoftLimits(){
		turretRotator.setForwardSoftLimit(RobotMap.Turret.FORWARD_LIMIT);
		turretRotator.setReverseSoftLimit(RobotMap.Turret.REVERSE_LIMIT);
		turretRotator.enableForwardSoftLimit(true);
		turretRotator.enableReverseSoftLimit(true);
	}
	
	public void resetPosition(){
		setPosition(0);
	}

	public void setPosition(double pos){
		turretRotator.setPosition(pos);
	}
	
	public boolean isALimitSwitchClosed(){
		return turretRotator.isFwdLimitSwitchClosed() || turretRotator.isRevLimitSwitchClosed();
	}
	
	public void setPower(double power){
		turretRotator.set(power);
	}
	
	public void setSetpoint(double point){
		control.setSetpoint(point);
		if(!control.isEnabled())
			control.enable();
	}
	
	public void setAngle(double degree) {
		setSetpoint(degree * RobotMap.Turret.COUNTS_PER_DEGREE);
	}
	
	public double getPower(){
		return turretRotator.get();
	}
	
	public double getSpeed(){
		return count.getRate();
	}
	
	public double getEncoderPosition(){
		return count.get();
	}
	
	public double getAngle() {
		return getEncoderPosition() * RobotMap.Turret.DEGREES_PER_COUNT;
	}
	
	public double getCANTalonPosition(){
		return turretRotator.getPosition();
	}
	
	public double getSetpoint(){
		return turretRotator.getSetpoint();
	}
	
	public void updateSD(){
		SmartDashboard.putData("Turret PID", control);
		SmartDashboard.putNumber("Turret Position", getEncoderPosition());
		SmartDashboard.putNumber("Turret Power", getPower());
		SmartDashboard.putNumber("Turret Speed", getSpeed());
		SmartDashboard.putNumber("Turret Setpoint", getSetpoint());
	}

	public static TurretRotationSubsystem getInstance(){
		if(instance == null){
			instance = new TurretRotationSubsystem();
		}
		return instance;
	}

	public void initDefaultCommand() {
	}
}

