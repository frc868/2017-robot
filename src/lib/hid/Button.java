package lib.hid;

import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Implement our own Button class so that we can get the Button back on call.
 *
 * @author Atif Niyaz
 */
public abstract class Button extends Trigger {

    public Button whenPressed(final Command... command) {
        whenActive(setupCommand(command));
        return this;
    }

    public Button whileHeld(final Command... command) {
        whileActive(setupCommand(command));
        return this;
    }

    public Button whenReleased(final Command... command) {
        whenInactive(setupCommand(command));
        return this;
    }

    public Button toggleWhenPressed(final Command... command) {
        toggleWhenActive(setupCommand(command));
        return this;
    }

    public Button cancelWhenPressed(final Command... command) {
        cancelWhenActive(setupCommand(command));
        return this;
    }

    private Command setupCommand(Command... command) {

        if (command.length == 1) {
            return command[0];
        } else if (command.length == 0) {
            return new WaitCommand(0);
        }

        CommandGroup commandGroup = new CommandGroup();
        for (Command commands : command) {
            commandGroup.addParallel(commands);
        }

        return commandGroup;
    }
}
