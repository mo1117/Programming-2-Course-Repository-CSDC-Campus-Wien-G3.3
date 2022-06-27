package at.ac.fhcampuswien;

public class Article {
    private final String title;
    private final String author;
    private final String description;
    private final String url;
    private final String urlToImage;


    //Builder Pattern
    private Article(Builder builder) {
        this.title = builder.title;
        this.author = builder.author;
        this.description = builder.description;
        this.url = builder.url;
        this.urlToImage = builder.urlToImage;
    }

    public static class Builder {
        private final String title;
        private final String author;
        private String description;
        private String url;
        private String urlToImage;

        public Builder(String title, String author) {
            this.title = title;
            this.author = author;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder url(String url){
            this.url = url;
            return this;
        }

        public Builder urlToImage(String urlToImage){
            this.urlToImage = urlToImage;
            return this;
        }

        public Article build() {
            return new Article(this);
        }
    }


   /* public Article(String author, String title) {
        this.author = author;
        this.title = title;
    }*/


    public String getAuthor() throws NewsAPIException {
        if (this.author == null)
            throw new NewsAPIException("The author of this article is missing!");
        else
            return this.author;
    }

    public String getTitle() throws NewsAPIException {
        if (this.title == null)
            throw new NewsAPIException("The title of this article is missing!");
        else
            return this.title;
    }

    public String getDescription() throws NewsAPIException {
        if (this.description == null)
            throw new NewsAPIException("The description of this article is missing!");
        else
            return this.description;
    }

    public int getDescriptionLength() {
        return this.description.length();
    }

    public String getUrl() throws NewsAPIException {
        if (this.url == null)
            throw new NewsAPIException("The URL of this article is missing!");
        else
            return this.url;
    }

    public String getUrlToImage(){
        return this.urlToImage;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        //Title
        try {
            if (this.getTitle().length() < 60)
                output.append("Title: " + this.getTitle() + System.lineSeparator());
            else {
                boolean linebreak = false;
                output.append("Title: ");
                for (int i = 0; i < this.getTitle().length(); i++) {
                    output.append(this.getTitle().charAt(i));
                    if (i % 60 == 0 && i != 0)
                        linebreak = true;
                    if (linebreak && this.getTitle().charAt(i) == 32) {
                        output.append(System.lineSeparator());
                        linebreak = false;
                    }
                }
                output.append(System.lineSeparator());
            }
        } catch (NewsAPIException e) {
            System.out.println(e.getMessage());
            System.out.println("The title will be set to 'Unknown' instead!");
            output.append("Title: Unknown" + System.lineSeparator());
        }

        //Author
        try {
            output.append("Author: " + this.getAuthor() + System.lineSeparator());
        } catch (NewsAPIException e) {
            System.out.println(e.getMessage());
            System.out.println("The author will be set to 'Unknown' instead!");
            output.append("Author: Unknown" + System.lineSeparator());
        }

        //Description
        try {
            if (this.getDescription().length() < 60)
                output.append("Description: " + this.getDescription() + System.lineSeparator());
            else {
                boolean linebreak = false;
                output.append("Description: ");
                for (int i = 0; i < this.getDescription().length(); i++) {
                    output.append(this.getDescription().charAt(i));
                    if (i % 60 == 0 && i != 0)
                        linebreak = true;
                    if (linebreak && this.getDescription().charAt(i) == 32) {
                        output.append(System.lineSeparator());
                        linebreak = false;
                    }
                }
                output.append(System.lineSeparator());
            }
        } catch (NewsAPIException e) {
            System.out.println(e.getMessage());
            System.out.println("The description will be set to 'Unknown' instead!");
            output.append("Description: Unknown");
        }
        return output.toString();
    }
}
