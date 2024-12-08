import java.util.ArrayList;
import java.util.List;

// 📖 Book Class
class Book {
    private final int bookID;
    private String bookTitle;
    private String author;
    private boolean availabilityStatus;

    public Book(int bookID, String bookTitle, String author) {
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.author = author;
        this.availabilityStatus = true;
    }

    public boolean isAvailable() {
        return availabilityStatus;
    }

    public void borrow() {
        if (availabilityStatus) {
            availabilityStatus = false;
            System.out.println("Book borrowed: " + bookTitle);
        } else {
            System.out.println("Sorry, " + bookTitle + " is already borrowed.");
        }
    }

    public void returnBook() {
        availabilityStatus = true;
        System.out.println("Book returned: " + bookTitle);
    }

    @Override
    public String toString() {
        return "Book ID: " + bookID + ", Title: " + bookTitle + ", Author: " + author + ", Available: " + availabilityStatus;
    }
}

// 👤 User (Abstract Class)
abstract class User {
    protected String name;
    protected int age;
    protected int userID;

    public User(String name, int age, int userID) {
        this.name = name;
        this.age = age;
        this.userID = userID;
    }

    public abstract void borrowBook(Book book);

    public void returnBook(Book book) {
        book.returnBook();
    }

    @Override
    public String toString() {
        return "User ID: " + userID + ", Name: " + name + ", Age: " + age;
    }
}

// 👥 Patron Class (Subclass of User)
class Patron extends User {
    public Patron(String name, int age, int userID) {
        super(name, age, userID);
    }

    @Override
    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            book.borrow();
        } else {
            System.out.println("The book is not available for borrowing.");
        }
    }
}

// Interface for generating reports
interface ReportGenerator {
    void generateReport();
    void generateUserReport();
}

// 📋 Librarian Class (Subclass of User and Implements ReportGenerator Interface)
class Librarian extends User implements ReportGenerator {
    public Librarian(String name, int age, int userID) {
        super(name, age, userID);
    }

    @Override
    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            book.borrow();
        } else {
            System.out.println("The book is not available for borrowing.");
        }
    }

    @Override
    public void generateReport() {
        System.out.println("Generating book report...");
        Library.getInstance().listBooks();
    }

    @Override
    public void generateUserReport() {
        System.out.println("Generating user report...");
        Library.getInstance().listUsers();
    }
}

// 🏛 Library Class (Singleton Pattern for a single instance)
class Library {
    private static Library instance;
    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    private Library() { }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void listBooks() {
        System.out.println("\nLibrary Books:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void listUsers() {
        System.out.println("\nLibrary Users:");
        for (User user : users) {
            System.out.println(user);
        }
    }

    public void main() {
        // Creating and adding books
        Book book1 = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald");
        Book book2 = new Book(2, "1984", "George Orwell");
        addBook(book1);
        addBook(book2);

        // Creating and adding users (Patron and Librarian)
        Patron patron = new Patron("Alok", 24, 105);
        Librarian librarian = new Librarian("Mr. Ram", 40, 200);
        addUser(patron);
        addUser(librarian);

        // Borrowing and returning books
        System.out.println("\n--- Patron borrowing a book ---");
        patron.borrowBook(book1);
        System.out.println("--- Patron returning a book ---");
        patron.returnBook(book1);

        System.out.println("\n--- Librarian generating reports ---");
        librarian.generateReport();
        librarian.generateUserReport();
    }
}

// Demo
public class Librabary {
    public static void main(String[] args) {
        Library library = Library.getInstance();
        library.main();
    }
}




