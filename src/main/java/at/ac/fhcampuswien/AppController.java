package at.ac.fhcampuswien;

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

    public AppController() {
        this.articleCount = 0;
        this.articles = null;
    }

    public void generateMockList() {

    }

    //resets all the enum values used in the NewsApi class
    public void resetNewsApi() {
        NewsApi.categoryEnum = null;
        NewsApi.countryEnum = null;
        NewsApi.endpointEnum = null;
        NewsApi.languageEnum = null;
        NewsApi.sortByEnum = null;
        NewsApi.query = null;
    }

    public void setArticles(List<Article> articles) throws NewsAPIException { //NewsAPIException
        if (articles == null)
            throw new NewsAPIException("Cannot set an empty Article!");
        this.articles = articles;
        this.articleCount = articles.size();
    }

    public int getArticleCount() {
        return this.articleCount;
    }

    public List<Article> getTopHeadlinesAustria() {
        resetNewsApi();
        NewsApi.countryEnum = CountryEnum.at;
        NewsApi.endpointEnum = EndpointEnum.topHeadlines;
        try {
            this.newsResponse = this.newsApi.request();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.setArticles(newsResponse.getArticles());
        } catch (NewsAPIException e) {
            System.out.println(e.getMessage());
        }
        return this.articles;
    }

    protected static List<Article> filterList(String query, List<Article> articles) {
        List<Article> articlesNew = new LinkedList();
        if (articles != null)
            for (Article x : articles) {
                try {
                    if (x.getTitle().toLowerCase().contains(query.toLowerCase()))
                        articlesNew.add(x);
                }catch (NewsAPIException e){
                    e.getMessage();
                }
            }
        return articlesNew;
    }

    public List<Article> getAllNewsBitcoin() {
        resetNewsApi();
        NewsApi.endpointEnum = EndpointEnum.everything;
        NewsApi.query = "bitcoin";
        try {
            this.newsResponse = this.newsApi.request();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.setArticles(newsResponse.getArticles());
        } catch (NewsAPIException e) {
            System.out.println(e.getMessage());
        }
        return this.articles;
    }
}
