package at.ac.fhcampuswien;

import at.ac.fhcampuswien.downloader.Downloader;
import at.ac.fhcampuswien.enums.CountryEnum;
import at.ac.fhcampuswien.enums.EndpointEnum;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class AppController {

    private List<Article> articles;
    private NewsResponse newsResponse;
    private NewsApi newsApi = new NewsApi();

    // Method is needed for exercise 4 - ignore for exercise 3 solution
    // returns number of downloaded article urls
    public int downloadURLs(Downloader downloader) throws NewsAPIException {
        if (articles == null)
            throw new NewsAPIException();

        List<String> urls = new ArrayList<>();

        urls = this.articles.stream()
                .map(x -> {
                    try {
                        return x.getUrl();
                    } catch (NewsAPIException e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                })
                .collect(Collectors.toList());

        return downloader.process(urls);
    }

    /*
    public AppController() {
        this.articles = null;
    }
    */


    //Singleton
    private static AppController instance = null;

    private AppController() {
    }

    public static AppController getInstance() {
        if (instance == null) {
            instance = new AppController();
        }
        return instance;
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

    public String getMostArticles() throws NewsAPIException {


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


        Map<String, Long> temp = sources.stream()
                .collect(Collectors.groupingBy(a -> a, Collectors.counting()));


        return new HashSet<>(temp.values()).size() < temp.size() ?
                null : temp.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).get();

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

        return list.stream()
                .sorted()
                .collect(Collectors.toList());

    }


}
