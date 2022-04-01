package at.ac.fhcampuswien;

public class Article {
    private String title;
    private String author;

    public Article(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getTitle() {
        return this.title;
    }

    @Override
    public String toString() {
        return "Title: " + title + System.lineSeparator() + "Author: " + author + System.lineSeparator();
    }


}
