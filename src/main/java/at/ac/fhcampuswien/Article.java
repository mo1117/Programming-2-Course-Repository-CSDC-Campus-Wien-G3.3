package at.ac.fhcampuswien;

public class Article {
    private String title;
    private String author;
    private String description;
    private String url;

    public Article(String author, String title) {
        this.author = author;
        this.title = title;
    }

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

    public String getUrl() throws NewsAPIException {
        if (this.url == null)
            throw new NewsAPIException("The URL of this article is missing!");
        else
            return this.url;
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
