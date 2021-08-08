package CourseRegistrationSystem;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CourseCatalogue{
	
	private ArrayList <Course> courseList;

	public CourseCatalogue() {
		loadFromDataBase ();
	}
	
	private void loadFromDataBase() {
		// TODO Auto-generated method stub
		DBManager db = new DBManager();
		setCourseList(db.readCoursesFromDataBase());
		
	}
	
	synchronized public void createCourseOffering (Course c, int secNum, int secCap) {
		if (c!= null) {
			CourseOffering theOffering = new CourseOffering (secNum, secCap);
			c.addOffering(theOffering);
			courseList.add(c);
		}
	}
	public Course searchCat (String courseName, int courseNum) {
		for (Course c : courseList) {
			if (courseName.equals(c.getCourseName()) &&
					courseNum == c.getCourseNum()) {
				return c;
			}	
		}
		displayCourseNotFoundError();
		return null;
	}
	
	private void displayCourseNotFoundError() {
		// TODO Auto-generated method stub
		System.err.println("Course was not found!");
		
	}
	public ArrayList <Course> getCourseList() {
		return courseList;
	}


	public void setCourseList(ArrayList <Course> courseList) {
		this.courseList = courseList;
	}
	
	@Override
	public String toString () {
		String st = "All courses in the catalogue:";
		for (Course c : courseList) {
			st += c;  //This line invokes the toString() method of Course
		}
		return st;
	}

}
