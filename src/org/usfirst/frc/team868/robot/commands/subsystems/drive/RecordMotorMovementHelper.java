package org.usfirst.frc.team868.robot.commands.subsystems.drive;

import java.io.IOException;

import edu.wpi.first.wpilibj.command.Command;
import lib.util.RecordMotorMovement;

/**
 *
 */
public class RecordMotorMovementHelper extends Command {

    String todo = "", fileLoc = "";
    boolean isFinished = false;

    public RecordMotorMovementHelper(String todo, String fileLoc) {
        this.todo = todo;
        this.fileLoc = fileLoc;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        if (todo.equals("saveFile")) {
            try {
                RecordMotorMovement.getInstance().saveFile(fileLoc);
            } catch (IOException e) {
            }
            isFinished = true;
        } else if (todo.equals("readFile")) {
            try {
                RecordMotorMovement.getInstance().readFile(fileLoc);
            } catch (IOException e) {
            }
            isFinished = true;
        } else if (todo.equals("record")) {

        }
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        RecordMotorMovement.getInstance().RecordMotors();
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
