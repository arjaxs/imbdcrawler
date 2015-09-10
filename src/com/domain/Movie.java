package com.domain;

import java.util.Comparator;

/**
 * Created by Development on 4/1/2015.
 */
public class Movie {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    String name;
    Double rating;
    Integer year;



}


