package CourseRegistrationSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

//This class is simulating a database for our
//program
public class DBManager {
	
	static ArrayList <Course> courseList;
	static ArrayList <Student> studentList;

	public DBManager () {
		courseList = new ArrayList<Course>();
		studentList = new ArrayList<Student>();
	}

	@SuppressWarnings("resource")
	public static ArrayList<Course> readCoursesFromDataBase() {
		// TODO Auto-generated method stub
        File courseInputFile = new File("courseInput.txt");
        Scanner fileRead;
        try {
                fileRead = new Scanner(courseInputFile); 
            while(fileRead.hasNextLine()){
                String data = fileRead.nextLine();
                StringTokenizer tokenizer = new StringTokenizer(data);
                Course temp = new Course(tokenizer.nextToken(),Integer.parseInt(tokenizer.nextToken()));
                courseList.add(temp);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred while importing course information.");
            //return courseList;
           // e.printStackTrace();
        }
		return courseList;
	}
	
	public static ArrayList<Student> readStudentsFromDataBase() {

		File studentInputFile = new File("studentInput.txt");
        Scanner fileRead2;
        try {
                fileRead2 = new Scanner(studentInputFile); 
            while(fileRead2.hasNextLine()){
                String data = fileRead2.nextLine();
                StringTokenizer tokenizer = new StringTokenizer(data);
                Student temp = new Student(tokenizer.nextToken(),Integer.parseInt(tokenizer.nextToken()));
                studentList.add(temp);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred while importing student information.");
         
           // e.printStackTrace();
        }
		return studentList;
	}

}
