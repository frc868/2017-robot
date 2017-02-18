package lib.trajectory;

import java.io.File;
import java.util.HashMap;

public class TrajectoryLoader {

    private static TrajectoryLoader instance;
    private static HashMap<String, TrajectoryPair> trajectories;

    private TrajectoryLoader() {
        loadTrajectories();
    }

    public void loadTrajectories() {
        (new LoadFiles()).run();
    }

    public class LoadFiles implements Runnable {

        @Override
        public void run() {

            File[] directories = new File("/trajectory").listFiles(File::isDirectory);

            trajectories = new HashMap<>();

            for (int i = 0; i < directories.length; i++) {

                String path = directories[i].getAbsolutePath();
                String dirName = directories[i].getName();

                Trajectory left2 = TrajectorySerializer.readFromCSV(new File(path + "/left.csv"));
                Trajectory right2 = TrajectorySerializer.readFromCSV(new File(path + "/right.csv"));

                trajectories.put(dirName, new TrajectoryPair(left2, right2));
            }
        }
    }

    public TrajectoryPair getTrajectory(String name) {
        return trajectories.get(name);
    }

    public static TrajectoryLoader getInstance() {
        return instance == null ? instance = new TrajectoryLoader() : instance;
    }
}
