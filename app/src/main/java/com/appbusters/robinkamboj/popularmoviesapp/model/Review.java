package com.appbusters.robinkamboj.popularmoviesapp.model;

public class Review {

    /**
     * id : 51910979760ee320eb020fc2
     * author : Andres Gomez
     * content : Interesting film with an exceptional cast, fantastic performances and characterizations. The story, though, is a bit difficult to follow and, in the end, seems to not have a real point.
     * url : https://www.themoviedb.org/review/51910979760ee320eb020fc2
     */

    private String id;
    private String author;
    private String content;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
