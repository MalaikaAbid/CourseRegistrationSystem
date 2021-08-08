package CourseRegistrationSystem;


public class Registration {
	private Student theStudent;
	private CourseOffering theOffering;
	private char grade;
	
	void completeRegistration (Student st, CourseOffering of) {
		theStudent = st;
		theOffering = of;
		addRegistration();
	}
	
	private void addRegistration () {
		theStudent.addRegistration(this);
		theOffering.addRegistration(this);
	}
	
	
	public Student getTheStudent() {
		return theStudent;
	}
	public void setTheStudent(Student theStudent) {
		this.theStudent = theStudent;
	}
	public CourseOffering getTheOffering() {
		return theOffering;
	}
	public void setTheOffering(CourseOffering theOffering) {
		this.theOffering = theOffering;
	}
	public char getGrade() {
		return grade;
	}
	public void setGrade(char grade) {
		this.grade = grade;
	}
	
	@Override
	public String toString () {
		String st = " ";
		st += "Student Name: " + getTheStudent();
		st += "The Offering: " + getTheOffering ();
		st += "Grade: " + getGrade();
		return st;
		
	}
	

}
