package com.example.admin.firebase;

/**
 * Created by admin on 9/21/2017.
 */

public class Movie {
    String title, genere, year, director;

    public Movie() {
    }

    public Movie(String title, String genere, String year, String director) {
        this.title = title;
        this.genere = genere;
        this.year = year;
        this.director = director;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", genere='" + genere + '\'' +
                ", year='" + year + '\'' +
                ", director='" + director + '\'' +
                '}';
    }
}
