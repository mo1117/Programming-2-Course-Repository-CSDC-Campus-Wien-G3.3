package at.ac.fhcampuswien;

import java.util.List;
import java.util.stream.Collectors;


public class AppController {

    private int articleCount;
    private List<Article> articles;

    void generateMockList() {

    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
        this.articleCount = articles.size();
    }

    public int getArticleCount() {
        return this.articleCount;
    }

    public List<Article> getTopHeadlinesAustria() {
        return this.articles;
    }

    protected static List<Article> filterList(String query, List<Article> articles) {
        return articles.stream()
                .filter(Article -> at.ac.fhcampuswien.Article.getTitle().contains(query))
                .collect(Collectors.toList());
    }

    public List<Article> getAllNewsBitcoin() {
        return filterList("bitcoin", this.articles);
    }
}
