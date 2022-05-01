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

    public String getAuthor() {
        return this.author;
    }

    public String getTitle() {
        return this.title;
    }

    public String getUrl(){
        return this.url;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        //Title
        if (this.title == null)
            output.append("Title: Unknown" + System.lineSeparator());
        else {
            if (this.title.length() < 60)
                output.append("Title: " + this.title + System.lineSeparator());
            else {
                boolean linebreak = false;
                output.append("Title: ");
                for (int i = 0; i < this.title.length(); i++) {
                    output.append(this.title.charAt(i));
                    if (i % 60 == 0 && i != 0)
                        linebreak = true;
                    if (linebreak && this.title.charAt(i) == 32) {
                        output.append(System.lineSeparator());
                        linebreak = false;
                    }
                }
                output.append(System.lineSeparator());
            }
        }

        //Author
        if (this.author == null)
            output.append("Author: Unknown" + System.lineSeparator());
        else
            output.append("Author: " + this.author + System.lineSeparator());

        //Description
        if (this.description == null)
            output.append("Description: Unknown" + System.lineSeparator());
        else {
            if (this.description.length() < 60)
                output.append("Description: " + this.description + System.lineSeparator());
            else {
                boolean linebreak = false;
                output.append("Description: ");
                for (int i = 0; i < this.description.length(); i++) {
                    output.append(this.description.charAt(i));
                    if (i % 60 == 0 && i != 0)
                        linebreak = true;
                    if (linebreak && this.description.charAt(i) == 32) {
                        output.append(System.lineSeparator());
                        linebreak = false;
                    }
                }
                output.append(System.lineSeparator());
            }
        }
        return output.toString();
    }


}
