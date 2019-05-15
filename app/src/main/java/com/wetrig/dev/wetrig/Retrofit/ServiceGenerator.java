    package com.wetrig.dev.wetrig.Retrofit;

    import com.google.gson.Gson;
    import com.google.gson.GsonBuilder;

    import okhttp3.OkHttpClient;
    import okhttp3.logging.HttpLoggingInterceptor;
    import retrofit2.Retrofit;
    import retrofit2.converter.gson.GsonConverterFactory;

    /**
     * Created by Eduardo on 29/05/2016.
     */
    public class ServiceGenerator {

        public static final String API_BASE_URL = "http://dev.wetrig.com/";
        private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        private static Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create());

        public static <S> S createService(Class<S> serviceClass) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            Retrofit retrofit = builder.client(httpClient.addInterceptor(interceptor).build()).build();
            return retrofit.create(serviceClass);
        }
    }
