package at.ac.fhcampuswien;

import at.ac.fhcampuswien.enums.CategoryEnum;
import at.ac.fhcampuswien.enums.CountryEnum;
import at.ac.fhcampuswien.enums.EndpointEnum;

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

    //resets all the enum values uses in the NewsApi class
    public void resetNewsApi(){
        NewsApi.categoryEnum = null;
        NewsApi.countryEnum = null;
        NewsApi.endpointEnum = null;
        NewsApi.languageEnum = null;
        NewsApi.sortByEnum = null;
        NewsApi.query = null;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
        this.articleCount = articles.size();
    }

    public int getArticleCount() {
        return this.articleCount;
    }

    public List<Article> getTopHeadlinesAustria() throws IOException {
        resetNewsApi();
        NewsApi.countryEnum = CountryEnum.at;
        NewsApi.endpointEnum = EndpointEnum.topHeadlines;
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

    public List<Article> getAllNewsBitcoin() throws IOException {
        resetNewsApi();
        NewsApi.endpointEnum = EndpointEnum.everything;
        NewsApi.query = "bitcoin";
        this.newsResponse = this.newsApi.request();
        this.setArticles(newsResponse.getArticles());
        return this.articles;
    }
}
