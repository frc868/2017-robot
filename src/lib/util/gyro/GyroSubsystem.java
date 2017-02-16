package lib.util.gyro;

import lib.util.HoundSubsystem;

import edu.wpi.first.wpilibj.GyroBase;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Subsystem that provides access to sensors used to track rotation about x, y
 * an z axis.
 * 
 * <p>
 * General Usage:
 * </p>
 * 
 * <ul>
 * <li>Use {@link #getInstance()}.{@link #createRotationGyro()} to create a
 * zeroed Gryo instance in your Command's initialize() implementation.</li>
 * <li>Use the gyro's getAngle() method in your isFinished() method
 * (it will be zero initially and change as the robot rotates).</li>
 * </ul>
 * 
 * <p>
 * NOTE: This is a "read only" subsystem. You do NOT use requires() in your
 * Command constructor as it is valid for multiple commands to read gyro values
 * at the same time!
 * </p>
 */

// Please do note that the Y gyro may currently be inverted, if so please change the value to true on line 185.

public abstract class GyroSubsystem extends HoundSubsystem {
	
	// NOTE: Several of these constants could be relocated to RobotMap if you want to make them public
	// (I just grew tired of fighting conflicts).
	
	/**
	 * Type of Gyro hardware that can be used by this subsystem (NOTE: FAKE always returns 0).
	 */
	private enum GyroType {
		FAKE, 
		BNO055,
		ITG3200
	}
	
	private double previousAngle = 0;
	private double previousTilt = 0;
	private double previousLean = 0;
	
	/**
	 * Set to type of gyro that is currently installed on the robot.
	 */
	private static final GyroType GYRO_INSTALLED = GyroType.BNO055;
	
	/** The I2C port the BNO055 is connected to (when BNO055 is used). */
	private static final I2C.Port BNO055_PORT = I2C.Port.kOnboard;
	
	/** The I2C port the ITG-3200 is connected to (when ITG-3200 is used). */
	private static final I2C.Port ITG3200_PORT = I2C.Port.kMXP;

	/** Set to true if the address jumper has been installed on your ITG-3200 board. */
	private static final boolean ITG3200_ADDR_JUMPER = false;
	
	// Single instance of the subsystem
	private static GyroSubsystem instance;

	// Instance used to track the rotation throughout the entire match
	public GyroBase gyrox;
	public GyroBase gyroy;
	public GyroBase gyroz;

	/**
	 * Private constructor to force {@link #getInstance()} usage.
	 */
	private GyroSubsystem() {
	}

	/**
	 * Get access to the single instance of the gyro subsystem.
	 * 
	 * @return Reference to gyro subsystem implementation.
	 */
	public static GyroSubsystem getInstance() {
		if (instance == null) {
			switch (GYRO_INSTALLED) {
			case BNO055:
				instance = new GyroBNO055();
				break;
			case ITG3200:
				instance = new GyroITG3200();
				break;
			default:
				instance = new GyroFake();	
			}

			// NOTE: This must be done here and not in the constructor to
			// make use of overridden methods.
			instance.gyroy = instance.createTiltGyro();
			instance.gyrox = instance.createRotationGyro();
			instance.gyroz = instance.createLeanGyro();
			LiveWindow.addSensor("Gyro", "Rotation", instance.gyrox);
			LiveWindow.addSensor("Gyro", "Tilt", instance.gyroy);
			LiveWindow.addSensor("Gyro", "Lean", instance.gyroz);
		}
		return instance;
	}
	
	public void storeCurrentAngle(){
		previousAngle = getRotation();
	}
	
	public double getStoredAngle(){
		return previousAngle;
	}
	
	public void storeCurrentTilt(){
		previousTilt = getTilt();
	}
	
	public double getStoredTilt(){
		return previousTilt;
	}
	
	public void storeCurrentLean() {
		previousLean = getLean();
	}
	
	public double getStoredLean() {
		return previousLean;
	}

	public void updatePeriodic() {
		
	}
	
	/**
	 * Update the dashboard with current heading of the robot (since boot).
	 */
	public void updateSmartDashboard() {
		SmartDashboard.putNumber("Tilt (Y)", getTilt());
	    SmartDashboard.putNumber("Lean", getLean());
		SmartDashboard.putNumber("Rotation (X)", getRotation());
	}

	/**
	 * No default command is required.
	 */
	protected final void initDefaultCommand() {

	}

	/**
	 * Get the total rotation in degrees since the gyro subsystem was first
	 * realized.
	 * 
	 * @return Number of degrees rotated in the range of [-INF, +INF].
	 */
	public final double getRotation() {
		return gyrox.getAngle();
	}
	
	public final double getTilt() {
		return gyroy.getAngle();
	}
	
	public final double getLean() {
		return gyroz.getAngle();
	}
	
	public final double getRotationRadians() {
		return Math.toRadians(getRotation());
	}

	/**
	 * Returns a "nice" independent Gyro your can used in PID methods to track
	 * rotation.
	 * 
	 * @return A Gyro (initially 0) you can use to track relative rotation with.
	 */
	public abstract GyroBase createRotationGyro();
	public abstract GyroBase createTiltGyro();
	public abstract GyroBase createLeanGyro();

	/**
	 * Implementation using BNO055 sensor as the gyro source.
	 */
	private static class GyroBNO055 extends GyroSubsystem {
		private BNO055 sensor;

		GyroBNO055() {
			sensor = BNO055.getInstance(BNO055_PORT);
		}
		
		public GyroBase createTiltGyro() {
			return sensor.createGyroY();
		}

		public GyroBase createRotationGyro() {
			return sensor.createGyroX();
		}
		
		public GyroBase createLeanGyro() {
			return sensor.createGyroZ();
		}
	}

	/**
	 * Implementation using BNO055 sensor as the gyro source.
	 */
	private static class GyroITG3200 extends GyroSubsystem {
		private ITG3200 sensor;

		public GyroITG3200() {
			sensor = new ITG3200(ITG3200_PORT, ITG3200_ADDR_JUMPER);
		}
		
		public GyroBase createTiltGyro() {
			GyroAdapter gyro = sensor.createGyroY();
			gyro.setInverted(false);
			return gyro;
		}

		public GyroBase createRotationGyro() {
			GyroAdapter gyro = sensor.createGyroX();
			gyro.setInverted(true);
			return gyro;
		}
		
		public GyroBase createLeanGyro() {
			GyroAdapter gyro = sensor.createGyroZ();
			gyro.setInverted(true);
			return gyro;
		}
	}

	/**
	 * Fake implementation when no gyro is available.
	 */
	private static class GyroFake extends GyroSubsystem {
		
		public GyroBase createTiltGyro() {
			return GyroAdapter.createFakeGyro();
		}

		public GyroBase createRotationGyro() {
			return GyroAdapter.createFakeGyro();
		}
		
		public GyroBase createLeanGyro() {
			return GyroAdapter.createFakeGyro();
		}
	}

}
