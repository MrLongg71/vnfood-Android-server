package vn.mrlongg71.vnfood.src.network;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vn.mrlongg71.vnfood.src.model.StoreKey;

public class APIVnFood {
    private static Retrofit retrofit = null;
    protected  Application mApplication;

    public APIVnFood(Application mApplication) {
        this.mApplication = mApplication;
    }
    SharedPreferences sharedPreferences=  mApplication.getSharedPreferences("User", Context.MODE_WORLD_READABLE);
    String token = sharedPreferences.getString("token","");

    //    private  Context context;
//
//    public APIVnFood(Context context) {
//        this.context = context;
//    }
//
//    public  String getToken(){
//        return
//    }
    public static Retrofit getAPIVnFood() {
        if (retrofit == null) {
//
//            String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7InVzZXJJZCI6ImJhMmRjYjYwLTc0ZmUtMTFlYS04MTBiLTc3N2ZmODZhNjAzMCIsImVtYWlsIjoibXJsb25nZzcxQGdtYWlsLmNvbSJ9LCJpYXQiOjE1ODU4OTYxNjJ9.5MuNPuWpT5D7qlN-ozUDqvl2i32JVUeO-DpCvFnyfWw";
//            Log.d("LONgKUTE", "getAPIVnFood: " + token);

            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @NotNull
                @Override
                public Response intercept(Chain chain) throws IOException {


                    Request newRequest  = chain.request().newBuilder()
                            .addHeader("Authorization", "Bearer " + StoreKey.getToken())
                            .build();
                    return chain.proceed(newRequest);
                }

            }).build();

//
//            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//            OkHttpClient client = new OkHttpClient.Builder()
//                    .addInterceptor(logging)
//                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(EndPoint.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
//            Log.d("LONgKUTE", "getAPIVnFood: " + token);

        }

        return retrofit;
    }

}
