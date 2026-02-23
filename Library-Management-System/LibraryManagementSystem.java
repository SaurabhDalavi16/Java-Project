import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Main class for the Library Management System.
 * Provides a menu-driven console interface for managing books.
 * Demonstrates polymorphism through the LibraryService interface.
 */
public class LibraryManagementSystem {
    
    private static LibraryService libraryService;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        // Initialize service and scanner
        libraryService = new LibraryServiceImpl();
        scanner = new Scanner(System.in);
        
        System.out.println("==============================================");
        System.out.println("   LIBRARY MANAGEMENT SYSTEM");
        System.out.println("==============================================");
        System.out.println("Welcome to the Library Management System!");
        System.out.println();
        
        // Main menu loop
        int choice;
        do {
            displayMenu();
            choice = getValidIntInput("Enter your choice (1-8): ");
            
            try {
                switch (choice) {
                    case 1:
                        addBook();
                        break;
                    case 2:
                        viewAllBooks();
                        break;
                    case 3:
                        searchBook();
                        break;
                    case 4:
                        updateBook();
                        break;
                    case 5:
                        deleteBook();
                        break;
                    case 6:
                        viewStatistics();
                        break;
                    case 7:
                        addSampleBooks();
                        break;
                    case 8:
                        System.out.println("Thank you for using Library Management System!");
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice! Please enter a number between 1 and 8.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            
            if (choice != 8) {
                System.out.println("\nPress Enter to continue...");
                try {
                    scanner.nextLine();
                } catch (Exception e) {
                    // Ignore any exceptions during continue prompt
                }
                System.out.println();
            }
            
        } while (choice != 8);
        
        // Close scanner
        scanner.close();
    }
    
    /**
     * Displays the main menu options
     */
    private static void displayMenu() {
        System.out.println("MAIN MENU");
        System.out.println("---------");
        System.out.println("1. Add New Book");
        System.out.println("2. View All Books");
        System.out.println("3. Search Book");
        System.out.println("4. Update Book Details");
        System.out.println("5. Delete Book");
        System.out.println("6. View Library Statistics");
        System.out.println("7. Add Sample Books (for testing)");
        System.out.println("8. Exit");
        System.out.println();
    }
    
    /**
     * Adds a new book to the library
     */
    private static void addBook() {
        System.out.println("ADD NEW BOOK");
        System.out.println("-------------");
        
        try {
            String bookId = getValidStringInput("Enter Book ID: ");
            
            // Check if book already exists
            if (libraryService.bookExists(bookId)) {
                System.out.println("Error: Book with ID '" + bookId + "' already exists!");
                return;
            }
            
            String title = getValidStringInput("Enter Book Title: ");
            String author = getValidStringInput("Enter Author Name: ");
            int quantity = getValidIntInput("Enter Quantity: ", 1, Integer.MAX_VALUE);
            
            Book book = new Book(bookId, title, author, quantity);
            libraryService.addBook(book);
            
            System.out.println("Book added successfully!");
            System.out.println("Book Details: " + book);
            
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Displays all books in the library
     */
    private static void viewAllBooks() {
        System.out.println("ALL BOOKS");
        System.out.println("---------");
        
        List<Book> books = libraryService.getAllBooks();
        
        if (books.isEmpty()) {
            System.out.println("No books found in the library.");
            return;
        }
        
        System.out.println("Total Books: " + books.size());
        System.out.println("----------------------------------------");
        
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i));
        }
        
        System.out.println("----------------------------------------");
    }
    
    /**
     * Searches for a book by ID or title
     */
    private static void searchBook() {
        System.out.println("SEARCH BOOK");
        System.out.println("------------");
        
        System.out.println("Search by:");
        System.out.println("1. Book ID");
        System.out.println("2. Title");
        
        int searchChoice = getValidIntInput("Enter search option (1-2): ");
        
        switch (searchChoice) {
            case 1:
                searchById();
                break;
            case 2:
                searchByTitle();
                break;
            default:
                System.out.println("Invalid search option!");
        }
    }
    
    /**
     * Searches for a book by ID
     */
    private static void searchById() {
        String bookId = getValidStringInput("Enter Book ID to search: ");
        
        Optional<Book> book = libraryService.searchBookById(bookId);
        
        if (book.isPresent()) {
            System.out.println("Book Found:");
            System.out.println(book.get());
        } else {
            System.out.println("Book with ID '" + bookId + "' not found.");
        }
    }
    
