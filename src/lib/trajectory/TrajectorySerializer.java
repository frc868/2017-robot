package lib.trajectory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import lib.trajectory.Trajectory.Segment;

public class TrajectorySerializer {

	public static Trajectory readFromCSV(File file) {
		ArrayList<Segment> segments = new ArrayList<>();
		
		try {
			Scanner scanner = new Scanner(file);
			
			while(scanner.hasNextLine()) {

				Segment seg = new Segment();
				
				String[] data = scanner.nextLine().split(",");
				seg.dt = Double.parseDouble(data[0]);
				seg.x = Double.parseDouble(data[1]);
				seg.y = Double.parseDouble(data[2]);
				seg.pos = Double.parseDouble(data[3]);
				seg.vel = Double.parseDouble(data[4]);
				seg.acc = Double.parseDouble(data[5]);
				seg.jerk = Double.parseDouble(data[6]);
				seg.heading = Double.parseDouble(data[7]);
				
				segments.add(seg);
				
			}
			
			Segment[] segs = new Segment[segments.size()];
			segments.toArray(segs);
			
			return new Trajectory(segs);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return new Trajectory(0);
	}
}
