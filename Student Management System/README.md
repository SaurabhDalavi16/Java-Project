   # Student Management System
   
   A console-based Student Management System built with Core Java using OOP principles and file-based data persistence.
   
   ## Features
   
   - **Add Student**: Add new students with ID, name, age, and course
   - **View All Students**: Display all students in the system
   - **Search Student**: Find students by their unique ID
   - **Update Student**: Modify student information
   - **Delete Student**: Remove students from the system
   - **Data Persistence**: Automatic file storage using .txt files
   
   ## Project Structure
   
   ```
   Java Project Resume/
   ├── Student.java          # Model class with encapsulation
   ├── StudentService.java   # Business logic for CRUD operations
   ├── FileHandler.java      # File I/O operations
   ├── Main.java            # Console interface and menu system
   ├── students.txt         # Data storage file (auto-created)
   └── README.md           # This documentation
   ```
   
   ## Technical Implementation
   
   ### Architecture
   - **Model-View-Controller Pattern**: Separation of concerns
   - **Encapsulation**: Private fields with public getters/setters
   - **Modular Design**: Each class has a single responsibility
   - **Exception Handling**: Comprehensive error management
   
   ### Key Components
   
   #### Student.java
   - Entity model with encapsulated fields
   - Automatic ID generation
   - File serialization/deserialization methods
   
   #### StudentService.java
   - Business logic layer
   - ArrayList for in-memory operations
   - CRUD operations with validation
   - File persistence integration
   
   #### FileHandler.java
   - File I/O operations using FileWriter and BufferedReader
   - Data persistence to students.txt
   - Error handling for file operations
   
   #### Main.java
   - Menu-driven console interface
   - Input validation and error handling
   - User interaction management
   
   ### Data Storage
   - **Format**: Comma-separated values (CSV)
   - **File**: `students.txt` (auto-created)
   - **Structure**: `studentId,name,age,course`
   
   ## How to Run
   
   1. **Compile the code**:
      ```bash
      javac *.java
      ```
   
   2. **Run the application**:
      ```bash
      java Main
      ```
   
   3. **Follow the menu prompts** to perform operations
   
   ## Usage Examples
   
   ### Adding a Student
   ```
   Enter your choice (1-6): 1
   --- Add New Student ---
   Enter student name: John Doe
   Enter age: 20
   Enter course: Computer Science
   Student added successfully!
   ```
   
   ### Viewing All Students
   ```
   Enter your choice (1-6): 2
   --- All Students ---
   Total Students: 1
   ----------------------------------------
   Student ID: 1, Name: John Doe, Age: 20, Course: Computer Science
   ----------------------------------------
   ```
   
   ### Searching by ID
   ```
   Enter your choice (1-6): 3
   --- Search Student by ID ---
   Enter student ID: 1
   Student found:
   ----------------------------------------
   Student ID: 1, Name: John Doe, Age: 20, Course: Computer Science
   ----------------------------------------
   ```
   
   ## Input Validation
   
   - **Name**: Cannot be empty
   - **Age**: Must be between 1 and 120
   - **Course**: Cannot be empty
   - **Student ID**: Must be a valid integer
   - **Menu Choices**: Validated range checking
   
   ## Error Handling
   
   - File I/O exceptions
   - Invalid input formats
   - Data validation errors
   - Student not found scenarios
   
   ## Known Limitations
   
   - Data is stored in a text file, so performance may reduce with large datasets
   - No concurrent user support
   - Console-based UI only
   
   ## What I Learned
   
   - Applying OOP concepts in a real project
   - Managing file-based persistence without databases
   - Writing clean, modular Java code
   - Handling invalid user inputs gracefully
     
   ## System Requirements
   
   - Java Development Kit (JDK) 8 or higher
   - Command line/terminal access
   - File system write permissions
   
   ## Future Enhancements
   
   - Sorting functionality
   - Batch operations
   - Data export/import
   - Advanced search filters
   - Statistical reports
