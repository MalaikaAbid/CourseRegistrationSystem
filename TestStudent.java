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
class TestStudent {

	Student s;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		s = new Student("Mal", 500);
	}

	@Test
	void testNewStudent() {
		assertEquals(s.getStudentName(), "Mal");
		assertEquals(s.getStudentId(), 500);
	
	}
	
	@Test
	void testGetters() {
		assertEquals("Mal", s.getStudentName());
		assertEquals(500, s.getStudentId());	
	}
	
	@Test
	void testSetters() {
		s.setStudentName("Sam");
		s.setStudentId(400);
		assertEquals("Sam", s.getStudentName());
		assertEquals(400, s.getStudentId());
	
	}
	
	@Test
	void testAddRegistration() {
		Registration reg = new Registration();
		reg.setTheStudent(s);
		reg.setTheOffering(new CourseOffering(1, 100));
		s.addRegistration(reg);
		assertEquals(1, s.getStudentRegList().size());
		assertEquals("Mal", s.getStudentRegList().get(0).getTheStudent().getStudentName());
		assertEquals(500, s.getStudentRegList().get(0).getTheStudent().getStudentId());
		assertEquals(1, s.getStudentRegList().get(0).getTheOffering().getSecNum());
		assertEquals(100, s.getStudentRegList().get(0).getTheOffering().getSecCap());
	}
	
	@Test
	void testRemoveRegistration() {
		Registration reg = new Registration();
		reg.setTheStudent(s);
		Course c = new Course("TEST", 111);
		CourseOffering courseOff = new CourseOffering(1, 100);
		courseOff.setTheCourse(c);
		reg.setTheOffering(courseOff);
		s.addRegistration(reg);
		s.removeRegistration(c);
		assertEquals(0, s.getStudentRegList().size());
	}
	
	@Test
	void testViewAllCourses() {
		Registration reg = new Registration();
		reg.setTheStudent(s);
		Course c = new Course("TEST", 111);
		CourseOffering courseOff = new CourseOffering(1, 100);
		courseOff.setTheCourse(c);
		reg.setTheOffering(courseOff);
		s.addRegistration(reg);
		assertEquals("~Student Name: Mal~Student Id: 500~~~The Offering: ~TEST 111~Section Num: 1, section cap: 100~~Grade:  ~-----------~",
				s.viewAllCourses());
	}

	@Test
	void testAddCourseToStudentCourses() {
		Course c = new Course("TEST", 111);
		CourseOffering courseOff = new CourseOffering(1, 100);
		courseOff.setTheCourse(c);
		s.addCourseToStudentCourses(c);
		assertEquals(1, s.getStudentRegList().size());
		assertEquals("Mal", s.getStudentRegList().get(0).getTheStudent().getStudentName());
		assertEquals(500, s.getStudentRegList().get(0).getTheStudent().getStudentId());
		assertEquals(1, s.getStudentRegList().get(0).getTheOffering().getSecNum());
		assertEquals(100, s.getStudentRegList().get(0).getTheOffering().getSecCap());
	}

}
