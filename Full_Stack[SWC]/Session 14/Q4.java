import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

class Book {
    private int id;
    private String title;
    private String author;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return String.format("%-10d %-25s %-20s", id, title, author);
    }
}

class Library {
    private List<Book> books = new ArrayList<>();

    public void createBook(Book book) {
        books.add(book);
        System.out.println("Book Added Successfully");
    }

    public void readBooks() {
        if (books.isEmpty()) {
            System.out.println("No Books Available");
            return;
        }

        System.out.printf("%-10s %-25s %-20s%n", "ID", "TITLE", "AUTHOR");

        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void updateBook(int id, String title, String author) {
        for (Book book : books) {
            if (book.getId() == id) {
                book.setTitle(title);
                book.setAuthor(author);
                System.out.println("Book Updated Successfully");
                return;
            }
        }
        System.out.println("Book Not Found");
    }

    public void deleteBook(int id) {
        Iterator<Book> iterator = books.iterator();

        while (iterator.hasNext()) {
            Book book = iterator.next();

            if (book.getId() == id) {
                iterator.remove();
                System.out.println("Book Deleted Successfully");
                return;
            }
        }

        System.out.println("Book Not Found");
    }
}

public class Q4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Library library = new Library();

        while (true) {
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    int id = sc.nextInt();
                    sc.nextLine();
                    String title = sc.nextLine();
                    String author = sc.nextLine();

                    library.createBook(new Book(id, title, author));
                    break;

                case 2:
                    library.readBooks();
                    break;

                case 3:
                    int updateId = sc.nextInt();
                    sc.nextLine();
                    String newTitle = sc.nextLine();
                    String newAuthor = sc.nextLine();

                    library.updateBook(updateId, newTitle, newAuthor);
                    break;

                case 4:
                    int deleteId = sc.nextInt();
                    library.deleteBook(deleteId);
                    break;

                case 5:
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}