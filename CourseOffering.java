package CourseRegistrationSystem;

import java.util.ArrayList;

public class CourseOffering {
	
	private int secNum;
	private int secCap;
	private Course theCourse;
	private ArrayList<Student> studentList;
	private ArrayList<Registration> offeringRegList;
	
	public CourseOffering (int secNum, int secCap) {
		this.setSecNum(secNum);
		this.setSecCap(secCap);
		offeringRegList = new ArrayList <Registration>();
		studentList = new ArrayList<Student>();
	}
	
	public int getSecNum() {
		return secNum;
	}
	public void setSecNum(int secNum) {
		this.secNum = secNum;
	}
	public int getSecCap() {
		return secCap;
	}
	public void setSecCap(int secCap) {
		this.secCap = secCap;
	}
	public Course getTheCourse() {
		return theCourse;
	}
	public void setTheCourse(Course theCourse) {
		this.theCourse = theCourse;
	}
	public ArrayList<Student> getStudentList()
	{
		return studentList;
	}
	public ArrayList<Registration> getOfferingRegList()
	{
		return offeringRegList;
	}
	@Override
	public String toString () {
		String st = "~";
		st += getTheCourse().getCourseName() + " " + getTheCourse().getCourseNum() + "~";
		st += "Section Num: " + getSecNum() + ", section cap: "+ getSecCap() +"~";
		//We also want to print the names of all students in the section
		return st;
	}
	public void addRegistration(Registration registration) {

		studentList.add(registration.getTheStudent());
		offeringRegList.add(registration);

		if((studentList.size()) < 8)
		{
			System.err.println("This course offering does not have enough student registrations. You have been registered, "
					+ "but must wait for " + (8 - studentList.size()) +  " more students to join for the class to run.");
		}else
		{
			System.out.println("Registration succesful");
		}

		
		
	}
	
	

}
