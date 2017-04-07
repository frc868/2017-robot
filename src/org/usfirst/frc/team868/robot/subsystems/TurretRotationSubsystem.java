package org.usfirst.frc.team868.robot.subsystems;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.commands.operator.JoystickTurretControl;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import lib.util.HoundMath;

/**
 *
 */
public class TurretRotationSubsystem extends Subsystem {

	private static TurretRotationSubsystem instance;
	private CANTalon turretRotator;
	private PIDController control;
	private final double P = 0.12, I = 0, D = 0.02;
	private boolean isPixyTargeting = true;
	private final boolean DEBUG = false;

	private TurretRotationSubsystem(){
		turretRotator = new CANTalon(RobotMap.Turret.TURRET_MOTOR);
		turretRotator.setInverted(RobotMap.Turret.IS_INVERTED);
		// Make sure we stop if we hit a physical limit switch
		turretRotator.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		turretRotator.ConfigFwdLimitSwitchNormallyOpen(true);
		turretRotator.ConfigRevLimitSwitchNormallyOpen(true);
		turretRotator.enableLimitSwitch(true, true);
		
		turretRotator.changeControlMode(CANTalon.TalonControlMode.Voltage);
		turretRotator.enableBrakeMode(true);
		turretRotator.setVoltageRampRate(RobotMap.Turret.RAMP_RATE);
		control = new PIDController(P, I, D, new PIDSource(){

			public void setPIDSourceType(PIDSourceType pidSource) {}

			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kDisplacement;
			}

			public double pidGet() {
				return getAngle();
			}
			
		}, new PIDOutput(){

			public void pidWrite(double output) {
				if(output < 0){
					output -= RobotMap.Turret.MIN_PID_ADDITIONAL_VOLTAGE;
				}else if(output > 0){
					output += RobotMap.Turret.MIN_PID_ADDITIONAL_VOLTAGE;
				}
				setPower(output);
			}
			
		});
		control.setAbsoluteTolerance(.5);
		control.setOutputRange(-RobotMap.Turret.MAX_VOLTAGE, RobotMap.Turret.MAX_VOLTAGE);
		
