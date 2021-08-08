package CourseRegistrationSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class BackEnd implements Runnable
{

	private PrintWriter socketOut;
	private BufferedReader socketIn;
	private BufferedReader stdIn;
	
	public BackEnd(BufferedReader in, PrintWriter out)
	{
		socketIn = in;
		socketOut = out;
		stdIn = new BufferedReader (new InputStreamReader (System.in));
	}

	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		try {
			backEnd();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// The logic of the application:
	private void backEnd() throws IOException
	{
		
		String option = null; 

		while(true)
		{
			try
			{	
				option = socketIn.readLine();
				CourseCatalogue cat = new CourseCatalogue();
				StudentsRegistry studentRegistry = new StudentsRegistry();
				if(option.equals("1"))
				{
					String name = socketIn.readLine();
					int number = Integer.parseInt(socketIn.readLine());
					Course course = cat.searchCat(name, number);
					if(course != null)
					{
						socketOut.println(course.getCourseName() + " " + course.getCourseNum());

					}else
					{
						socketOut.println("Course not found!");

					}

				}else if(option.equals("2"))
				{	
					String name = socketIn.readLine();
					int id = Integer.parseInt(socketIn.readLine());
					String courseName = socketIn.readLine();
					int courseNumber = Integer.parseInt(socketIn.readLine());
					Student s = studentRegistry.searchRegistry(name, id);
					if(s == null)
					{
						socketOut.println("Student not registered!");
					}else
					{
						Course course = cat.searchCat(courseName, courseNumber);
						if(course != null)
						{
							s.addCourseToStudentCourses(course);
							socketOut.println("Student registered in course");
						}else
						{
							socketOut.println("Course not found!");
						}
						
					}

				}else if(option.equals("3"))
				{
					String name = socketIn.readLine();
					int id = Integer.parseInt(socketIn.readLine());
					Student s = studentRegistry.searchRegistry(name, id);
					if(s == null)
					{
						socketOut.println("Student not registered!");
					}else
					{
						String courseName = socketIn.readLine();
						int courseNumber = Integer.parseInt(socketIn.readLine());
						Course course = cat.searchCat(courseName, courseNumber);
						if(course != null)
						{
							s.removeRegistration(course);
							socketOut.println("Course removed from student courses");
						}else
						{
							socketOut.println("Course not found!");

						}
					}
				}else if(option.equals("4"))
				{
					socketOut.println(cat);
				}else if(option.equals("5"))
				{
					String name = socketIn.readLine();
					int id = Integer.parseInt(socketIn.readLine());
					Student s = studentRegistry.searchRegistry(name, id);
					if(s == null)
					{
						socketOut.println("Student not registered!");
					}else
					{
					    socketOut.println(s.viewAllCourses());
					}
				}else
				{
					break;
				}
				
				
			}catch (IOException e)
			{
				e.getStackTrace();
			}
			
		}
		
	}
	
	
	
}
