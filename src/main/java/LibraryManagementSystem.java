import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

class LibraryManagementSystem {
    private ArrayList<Book> books;
    private HashMap<String, User> users;

    public LibraryManagementSystem() {
        this.books = new ArrayList<>();
        this.users = new HashMap<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public void updateBook(Book book, int copiesAvailable) {
        book.setCopiesAvailable(copiesAvailable);
    }

    public void registerUser(User user) {
        users.put(user.getUsername(), user);
    }

    public User signIn(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public void signOut(User user) {

    }

    public boolean borrowBook(User user, Book book) {
        if (user.getBorrowedBooks().contains(book) || user.getBorrowedBooks().size() >= 5 || book.getCopiesAvailable() <= 0) {
            return false;
        }

        user.addBorrowedBook(book);
        book.setCopiesAvailable(book.getCopiesAvailable() - 1);
        return true;
    }

    public void returnBook(User user, Book book) {
        user.removeBorrowedBook(book);
        book.setCopiesAvailable(book.getCopiesAvailable() + 1);
    }

    public ArrayList<Book> searchBooks(String query) {
        ArrayList<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(query.toLowerCase())
                    || book.getAuthor().toLowerCase().contains(query.toLowerCase())) {
                results.add(book);
            }
        }
        return results;
    }

    public void showAllBooks() {
        StringBuilder sb = new StringBuilder();
        for (Book book : books) {
            sb.append("Title: ").append(book.getTitle()).append("\n")
                    .append("Author: ").append(book.getAuthor()).append("\n")
                    .append("Description: ").append(book.getDescription()).append("\n")
                    .append("Copies Available: ").append(book.getCopiesAvailable()).append("\n")
                    .append("---------------------------\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "All Books", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showAllAvailableBooks() {
        StringBuilder sb = new StringBuilder();
        sb.append("All Available Books:\n");
        for (Book book : books) {
            if (book.getCopiesAvailable() > 0) {
                sb.append("Title: ").append(book.getTitle()).append("\n")
                        .append("Author: ").append(book.getAuthor()).append("\n")
                        .append("Description: ").append(book.getDescription()).append("\n")
                        .append("Copies Available: ").append(book.getCopiesAvailable()).append("\n")
                        .append("---------------------------\n");
            }
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Available Books", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showUserBorrowedBooks(User user) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are all the books borrowed by ").append(user.getUsername()).append(":\n");
        ArrayList<Book> borrowedBooks = user.getBorrowedBooks();
        if (borrowedBooks.isEmpty()) {
            sb.append("You haven't borrowed any books.");
        } else {
            for (Book book : borrowedBooks) {
                sb.append("Title: ").append(book.getTitle()).append("\n")
                        .append("Author: ").append(book.getAuthor()).append("\n")
                        .append("Description: ").append(book.getDescription()).append("\n")
                        .append("Copies Available: ").append(book.getCopiesAvailable()).append("\n")
                        .append("---------------------------\n");
            }
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Books you currently borrow", JOptionPane.INFORMATION_MESSAGE);
    }
}
