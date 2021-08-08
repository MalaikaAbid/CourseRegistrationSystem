package CourseRegistrationSystem;


import javax.swing.*;
import java.awt.*;
import java.io.IOException; 
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.util.Scanner;

public class FrontEnd extends JFrame {
	
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	private BufferedReader stdIn;
    JFrame f;
    JPanel buttonPanel;
    JPanel textFieldPanel;
    JPanel textAreaPanel;
    JPanel studentPanel;
    JPanel coursePanel;
    JTextField  studentName = new JTextField(5);
    JTextField studentID= new JTextField(5);
    JTextField courseName = new JTextField(5);
    JTextField courseID = new JTextField(5);
    String response = "";
    Object[] studentMessage = {"Name: ", studentName, "ID: ", studentID}; 
    Object[] courseMessage = { "Name: ", courseName,"ID: ", courseID};

	public FrontEnd (Client client) {
		socketIn = client.getSocketIn();
		socketOut = client.getSocketOut();
		stdIn = new BufferedReader (new InputStreamReader (System.in));
		 f = new JFrame("Frame1");
		 
	        buttonPanel = new JPanel();
	        textFieldPanel = new JPanel();
	        textAreaPanel = new JPanel();
	        studentPanel = new JPanel();
	        coursePanel = new JPanel();
	        
	          
	         
	         

	        
	        JButton b1 = new JButton("Search Catalogue Courses");
	        JButton b2 = new JButton("Add Course To Student Courses");
	        JButton b3 = new JButton("Remove Course From Student Catalogue");
	        JButton b4 = new JButton("View all courses in catalogue");
	        JButton b5 = new JButton("View all courses taken by student");

	        studentPanel.add(new JLabel("Student Name:"));
	        studentPanel.add(new JLabel("Student ID:"));
	        coursePanel.add(new JLabel("Course Name:"));
	        coursePanel.add(new JLabel("Course ID:"));
	        studentPanel.add(studentName);
	        studentPanel.add(studentID);
	        coursePanel.add(courseName);
	        coursePanel.add(courseID);
	        JTextField windowTitle = new JTextField();
	        windowTitle.setText("Course Registration System");
	        windowTitle.setEditable(false);
	        textFieldPanel.add(windowTitle);
	        JTextArea textArea = new JTextArea();
	        textArea.setEditable(false);
	        JScrollPane scroll = new JScrollPane(textArea);
	        textAreaPanel.add(scroll,BorderLayout.EAST);
	        
	        
	        
	        
	        b5.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e){
	            	socketOut.println("5");
	            try {
					findStudent();
				} catch (IOException e1) {
					System.out.println("Operation Cancelled!");
				}
				try {
					String unformatted = socketIn.readLine();
					String formatted = unformatted.replace("~","\n");
					textArea.setText(formatted);
				} catch (IOException e1) {
					System.out.println("Operation Cancelled!");
				}
	            textAreaPanel.repaint();
	            }
	        });
	        
	        
	        b4.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e){
	            	socketOut.println("4");
					try {
						String unformatted = socketIn.readLine();
						
						String formatted = unformatted.replace("~","\n");
						textArea.setText(formatted);
					} catch (IOException e1) {
						System.out.println("Operation Cancelled!");
					}
	            }
	        });


	        b3.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e){
	            	socketOut.println("3");
	            	try {
	            	findStudent();
					try {
						Thread.sleep(5);
					} catch (InterruptedException e1) {
						System.out.println("Operation Cancelled!");
					}
					enterCourse();
	            	}
	            	catch(IOException e1){
	            		System.out.println("Operation Cancelled!");
	            	}
					try {
						String unformatted = socketIn.readLine();	
						String formatted = unformatted.replace("~","\n");
						textArea.setText(formatted);
					} catch (IOException e1) {
						System.out.println("Operation Cancelled!");
					}
	            }
	        });


	        b2.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e){
	            	socketOut.println("2");
	            
					try {
						findStudent();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						//e2.printStackTrace();
					}
	           
	        		   
				 
				try {
					enterCourse();
				} catch (IOException e1) {
					System.out.println("Operation Cancelled!");
				}
				try {
					String unformatted = socketIn.readLine();
					
					String formatted = unformatted.replace("~","\n");
					textArea.setText(formatted);
				} catch (IOException e1) {
					System.out.println("Operation Cancelled!");
				}
	            textAreaPanel.repaint();
	            }
	        });



	        b1.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e){
	            	socketOut.println("1");
					try {
						enterCourse();
					} catch (IOException e1) {
						System.out.println("Operation Cancelled!");
					}
					try {
						String unformatted = socketIn.readLine();
						
						String formatted = unformatted.replace("~","\n");
						textArea.setText(formatted);
					} catch (IOException e1) {
						System.out.println("Operation Cancelled!");
					}
	            }
	        });

	        f.setSize(1500,400);
	        f.setTitle("Main Window");
	        f.add(buttonPanel,BorderLayout.SOUTH);
	        f.add(textAreaPanel,BorderLayout.CENTER);
	        f.add(scroll,BorderLayout.CENTER);
	        f.add(textFieldPanel,BorderLayout.NORTH);
	        buttonPanel.add(b1);
	        buttonPanel.add(b2);
	        buttonPanel.add(b3);
	        buttonPanel.add(b4);
	        buttonPanel.add(b5);
	        f.setVisible(true);
	        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	
	
	private void enterCourse() throws IOException
	{
		
		
		courseName.setText("");
		courseID.setText("");
		int option = JOptionPane.showConfirmDialog(null, courseMessage,"Input", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			if(courseName.getText() != null && courseID.getText() != null) {
		    socketOut.println(courseName.getText());
		    socketOut.println(courseID.getText());
		    return;
			}
			else {
				JOptionPane.showMessageDialog(null, "Please fill out all fields", "Error", JOptionPane.ERROR_MESSAGE);
				socketOut.println("soup");
			    socketOut.println("2");
			    return;
				//enterCourse();
			}
		}
		else {
			System.err.println("Input canceled");
		    socketOut.println("soup");
		    socketOut.println("2");
			return;
		
		}
	}
		/*
		String name = JOptionPane.showInputDialog("Please Enter Course Name:");
		int number;
		if(name != null)
		{
			number = Integer.parseInt(JOptionPane.showInputDialog("Please Enter Course Number:"));
			if(number != -1)
			{
				socketOut.println(name);
				socketOut.println(number);
				return;
			}
		}
		System.out.println("Course not found!");
		*/
	
	
	
	/**
	 * Prompts user for student name and ID then attempts to find
	 * @throws IOException
	 */
	private void findStudent() throws IOException
	{
		// = JOptionPane.showInputDialog("Please Enter Student Name:");
		studentName.setText("");
		studentID.setText("");
		int option = JOptionPane.showConfirmDialog(null, studentMessage,"Input", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			if(studentName.getText().isEmpty() || studentID.getText().isEmpty() ) {
				JOptionPane.showMessageDialog(null, "Please fill out all fields", "Error", JOptionPane.ERROR_MESSAGE);
				socketOut.println("soup1");
			    socketOut.println("1");
			    return;
		    
		   
			}
			else {
				socketOut.println(studentName.getText());
				socketOut.println(studentID.getText());
			    return;
				//findStudent();
			}
		}
		else {
			System.err.println("Input canceled");
			socketOut.println("soup4");
		    socketOut.println("4");
		    return;
		}
		    
		//if(name != null)
		//{
			//id = Integer.parseInt(JOptionPane.showInputDialog("Please Enter Student ID :"));
			//if(id != -1)
		//	{
			//	
			//	
			
		//	}
	//	}
		//System.err.println("Name or ID is invalid");
	}
	
}




