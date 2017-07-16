package com.wyat.wyat.events.presenters;

import com.wyat.GetEventDetailUseCase;
import com.wyat.wyat.common.Presenter;
import com.wyat.wyat.events.views.EventDetailView;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zack on 27/02/17.
 */

public class EventDetailPresenter implements Presenter<EventDetailView> {

    private Subscription subscription;
    private GetEventDetailUseCase getEventDetailUseCase;

    public EventDetailPresenter(GetEventDetailUseCase getEventDetailUseCase) {
        this.getEventDetailUseCase = getEventDetailUseCase;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

        if(subscription!=null&&!subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }

    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(EventDetailView view) {

    }

    @Override
    public void onCreate() {
        getEvent();
    }


    public void getEvent(){

        subscription = getEventDetailUseCase.execute().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();



    }

}
