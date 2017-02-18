package org.usfirst.frc.team868.robot.subsystems;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.commands.subsystems.SetLEDs;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LEDSubsystem extends Subsystem {

    private static LEDSubsystem instance;
    private DigitalOutput led1, led2, ledGear;

    private LEDSubsystem() {
        led1 = new DigitalOutput(RobotMap.LEDs.DIO_1_PORT);
        led2 = new DigitalOutput(RobotMap.LEDs.DIO_2_PORT);
        ledGear = new DigitalOutput(RobotMap.LEDs.DIO_GEAR_PORT);
    }

    /**
     * Sets the shooter based LEDs.
     * 
     * @param state1
     * @param state2
     */
    public void setShooterLEDs(boolean state1, boolean state2) {
        led1.set(state1);
        led2.set(state2);
    }

    /**
     * Sets the gear based LED.
     * 
     * @param state
     */
    public void setGearLEDs(boolean state) {
        ledGear.set(state);
    }

    /**
     * Normal get instance method.
     */
    public static LEDSubsystem getInstance() {
        if (instance == null) {
            instance = new LEDSubsystem();
        }
        return instance;
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new SetLEDs());
    }
}
