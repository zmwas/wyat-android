package com.wyat.repository;

import com.wyat.entities.AccessToken;
import com.wyat.entities.Album;
import com.wyat.entities.Event;
import com.wyat.entities.Photo;
import com.wyat.entities.User;
import com.wyat.entities.Venue;

import java.util.List;
import java.util.Map;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by zack on 29/12/16.
 */

public interface WyatRepository {




    Observable<List<Event>> getEvents();


    Observable<Event> getEvent(int id);


    Observable<Event> postEvent(String  path,String venue_id, String name, String time, String type, String description,String invite,String ageRestricted,String free,String ticketPrice);

    Observable<Venue> postVenue(Venue venue);

    Observable<List<Photo>> getPhotos();


    Observable<Photo> getPhoto(int id);


    Observable<Photo> postPhoto(String path,Map map);


    Observable<List<Album>> getAlbums();


    Observable<Album> getAlbum(int id);


    Observable<Album> postAlbum(Album album);


    Observable<User> postUser(User user);

    Observable<AccessToken> getAccessToken(String email, String password);






}
