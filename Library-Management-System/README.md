# Library Management System (Console-Based)

A comprehensive Library Management System built using Core Java with proper Object-Oriented Programming principles.

## Features

- **Complete CRUD Operations**: Add, View, Search, Update, and Delete books
- **Console-Based Interface**: Menu-driven program with user-friendly interface
- **Data Validation**: Input validation for all fields with proper error handling
- **Efficient Search**: Uses HashMap for O(1) book lookup by ID
- **Statistics**: View library statistics including total books and low inventory alerts
- **Sample Data**: Built-in function to add sample books for testing

## Technical Implementation

### OOP Principles Demonstrated

1. **Encapsulation**: All fields in `Book` class are private with public getters/setters
2. **Inheritance**: `LibraryServiceImpl` implements `LibraryService` interface
3. **Polymorphism**: `LibraryService` interface allows for different implementations
4. **Abstraction**: `LibraryService` interface hides implementation details

### Data Structures Used

- **ArrayList**: Stores all book records
- **HashMap**: Provides O(1) lookup for books by ID
- **Optional**: Handles null-safe return values
- **Java 8 Streams**: Used for filtering and searching operations

### Exception Handling

- Invalid input validation (wrong data types)
- Book not found scenarios
- Duplicate book ID prevention
- Empty field validation
- Negative quantity prevention

## File Structure

```
Library Management/
├── Book.java                    # Book entity class
├── LibraryService.java          # Service interface (abstraction)
├── LibraryServiceImpl.java      # Service implementation
├── LibraryManagementSystem.java # Main application class
└── README.md                    # This documentation file
```

## How to Compile and Run

### Prerequisites
- Java 8 or above
- No external libraries required

### Compilation
```bash
javac *.java
```

### Execution
```bash
java LibraryManagementSystem
```

## Sample Console Output

### Main Menu
```
==============================================
   LIBRARY MANAGEMENT SYSTEM
==============================================
Welcome to the Library Management System!

MAIN MENU
---------
1. Add New Book
2. View All Books
3. Search Book
4. Update Book Details
5. Delete Book
6. View Library Statistics
7. Add Sample Books (for testing)
8. Exit

Enter your choice (1-8):
```

### Adding a Book
```
ADD NEW BOOK
-------------
Enter Book ID: B001
Enter Book Title: Java Programming
Enter Author Name: John Smith
Enter Quantity: 10
Book added successfully!
Book Details: Book ID: B001 | Title: Java Programming | Author: John Smith | Quantity: 10
```

### Viewing All Books
```
ALL BOOKS
---------
Total Books: 3
----------------------------------------
1. Book ID: B001 | Title: Java Programming | Author: John Smith | Quantity: 10
2. Book ID: B002 | Title: Data Structures | Author: Jane Doe | Quantity: 5
3. Book ID: B003 | Title: Algorithm Design | Author: Robert Johnson | Quantity: 3
----------------------------------------
```

### Searching by ID
```
SEARCH BOOK
------------
Search by:
1. Book ID
2. Title
Enter search option (1-2): 1
Enter Book ID to search: B001
Book Found:
Book ID: B001 | Title: Java Programming | Author: John Smith | Quantity: 10
```

### Searching by Title
```
SEARCH BOOK
------------
Search by:
1. Book ID
2. Title
Enter search option (1-2): 2
Enter title or partial title to search: Java
Found 1 book(s):
----------------------------------------
1. Book ID: B001 | Title: Java Programming | Author: John Smith | Quantity: 10
----------------------------------------
```

### Updating a Book
```
UPDATE BOOK
------------
Enter Book ID to update: B001
Current Book Details:
Book ID: B001 | Title: Java Programming | Author: John Smith | Quantity: 10

Enter new details (press Enter to keep current value):
New Book ID [B001]: B001
New Title [Java Programming]: Advanced Java Programming
New Author [John Smith]: John Smith
New Quantity [10]: 15
Book updated successfully!
Updated Book Details: Book ID: B001 | Title: Advanced Java Programming | Author: John Smith | Quantity: 15
```

### Deleting a Book
```
DELETE BOOK
------------
Enter Book ID to delete: B003
Book to be deleted:
Book ID: B003 | Title: Algorithm Design | Author: Robert Johnson | Quantity: 3

Are you sure you want to delete this book? (yes/no): yes
Book deleted successfully!
```

### Library Statistics
```
LIBRARY STATISTICS
------------------
Total Books: 2
Total Quantity: 20
Books with low quantity (≤5): 1
  - Data Structures (5 copies)
```

### Error Handling Examples

#### Duplicate Book ID
```
ADD NEW BOOK
-------------
Enter Book ID: B001
Error: Book with ID 'B001' already exists!
```

#### Invalid Input
```
Enter your choice (1-8): abc
Invalid input! Please enter a valid number.
```

#### Empty Fields
```
ADD NEW BOOK
-------------
Enter Book ID: 
Input cannot be empty! Please enter a valid value.
```

#### Negative Quantity
```
Enter Quantity: -5
Please enter a value between 1 and 2147483647.
```

## Key Features Demonstrated

1. **Input Validation**: All user inputs are validated with appropriate error messages
2. **Exception Handling**: Comprehensive exception handling for various scenarios
3. **Memory Management**: Efficient data structures for optimal performance
4. **User Experience**: Clear menu system with intuitive navigation
5. **Data Integrity**: Prevents duplicate entries and maintains data consistency
6. **Search Functionality**: Multiple search options with partial matching support

## Extension Possibilities

- Add user management system
- Implement book borrowing/returning functionality
- Add file persistence (CSV/JSON)
- Implement multi-threading for concurrent operations
- Add more sophisticated search filters
- Implement book categories and genres
- Add reporting and analytics features

## Author

This Library Management System demonstrates best practices in Java programming and Object-Oriented Design principles.
