package lib.trajectory;

public class TrajectoryPair {

    private Trajectory left, right;

    public TrajectoryPair(Trajectory left, Trajectory right) {
        this.left = left;
        this.right = right;
    }

    public Trajectory getLeft() {
        return left;
    }

    public Trajectory getRight() {
        return right;
    }
}
