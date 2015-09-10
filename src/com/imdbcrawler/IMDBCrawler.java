package com.imdbcrawler;

import com.domain.Movie;
import com.domain.RatingComparator;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Development on 4/1/2015.
 */
public class IMDBCrawler implements WebCrawler {
    @Override
    public void crawlerOutput(HashMap<String, String> inputs) {
        //Reads the input URL file
        FileInputStream fis = getFileInputStream(inputs);
        //Construct BufferedReader from InputStreamReader
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String line = null;
        List<Movie> movies = new ArrayList<Movie>();
        try {
            while ((line = br.readLine()) != null) {
                //Crawl and extract data for each URL
                movies.add(extractData(line));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Sort the movie list based on the movie rating
        Collections.sort(movies, new RatingComparator());
        String fileName = inputs.get("OUTPUTFILELOCATION") + "topfivemovies.json";
        createOutputJsonFile(inputs, movies, fileName);
    }

    /**
     * Create output JSON from the movie list
     * @param inputs
     * @param movies
     * @param fileName
     */
    private void createOutputJsonFile(HashMap<String, String> inputs, List<Movie> movies, String fileName) {
        File outputFile = new File(fileName);
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(outputFile, movies.subList(0, Integer.parseInt(inputs.get("NUMBEROFMOVIES"))));
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the input file stream.
     *
     * @param inputs
     * @return Input file stream.
     */
    private FileInputStream getFileInputStream(HashMap<String, String> inputs) {
        File inputFile = new File(inputs.get("INPUTFILENAME"));
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(inputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fis;
    }

    /**
     * Extract and populate movie data from the URLS using Jsoup API
     *
     * @param url in
     * @return
     */
    private static Movie extractData(String url) {
        Movie movie = new Movie();
        Document doc = null;
        Elements content = null;
        try {
            doc = Jsoup.connect(url).get();
            extractRating(movie, doc);
            extractName(movie, doc);
            extractYear(movie, doc);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return movie;
    }

    private static void extractYear(Movie movie, Document doc) {
        Elements contents = doc.getElementsByClass("nobr");
        for (Element c : contents) {
            Elements links = c.getElementsByTag("a");
            for (Element link : links) {
                movie.setYear(Integer.parseInt(link.text()));
                break;
            }
            break;
        }
    }

    private static void extractName(Movie movie, Document doc) {
        Elements content;
        content = doc.getElementsByClass("itemprop");
        for (Element link : content) {
            String linkHref = link.attr("itemprop");
            movie.setName(link.text());
            break;
        }
    }

    private static void extractRating(Movie movie, Document doc) {
        Elements content;
        content = doc.getElementsByClass("star-box-giga-star");
        for (Element link : content) {
            movie.setRating(Double.parseDouble(link.text()));

        }
    }
}
