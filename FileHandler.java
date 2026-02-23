import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * FileHandler Class
 * Handles all file operations for student data persistence
 * Uses FileWriter and BufferedReader for file I/O operations
 */
public class FileHandler {
    private static final String FILE_NAME = "students.txt";

    /**
     * Reads all student records from the file
     * @return List of Student objects
     */
    public static List<Student> readAllStudents() {
        List<Student> students = new ArrayList<>();
        File file = new File(FILE_NAME);
        
        // If file doesn't exist, return empty list
        if (!file.exists()) {
            return students;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }
                
                Student student = Student.fromFileString(line);
                if (student != null) {
                    students.add(student);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        
        return students;
    }

    /**
     * Writes all student records to the file
     * @param students List of Student objects to write
     */
    public static void writeAllStudents(List<Student> students) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (Student student : students) {
                writer.write(student.toFileString() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Appends a single student record to the file
     * @param student Student object to append
     */
    public static void appendStudent(Student student) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(student.toFileString() + "\n");
        } catch (IOException e) {
            System.err.println("Error appending to file: " + e.getMessage());
        }
    }

    /**
     * Checks if the data file exists
     * @return true if file exists, false otherwise
     */
    public static boolean fileExists() {
        return new File(FILE_NAME).exists();
    }

    /**
     * Creates the data file if it doesn't exist
     */
    public static void createFileIfNotExists() {
        if (!fileExists()) {
            try {
                new File(FILE_NAME).createNewFile();
            } catch (IOException e) {
                System.err.println("Error creating file: " + e.getMessage());
            }
        }
    }

    /**
     * Clears all data from the file
     */
    public static void clearFile() {
        try (FileWriter writer = new FileWriter(FILE_NAME, false)) {
            // Writing nothing clears the file
        } catch (IOException e) {
            System.err.println("Error clearing file: " + e.getMessage());
        }
    }
}
