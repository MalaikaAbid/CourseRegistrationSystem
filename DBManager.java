package CourseRegistrationSystem;

import java.util.ArrayList;

//This class is simulating a database for our
//program
public class DBManager {
	
	ArrayList <Course> courseList;
	ArrayList <Student> studentList;

	public DBManager () {
		courseList = new ArrayList<Course>();
		studentList = new ArrayList<Student>();
	}

	public ArrayList readCoursesFromDataBase() {
		// TODO Auto-generated method stub
		courseList.add(new Course ("ENGG", 233));
		courseList.add(new Course ("ENSF", 409));
		courseList.add(new Course ("PHYS", 259));
		courseList.add(new Course ("ENGG", 201));
		courseList.add(new Course ("ENGG", 202));
		courseList.add(new Course ("MATH", 211));
		return courseList;
	}
	
	public ArrayList readStudentsFromDataBase() {

		studentList.add(new Student ("Mal", 300));
		studentList.add(new Student ("Mah", 200));
		return studentList;
	}
	

}
