package com.kafilicious.popularmovies.utils;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Abdulkarim on 4/7/2017.
 */

public class NetworkUtils {
    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String BASE_API_URL =
            "https://api.themoviedb.org/3/";
    private static final String API_PAGE = "&page=";
    private static final String API_KEY ="";
    private static final String DEFAULT_SORT_TYPE =
            "movie/popular";
    private static final String BASE_IMAGE_URL =
            "http://image.tmdb.org/t/p/";
    private static final String DEFAULT_IMAGE_SIZE =
            "w185/";


    public static URL buildUrl(){
        String address = BASE_API_URL + DEFAULT_SORT_TYPE
                +API_KEY;
        URL url = null;
        try{
            url = new URL(address);
        }catch (MalformedURLException e){
            e.printStackTrace();
            return null;
        }

        Log.v(TAG, "Built URL " + url);

        return url;
    }

    public static URL buildUrl(String sort, int page){
        String address = BASE_API_URL + sort + API_KEY + API_PAGE + page;
        URL url = null;
        try{
            url = new URL(address);
        }catch (MalformedURLException e){
            e.printStackTrace();
            return null;
        }
        Log.v(TAG, "Built URL " + url);
        return url;
    }

    public static URL buildMovieUrl(String path){
        URL url = null;
        String movieAds = BASE_IMAGE_URL + DEFAULT_IMAGE_SIZE + path;
        try{
            url = new URL(movieAds);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        Log.v(TAG, "Built image URL " + url);
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException{
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try{
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput){
                Log.v(TAG, "HttpRequest sucessful");
                return scanner.next();

            }else {
                return null;
            }
        }finally {
            urlConnection.disconnect();
        }
    }
}
