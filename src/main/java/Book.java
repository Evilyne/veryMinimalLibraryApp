class Book {
    private String title;
    private String author;
    private String description;
    private int copiesAvailable;

    public Book(String title, String author, String description, int copiesAvailable) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.copiesAvailable = copiesAvailable;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public void setCopiesAvailable(int copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }
}
