package com.wyat.wyat.events.presenters;

import android.util.Log;

import com.wyat.GetEventListUseCase;
import com.wyat.entities.Event;
import com.wyat.wyat.common.Presenter;
import com.wyat.wyat.events.views.EventListView;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zack on 16/01/17.
 */

public class EventListPresenter implements Presenter<EventListView> {

    EventListView eventListView;
    Subscription  subscription;
    GetEventListUseCase getEventListUseCase;
    private List<Event> events = new ArrayList<>();

    public EventListPresenter(GetEventListUseCase getEventListUseCase) {
        this.getEventListUseCase = getEventListUseCase;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if(subscription!=null && !subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }

    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(EventListView view) {
        this.eventListView = view;
    }

    @Override
    public void onCreate() {
            getEvents();
    }


    public void onEventsReceived(List<Event>receivedEvents){
        events.addAll(receivedEvents);
        eventListView.bindEventList(events);


    }


    public void getEvents(){
        subscription = getEventListUseCase
                .execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Event>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("Error",e.getMessage());
                    }

                    @Override
                    public void onNext(List<Event> events) {
                        onEventsReceived(events);
                    }
                });



    }



}
