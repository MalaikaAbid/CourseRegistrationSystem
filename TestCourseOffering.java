/**
 * 
 */
package CourseRegistrationSystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author malaikaabid
 *
 */
class TestCourseOffering {

	CourseOffering courseOff;
	Course c;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		courseOff = new CourseOffering(1, 100);
		courseOff.setSecCap(20);
		courseOff.setSecNum(40);
		c = new Course("TEST", 111);
		courseOff.setTheCourse(c);
	}

	@Test
	void testGetCourseOffering() {
		assertEquals(40, courseOff.getSecNum());
		assertEquals(20, courseOff.getSecCap());
		assertEquals(c, courseOff.getTheCourse());
	}
	
	@Test
	void testSetCourseOffering() {
		courseOff.setSecCap(200);
		courseOff.setSecNum(400);
		Course newCourse = new Course("TEST", 222);
		courseOff.setTheCourse(newCourse);
		assertEquals(400, courseOff.getSecNum());
		assertEquals(200, courseOff.getSecCap());
		assertEquals(true, courseOff.getTheCourse().equals(newCourse));

	}
	
	@Test
	void testToString() {
		assertEquals("~TEST 111~Section Num: 40, section cap: 20~", courseOff.toString());

	}
	
	@Test
	void testAddRegistration() {
		Registration reg = new Registration();
		Student s = new Student("Mal", 500);
		reg.setTheStudent(s);
		courseOff.addRegistration(reg);
		assertEquals(1, courseOff.getStudentList().size());
		assertEquals(1, courseOff.getOfferingRegList().size());


	}

}
