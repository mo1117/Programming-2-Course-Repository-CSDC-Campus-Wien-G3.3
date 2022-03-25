package at.ac.fhcampuswien;

import java.util.List;
import java.util.stream.Collectors;

public class AppController {

    private List<Article> articles;

    void generateMockList(){

    }

    void setArticles(List<Article> articles){

    }

    public int getArticleCount(){
        return articles.size();

    }

    public List<Article> getTopHeadlinesAustria(){
        return articles;
    }

    protected List<Article> filterList(String query, List<Article> articles) {
        return articles.stream()
                .filter(article -> article.getTitle().equals(query))
                .collect(Collectors.toList());
    }

    public List<Article> getAllNewsBitcoin(){
        return articles;
    }



}
