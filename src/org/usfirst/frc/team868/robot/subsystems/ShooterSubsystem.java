package org.usfirst.frc.team868.robot.subsystems;

import org.usfirst.frc.team868.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Counter;
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
public class ShooterSubsystem extends Subsystem {

    private static ShooterSubsystem instance;
    private CANTalon rightShooter;
    private CANTalon leftShooter;
    private PIDController control;
    private Counter count;
    public static final double P = 0;
    public static final double I = 0;
    public static final double D = 0;
    
    private ShooterSubsystem(){
    	rightShooter = new CANTalon(RobotMap.Shoot.RIGHT_SHOOTER_MOTOR);
    	leftShooter = new CANTalon(RobotMap.Shoot.LEFT_SHOOTER_MOTOR);
    	
    	rightShooter.setInverted(RobotMap.Shoot.IS_INVERTED);
    	leftShooter.setInverted(!RobotMap.Shoot.IS_INVERTED);
    	
    	rightShooter.changeControlMode(CANTalon.TalonControlMode.Voltage);
    	leftShooter.changeControlMode(CANTalon.TalonControlMode.Follower);
    	leftShooter.set(rightShooter.getDeviceID());
    	count = new Counter(RobotMap.Shoot.ENCODER_A);
    	
    	count.setDistancePerPulse(RobotMap.Shoot.ROTATIONS_PER_COUNT);

    	control = new PIDController(P, I, D, new PIDSource(){

			public void setPIDSourceType(PIDSourceType pidSource) {
			}

			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kRate;
			}

			public double pidGet() {
				return getSpeed();
			}
    		
    	}, new PIDOutput(){

			public void pidWrite(double output) {
				rightShooter.set(output);
			}
    		
    	});
    	control.setOutputRange(0, 12);
    	
		// Assign test mode group
    	LiveWindow.addActuator("Shooter", "RIGHT", rightShooter);
    	LiveWindow.addActuator("Shooter", "LEFT", leftShooter);
		LiveWindow.addSensor("Shooter", "Encoder", count);
    }
    
    /**
	 * Sets the shooter's power
	 * @param power in voltage from 0 to 12.
	 */
    public void setPower(double power){
    	control.disable();
    	rightShooter.set(HoundMath.checkRange(power, 0, 12));
    }
    /**
     * @return the shooter's PID controller
     */
    public PIDController getPIDController(){
    	return control;
    }
    
    /**
     * Sets the shooter's speed using it's PID controller.
     * @param speed
     */
    public void setSpeed(double speed) {
    	control.setSetpoint(HoundMath.checkRange(speed, 0, 1));
    	control.enable();
    }
    
    /**
     * Gets the shooter's targeted speed.
     * @return setpoint
     */
    public double getSetpoint(){
    	return control.getSetpoint();
    }
    
    /**
     * Gets the shooter's speed using it's encoder.
     * @return
     */
    public double getSpeed(){
    	return count.getRate();
    }
    
    /**
     * Gets the shooter's power.
     * @return in voltage from 0 to 12
     */
    public double getPower(){
    	return rightShooter.get();
    }
    
    /**
     * Gets the number of counts that the shooter's encoder has tracked.
     * @return in counts
     */
    public double getCounts() {
    	return rightShooter.getPosition();
    }
    
    /**
	 * Get the instance of this subsystem
	 * @return instance
	 */
    public static ShooterSubsystem getInstance(){
    	if(instance == null){
    		instance = new ShooterSubsystem();
    	}
    	return instance;
    }
	
	/**
	 * Update information on SmartDashboard.
	 */
    public void updateSD(){
    	SmartDashboard.putNumber("Shooter Speed", getSpeed());
    	SmartDashboard.putNumber("Shooter Power", getPower());
    	SmartDashboard.putData("Shooter PID", control);
    }

    public void initDefaultCommand() {
    }
}

