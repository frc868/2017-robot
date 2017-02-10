package com.team254.lib.trajectory.io;

import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.Trajectory;
import com.team254.lib.trajectory.Trajectory.Segment;

/**
 * Serializes a Path to a simple space and CR separated text file.
 * 
 * @author Jared341
 */
public class TextFileSerializer implements IPathSerializer {

  /**
   * Format:
   *   PathName
   *   NumSegments
   *   LeftSegment1
   *   ...
   *   LeftSegmentN
   *   RightSegment1
   *   ...
   *   RightSegmentN
   * 
   * Each segment is in the format:
   *   pos vel acc jerk heading dt x y
   * 
   * @param path The path to serialize.
   * @return A string representation.
   */
  public String serialize(Path path) {
	  String content = "";
    content += serializeTrajectory(path.getLeftWheelTrajectory());
    content += serializeTrajectory(path.getRightWheelTrajectory());
    return content;
  }
  
  public String serializeLeft(Path path) {
	  return serializeTrajectory(path.getLeftWheelTrajectory());
  }
  
  public String serializeRight(Path path) {
	  return serializeTrajectory(path.getRightWheelTrajectory());
  }
  
  public String serialize(Trajectory trajectory) {
	  return serializeTrajectory(trajectory);
  }
  
  public String serializeTogether(Path path) {
	  return serializeTogether(path.getLeftWheelTrajectory(), path.getRightWheelTrajectory());
  }
  
  private String serializeTogether(Trajectory left, Trajectory right) {
	  String content = "";
	    for (int i = 0; i < left.getNumSegments(); ++i) {
	      Segment leftSeg = left.getSegment(i);
	      Segment rightSeg = right.getSegment(i);
	      content += String.format(
	              "%.3f,%.3f,%.3f,%.3f,%.3f,%.3f,%.3f,%.3f,,%.3f,%.3f,%.3f,%.3f,%.3f,%.3f,%.3f,%.3f\n",
	              leftSeg.dt, leftSeg.x, leftSeg.y, 
	              leftSeg.pos, leftSeg.vel, leftSeg.acc, leftSeg.jerk,
	              leftSeg.heading, rightSeg.dt, rightSeg.x, rightSeg.y, 
	              rightSeg.pos, rightSeg.vel, rightSeg.acc, rightSeg.jerk,
	              rightSeg.heading);
	    }
	    return content;
  }
  
  private String serializeTrajectory(Trajectory trajectory) {
    String content = "";
    for (int i = 0; i < trajectory.getNumSegments(); ++i) {
      Segment segment = trajectory.getSegment(i);
      content += String.format(
              "%.3f,%.3f,%.3f,%.3f,%.3f,%.3f,%.3f,%.3f\n",
              segment.dt, segment.x, segment.y, 
              segment.pos, segment.vel, segment.acc, segment.jerk,
              segment.heading);
    }
    return content;
  }
}
