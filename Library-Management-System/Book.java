/**
 * Book class represents a book in the library system.
 * Follows encapsulation principle with private fields and public getters/setters.
 */
public class Book {
    private String bookId;
    private String title;
    private String author;
    private int quantity;
    
    /**
     * Constructor to initialize a Book object
     * @param bookId Unique identifier for the book
     * @param title Title of the book
     * @param author Author of the book
     * @param quantity Available quantity of the book
     */
    public Book(String bookId, String title, String author, int quantity) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }
    
    // Getter methods
    public String getBookId() {
        return bookId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    // Setter methods with validation
    public void setBookId(String bookId) {
        if (bookId == null || bookId.trim().isEmpty()) {
            throw new IllegalArgumentException("Book ID cannot be null or empty");
        }
        this.bookId = bookId;
    }
    
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        this.title = title;
    }
    
    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be null or empty");
        }
        this.author = author;
    }
    
    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = quantity;
    }
    
    /**
     * Returns a string representation of the Book object
     * @return Formatted string with book details
     */
    @Override
    public String toString() {
        return String.format("Book ID: %s | Title: %s | Author: %s | Quantity: %d", 
                           bookId, title, author, quantity);
    }
    
    /**
     * Compares two Book objects based on bookId
     * @param obj Object to compare with
     * @return true if books are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return bookId.equals(book.bookId);
    }
    
    @Override
    public int hashCode() {
        return bookId.hashCode();
    }
}
