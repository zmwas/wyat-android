package com.wyat.wyat.events.presenters;

import android.util.Log;

import com.wyat.PostEventUseCase;
import com.wyat.PostVenueUseCase;
import com.wyat.entities.Event;
import com.wyat.entities.Venue;
import com.wyat.wyat.common.Presenter;
import com.wyat.wyat.events.views.PostEventView;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zack on 23/01/17.
 */

public class PostEventPresenter implements Presenter<PostEventView> {

    private PostEventView postEventView;

    private PostEventUseCase postEventUseCase;

    private PostVenueUseCase postVenueUseCase;

    private Subscription subscription;

    private Venue venue;

    private String path, name, time, type, description, invite, ageRestricted, free, ticketPrice;


    public PostEventPresenter(PostEventUseCase postEventUseCase, PostVenueUseCase postVenueUseCase) {
        this.postEventUseCase = postEventUseCase;
        this.postVenueUseCase = postVenueUseCase;
    }


    public void setAgeRestricted(String ageRestricted) {
        this.ageRestricted = ageRestricted;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFree(String free) {
        this.free = free;
    }

    public void setInvite(String invite) {
        this.invite = invite;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setTicketPrice(String ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

        if (!subscription.isUnsubscribed() || subscription != null) {
            subscription.unsubscribe();
        }

    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(PostEventView view) {

        postEventView = view;


    }

    @Override
    public void onCreate() {

    }




    public void postVenue() {


        postVenueUseCase.setVenue(venue);

        subscription = postVenueUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Venue>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("error", e.getMessage());
                    }

                    @Override
                    public void onNext(Venue venue) {

                        String venue_id;

                        venue_id = venue.getId();

                        postEvent(venue_id);




                    }
                });


    }


    public void postEvent(String venue_id){
        postEventUseCase.setAgeRestricted(ageRestricted);
        postEventUseCase.setTime(time);
        postEventUseCase.setFree(free);
        postEventUseCase.setInvite(invite);
        postEventUseCase.setDescription(description);
        postEventUseCase.setName(name);
        postEventUseCase.setPath(path);
        postEventUseCase.setTicketPrice(ticketPrice);
        postEventUseCase.setType(type);
        postEventUseCase.setVenue_id(venue_id);

        subscription = postEventUseCase.execute().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Event>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Event event) {

            }
        });




    }
}
