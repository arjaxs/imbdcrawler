package com.domain;

import java.util.Comparator;

/**
 * Helps to sort movies list in descending order of movie ratings.
 */
public class RatingComparator implements Comparator<Movie> {

    @Override
    public int compare(Movie movie1, Movie movie2) {
        if (movie1.getRating() < movie2.getRating()) {
            return 1;
        } else {
            return -1;
        }

    }
}
