
package org.usfirst.frc.team868.robot.subsystems;

import org.usfirst.frc.team868.robot.RobotMap;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// @formatter:off
/**
 * Pinout of back of Pixy: ^top of Pixy^ 1 2 3 4 5 6 7 8 9 10
 * 
 * RC Servo ports: ^directly below pinout^ PWM0 PWM1 <---(Programmable Pixy
 * output PWMs) 5V 5V <---(5V of power) GND GND <---(Ground)
 * 
 * Pixy to I2C ports: (kOnboard)
 * 
 * Pixy port 5 goes to I2C port SCL ... 9 to SDA
 * 
 * OR
 * 
 * Pixy to I2C ports: (kMXP)
 * 
 * Pinout of kMXP ports: 
 * 		33 31 29 27 ... 7 5 3 1
 * 		34 32 30 28 ... 8 6 4 2
 * 
 * Pixy port 5 goes to I2C port 32
 * ...	 	 9		to			34
 * 
 */
// @formatter:on
public class ColorPixySubsystem extends Subsystem {
    private static int idCounter = 0;

    public final class Record {
        private int x;
        private int y;
        private int width;
        private int height;
        private int id;

        public Record(int x, int y, int width, int height) {
            this.id = idCounter++;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public int getId() {
            return id;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public int getArea() {
            return width * height;
        }

        /**
         * Returns the angle in degrees off from the x-axis center the target
         * is.
         * Negative degrees means left, positive means right, zero means it is
         * either centered or cannot be found.
         * 
         * @return
         */
        public double getXAngleOffFromCenter() {
            int xMid = target.getX();
            if (xMid != 0) {
                return (xMid - (RobotMap.Pixy.CAM_WIDTH / 2)) * (RobotMap.Pixy.CAM_X_ANGLE / 2)
                        / RobotMap.Pixy.CAM_WIDTH;
            } else {
                return xMid;
            }
        }

        /**
         * Returns the angle in degrees off from the y-axis center the target
         * is.
         * Negative degrees means below, positive means above, zero means it is
         * either centered or cannot be found.
         */
        public double getYAngleOffFromCenter() {
            int yMid = target.getY();
            if (yMid != 0) {
                return (yMid - (RobotMap.Pixy.CAM_HEIGHT / 2)) * (RobotMap.Pixy.CAM_Y_ANGLE / 2)
                        / RobotMap.Pixy.CAM_HEIGHT;
            } else {
                return yMid;
            }
        }

        /**
         * Returns the size of the current target in pixels.
         */
        public int getSizeOfTargetInPixels() {
            return width * height;
        }

        /**
         * Returns the width of the current target in pixels.
         */
        public int getWidthOfTarget() {
            return width;
        }

        /**
         * Returns the height of the current target in pixels.
         */
        public int getHeightOfTarget() {
            return height;
        }
    }

    private static ColorPixySubsystem instance;
    private SerialPort pixyCamS;
    private I2C pixyCamI;
    private int lastVal = 0;
    private boolean recordStarted = false;
    private int bytesRecorded;
    private int[] record = new int[6];
    // volatile private int frame = 0;
    volatile private Record target;

    private Thread thread;

    private ColorPixySubsystem() {
        pixyCamS = null;
        pixyCamI = null;
        target = new Record(0, 0, 0, 0);
        startThread();
    }

    /**
     * Get information about the last target received.
     * 
     * @return Reference to the last target received (note getID() will be 0 if
     *         none found yet).
     */
    public Record getTarget() {
        return target;
    }

    synchronized public void startThread() {
        if (thread != null) return;

        thread = createThread();
        thread.start();
    }

    synchronized public void stopThread() {
        if (thread == null) return;

        thread.interrupt();
        thread = null;
    }

    private boolean checkConnection() {
        // If we still need to create a connection
        if ((pixyCamS == null) && (pixyCamI == null)) {
            if (RobotMap.Pixy.COLOR_PORT_TYPE == RobotMap.SerialPortType.SERIAL_MXP) {
                pixyCamS = new SerialPort(RobotMap.Pixy.BAUDRATE, SerialPort.Port.kMXP);
            } else if (RobotMap.Pixy.COLOR_PORT_TYPE == RobotMap.SerialPortType.I2C_ONBOARD) {
                pixyCamI = new I2C(I2C.Port.kOnboard, RobotMap.Pixy.COLOR_I2C_VALUE);
            } else {
                pixyCamI = new I2C(I2C.Port.kMXP, RobotMap.Pixy.COLOR_I2C_VALUE);
            }
        }
        return (pixyCamS != null) || (pixyCamI != null);
    }

    private Thread createThread() {
        return new Thread() {
            @Override
            public void run() {

                while (!isInterrupted()) {
                    try {
                        if (!checkConnection()) {
                            Thread.sleep(3000);
                            continue;
                        }
                    } catch (Exception ignore) {

                    }
                    getValues();
                }
            }
        };
    }

    synchronized public void getValues() {// Call this method to update all of
                                          // the data obtained from the Pixy
        byte[] input = new byte[14];
        try {
            if (RobotMap.Pixy.COLOR_PORT_TYPE == RobotMap.SerialPortType.SERIAL_MXP) {
                int bytesAvailable = pixyCamS.getBytesReceived();
                if (bytesAvailable == 0) {
                    return;
                }
                input = pixyCamS.read(bytesAvailable);
            } else {
                if (pixyCamI.readOnly(input, 14)) {
                    return;
                }
            }
        } catch (Throwable t) {
            // Force re-open of port
            pixyCamS = null;
            pixyCamI = null;
            return;
        }
        StringBuilder dump = new StringBuilder(48);
        if (input.length > 0) {
            dump.append(Integer.toHexString(input[0] & 0xff));
            for (int i = 1; i < input.length; i++) {
                dump.append(' ');
                dump.append(Integer.toHexString(input[i] & 0xff));
            }
            processBytesRecieved(input);
        }
    }

    private void processBytesRecieved(byte[] input) {// See getValues()
        for (int i = 0; i < input.length; i++) {
            processByte(input[i]);
        }
    }

    private void processByte(byte b) {// See processBytesRecieved()
        int val = b & 0xff;
        if (val == 0xaa && lastVal == 0x55) {
            recordStarted = true;
            bytesRecorded = 0;
        } else if (recordStarted) {
            bytesRecorded++;
            if (bytesRecorded % 2 == 0) {
                int field = (val << 8) + lastVal;
                record[(bytesRecorded / 2) - 1] = field;
                if (bytesRecorded == 12) {
                    processRecord();
                    bytesRecorded = 0;
                }
            }
        }
        lastVal = val;
    }

    private void processRecord() {// Called in processByte(), sends obtained
                                  // Pixy values to field values.
        int xMid = record[2];
        int yMid = record[3];
        int width = record[4];
        int height = record[5];
        target = new Record(xMid, yMid, width, height);
    }

    public void updateSD() {// Updates the SD with the values specified.
        Record target = getTarget();
        SmartDashboard.putNumber("Color Target X", target.getXAngleOffFromCenter());
        SmartDashboard.putNumber("Color Target Y", target.getYAngleOffFromCenter());
        // SmartDashboard.putNumber("Color Camera Target Width",
        // getWidthOfTarget());
        // SmartDashboard.putNumber("Color Camera Target Height",
        // getHeightOfTarget());
        // SmartDashboard.putNumber("Color Frame count", frame);
    }

    synchronized public static ColorPixySubsystem getInstance() {
        if (instance == null) {
            instance = new ColorPixySubsystem();
        }
        return instance;
    }

    public void initDefaultCommand() {
    }
}
