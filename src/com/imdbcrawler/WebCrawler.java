package com.imdbcrawler;

import java.util.HashMap;

/**
 * Interface which provides the api for any kind of Craweler data.
 */
public interface WebCrawler {

    /**
     * Crawls the data from the specified input websites.
     * Outputs processed data.
     * @param inputs
     */
    public void crawlerOutput(HashMap<String, String> inputs);//This method has been implemented in the IMDBCrawler.java file.

}
