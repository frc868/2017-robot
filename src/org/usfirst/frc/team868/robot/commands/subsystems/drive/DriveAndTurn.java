package org.usfirst.frc.team868.robot.commands.subsystems.drive;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveAndTurn extends Command {

    private boolean isFinished = false;
    private DriveSubsystem drive;
    private int endCount;
    private boolean isTurningR;

    public DriveAndTurn(double cm, boolean isTurningR) {
        this((int) (cm * RobotMap.Drive.COUNTS_PER_CM));
        this.isTurningR = isTurningR;
    }

    private DriveAndTurn(int counts) {
        drive = DriveSubsystem.getInstance();
        requires(drive);

        endCount = drive.getAvgEncoders() + counts;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        if (isTurningR) {
            drive.setR(RobotMap.Drive.SPEED);
            drive.setL(RobotMap.Drive.SPEED / 2.0);
        } else {
            drive.setL(RobotMap.Drive.SPEED);
            drive.setR(RobotMap.Drive.SPEED / 2.0);
        }
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        if (drive.getAvgEncoders() > endCount) {
            isFinished = true;
            drive.setSpeed(0);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
