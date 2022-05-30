package at.ac.fhcampuswien;

import at.ac.fhcampuswien.enums.CountryEnum;
import at.ac.fhcampuswien.enums.EndpointEnum;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AppController {

    private List<Article> articles;
    private NewsResponse newsResponse;
    private NewsApi newsApi = new NewsApi();

    public AppController() {
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

    public void setArticles(List<Article> articles) throws NewsAPIException {
        if (articles == null)
            throw new NewsAPIException("Cannot set an empty list!");
        this.articles = articles;
    }

    public int getArticleCount() throws NewsAPIException {
        if (this.articles == null)
            throw new NewsAPIException("This list seems to be empty!");
        else
            return this.articles.size();
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
                } catch (NewsAPIException e) {
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

    public String getMostArticles(){
        OptionalInt max = articles.stream().map(article -> {
            try {
                return article.getAuthor();
            } catch (NewsAPIException e) {
                return "";
            }
        }).mapToInt(String::length).max();

        Optional<String> name = articles.stream().map(article -> {
            try {
                return article.getAuthor();
            } catch (NewsAPIException e) {
                return "";
            }
        }).reduce((name1,name2 ) -> name1.length() >= name2.length() ? name1 : name2);

        return name.get();
    }

    public String getLongestAuthorName() {
        return articles.stream().map(article -> {
            try {
                return article.getAuthor();
            } catch (NewsAPIException e) {
                System.out.println(e.getMessage());
                return null;
            }
        }).reduce((s1, s2) -> s1.length() >= s2.length() ? s1 : s2).get();
    }

    public String getNYT() {
        return String.valueOf(articles.stream().filter(a -> {
            try {
                return a.getUrl().contains("nytimes.com");
            } catch (NewsAPIException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }).count());
    }
}
