import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Samuella Helha
 */
/**
 * Student JUnit test class for testing the CourseDBStructure class.
 * It verifies correct behavior of add, get, and showAll methods.
 */
public class CourseDBStructureTest_STUDENT_Test {

    private CourseDBStructure structure;

    /**
     * Sets up a fresh instance of CourseDBStructure before each test.
     */
    @Before
    public void setUp() {
        structure = new CourseDBStructure("StudentTest", 10); // Fixed size for controlled testing
    }

    /**
     * Tests that a CourseDBElement can be successfully added and retrieved by CRN.
     *
     * @throws IOException if the course is not found (should not happen here)
     */
    @Test
    public void testAddAndGet() throws IOException {
        CourseDBElement course = new CourseDBElement("CMSC110", 22222, 3, "MT100", "S. Lee");
        structure.add(course);

        CourseDBElement found = structure.get(22222);
        assertNotNull("Course should be found after adding", found);
        assertEquals("CMSC110", found.getID());
        assertEquals("MT100", found.getRoomNum());
    }

    /**
     * Tests that getting a CRN that doesn't exist throws an IOException.
     *
     * @throws IOException expected exception for non-existent CRN
     */
    @Test(expected = IOException.class)
    public void testGetNonExistentThrows() throws IOException {
        structure.get(99999); // CRN does not exist in structure
    }

    /**
     * Tests that showAll() returns a properly formatted string list with course details.
     */
    @Test
    public void testShowAllFormat() {
        structure.add(new CourseDBElement("CMSC101", 12345, 3, "VH100", "Alex"));
        ArrayList<String> output = structure.showAll();

        assertEquals(1, output.size());
        assertTrue("Output should contain Course ID", output.get(0).contains("Course:CMSC101"));
        assertTrue("Output should contain CRN", output.get(0).contains("CRN:12345"));
        assertTrue("Output should contain Instructor", output.get(0).contains("Instructor:Alex"));
    }
}
