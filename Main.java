

import java.util.*;

// Represents a Book entity
class Book {
    private String id;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    // Getters and Setters for Encapsulation
    public String getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public boolean isIssued() { return isIssued; }
    public void setIssued(boolean issued) { isIssued = issued; }

    @Override
    public String toString() {
        return String.format("ID: %s | Title: %s | Author: %s | Status: %s", 
                id, title, author, (isIssued ? "Issued" : "Available"));
    }
}

// Represents a Library User
class User {
    private String id;
    private String name;
    private List<String> borrowedBookIds = new ArrayList<>();

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public List<String> getBorrowedBookIds() { return borrowedBookIds; }
}

// Manages the system operations
class LibrarySystem {
    private Map<String, Book> books = new HashMap<>();
    private Map<String, User> users = new HashMap<>();

    // --- CREATE ---
    public void addBook(String id, String title, String author) {
        if (books.containsKey(id)) {
            System.out.println("Error: Book ID " + id + " already exists.");
        } else {
            books.put(id, new Book(id, title, author));
            System.out.println("Book '" + title + "' added successfully.");
        }
    }

    public void registerUser(String id, String name) {
        users.put(id, new User(id, name));
        System.out.println("User '" + name + "' registered.");
    }

    // --- UPDATE ---
    public void updateBook(String id, String newTitle, String newAuthor) {
        Book book = books.get(id);
        if (book != null) {
            book.setTitle(newTitle);
            book.setAuthor(newAuthor);
            System.out.println("Book ID " + id + " updated successfully.");
        } else {
            System.out.println("Error: Book not found.");
        }
    }

    // --- DELETE ---
    public void deleteBook(String id) {
        if (books.containsKey(id)) {
            if (books.get(id).isIssued()) {
                System.out.println("Error: Cannot delete a book that is currently issued.");
            } else {
                books.remove(id);
                System.out.println("Book ID " + id + " deleted.");
            }
        } else {
            System.out.println("Error: Book not found.");
        }
    }

    // --- TRANSACTIONAL FEATURES ---
    public void issueBook(String bookId, String userId) {
        Book book = books.get(bookId);
        User user = users.get(userId);

        if (book != null && user != null && !book.isIssued()) {
            book.setIssued(true);
            user.getBorrowedBookIds().add(bookId);
            System.out.println("SUCCESS: '" + book.getTitle() + "' issued to " + user.getName());
        } else {
            System.out.println("FAILURE: Transaction failed. Check IDs or book availability.");
        }
    }

    public void returnBook(String bookId, String userId) {
        Book book = books.get(bookId);
        User user = users.get(userId);

        if (book != null && user != null && user.getBorrowedBookIds().contains(bookId)) {
            book.setIssued(false);
            user.getBorrowedBookIds().remove(bookId);
            System.out.println("SUCCESS: '" + book.getTitle() + "' returned by " + user.getName());
        } else {
            System.out.println("FAILURE: Invalid return request.");
        }
    }

    public void displayAllBooks() {
        System.out.println("\n--- Library Inventory ---");
        books.values().forEach(System.out::println);
    }
}

public class Main {
    public static void main(String[] args) {
        LibrarySystem lib = new LibrarySystem();

        // Testing ADD
        lib.addBook("101", "Clean Code", "Robert Martin");
        lib.addBook("102", "Effective Java", "Joshua Bloch");
        lib.registerUser("U01", "Alice");

        // Testing UPDATE
        lib.updateBook("101", "Clean Code (2nd Ed)", "Robert C. Martin");

        // Testing ISSUE & RETURN
        lib.issueBook("101", "U01");
        lib.displayAllBooks();
        
        lib.returnBook("101", "U01");

        // Testing DELETE
        lib.deleteBook("102");
        lib.displayAllBooks();
    }
}
