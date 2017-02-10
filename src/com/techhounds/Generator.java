package com.techhounds;

import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.PathGenerator;
import com.team254.lib.trajectory.WaypointSequence;
import com.team254.lib.trajectory.WaypointSequence.Waypoint;

public class Generator extends Functions {

	public static final double dt = 0.0,
	    max_acc = 0.0,
	    max_jerk = 0.0,
	    max_vel = 0.0;

	static final double wheelBase_width = 0.0;
	static String directory = "paths";
	static String workingDirectory = "paths/trajectory";

	public static void main(String[] args) {

	    // Resolve Configuration Values
	    // Always do this
	    config.dt = dt;
	    config.max_acc = max_acc;
	    config.max_jerk = max_jerk;
	    config.max_vel = max_vel;

	    // Creating a Trajectory
	    {
		// Max Power (1 means max_vel)
		config(0.825);

		// Name of Path
		final String path_name = "Drive200";

		// Argument 10 - Maximum Waypoints Allotted in a Waypoint Sequence
		// new Waypoint(y, x, theta);
		WaypointSequence points = new WaypointSequence(10);
		points.addWaypoint(new Waypoint(0, 0, 0));
		points.addWaypoint(new Waypoint(200, 0, 0));

		// makePath(WaypointSequence points, config, Width from Left to Right wheels, Path Name);
		Path path = PathGenerator.makePath(points, config, wheelBase_width, path_name);

		// create(Paths, Combined Trajecotry Directory, Useful Directory, Path Name, Going Backwards);
		create(path, directory, workingDirectory, path_name, false);
	    }
	}
}
