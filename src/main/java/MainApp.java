import javax.swing.*;
import java.util.ArrayList;

class MainApp {
    public static void main(String[] args) {
        LibraryManagementSystem library = new LibraryManagementSystem();

        // Sample data
        library.addBook(new Book("La Medeleni", "Ionel Teodoreanu", "Children book", 7));
        library.addBook(new Book("Alexandru Lapusneanu", "Constantin Negruzzi", "Historical Fiction", 9));
        library.addBook(new Book("Harap Alb", "Ion Creanga", "Fables", 12));

        while (true) {
            int choice = MenuOptions.showMainMenu();

            switch (choice) {
                case 0:
                    String username = MenuOptions.getUsername();
                    String password = MenuOptions.getPassword();
                    library.registerUser(new User(username, password));
                    break;

                case 1:
                    String signInUsername = MenuOptions.getUsername();
                    String signInPassword = MenuOptions.getPassword();
                    User user = library.signIn(signInUsername, signInPassword);
                    if (user != null) {
                        JOptionPane.showMessageDialog(null, "You're logged in as: " + user.getUsername());
                        while (true) {
                            int userChoice = MenuOptions.showUserMenu(user);

                            switch (userChoice) {
                                case 0:
                                    String searchQuery = MenuOptions.getSearchQuery();
                                    ArrayList<Book> searchResults = library.searchBooks(searchQuery);
                                    StringBuilder searchResultString = new StringBuilder();
                                    for (Book book : searchResults) {
                                        searchResultString.append("Title: ").append(book.getTitle()).append("\n")
                                                .append("Author: ").append(book.getAuthor()).append("\n")
                                                .append("Description: ").append(book.getDescription()).append("\n")
                                                .append("Copies Available: ").append(book.getCopiesAvailable()).append("\n")
                                                .append("---------------------------\n");
                                    }
                                    JOptionPane.showMessageDialog(null, searchResultString.toString(), "Search Results", JOptionPane.INFORMATION_MESSAGE);
                                    break;

                                case 1:
                                    library.showAllBooks();
                                    break;

                                case 2:
                                    String borrowQuery = MenuOptions.getBookTitleToBorrow();
                                    ArrayList<Book> borrowResults = library.searchBooks(borrowQuery);
                                    if (borrowResults.isEmpty()) {
                                        JOptionPane.showMessageDialog(null, "No book found with the given title.", "Error", JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        Book borrowBook = borrowResults.get(0);
                                        if (library.borrowBook(user, borrowBook)) {
                                            JOptionPane.showMessageDialog(null, "Book borrowed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Cannot borrow the book. Either maximum books borrowed or no copies available.", "Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                    break;

                                case 3: // Return Book
                                    String returnQuery = MenuOptions.getBookTitleToReturn();
                                    ArrayList<Book> returnResults = library.searchBooks(returnQuery);
                                    if (returnResults.isEmpty()) {
                                        JOptionPane.showMessageDialog(null, "No book found with the given title.", "Error", JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        Book returnBook = returnResults.get(0); // Assuming the first book in the search results is the one to be returned
                                        library.returnBook(user, returnBook);
                                        JOptionPane.showMessageDialog(null, "Book returned successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                    break;

                                case 4: // Borrowed Books
                                    library.showUserBorrowedBooks(user);
                                    break;

                                case 5: // Sign Out
                                    library.signOut(user);
                                    user = null;
                                    break;
                            }

                            if (user == null) {
                                break;
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                case 2: // Search Books
                    String searchQuery = MenuOptions.getSearchQuery();
                    ArrayList<Book> searchResults = library.searchBooks(searchQuery);
                    StringBuilder searchResultString = new StringBuilder();
                    for (Book book : searchResults) {
                        searchResultString.append("Title: ").append(book.getTitle()).append("\n")
                                .append("Author: ").append(book.getAuthor()).append("\n")
                                .append("Description: ").append(book.getDescription()).append("\n")
                                .append("Copies Available: ").append(book.getCopiesAvailable()).append("\n")
                                .append("---------------------------\n");
                    }
                    JOptionPane.showMessageDialog(null, searchResultString.toString(), "Search Results", JOptionPane.INFORMATION_MESSAGE);
                    break;

                case 3:
                    library.showAllBooks();
                    break;

                case 4:
                    System.exit(0);
            }
        }
    }
}