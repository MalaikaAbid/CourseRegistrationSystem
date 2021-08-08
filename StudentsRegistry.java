package CourseRegistrationSystem;

import java.util.ArrayList;

public class StudentsRegistry {
	
	private ArrayList<Student> studentList;
	
	private void loadFromDataBase() {

		DBManager db = new DBManager();
		setStudentsRegistry(db.readStudentsFromDataBase());
	}
		
	public StudentsRegistry()
	{
		loadFromDataBase();
	}
	
	public ArrayList<Student> getStudentsRegistry()
	{
		return studentList;
	}
	
	public void setStudentsRegistry(ArrayList<Student> s)
	{
		studentList = s;
	}
	
	public Student searchRegistry (String name, int id) {
		for (Student s : studentList) {
			if (name.equals(s.getStudentName()) &&
					id == s.getStudentId()) {
				return s;
			}	
		}
		return null;
	}
	
	public void addToStudentRegistry (Student s) {
		if (s!= null) {
			studentList.add(s);
		}
	}
	
	@Override
	public String toString () {
		String st = "All students in the registry:";
		for (Student s : studentList) {
			st += s; 
		}
		
		return st;
		
	}

}
