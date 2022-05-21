package at.ac.fhcampuswien;

public class NewsAPIException extends Exception{

    public NewsAPIException(){
        super("NewsAPIException!");
    }

    public NewsAPIException(String msg){
        super(msg);
    }
}
