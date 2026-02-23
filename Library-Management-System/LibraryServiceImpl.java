import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * LibraryServiceImpl class implements the LibraryService interface.
 * Provides concrete implementations for all library management operations.
 * Uses ArrayList for storage and HashMap for efficient search operations.
 */
public class LibraryServiceImpl implements LibraryService {
    
    // ArrayList to store all books
    private final List<Book> books;
    
    // HashMap for efficient book lookup by ID
    private final Map<String, Book> bookMap;
    
    /**
     * Constructor initializes the data structures
     */
    public LibraryServiceImpl() {
        this.books = new ArrayList<>();
        this.bookMap = new HashMap<>();
    }
    
    @Override
    public void addBook(Book book) {
        // Validate input
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }
        
        // Check if book already exists
        if (bookExists(book.getBookId())) {
            throw new IllegalArgumentException("Book with ID " + book.getBookId() + " already exists");
        }
        
        // Add to both data structures
        books.add(book);
        bookMap.put(book.getBookId(), book);
    }
    
    @Override
    public List<Book> getAllBooks() {
        // Return a copy to prevent external modification
        return new ArrayList<>(books);
    }
    
    @Override
    public Optional<Book> searchBookById(String bookId) {
        if (bookId == null || bookId.trim().isEmpty()) {
            return Optional.empty();
        }
        
        // Use HashMap for O(1) lookup
        Book book = bookMap.get(bookId.trim());
        return Optional.ofNullable(book);
    }
    
    @Override
    public List<Book> searchBookByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        String searchTitle = title.trim().toLowerCase();
        
        // Use Java 8 streams for filtering
        return books.stream()
                   .filter(book -> book.getTitle().toLowerCase().contains(searchTitle))
                   .collect(Collectors.toList());
    }
    
    @Override
    public boolean updateBook(String bookId, Book updatedBook) {
        if (bookId == null || bookId.trim().isEmpty() || updatedBook == null) {
            return false;
        }
        
        String searchId = bookId.trim();
        
        // Check if book exists
        if (!bookExists(searchId)) {
            return false;
        }
        
        // Check if updated book ID conflicts with existing book (if ID is being changed)
        if (!searchId.equals(updatedBook.getBookId()) && bookExists(updatedBook.getBookId())) {
            throw new IllegalArgumentException("Book with ID " + updatedBook.getBookId() + " already exists");
        }
        
        // Find and update the book
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getBookId().equals(searchId)) {
                // Remove old entry from map if ID is changing
                if (!searchId.equals(updatedBook.getBookId())) {
                    bookMap.remove(searchId);
                }
                
                // Update the book in list
                books.set(i, updatedBook);
                
                // Update the book in map
                bookMap.put(updatedBook.getBookId(), updatedBook);
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public boolean deleteBook(String bookId) {
        if (bookId == null || bookId.trim().isEmpty()) {
            return false;
        }
        
        String searchId = bookId.trim();
        
        // Remove from ArrayList
        boolean removedFromList = books.removeIf(book -> book.getBookId().equals(searchId));
        
        // Remove from HashMap
        Book removedFromMap = bookMap.remove(searchId);
        
        return removedFromList || removedFromMap != null;
    }
    
    @Override
    public boolean bookExists(String bookId) {
        if (bookId == null || bookId.trim().isEmpty()) {
            return false;
        }
        
        return bookMap.containsKey(bookId.trim());
    }
    
    @Override
    public int getBookCount() {
        return books.size();
    }
    
    /**
     * Gets books by a specific author
     * @param author Author name to search for
     * @return List of books by the author
     */
    public List<Book> getBooksByAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        String searchAuthor = author.trim().toLowerCase();
        
        return books.stream()
                   .filter(book -> book.getAuthor().toLowerCase().contains(searchAuthor))
                   .collect(Collectors.toList());
    }
    
    /**
     * Gets books with low quantity (less than or equal to specified threshold)
     * @param threshold Quantity threshold
     * @return List of books with low quantity
     */
    public List<Book> getLowQuantityBooks(int threshold) {
        return books.stream()
                   .filter(book -> book.getQuantity() <= threshold)
                   .collect(Collectors.toList());
    }
}
