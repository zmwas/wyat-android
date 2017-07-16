package com.wyat.rest;

import com.wyat.SharedPrefsWrapper;
import com.wyat.entities.AccessToken;
import com.wyat.entities.Album;
import com.wyat.entities.Event;
import com.wyat.entities.Photo;
import com.wyat.entities.User;
import com.wyat.entities.Venue;
import com.wyat.repository.WyatRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class RestDataSource implements WyatRepository {

    public final static String ENDPOINT = "http://www.wyat-app.com/";
    private final ApiService apiService;
    private SharedPrefsWrapper sharedPrefsWrapper;
    private final static String KEY = "token";


    public RestDataSource(final SharedPrefsWrapper sharedPrefsWrapper) {

        this.sharedPrefsWrapper = sharedPrefsWrapper;


        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).addNetworkInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                // Add authorization header with updated authorization value to intercepted request
                Request authorisedRequest = chain.request().newBuilder()
                        .header("Authorization:", "Token " + sharedPrefsWrapper.getString(KEY))
                        .build();


                return chain.proceed(authorisedRequest);
            }
        }).authenticator(new Authenticator() {
            @Override
            public Request authenticate(Route route, Response response) throws IOException {
                return response.request().newBuilder().header("Authorization:", "Token " + sharedPrefsWrapper.getString(KEY)).build();
            }
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    @Override
    public Observable<Album> getAlbum(int id) {
        return apiService.getAlbum(id);
    }

    @Override
    public Observable<List<Event>> getEvents() {
        return apiService.getEvents();
    }

    @Override
    public Observable<Event> getEvent(int id) {
        return apiService.getEvent(id);
    }

    @Override
    public Observable<Event> postEvent(String path,String venue_id, String name, String time, String type, String description, String invite, String ageRestricted, String free, String ticketPrice) {


        MediaType image = MediaType.parse("image/*");
        MediaType text = MediaType.parse("text/plain");

        File file = new File(path);

        RequestBody requestBodyImage = RequestBody.create(image,file);
        RequestBody requestBodyVenue_id = RequestBody.create(text,venue_id);
        RequestBody requestBodyName = RequestBody.create(text,name);
        RequestBody requestBodyDescription = RequestBody.create(text,description);
        RequestBody requestBodyDate = RequestBody.create(text,time);
        RequestBody requestBodyType = RequestBody.create(text,type);
        RequestBody requestBodyInviteOnly= RequestBody.create(text,invite);
        RequestBody requestBodyAge = RequestBody.create(text,ageRestricted);
        RequestBody requestBodyFree= RequestBody.create(text,free);
        RequestBody requestBodyTicketPrice = RequestBody.create(text,ticketPrice);

        MultipartBody.Part typedFile = MultipartBody.Part.createFormData("event_pic_url",file.getName(),requestBodyImage);




        return apiService.postEvent(typedFile,requestBodyVenue_id,requestBodyName,requestBodyDescription,requestBodyDate,requestBodyType,requestBodyInviteOnly,requestBodyAge,requestBodyFree,requestBodyTicketPrice);
    }

    @Override
    public Observable<Venue> postVenue(Venue venue) {
        return apiService.postVenue(venue);
    }


    @Override
    public Observable<List<Photo>> getPhotos() {
        return apiService.getPhotos();
    }

    @Override
    public Observable<Photo> getPhoto(int id) {
        return apiService.getPhoto(id);
    }

    @Override
    public Observable<Photo> postPhoto(String path, Map map) {
        MediaType mediaType = MediaType.parse("image/png");
        File file = new File(path);
        RequestBody requestBody = RequestBody.create(mediaType, file);


        return apiService.postPhoto(requestBody, map);
    }

    @Override
    public Observable<List<Album>> getAlbums() {
        return apiService.getAlbums();
    }

    @Override
    public Observable<Album> postAlbum(Album album) {
        return apiService.postAlbum(album);
    }

    @Override
    public Observable<User> postUser(User user) {
        return apiService.postUser(user);
    }

    @Override
    public Observable<AccessToken> getAccessToken(String email, String password) {
        return apiService.getAccessToken(email, password);
    }
}
