import java.util.*;

class Book {
    private int id;
    private String title;
    private boolean issued;

    public Book(int id, String title) {
        this.id = id;
        this.title = title;
        this.issued = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isIssued() {
        return issued;
    }

    public void setIssued(boolean issued) {
        this.issued = issued;
    }

    @Override
    public String toString() {
        return id + " - " + title;
    }
}

class User {
    private int userId;
    private String name;
    private List<Book> issuedBooks;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
        this.issuedBooks = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getIssuedBooks() {
        return issuedBooks;
    }

    public void setIssuedBooks(List<Book> issuedBooks) {
        this.issuedBooks = issuedBooks;
    }
}

class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addBook(Book[] bookArray) {
        books.addAll(Arrays.asList(bookArray));
    }

    public void issueBook(int bookId, User user) {
        for (Book book : books) {
            if (book.getId() == bookId) {
                if (book.isIssued()) {
                    System.out.println("Book already issued");
                    return;
                }
                book.setIssued(true);
                user.getIssuedBooks().add(book);
                System.out.println("Book issued successfully");
                return;
            }
        }
        System.out.println("Book not found");
    }

    public void returnBook(int bookId, User user) {
        Iterator<Book> iterator = user.getIssuedBooks().iterator();

        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getId() == bookId) {
                book.setIssued(false);
                iterator.remove();
                System.out.println("Book returned successfully");
                return;
            }
        }

        System.out.println("Book not issued to this user");
    }

    public void displayAvailableBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            if (!book.isIssued()) {
                System.out.println(book);
            }
        }
    }

    public void displayIssuedBooks() {
        System.out.println("Issued Books:");
        for (Book book : books) {
            if (book.isIssued()) {
                System.out.println(book);
            }
        }
    }
}

public class Q1 {
    public static void main(String[] args) {
        Library library = new Library();

        Book b1 = new Book(101, "Java Programming");
        Book b2 = new Book(102, "Data Structures");

        library.addBook(b1);

        Book[] bulkBooks = {
                new Book(103, "Operating Systems"),
                new Book(104, "Database Management")
        };

        library.addBook(bulkBooks);
        library.addBook(b2);

        User user = new User(1, "Alice");

        library.issueBook(101, user);
        library.issueBook(101, user);

        library.displayAvailableBooks();
        library.displayIssuedBooks();

        library.returnBook(101, user);

        library.displayAvailableBooks();
        library.displayIssuedBooks();

        System.out.println("User Issued Books:");
        for (Book book : user.getIssuedBooks()) {
            System.out.println(book);
        }
    }
}