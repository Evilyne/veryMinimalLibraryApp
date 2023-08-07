import javax.swing.*;

class MenuOptions {
    public static int showMainMenu() {
        String[] options = {"Register", "Sign In", "Search Books by Title", "Show All Books", "Exit"};
        return JOptionPane.showOptionDialog(null,
                "Select an option:", "Library Management System",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
    }

    public static int showUserMenu(User user) {
        String[] userOptions = {"Search Books", "Show All Books by Title", "Borrow a Book", "Return a Book", "Currently Borrowed Books", "Sign Out"};
        return JOptionPane.showOptionDialog(null,
                "Select an option:", "Library Management System - " + user.getUsername(),
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, userOptions, userOptions[0]);
    }

    public static String getUsername() {
        return JOptionPane.showInputDialog("Enter username:");
    }

    public static String getPassword() {
        return JOptionPane.showInputDialog("Enter password:");
    }

    public static String getSearchQuery() {
        return JOptionPane.showInputDialog("Enter book title to display coppies:");
    }

    public static String getBookTitleToBorrow() {
        return JOptionPane.showInputDialog("Enter title of the book you wish to borrow:");
    }

    public static String getBookTitleToReturn() {
        return JOptionPane.showInputDialog("Enter title of book you wish to return:");
    }
}