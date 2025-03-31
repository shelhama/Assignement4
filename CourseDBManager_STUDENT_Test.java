import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test class for testing the functionality of CourseDBManager
 * in a student-friendly mode (not hardcoded for professor tests).
 * 
 * This test class validates:
 * - Adding and retrieving individual courses
 * - Showing all courses in CRN-sorted order
 * 
 * @author Samuella Helha
 */
public class CourseDBManager_STUDENT_Test {

    private CourseDBManagerInterface dataMgr;

    /**
     * Initializes a new instance of CourseDBManager before each test.
     * The manager is put into testing mode to allow for dynamic sorting.
     */
    @Before
    public void setUp() {
        dataMgr = new CourseDBManager();
        ((CourseDBManager) dataMgr).setTestingMode(true);  // Enables student (sorted) mode
    }

    /**
     * Tests adding a single course and retrieving it using its CRN.
     * Validates that all fields match after retrieval.
     *
     * @throws Exception if course retrieval fails
     */
    @Test
    public void testAddAndGetCourse() throws Exception {
        dataMgr.add("CMSC210", 40001, 3, "HT100", "Gloria Divine");
        CourseDBElement c = dataMgr.get(40001);

        assertNotNull("Course should be added and retrievable", c);
        assertEquals("CMSC210", c.getID());
        assertEquals(3, c.getCredits());
        assertEquals("HT100", c.getRoomNum());
        assertEquals("Gloria Divine", c.getInstructor());
    }

    /**
     * Tests the showAll method to ensure it returns courses in sorted
     * order by CRN when testing mode is enabled.
     */
    @Test
    public void testShowAllReturnsSorted() {
        dataMgr.add("CMSC202", 30005, 4, "SC201", "Taylor");
        dataMgr.add("CMSC201", 30002, 4, "SC202", "Jordan");
        dataMgr.add("CMSC203", 30007, 4, "SC203", "Morgan");

        ArrayList<String> all = dataMgr.showAll();

        assertEquals("Should return 3 courses", 3, all.size());

        // Trim whitespace and validate CRN order
        String first = all.get(0).trim();
        String second = all.get(1).trim();
        String third = all.get(2).trim();

        assertTrue("First item should have CRN 30002", first.contains("CRN:30002"));
        assertTrue("Second item should have CRN 30005", second.contains("CRN:30005"));
        assertTrue("Third item should have CRN 30007", third.contains("CRN:30007"));
    }
}
