package com.wyat.rest;

import com.wyat.entities.AccessToken;
import com.wyat.entities.Album;
import com.wyat.entities.Event;
import com.wyat.entities.Photo;
import com.wyat.entities.User;
import com.wyat.entities.Venue;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by zack on 29/12/16.
 */

public interface ApiService {


    @GET("api/events/")
    Observable<List<Event>> getEvents();

    @GET("api/events/{id}/")
    Observable<Event> getEvent(@Path("id") int id);

    @Multipart
    @POST("api/events/")
    Observable<Event> postEvent(
            @Part MultipartBody.Part image,
            @Part ("venue_id") RequestBody venue_id,
            @Part("name") RequestBody name,
            @Part("description") RequestBody description,
            @Part("time") RequestBody date,
            @Part("event_type") RequestBody type,
            @Part("invite_only") RequestBody isInviteOnly,
            @Part("age_restriction") RequestBody isAgeRestricted,
            @Part("free") RequestBody isFree,
            @Part("ticket_price") RequestBody ticketPrice

    );
    @GET("api/events/id/photos")
    Observable<Photo>getEventPhotos(@Path("id") int id);


    @POST("api/venues/")
    Observable<Venue>postVenue(@Body Venue venue);

    @GET("api/photos/")
    Observable<List<Photo>> getPhotos();

    @GET("api/photos/{id}/")
    Observable<Photo> getPhoto(@Path("id") int id);

    @POST("api/photos/")
    Observable<Photo> postPhoto(@Part RequestBody file,
                                @PartMap Map<String, RequestBody> params
    );

    @GET("api/albums/")
    Observable<List<Album>> getAlbums();

    @GET("api/album/{id}/")
    Observable<Album> getAlbum(@Path("id") int id);

    @POST("api/albums/")
    Observable<Album> postAlbum(@Body Album album);

    @POST("api/users/")
    Observable<User> postUser(@Body User user);

    @FormUrlEncoded
    @POST("api-token-auth/")
    Observable<AccessToken> getAccessToken(@Field("username") String email, @Field("password") String password);


}
