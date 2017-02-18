package org.usfirst.frc.team868.robot.commands.subsystems.drive;

import org.usfirst.frc.team868.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team868.robot.subsystems.GyroSubsystem;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToAngle extends Command {

    private DriveSubsystem motors;
    private PIDController controller;
    private final double P = 0, I = 0, D = 0;
    private double setAngle;

    /**
     * Rotates the robot to the orientation of the given angle.
     * (Note that this is not relative to the robot's orientation is when this
     * command is called)
     * 
     * @param angle
     *            in degrees
     */
    public TurnToAngle(double angle) {
        motors = DriveSubsystem.getInstance();
        requires(motors);
        setAngle = angle;
        controller = new PIDController(P, I, D, new PIDSource() {

            @Override
            public void setPIDSourceType(PIDSourceType pidSource) {
            }

            @Override
            public PIDSourceType getPIDSourceType() {
                return PIDSourceType.kDisplacement;
            }

            @Override
            public double pidGet() {
                return GyroSubsystem.getInstance().getRotation();
            }

        }, new PIDOutput() {

            @Override
            public void pidWrite(double output) {
                motors.setL(rangeCheck(output));
                motors.setR(rangeCheck(-output));
            }

        });
        controller.setAbsoluteTolerance(1);
        controller.setToleranceBuffer(3);// I read the docs, and thought it was
                                         // applicable, may be good to test if
                                         // it is helpful or not.
    }

    public double rangeCheck(double power) {
        if (power > 1) {
            return 1;
        } else if (power < -1) {
            return -1;
        } else {
            return power;
        }
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        controller.setSetpoint(setAngle);
        controller.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return controller.onTarget();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        controller.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
