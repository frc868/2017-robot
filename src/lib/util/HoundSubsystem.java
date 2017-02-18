package lib.util;

import java.util.Vector;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class HoundSubsystem extends Subsystem {

    private static Vector<HoundSubsystem> subsystems = new Vector<>();

    public HoundSubsystem() {
        HoundSubsystem.subsystems.add(this);
    }

    public HoundSubsystem(String name) {
        super(name);
        HoundSubsystem.subsystems.add(this);
    }

    public static void updateSubsystemsPeriodic() {
        for (HoundSubsystem sub : HoundSubsystem.subsystems) {
            sub.updatePeriodic();
        }
    }

    public abstract void updatePeriodic();
}
