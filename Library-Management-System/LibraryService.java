import java.util.List;
import java.util.Optional;

/**
 * LibraryService interface defines the contract for library operations.
 * Follows abstraction principle by defining only the method signatures.
 * All classes implementing this interface must provide concrete implementations.
 */
public interface LibraryService {
    
    /**
     * Adds a new book to the library
     * @param book Book object to be added
     * @throws IllegalArgumentException if book already exists or validation fails
     */
    void addBook(Book book);
    
    /**
     * Retrieves all books from the library
     * @return List of all books
     */
    List<Book> getAllBooks();
    
    /**
     * Searches for a book by its ID
     * @param bookId Unique identifier of the book
     * @return Optional containing the book if found, empty otherwise
     */
    Optional<Book> searchBookById(String bookId);
    
    /**
     * Searches for books by title (partial match)
     * @param title Title or partial title to search for
     * @return List of books matching the title
     */
    List<Book> searchBookByTitle(String title);
    
    /**
     * Updates book details
     * @param bookId ID of the book to update
     * @param updatedBook Book object with updated details
     * @return true if update successful, false if book not found
     */
    boolean updateBook(String bookId, Book updatedBook);
    
    /**
     * Deletes a book from the library
     * @param bookId ID of the book to delete
     * @return true if deletion successful, false if book not found
     */
    boolean deleteBook(String bookId);
    
    /**
     * Checks if a book exists in the library
     * @param bookId ID of the book to check
     * @return true if book exists, false otherwise
     */
    boolean bookExists(String bookId);
    
    /**
     * Gets the total count of books in the library
     * @return Total number of books
     */
    int getBookCount();
}
