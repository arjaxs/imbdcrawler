package com.imdb.consoleapp;

import com.imdbcrawler.IMDBCrawler;
import com.imdbcrawler.WebCrawler;

import java.util.HashMap;

public class Main {
    /**
     *  Main class to invoke the IMDB crawler.
     * @param args
     */
    public static void main(String[] args) {
        WebCrawler webCrawler=new IMDBCrawler();
        HashMap<String,String> inputs=new HashMap<String, String>();
        //input file name
        inputs.put("INPUTFILENAME",args[0]);
        //output  file destination
        inputs.put("OUTPUTFILELOCATION",args[1]);
        //Number of top movies needed
        inputs.put("NUMBEROFMOVIES",args[2]);
        webCrawler.crawlerOutput(inputs);
    }


}
