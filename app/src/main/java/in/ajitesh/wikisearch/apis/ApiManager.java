package in.ajitesh.wikisearch.apis;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ajitesh on 07/09/18.
 */

public class ApiManager {

    static IWikipediaService wikipediaService;

    static {
        init();
    }

    public static void init(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://en.wikipedia.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        wikipediaService = retrofit.create(IWikipediaService.class);
    }

    public static IWikipediaService getWikipediaService() {
        return wikipediaService;
    }
}
