package at.ac.fhcampuswien;

import java.util.List;

public class NewsResponse {
    private String status;
    private int totalResults;
    private List<Article> articles;

    public String getStatus(){
        return this.status;
    }

    public int getTotalResults(){
        return this.totalResults;
    }

    public List<Article> getArticles(){
        return this.articles;
    }
}
