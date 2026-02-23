/**
 * Student Model Class
 * Represents a student entity with encapsulated fields
 * Follows OOP principles with proper getters and setters
 */
public class Student {
    // Private fields for encapsulation
    private int studentId;
    private String name;
    private int age;
    private String course;

    /**
     * Default constructor
     */
    public Student() {
    }

    /**
     * Parameterized constructor
     * @param studentId unique identifier for the student
     * @param name student's full name
     * @param age student's age
     * @param course course enrolled in
     */
    public Student(int studentId, String name, int age, String course) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    // Getter and Setter methods for encapsulation

    /**
     * Gets the student ID
     * @return student ID
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * Sets the student ID
     * @param studentId student ID to set
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    /**
     * Gets the student name
     * @return student name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the student name
     * @param name student name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the student age
     * @return student age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the student age
     * @param age student age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the student course
     * @return student course
     */
    public String getCourse() {
        return course;
    }

    /**
     * Sets the student course
     * @param course student course to set
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * Returns a string representation of the student
     * @return formatted student information
     */
    @Override
    public String toString() {
        return "Student ID: " + studentId + 
               ", Name: " + name + 
               ", Age: " + age + 
               ", Course: " + course;
    }

    /**
     * Converts student object to a string format for file storage
     * @return comma-separated student data
     */
    public String toFileString() {
        return studentId + "," + name + "," + age + "," + course;
    }

    /**
     * Creates a Student object from a file string
     * @param fileString comma-separated student data
     * @return Student object
     */
    public static Student fromFileString(String fileString) {
        String[] parts = fileString.split(",");
        try {
            int id = Integer.parseInt(parts[0].trim());
            String name = parts[1].trim();
            int age = Integer.parseInt(parts[2].trim());
            String course = parts[3].trim();
            return new Student(id, name, age, course);
        } catch (Exception e) {
            System.err.println("Error parsing student data: " + fileString);
            return null;
        }
    }
}
