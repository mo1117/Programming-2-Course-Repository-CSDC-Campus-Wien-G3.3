package at.ac.fhcampuswien;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AppController {

    private int articleCount;
    private List<Article> articles;
    private NewsResponse newsResponse;
    private NewsApi newsApi = new NewsApi();

    public AppController(){
        this.articleCount = 0;
        this.articles = null;
    }

    public void generateMockList() {

    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
        this.articleCount = articles.size();
    }

    public int getArticleCount() {
        return this.articleCount;
    }

    public List<Article> getTopHeadlinesAustria() throws IOException {
        this.newsResponse = this.newsApi.request();
        this.setArticles(newsResponse.getArticles());
        return this.articles;
    }



    protected static List<Article> filterList(String query, List<Article> articles) {
        List<Article> articlesNew = new LinkedList();
        if (articles != null)
            for (Article x : articles) {
                if (x.getTitle().toLowerCase().contains(query.toLowerCase()))
                    articlesNew.add(x);
            }
        return articlesNew;
    }

    public List<Article> getAllNewsBitcoin() {
        return filterList("bitcoin", this.articles);
    }
}
