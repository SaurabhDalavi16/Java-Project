import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Main Class
 * Entry point for the Student Management System
 * Provides menu-driven console interface for user interaction
 */
public class Main {
    private static StudentService studentService;
    private static Scanner scanner;

    /**
     * Main method - entry point of the application
     * @param args command line arguments
     */
    public static void main(String[] args) {
        studentService = new StudentService();
        scanner = new Scanner(System.in);
        
        // Ensure data file exists
        FileHandler.createFileIfNotExists();
        
        System.out.println("=================================");
        System.out.println("  Student Management System");
        System.out.println("=================================");
        
        runMainMenu();
        
        scanner.close();
    }

    /**
     * Runs the main menu loop
     */
    private static void runMainMenu() {
        while (true) {
            displayMainMenu();
            int choice = getMenuChoice(1, 6);
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    searchStudentById();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    System.out.println("Thank you for using Student Management System!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            
            System.out.println(); // Add spacing for readability
        }
    }

    /**
     * Displays the main menu options
     */
    private static void displayMainMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Exit");
        System.out.print("Enter your choice (1-6): ");
    }

    /**
     * Gets and validates menu choice from user
     * @param min minimum valid choice
     * @param max maximum valid choice
     * @return validated user choice
     */
    private static int getMenuChoice(int min, int max) {
        while (true) {
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.print("Invalid choice. Please enter a number between " + min + " and " + max + ": ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    /**
     * Handles adding a new student
     */
    private static void addStudent() {
        System.out.println("\n--- Add New Student ---");
        
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();
        
        int age = getValidAge();
        
        System.out.print("Enter course: ");
        String course = scanner.nextLine().trim();
        
        if (studentService.addStudent(name, age, course)) {
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Failed to add student.");
        }
    }

    /**
     * Gets and validates age input
     * @return valid age
     */
    private static int getValidAge() {
        while (true) {
            System.out.print("Enter age: ");
            try {
                int age = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                if (age > 0 && age <= 120) {
                    return age;
                } else {
                    System.out.println("Age must be between 1 and 120.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    /**
     * Displays all students
     */
    private static void viewAllStudents() {
        System.out.println("\n--- All Students ---");
        List<Student> students = studentService.getAllStudents();
        
        if (students.isEmpty()) {
            System.out.println("No students found in the system.");
        } else {
            System.out.println("Total Students: " + students.size());
            System.out.println("----------------------------------------");
            for (Student student : students) {
                System.out.println(student);
            }
            System.out.println("----------------------------------------");
        }
    }

    /**
     * Handles searching for a student by ID
     */
    private static void searchStudentById() {
        System.out.println("\n--- Search Student by ID ---");
        
        int studentId = getValidStudentId();
        
        Student student = studentService.getStudentById(studentId);
        if (student != null) {
            System.out.println("Student found:");
            System.out.println("----------------------------------------");
            System.out.println(student);
            System.out.println("----------------------------------------");
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
        }
    }

    /**
     * Handles updating student information
     */
    private static void updateStudent() {
        System.out.println("\n--- Update Student ---");
        
        int studentId = getValidStudentId();
        
        if (!studentService.studentExists(studentId)) {
            System.out.println("Student with ID " + studentId + " not found.");
            return;
        }
        
        System.out.println("Enter new details (leave blank to keep current value):");
        
        System.out.print("New name: ");
        String name = scanner.nextLine();
        if (name.trim().isEmpty()) {
            name = null;
        }
        
        System.out.print("New age (or press Enter to keep current): ");
        String ageStr = scanner.nextLine();
        int age = 0;
        if (!ageStr.trim().isEmpty()) {
            try {
                age = Integer.parseInt(ageStr.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid age. Keeping current age.");
                age = 0;
            }
        }
        
        System.out.print("New course: ");
        String course = scanner.nextLine();
        if (course.trim().isEmpty()) {
            course = null;
        }
        
        if (studentService.updateStudent(studentId, name, age, course)) {
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Failed to update student.");
        }
    }

    /**
     * Handles deleting a student
     */
    private static void deleteStudent() {
        System.out.println("\n--- Delete Student ---");
        
        int studentId = getValidStudentId();
        
        if (!studentService.studentExists(studentId)) {
            System.out.println("Student with ID " + studentId + " not found.");
            return;
        }
        
        System.out.print("Are you sure you want to delete this student? (y/N): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        
        if (confirmation.equals("y") || confirmation.equals("yes")) {
            if (studentService.deleteStudent(studentId)) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Failed to delete student.");
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    /**
     * Gets and validates student ID input
     * @return valid student ID
     */
    private static int getValidStudentId() {
        while (true) {
            System.out.print("Enter student ID: ");
            try {
                int id = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                return id;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
}
