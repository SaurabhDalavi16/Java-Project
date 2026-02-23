import java.util.ArrayList;
import java.util.List;

/**
 * StudentService Class
 * Contains business logic for CRUD operations on Student entities
 * Uses ArrayList for in-memory operations and FileHandler for persistence
 */
public class StudentService {
    private List<Student> students;
    private int nextStudentId;

    /**
     * Constructor initializes the service and loads existing data
     */
    public StudentService() {
        students = new ArrayList<>();
        loadStudents();
        nextStudentId = generateNextId();
    }

    /**
     * Loads all students from file into memory
     */
    private void loadStudents() {
        students = FileHandler.readAllStudents();
    }

    /**
     * Saves all students from memory to file
     */
    private void saveStudents() {
        FileHandler.writeAllStudents(students);
    }

    /**
     * Generates the next available student ID
     * @return next available ID
     */
    private int generateNextId() {
        int maxId = 0;
        for (Student student : students) {
            if (student.getStudentId() > maxId) {
                maxId = student.getStudentId();
            }
        }
        return maxId + 1;
    }

    /**
     * Adds a new student to the system
     * @param name student name
     * @param age student age
     * @param course student course
     * @return true if added successfully, false otherwise
     */
    public boolean addStudent(String name, int age, String course) {
        try {
            // Validate input
            if (name == null || name.trim().isEmpty()) {
                System.out.println("Error: Name cannot be empty.");
                return false;
            }
            if (age <= 0 || age > 120) {
                System.out.println("Error: Age must be between 1 and 120.");
                return false;
            }
            if (course == null || course.trim().isEmpty()) {
                System.out.println("Error: Course cannot be empty.");
                return false;
            }

            Student student = new Student(nextStudentId, name.trim(), age, course.trim());
            students.add(student);
            nextStudentId++;
            saveStudents();
            return true;
        } catch (Exception e) {
            System.err.println("Error adding student: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves all students from the system
     * @return List of all students
     */
    public List<Student> getAllStudents() {
        return new ArrayList<>(students); // Return a copy to maintain encapsulation
    }

    /**
     * Searches for a student by ID
     * @param studentId ID to search for
     * @return Student object if found, null otherwise
     */
    public Student getStudentById(int studentId) {
        for (Student student : students) {
            if (student.getStudentId() == studentId) {
                return student;
            }
        }
        return null;
    }

    /**
     * Updates an existing student's information
     * @param studentId ID of student to update
     * @param name new name (null to keep existing)
     * @param age new age (0 to keep existing)
     * @param course new course (null to keep existing)
     * @return true if updated successfully, false otherwise
     */
    public boolean updateStudent(int studentId, String name, int age, String course) {
        try {
            Student student = getStudentById(studentId);
            if (student == null) {
                System.out.println("Error: Student with ID " + studentId + " not found.");
                return false;
            }

            // Update only non-null and valid values
            if (name != null && !name.trim().isEmpty()) {
                student.setName(name.trim());
            }
            if (age > 0 && age <= 120) {
                student.setAge(age);
            }
            if (course != null && !course.trim().isEmpty()) {
                student.setCourse(course.trim());
            }

            saveStudents();
            return true;
        } catch (Exception e) {
            System.err.println("Error updating student: " + e.getMessage());
            return false;
        }
    }

    /**
     * Deletes a student from the system
     * @param studentId ID of student to delete
     * @return true if deleted successfully, false otherwise
     */
    public boolean deleteStudent(int studentId) {
        try {
            Student student = getStudentById(studentId);
            if (student == null) {
                System.out.println("Error: Student with ID " + studentId + " not found.");
                return false;
            }

            boolean removed = students.remove(student);
            if (removed) {
                saveStudents();
                return true;
            }
            return false;
        } catch (Exception e) {
            System.err.println("Error deleting student: " + e.getMessage());
            return false;
        }
    }

    /**
     * Gets the total count of students
     * @return number of students
     */
    public int getStudentCount() {
        return students.size();
    }

    /**
     * Checks if a student ID exists
     * @param studentId ID to check
     * @return true if ID exists, false otherwise
     */
    public boolean studentExists(int studentId) {
        return getStudentById(studentId) != null;
    }
}
