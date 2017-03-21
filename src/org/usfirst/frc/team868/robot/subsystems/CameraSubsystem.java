package org.usfirst.frc.team868.robot.subsystems;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The Camera is responsible for autonomous vision processing on the robot.
 * It can also be used as live video feed to the Smart Dashboard as an aid
 * for drivers and operators. 
 */
public class CameraSubsystem extends Subsystem {
	
	private Thread visionThread;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public CameraSubsystem(){
		UsbCamera camera = new UsbCamera("rear", 0);
		camera.setResolution(320, 240);
		
		visionThread = new Thread(() -> {
			// Get the Axis camera from CameraServer
			CameraServer.getInstance().addCamera(camera);

			// Get a CvSink. This will capture Mats from the camera
			CvSink cvSink = CameraServer.getInstance().getVideo();
			// Setup a CvSource. This will send images back to the Dashboard
			CvSource outputStream = CameraServer.getInstance().putVideo("Rectangle", 320, 240);

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
				Imgproc.rectangle(mat, new Point(100, 100), new Point(400, 400),
						new Scalar(255, 255, 255), 5);
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

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}