		// Assign test mode group
    	LiveWindow.addActuator("Turret", "Motor", turretRotator);
    	SmartDashboard.putData(this);
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
		return isLeftLimitSwitchClosed() || isRightLimitSwitchClosed();
	}
	
	/**
	 * @return whether the leftmost limit switch has been closed.
	 */
	public boolean isLeftLimitSwitchClosed(){
		return turretRotator.isRevLimitSwitchClosed();//!leftLimit.get();
	}
	
	public boolean isAtLeftSoftLimit(){
		return turretRotator.getPosition() >= turretRotator.getForwardSoftLimit();
	}
	
	public boolean isAtRightSoftLimit(){
		return turretRotator.getPosition() <= turretRotator.getReverseSoftLimit();
	}

	/**
	 * @return whether the rightmost limit switch has been closed.
	 */
	public boolean isRightLimitSwitchClosed(){
		return turretRotator.isFwdLimitSwitchClosed();//!rightLimit.get();
	}
	
	/**
	 * Sets the power being output to the turret's motor.
	 * @param power in voltage from -12 to 12
	 */
	public void setPower(double power){
		if(isLeftLimitSwitchClosed())
			power = HoundMath.checkRange(power, 0, RobotMap.Turret.MAX_VOLTAGE);
		else if(isRightLimitSwitchClosed())
			power = HoundMath.checkRange(power, -RobotMap.Turret.MAX_VOLTAGE, 0);
		else
			power = HoundMath.checkRange(power, -RobotMap.Turret.MAX_VOLTAGE, RobotMap.Turret.MAX_VOLTAGE);
		turretRotator.set(power);
	}
	
	/**
	 * Deactivates the PID controller and sets the turret's motor's power to 0.
	 */
	public void stop(){
		control.disable();
		turretRotator.set(0);
	}
	
	@SuppressWarnings("unused")
	private void calibrateTurret() {// use the command instead
		/*
		turretRotator.enableForwardSoftLimit(false);
		turretRotator.enableReverseSoftLimit(false);
		while(!isRightLimitSwitchClosed()){
			setPower(RobotMap.Turret.MIN_VOLTAGE);
		}
		stop();
		double rightLimit = getPosition();
		while(!isLeftLimitSwitchClosed()) {
			setPower(-RobotMap.Turret.MIN_VOLTAGE);
		}
		stop();
		double leftLimit = getPosition();
		setAngle(getAngle()+RobotMap.Turret.LEFT_LIMIT_TO_FORWARD);
		while(!control.onTarget()){}
		double endPos = getPosition();
		stop();
		resetPosition();
		setAngle(0);
		stop();
		turretRotator.setForwardSoftLimit(leftLimit-endPos-RobotMap.Turret.SOFT_LIMIT_OFFSET);
		turretRotator.setReverseSoftLimit(rightLimit-endPos+RobotMap.Turret.SOFT_LIMIT_OFFSET);
		turretRotator.enableForwardSoftLimit(true);
		turretRotator.enableReverseSoftLimit(true);
		if(control.isEnabled())
			stop();
		*/
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
		setSetpoint(degree);
	}
	
	/**
	 * Gets the current power being put out to the turret's motor.
	 * @return power in voltage from -12 to 12
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
		return -getPosition() * RobotMap.Turret.DEGREES_PER_COUNT;
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
		return control.getSetpoint() * RobotMap.Turret.DEGREES_PER_COUNT;
	}
	
	public void toggleTargeting(boolean setTo){
		isPixyTargeting = setTo;
	}
	
	public boolean isPixyTargeting(){
		return isPixyTargeting;
	}
	
	public boolean isPIDEnabled(){
		return control.isEnabled();
	}
	
	public boolean isOnTarget(){
		return control.onTarget();
	}
	
	public void disableSoftLimits(){
		turretRotator.enableForwardSoftLimit(false);
		turretRotator.enableReverseSoftLimit(false);
	}
	
	public void setSoftLimits(double forward, double backward){
		turretRotator.setForwardSoftLimit(forward);
		turretRotator.setReverseSoftLimit(backward);
		turretRotator.enableForwardSoftLimit(true);
		turretRotator.enableReverseSoftLimit(true);
	}
	
	/**
	 * Update information on SmartDashboard.
	 */
	public void updateSD(){
		if(DEBUG ){
			SmartDashboard.putData("Turret PID", control);
			SmartDashboard.putNumber("Turret Power", getPower());
			SmartDashboard.putNumber("Turret Speed", getSpeed());
			SmartDashboard.putNumber("Turret Position", getPosition());
			SmartDashboard.putNumber("Turret Setpoint", getSetpoint());
			SmartDashboard.putBoolean("Turret at forward limit", isLeftLimitSwitchClosed());
			SmartDashboard.putBoolean("Turret at reverse limit", isRightLimitSwitchClosed());
		}
		SmartDashboard.putNumber("Turret Angle", getAngle());
		SmartDashboard.putBoolean("Pixy turret targeting", isPixyTargeting());
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
		setDefaultCommand(new JoystickTurretControl());
	}

	/**
	 * Starts the process of calibrating the turret (disables soft limits and starts moving towards limit switch).
	 */
	public void calibrationBegin() {
		turretRotator.enableForwardSoftLimit(false);
		turretRotator.enableReverseSoftLimit(false);
		if (!isCalibrationDone()) {
			// Start moving if not at limit switch
			setPower(-RobotMap.Turret.MIN_VOLTAGE);
		}
	}

	/**
	 * Checks to see if hard limit switch is reached and if so, stops motors and re-enables soft limits.
	 * 
	 * @return true If calibration when at limit switch and calibration done.
	 */
	public boolean isCalibrationDone() {
		boolean done = false;
		
		// Once we've closed the limit switch, we are done
		// WARNING: I am uncertain at this point how left/right map to forward/reverse!!!
		if (isLeftLimitSwitchClosed()) {
			stop();
			
			// Set current position to where limit switch is located and re-enable soft limits
			double degreesBetweenLimits = 280.0; // RobotMap.Turret.ROTATION_RANGE_DEG
			double countsBetweenLimits = degreesBetweenLimits * RobotMap.Turret.COUNTS_PER_DEGREE;
			double rightLimitPosition = countsBetweenLimits / 2;
			double leftLimitPosition = -rightLimitPosition;
			turretRotator.setPosition(leftLimitPosition);
			turretRotator.setReverseSoftLimit(leftLimitPosition + RobotMap.Turret.SOFT_LIMIT_OFFSET);
			turretRotator.setForwardSoftLimit(rightLimitPosition - RobotMap.Turret.SOFT_LIMIT_OFFSET);
			turretRotator.enableForwardSoftLimit(true);
			turretRotator.enableReverseSoftLimit(true);
		}
		return done;
	}

	/**
	 * Any command that starts calibrating ({@link #calibrationBegin()) should call this if they give up.
	 */
	public void calbrationCancel() {
		// Give one last chance to check if calibration is done.
		if (!isCalibrationDone()) {
			// Stop moving, and leave soft limits disabled as we haven't reached the
			// limit switch.
			stop();
		}
	}
}
