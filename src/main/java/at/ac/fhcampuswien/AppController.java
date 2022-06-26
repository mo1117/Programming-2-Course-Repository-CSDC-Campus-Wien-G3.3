package at.ac.fhcampuswien;

import at.ac.fhcampuswien.downloader.Downloader;
import at.ac.fhcampuswien.enums.CountryEnum;
import at.ac.fhcampuswien.enums.EndpointEnum;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppController {

    private List<Article> articles;
    private NewsResponse newsResponse;
    private NewsApi newsApi = new NewsApi();

    public AppController() {
        this.articles = null;
    }


    public int downloadURLs(Downloader downloader) throws NewsAPIException {
        if (this.articles == null)
            throw new NewsAPIException();

        List<String> urls = new ArrayList<>();

        urls = this.articles.stream()
                .map(Article::getUrl)
                .toList();

        return downloader.process(urls);
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

    public List<Article> currentList() {
        return this.articles;
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

    public String getMostArticles() {


        List<String> sources = new ArrayList<String>(20);

        int count = 0;
        for (int i = 0; i < articles.size() - 1; i++) {
            try {
                sources.add(count, articles.get(i).getAuthor());
                count++;
            } catch (NewsAPIException e) {
                System.out.println(e.getMessage());
            }
        }

        return sources.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue()).map(Map.Entry::getKey)
                .orElse(null);

    }

    public String getLongestAuthorName() {
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
                System.out.println(e.getMessage());
                return "";
            }
        }).reduce((name1, name2) -> name1.length() >= name2.length() ? name1 : name2);

        return name.get();
    }

    public String getNYT() {

        return String.valueOf(articles.stream().filter(a -> {
            try {
                return a.getUrl().contains("reuters.com");
            } catch (NewsAPIException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }).count());

    }

    public List<Article> getHeadlineSmallerFifteen(List<Article> list) {
        return list.stream()
                .filter(article -> {
                    try {
                        return article.getTitle().length() < 15;
                    } catch (NewsAPIException e) {
                        System.out.println(e.getMessage());
                        return false;
                    }
                })
                .collect(Collectors.toList());
    }

    public List<Article> SortByDescriptionLength(List<Article> list) {
        try {
        return list.stream()
                .sorted(Comparator.comparingInt(Article::getDescriptionLength))
                .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
