package CourseRegistrationSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.util.Scanner;

public class FrontEnd implements Runnable{
	
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	private BufferedReader stdIn;
	
	public FrontEnd (Client client) {
		socketIn = client.getSocketIn();
		socketOut = client.getSocketOut();
		stdIn = new BufferedReader (new InputStreamReader (System.in));
	}

	@Override
	public void run() {
		frontEnd();
	}
	
	public void frontEnd () 
	{
		String option = "";	
		String response = "";	
		
		//The problem arises when you enter a student
		//that does not exist, that is because the
		//backend at that point only reads the socket
		//once for student name and id and advances to end
		//of loop while the front end next stream to the
		//socket is course information and not a menu option
		
		while(!option.equals("6"))
		{
			try
			{
				option = menu();
				if(option.equals("1"))
				{
					socketOut.println("1");
					enterCourse();
					response = socketIn.readLine();
				}else if(option.equals("2"))
				{
					socketOut.println("2");
					findStudent();
					enterCourse();
					response = socketIn.readLine(); 
				}else if(option.equals("3"))
				{
					socketOut.println("3");
					findStudent();
					Thread.sleep(5);
					enterCourse();
					response = socketIn.readLine();
					
				}else if(option.equals("4"))
				{
					socketOut.println("4");
					response = socketIn.readLine();
					
				}else if(option.equals("5"))
				{
					socketOut.println("5");
					findStudent();
					response = socketIn.readLine();
					
				}else
				{
					socketOut.println("Invalid option.");
				}
				
				System.out.println(response);

			}
			catch(IOException | InterruptedException e)
			{
				e.getStackTrace();
			}
				
		}
		
		
		System.out.println("Program terminated.");

		try
		{
			stdIn.close();
			socketIn.close();
			socketOut.close();
		}catch(IOException e)
		{
			e.getStackTrace();
		}
	}
		
	
	private String menu() throws IOException
	{
		System.out.println("Select an option from the menu below (enter the number of the menu option):");
		System.out.println("1. Search catalogue courses");
		System.out.println("2. Add course to student courses");
		System.out.println("3. Remove course from student courses");
		System.out.println("4. View all courses in catalogue");
		System.out.println("5. View all courses taken by student");
		System.out.println("6. Quit");
		
		return stdIn.readLine();

	}
	
	private void enterCourse() throws IOException
	{
		System.out.println("Enter course name:");
		String name = stdIn.readLine();
		int number;
		if(name != null)
		{
			System.out.println("Enter course number:");
			number = Integer.parseInt(stdIn.readLine());
			if(number != -1)
			{
				socketOut.println(name);
				socketOut.println(number);
				return;

			}
		}
		
		System.out.println("Course not found!");
		return;
	}
	
	private void findStudent() throws IOException
	{
		System.out.println("Enter student name:");
		String name = stdIn.readLine();
		int id;
		if(name != null)
		{
			System.out.println("Enter student id:");
			id = Integer.parseInt(stdIn.readLine());
			if(id != -1)
			{
				socketOut.println(name);
				socketOut.println(id);
				return;
				
			}
		}
		System.err.println("Name or ID is invalid");
		return;
	}
	
	



}
