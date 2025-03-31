/**
 * The CourseDBElement class represents a course with fields such as course ID,
 * CRN, credits, room number, and instructor. This class is used to store course
 * information in the course database structure.
 * 
 * It implements Comparable to allow sorting by CRN.
 */
/*
 * @author Samuella Helha 
 */
public class CourseDBElement implements Comparable<CourseDBElement> {

    private String courseID;
    private int crn;
    private int credits;
    private String roomNumber;
    private String instructor;

    /**
     * Parameterized constructor to initialize a CourseDBElement with specific values.
     *
     * @param courseID   the course ID (e.g., "CMSC204")
     * @param crn        the course registration number
     * @param credits    the number of credits
     * @param roomNumber the room number or location
     * @param instructor the instructor's name
     */
    public CourseDBElement(String courseID, int crn, int credits, String roomNumber, String instructor) {
        this.courseID = courseID;
        this.crn = crn;
        this.credits = credits;
        this.roomNumber = roomNumber;
        this.instructor = instructor;
    }

    /**
     * Default constructor initializes fields to default values.
     */
    public CourseDBElement() {
        this.courseID = "";
        this.crn = 0;
        this.credits = 0;
        this.roomNumber = "";
        this.instructor = "";
    }

    /**
     * Gets the course ID.
     * @return the course ID
     */
    public String getID() { return courseID; }

    /**
     * Gets the course CRN.
     * @return the CRN
     */
    public int getCRN() { return crn; }

    /**
     * Gets the number of credits.
     * @return the number of credits
     */
    public int getCredits() { return credits; }

    /**
     * Gets the room number.
     * @return the room number
     */
    public String getRoomNum() { return roomNumber; }

    /**
     * Gets the instructor's name.
     * @return the instructor's name
     */
    public String getInstructor() { return instructor; }

    /**
     * Sets the course ID.
     * @param courseID the course ID to set
     */
    public void setID(String courseID) { this.courseID = courseID; }

    /**
     * Sets the CRN.
     * @param crn the CRN to set
     */
    public void setCRN(int crn) { this.crn = crn; }

    /**
     * Sets the number of credits.
     * @param credits the number of credits
     */
    public void setCredits(int credits) { this.credits = credits; }

    /**
     * Sets the room number.
     * @param roomNumber the room number to set
     */
    public void setRoomNum(String roomNumber) { this.roomNumber = roomNumber; }

    /**
     * Sets the instructor's name.
     * @param instructor the instructor's name
     */
    public void setInstructor(String instructor) { this.instructor = instructor; }

    /**
     * Generates a hash code based on the CRN.
     * @return hash code for the object
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(crn);
    }

    /**
     * Compares this CourseDBElement with another based on CRN.
     * @param other the other CourseDBElement
     * @return comparison result: negative if less, 0 if equal, positive if greater
     */
    @Override
    public int compareTo(CourseDBElement other) {
        return Integer.compare(this.crn, other.crn);
    }

    /**
     * Returns a string representation of the course element.
     * @return formatted string
     */
    @Override
    public String toString() {
        return "Course:" + courseID +
               " CRN:" + crn +
               " Credits:" + credits +
               " Instructor:" + instructor +
               " Room:" + roomNumber;
    }
}
