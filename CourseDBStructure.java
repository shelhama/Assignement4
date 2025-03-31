import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
/**
 * * @author Samuella Helha
 */
 

/**
 * CourseDBStructure provides a hash table implementation for storing and retrieving
 * course database elements using chaining for collision handling.
 * Implements the CourseDBStructureInterface.
 */
public class CourseDBStructure implements CourseDBStructureInterface {

    private LinkedList<CourseDBElement>[] hashTable;
    private int size;

    /**
     * Constructor to create the hash table based on estimated number of elements.
     * Determines a prime number of the form 4k+3 greater than n / 1.5 for the table size.
     *
     * @param n Estimated number of courses.
     */
    @SuppressWarnings("unchecked")
    public CourseDBStructure(int n) {
        int target = (int) Math.ceil(n / 1.5);

        // Find the next prime of the form 4k + 3
        int prime = target;
        while (!(isPrime(prime) && prime % 4 == 3)) {
            prime++;
        }

        size = prime;
        hashTable = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            hashTable[i] = new LinkedList<>();
        }
    }

    /**
     * Constructor used for testing. Uses the specified size directly.
     *
     * @param testing A dummy parameter to differentiate this constructor.
     * @param n       Size of the hash table to use.
     */
    @SuppressWarnings("unchecked")
    public CourseDBStructure(String testing, int n) {
        this.size = n;
        hashTable = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            hashTable[i] = new LinkedList<>();
        }
    }

    /**
     * Adds a CourseDBElement to the structure. If an element with the same CRN exists,
     * it is replaced.
     *
     * @param element The CourseDBElement to add.
     */
    @Override
    public void add(CourseDBElement element) {
        int index = Math.abs(element.hashCode()) % size;
        LinkedList<CourseDBElement> bucket = hashTable[index];

        for (CourseDBElement e : bucket) {
            if (e.getCRN() == element.getCRN()) {
                bucket.remove(e);
                break;
            }
        }
        bucket.add(element);
    }

    /**
     * Retrieves a CourseDBElement by its CRN.
     *
     * @param crn The CRN to search for.
     * @return The matching CourseDBElement.
     * @throws IOException If the CRN is not found.
     */
    @Override
    public CourseDBElement get(int crn) throws IOException {
        int index = Math.abs(Integer.hashCode(crn)) % size;
        LinkedList<CourseDBElement> bucket = hashTable[index];

        for (CourseDBElement e : bucket) {
            if (e.getCRN() == crn) {
                return e;
            }
        }
        throw new IOException("Course not found.");
    }

    /**
     * Returns all elements in the structure, formatted as strings and sorted by CRN.
     *
     * @return A sorted list of formatted course strings.
     */
    @Override
    public ArrayList<String> showAll() {
        ArrayList<CourseDBElement> all = getAllElements();
        Collections.sort(all);

        ArrayList<String> output = new ArrayList<>();
        for (CourseDBElement e : all) {
            output.add("Course:" + e.getID() + " CRN:" + e.getCRN() + " Credits:" +
                       e.getCredits() + " Instructor:" + e.getInstructor() + " Room:" + e.getRoomNum());
        }

        return output;
    }

    /**
     * Returns all CourseDBElement objects from the hash table.
     *
     * @return A list of all CourseDBElements in the structure.
     */
    public ArrayList<CourseDBElement> getAllElements() {
        ArrayList<CourseDBElement> all = new ArrayList<>();
        for (LinkedList<CourseDBElement> bucket : hashTable) {
            if (bucket != null) {
                all.addAll(bucket);
            }
        }
        return all;
    }

    /**
     * Returns the size of the hash table (number of buckets).
     *
     * @return The hash table size.
     */
    @Override
    public int getTableSize() {
        return size;
    }

    /**
     * Helper method to determine if a number is prime.
     *
     * @param num The number to test.
     * @return True if the number is prime, false otherwise.
     */
    private boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
