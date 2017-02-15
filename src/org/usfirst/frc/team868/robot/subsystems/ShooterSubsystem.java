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
public class ShooterSubsystem extends Subsystem {

    private static ShooterSubsystem instance;
    private CANTalon shooter;
    private PIDController control;
    private Encoder count;
    public static final double P = 0;
    public static final double I = 0;
    public static final double D = 0;
    
    private ShooterSubsystem(){
    	shooter = new CANTalon(RobotMap.Shoot.SHOOTER_MOTOR);
    	// TODO: Need second shooter motor controller set to follower mode
    	shooter.setInverted(RobotMap.Shoot.IS_INVERTED);
    	count = new Encoder(RobotMap.Shoot.ENCODER_A, RobotMap.Shoot.ENCODER_B);
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
				shooter.set(output);
			}
    		
    	}, 1/100);
    	control.setOutputRange(0, 1);
    	
		// Assign test mode group
    	LiveWindow.addActuator("Shooter", "Motor Main", shooter);
    	//LiveWindow.addActuator("Shooter", "Motor Follower", shooter2);
		LiveWindow.addSensor("Shooter", "Encoder", count);
    }
    
    /**
	 * Sets the shooter's power
	 * @param power in percentage from -1 to 1.
	 */
    public void setPower(double power){
    	control.disable();
    	shooter.set(power);
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
    	control.setSetpoint(speed);
    	control.enable();
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
     * @return in percentage from -1 to 1
     */
    public double getPower(){
    	return shooter.get();
    }
    
    /**
     * Gets the number of counts that the shooter's encoder has tracked.
     * @return in counts
     */
    public double getCounts() {
    	return shooter.getPosition();
    	
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

