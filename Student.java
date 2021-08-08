package CourseRegistrationSystem;

import java.util.ArrayList;

public class Student {
	
	private String studentName;
	private int studentId;
	//private ArrayList<CourseOffering> offeringList;
	private ArrayList<Registration> studentRegList; // should have maximum size of 6
	
	public Student (String studentName, int studentId) {
		this.setStudentName(studentName);
		this.setStudentId(studentId);
		studentRegList = new ArrayList<Registration>();
	}
	
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	public ArrayList<Registration> getStudentRegList() {
		return studentRegList;
	}
	@Override
	public String toString () {
		String st = "Student Name: " + getStudentName() +
				"Student Id: " + getStudentId();
		return st;
	}

	synchronized public void addRegistration(Registration registration) {
		if(studentRegList.size() <= 6)
		{
			studentRegList.add(registration);

		}else
		{
			System.err.println("Maximum course registration limit reached for " + studentName);
		}
	}
	

	synchronized public void removeRegistration(Course course) {
		if(course != null)
		{
			for(int i = 0; i < studentRegList.size(); i++)
			{
				if(studentRegList.get(i).getTheOffering().getTheCourse().equals(course))
				{
					studentRegList.remove(i);
					System.out.println(course.getCourseName() + " " + course.getCourseNum() + " removed from your courses");

				}
			}

		}else
		{
			System.err.println("Course not found!");
		}
		
		
	}
	
	public String viewAllCourses()
	{
		String courses = " ";
		ArrayList<Registration> registrations = getStudentRegList();
		System.out.println("registrations is" + " " + registrations.size());
		for(Registration reg: registrations)
		{
			courses += reg.toString();
		}
		
		return courses;

	}
	
	synchronized public void addCourseToStudentCourses(Course course)
	{
		if(course != null)
		{
			Registration reg = new Registration();
			int i = 0;
			int checkIfRegistered = 0;
			while(i != course.getOfferingList().size())
			{
				//case 1: it doesn't have any offering (need new offering)
				//case 2: offerings but they are not full (add to old)
				//case 3: offerings and they are full (need new offering)
				if(course.getCourseOfferingAt(i) != null &&
						course.getCourseOfferingAt(i).getStudentList().size() != 
						course.getCourseOfferingAt(i).getSecCap())
				{
					reg.completeRegistration(this, course.getCourseOfferingAt(i));
					checkIfRegistered = 1;
				}
				
				i++;
				
			}
			if(checkIfRegistered == 0)
			{
				course.addOffering(new CourseOffering(1, 100));
				reg.completeRegistration(this, course.getCourseOfferingAt(i));
			}
			
			System.out.println(course.getCourseName() + " " + course.getCourseNum() + " added to your courses");

		}
	}
}
