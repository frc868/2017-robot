package lib.util;

public class DriveSignal {

    public double left, right;

    public DriveSignal(double left, double right) {
        this.left = left;
        this.right = right;
    }

    public static DriveSignal STOP = new DriveSignal(0, 0);
}
