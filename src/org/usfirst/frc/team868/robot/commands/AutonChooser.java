package org.usfirst.frc.team868.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 *
 */
public class AutonChooser {

    private static AutonChooser instance;

    private static SendableChooser<StartingPoint> chooseStart;
    private static SendableChooser<DoThis> chooseDoThis;

    public static AutonChooser getInstance() {
        if (AutonChooser.instance == null) {
            AutonChooser.instance = new AutonChooser();
        }
        return AutonChooser.instance;
    }

    private AutonChooser() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        setupDashboard();
    }

    public static enum StartingPoint {
        B1, B2, B3, R1, R2, R3
    }

    public static enum DoThis {
        CROSS_BASE, DROP_GEAR, HOPPER, GEAR_TO_NEUTRAL, GEAR_TO_HOPPER
    }

    public static StartingPoint getStart() {
        return (AutonChooser.chooseStart.getSelected());
    }

    public static DoThis getDoThis() {
        return (AutonChooser.chooseDoThis.getSelected());
    }

    public void setupDashboard() {
        AutonChooser.chooseStart = new SendableChooser<>();
        AutonChooser.chooseStart.addObject("B1", StartingPoint.B1);
        AutonChooser.chooseStart.addObject("B2", StartingPoint.B2);
        AutonChooser.chooseStart.addObject("B3", StartingPoint.B3);
        AutonChooser.chooseStart.addObject("R1", StartingPoint.R1);
        AutonChooser.chooseStart.addObject("R2", StartingPoint.R2);
        AutonChooser.chooseStart.addObject("R3", StartingPoint.R3);

        AutonChooser.chooseDoThis = new SendableChooser<>();
        AutonChooser.chooseDoThis.addObject("A: Cross the Baseline", DoThis.CROSS_BASE);
        AutonChooser.chooseDoThis.addObject("B: Drop off Gear", DoThis.DROP_GEAR);
        AutonChooser.chooseDoThis.addObject("C: Shoot Balls", DoThis.HOPPER);
        AutonChooser.chooseDoThis.addObject("D: Drop off Gear Then Go To Neutral", DoThis.GEAR_TO_NEUTRAL);
        AutonChooser.chooseDoThis.addObject("E: Drop Off Gear Then Hit Hopper", DoThis.GEAR_TO_HOPPER);
    }

}
