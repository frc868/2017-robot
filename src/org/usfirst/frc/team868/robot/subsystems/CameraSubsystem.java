package org.usfirst.frc.team868.robot.subsystems;

import org.opencv.core.Mat;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The Camera is responsible for autonomous vision processing on the robot.
 * It can also be used as live video feed to the Smart Dashboard as an aid
 * for drivers and operators. 
 */
public class CameraSubsystem extends Subsystem {
	
	private static CameraSubsystem rearInstance;
	private static CameraSubsystem frontInstance;
	private Thread visionThread; //NOTE by having this, you won't be able to have both a front and back camera
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private CameraSubsystem(VideoCamera camera){
		if(visionThread != null) visionThread.interrupt();
		
		visionThread = new Thread(() -> {
			
			//camera.setFPS(30); //TODO this may be causing issues
			
			// Get the Axis camera from CameraServer
			CameraServer.getInstance().addCamera(camera);

			// Get a CvSink. This will capture Mats from the camera
			CvSink cvSink = CameraServer.getInstance().getVideo();
			// Setup a CvSource. This will send images back to the Dashboard
			CvSource outputStream = CameraServer.getInstance().putVideo("Rectangle", 320, 240);
			//outputStream.setFPS(30);

			// Mats are very memory expensive. Lets reuse this Mat.
			Mat mat = new Mat();

			// This cannot be 'true'. The program will never exit if it is. This
			// lets the robot stop this thread when restarting robot code or
			// deploying.
			while (!Thread.interrupted()) {
				// Tell the CvSink to grab a frame from the camera and put it
				// in the source mat.  If there is an error notify the output.
				if (cvSink.grabFrame(mat) == 0) {
					// Send the output the error.
					outputStream.notifyError(cvSink.getError());
					// skip the rest of the current iteration
					continue;
				}
				// Put a rectangle on the image
//				Imgproc.rectangle(mat, new Point(100, 100), new Point(400, 400),
//						new Scalar(255, 255, 255), 5);
				// Give the output stream a new image to display
				outputStream.putFrame(mat);
			}
		});
		visionThread.setDaemon(true);
		visionThread.start();
	}
	
	/* TODO: Add a getInstance method for the PixiCam. It will be mounted
	 * on the turret shooter and can locate the reflective tape
	 * on the boilers. Once the boiler is located, the {@link TurretRotationSubsystem}
	 * can rotate the turret to aim at the goal.
	 * */
	
	/**
	 * Return an instance of the USB camera mounted on the rear of the robot.
	 * 
	 * @return An instance of the rear-mounted USB camera.
	 * */
	public static CameraSubsystem getRearInstance(){
		if(rearInstance == null){
			UsbCamera cam = new UsbCamera("rear", 0);
			cam.setResolution(320, 240);
			rearInstance = new CameraSubsystem(cam);
		}
		return rearInstance;
	}
	
	/**
	 * Return and instance of the Axis Camera that is mounted on the
	 * gear collector. This camera will be used by the drivers to align
	 * the robot while collecting gears from the feeder station, and
	 * so sophisticated vision processing will likely not be required.
	 * 
	 * @deprecated Not using the network cam
	 * 
	 * @return An instance of the Axis camera on the gear collector.
	 */
	public static CameraSubsystem getFrontInstance(){
		if(frontInstance == null){
			AxisCamera cam = new AxisCamera("front", "10.8.68.11");
			cam.setResolution(320, 240);
			frontInstance = new CameraSubsystem(cam);
		}
		return frontInstance;
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}