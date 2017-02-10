package com.techhounds;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.Trajectory;
import com.team254.lib.trajectory.Trajectory.Segment;
import com.team254.lib.trajectory.TrajectoryGenerator;
import com.team254.lib.trajectory.io.TextFileSerializer;

public class Functions {

	protected static TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();;

	public static final double dt = 0.0, max_acc = 0.0, max_jerk = 0.0, max_vel = 0.0;

	public static void config(double percentVel) {
		config.max_vel = percentVel * max_vel;
	}

	public static void create(Path path, String directory, String workingDirectory, String path_name, boolean toFlip) {
		// Outputs to the directory supplied as the first argument.
		TextFileSerializer js = new TextFileSerializer();
		String serialized = js.serialize(path);

		// System.out.print(serialized);
		String fullpath = directory + "/" + path_name + ".csv";

		String leftSerialized = js.serializeLeft(path);
		String rightSerialized = js.serializeRight(path);

		if (toFlip) {
			leftSerialized = js.serialize(flipTrajectory(path.getLeftWheelTrajectory()));
			rightSerialized = js.serialize(flipTrajectory(path.getRightWheelTrajectory()));
			serialized = leftSerialized + rightSerialized;
		}

		String left = workingDirectory + "/" + path_name + "/left.csv";
		String right = workingDirectory + "/" + path_name + "/right.csv";

		if (!writeFile(fullpath, serialized) || !writeFile(left, leftSerialized)
				|| !writeFile(right, rightSerialized)) {
			System.err.println(fullpath + " could not be written!!!!1");
			System.exit(1);
		} else {
			System.out.println("Wrote " + fullpath);
		}
	}

	public static Trajectory flipTrajectory(Trajectory toFlip) {
		Segment[] segs = new Segment[toFlip.getNumSegments()];

		for (int k = 0, i = toFlip.getNumSegments() - 1; i >= 0; i--, k++) {
			Segment seg = toFlip.getSegment(i);
			Segment seg2 = new Segment();
			seg2.dt = seg.dt;
			seg2.x = seg.x;
			seg2.y = seg.y;
			seg2.pos = seg.pos;
			seg2.vel = -seg.vel;
			seg2.acc = seg.acc;
			seg2.jerk = -seg.jerk;
			seg2.heading = seg.heading;
			segs[k] = seg2;
		}

		return new Trajectory(segs);
	}

	private static boolean writeFile(String path, String data) {
		try {
			File file = new File(path);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(data);
			bw.close();
		} catch (IOException e) {
			return false;
		}

		return true;
	}
}
