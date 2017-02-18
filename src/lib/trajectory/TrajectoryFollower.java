package lib.trajectory;

import lib.util.HoundMath;

public class TrajectoryFollower {

    private Trajectory trajectory;
    private Double Kp, Ki, Kd, Kv, Ka, Kturn;

    private int currSeg;
    private double lastError;

    public TrajectoryFollower(Trajectory trajectory) {
        this.trajectory = trajectory;
    }

    public TrajectoryFollower(Trajectory trajectory, double Kp, double Ki, double Kd, double Kv, double Ka) {
        this(trajectory);
        setGains(Kp, Ki, Kd, Kv, Ka);
    }

    public TrajectoryFollower(Trajectory trajectory, double Kp, double Ki, double Kd, double Kv, double Ka,
            double Kturn) {
        this(trajectory);
        setGains(Kp, Ki, Kd, Kv, Ka, Kturn);
    }

    public void setGains(double Kp, double Ki, double Kd, double Kv, double Ka) {
        this.Kp = Kp;
        this.Ki = Ki;
        this.Kp = Kp;
        this.Kv = Kv;
        this.Ka = Ka;
    }

    public void setGains(double Kp, double Ki, double Kd, double Kv, double Ka, double Kturn) {
        setGains(Kp, Ki, Kd, Kv, Ka);
        this.Kturn = Kturn;
    }

    public void reset() {
        lastError = 0;
        currSeg = 0;
    }

    public boolean isFinished() {
        return currSeg > trajectory.getLength() - 1;
    }

    public double calculateOutput(double currDistance) {
        if (Kp == null || Ki == null || Kd == null || Kv == null || Ka == null || isFinished()) {
            return 0;
        }

        Trajectory.Segment segment = trajectory.getSegment(currSeg);

        double error = currDistance - segment.pos;

        double output = Kp * error + 													// Kp
                Kd * ((error - lastError) / segment.dt - segment.vel) + 		// Kd

                // Feedforward Terms
                Kv * segment.vel +												// Kv
                Ka * segment.acc;												// Ka

        lastError = error;
        currSeg++;

        return output;
    }

    public double getHeading() {
        if (isFinished()) return 0;
        return trajectory.getSegment(currSeg).heading;
    }

    public double calculateHeading(double currHeading) {
        if (Kturn == null || isFinished()) return 0;
        return Kturn * HoundMath.getAngleDifferenceDegrees(currHeading, getHeading());
    }
}