    /**
     * Searches for books by title
     */
    private static void searchByTitle() {
        String title = getValidStringInput("Enter title or partial title to search: ");
        
        List<Book> books = libraryService.searchBookByTitle(title);
        
        if (books.isEmpty()) {
            System.out.println("No books found with title containing '" + title + "'.");
            return;
        }
        
        System.out.println("Found " + books.size() + " book(s):");
        System.out.println("----------------------------------------");
        
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i));
        }
        
        System.out.println("----------------------------------------");
    }
    
    /**
     * Updates book details
     */
    private static void updateBook() {
        System.out.println("UPDATE BOOK");
        System.out.println("------------");
        
        String bookId = getValidStringInput("Enter Book ID to update: ");
        
        if (!libraryService.bookExists(bookId)) {
            System.out.println("Book with ID '" + bookId + "' not found.");
            return;
        }
        
        // Get current book details
        Optional<Book> currentBook = libraryService.searchBookById(bookId);
        if (currentBook.isPresent()) {
            System.out.println("Current Book Details:");
            System.out.println(currentBook.get());
            System.out.println();
        }
        
        try {
            System.out.println("Enter new details (press Enter to keep current value):");
            
            String newBookId = getOptionalStringInput("New Book ID [" + bookId + "]: ", bookId);
            String newTitle = getOptionalStringInput("New Title [" + currentBook.get().getTitle() + "]: ", currentBook.get().getTitle());
            String newAuthor = getOptionalStringInput("New Author [" + currentBook.get().getAuthor() + "]: ", currentBook.get().getAuthor());
            
            System.out.print("New Quantity [" + currentBook.get().getQuantity() + "]: ");
            String quantityInput = scanner.nextLine().trim();
            int newQuantity = quantityInput.isEmpty() ? currentBook.get().getQuantity() : Integer.parseInt(quantityInput);
            
            Book updatedBook = new Book(newBookId, newTitle, newAuthor, newQuantity);
            
            if (libraryService.updateBook(bookId, updatedBook)) {
                System.out.println("Book updated successfully!");
                System.out.println("Updated Book Details: " + updatedBook);
            } else {
                System.out.println("Failed to update book.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid quantity format. Please enter a valid number.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Deletes a book from the library
     */
    private static void deleteBook() {
        System.out.println("DELETE BOOK");
        System.out.println("------------");
        
        String bookId = getValidStringInput("Enter Book ID to delete: ");
        
        if (!libraryService.bookExists(bookId)) {
            System.out.println("Book with ID '" + bookId + "' not found.");
            return;
        }
        
        // Show book details before deletion
        Optional<Book> book = libraryService.searchBookById(bookId);
        if (book.isPresent()) {
            System.out.println("Book to be deleted:");
            System.out.println(book.get());
            System.out.println();
        }
        
        String confirm = getValidStringInput("Are you sure you want to delete this book? (yes/no): ");
        
        if (confirm.equalsIgnoreCase("yes") || confirm.equalsIgnoreCase("y")) {
            if (libraryService.deleteBook(bookId)) {
                System.out.println("Book deleted successfully!");
            } else {
                System.out.println("Failed to delete book.");
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }
    
    /**
     * Displays library statistics
     */
    private static void viewStatistics() {
        System.out.println("LIBRARY STATISTICS");
        System.out.println("------------------");
        
        System.out.println("Total Books: " + libraryService.getBookCount());
        
        List<Book> books = libraryService.getAllBooks();
        if (!books.isEmpty()) {
            int totalQuantity = books.stream().mapToInt(Book::getQuantity).sum();
            System.out.println("Total Quantity: " + totalQuantity);
            
            // Find books with low quantity
            LibraryServiceImpl impl = (LibraryServiceImpl) libraryService;
            List<Book> lowQuantityBooks = impl.getLowQuantityBooks(5);
            
            if (!lowQuantityBooks.isEmpty()) {
                System.out.println("Books with low quantity (≤5): " + lowQuantityBooks.size());
                for (Book book : lowQuantityBooks) {
                    System.out.println("  - " + book.getTitle() + " (" + book.getQuantity() + " copies)");
                }
            }
        }
    }
    
    /**
     * Adds sample books for testing purposes
     */
    private static void addSampleBooks() {
        System.out.println("ADDING SAMPLE BOOKS");
        System.out.println("-------------------");
        
        try {
            Book[] sampleBooks = {
                new Book("B001", "Java Programming", "John Smith", 10),
                new Book("B002", "Data Structures", "Jane Doe", 5),
                new Book("B003", "Algorithm Design", "Robert Johnson", 3),
                new Book("B004", "Web Development", "Emily Brown", 8),
                new Book("B005", "Database Systems", "Michael Wilson", 6)
            };
            
            int addedCount = 0;
            for (Book book : sampleBooks) {
                if (!libraryService.bookExists(book.getBookId())) {
                    libraryService.addBook(book);
                    addedCount++;
                    System.out.println("Added: " + book.getTitle());
                } else {
                    System.out.println("Skipped (already exists): " + book.getTitle());
                }
            }
            
            System.out.println("\nSuccessfully added " + addedCount + " sample books!");
            
        } catch (Exception e) {
            System.out.println("Error adding sample books: " + e.getMessage());
        }
    }
    
    // Utility methods for input validation
    
    /**
     * Gets a valid integer input from user
     */
    private static int getValidIntInput(String prompt) {
        return getValidIntInput(prompt, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    /**
     * Gets a valid integer input within specified range
     */
    private static int getValidIntInput(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Please enter a value between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }
    
    /**
     * Gets a non-empty string input from user
     */
    private static String getValidStringInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("Input cannot be empty! Please enter a valid value.");
            }
        }
    }
    
    /**
     * Gets optional string input with default value
     */
    private static String getOptionalStringInput(String prompt, String defaultValue) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        
        return input.isEmpty() ? defaultValue : input;
    }
}
