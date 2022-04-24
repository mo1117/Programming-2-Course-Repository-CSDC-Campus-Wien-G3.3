package at.ac.fhcampuswien;

public class Article {
    private String title;
    private String author;
    private String description;

    public Article(String author, String title, String description) {
        this.author = author;
        this.title = title;
        this.description = description;
    }

    public Article(String author, String title){
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
        return "Title: " + this.title + System.lineSeparator() + "Author: " + this.author + System.lineSeparator();
    }


}
