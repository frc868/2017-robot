package org.usfirst.frc.team868.robot.subsystems;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CameraSubsystem extends Subsystem {
	
	private static CameraSubsystem rearInstance;
	private static CameraSubsystem frontInstance;
	private Thread visionThread;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private CameraSubsystem(VideoCamera camera){
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
	
	public static CameraSubsystem getRearInstance(){
		if(rearInstance == null){
			UsbCamera cam = new UsbCamera("rear", 0);
			cam.setResolution(320, 240);
			rearInstance = new CameraSubsystem(cam);
		}
		return rearInstance;
	}
	
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

