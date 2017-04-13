package com.kafilicious.popularmovies.utils;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/*
 * Copyright (C) 2017 Popular Movies, Stage 1
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/

public class NetworkUtils {
    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String BASE_API_URL =
            "https://api.themoviedb.org/3/";
    private static final String API_PAGE = "&page=";
    private static final String API_KEY =
            "?api_key=";
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
