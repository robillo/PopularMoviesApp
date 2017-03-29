package com.appbusters.robinkamboj.popularmoviesapp.model;

import java.util.List;

public class VideosResponse {

    /**
     * id : 550
     * results : [{"id":"58257f4dc3a36836060060c4","iso_639_1":"en","iso_3166_1":"US","key":"qvCogW-N-HE","name":"HD Trailer","site":"YouTube","size":720,"type":"Trailer"},{"id":"533ec654c3a36854480003eb","iso_639_1":"en","iso_3166_1":"US","key":"SUXWAEX2jlg","name":"Trailer 1","site":"YouTube","size":720,"type":"Trailer"}]
     */

    private int id;
    private List<Video> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Video> getResults() {
        return results;
    }

    public void setResults(List<Video> results) {
        this.results = results;
    }
}
