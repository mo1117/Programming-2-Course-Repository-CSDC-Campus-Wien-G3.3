package at.ac.fhcampuswien;

public class Article {
    private static String title;
    private String author;

    public Article(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public String getAuthor(String author){
        return author;
    }

    public static String getTitle(){
        return title;
    }

    @Override
    public String toString(){
        return "Title: " + title + System.lineSeparator() + "Author: " + author;
    }


}
